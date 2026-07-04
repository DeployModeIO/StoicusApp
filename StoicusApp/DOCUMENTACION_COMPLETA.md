************************************************************************
# 📜 DOCUMENTACIÓN COMPLETA - EXPANSIÓN STOICUSAPP
## Sistema de Administrador, Documentos Legales, Contador Seguro y Gumroad

> **Versión:** 1.1  
> **Fecha:** 2026-07-04  
> **Autor:** Desarrollador Android Fullstack Senior / Seguridad / Legal Tech

---

# 📑 ÍNDICE

1. [Sistema de Administrador y Modo Bypass](#1-sistema-de-administrador-y-modo-bypass)
2. [Documentos Legales (Plantillas)](#2-documentos-legales-plantillas)
   - 2.1 Política de Privacidad
   - 2.2 Términos y Condiciones de Uso
   - 2.3 Política de Cookies / Almacenamiento Local
3. [Integración de Recursos Open Source, Contador y Gumroad](#3-integración-de-recursos-open-source-contador-y-gumroad)
4. [Resumen Ejecutivo de Seguridad](#4-resumen-ejecutivo-de-seguridad)

---

# 1. SISTEMA DE ADMINISTRADOR Y MODO BYPASS

## 1.1 ⚠️ Análisis de Riesgos — Por qué NO usar un "backdoor oculto"

### ❌ Anti-Patrón: Toques repetidos en la versión de la app
```kotlin
// 🚨 NUNCA HACER ESTO EN PRODUCCIÓN
if (versionLabelClicks >= 7) {
    grantAdminPrivileges() // BRECHA DE SEGURIDAD CRÍTICA
}
```
**Problemas:**
- 🔓 **Reversible engineering:** Cualquier usuario con APKTool/Jadx descubrirá el gesto en minutos.
- 🕵️ **Fácil de filtrar:** Un usuario curioso o un niño descubrirá el "huevo de pascua".
- 🚫 **No auditable:** No hay trazabilidad de quién accedió ni cuándo.
- 💀 **Sin revocación:** Una vez descubierto, no se puede desactivar sin actualizar la app.

### ✅ Estrategia Recomendada: JWT Backend + `ROLE_ADMIN` + Build Variant

La única estrategia **segura para producción** combina tres capas:

| Capa | Mecanismo | Propósito |
|------|-----------|-----------|
| **1. Build Configuration** | `BuildConfig.ENABLE_ADMIN_PANEL` (solo `debug` / `internal`) | El panel ni siquiera existe en el APK de Play Store |
| **2. Autenticación Fuerte** | PIN admin + derivación de clave (PBKDF2/Argon2) + EncryptedSharedPreferences | Sin backend, la auth es local pero criptográfica |
| **3. (Opcional) Backend JWT** | Endpoint `/admin/login` emite JWT con claim `role: "ROLE_ADMIN"` | Para SaaS multiplataforma con servidor |

---

## 1.2 🏗️ Arquitectura del Rol de Administrador

```
┌─────────────────────────────────────────────────────────────┐
│                     APP (Android)                            │
│                                                              │
│  ┌──────────────────┐   ┌──────────────────────────────┐   │
│  │ AdminGateScreen  │──▶│ AdminAuthManager             │   │
│  │ (PIN + 2FA opc.) │   │ - verifyPin() PBKDF2         │   │
│  └──────────────────┘   │ - EncryptedSharedPreferences │   │
│                          └────────────┬─────────────────┘   │
│                                       │                     │
│                                       ▼                     │
│  ┌──────────────────────────────────────────────────────┐  │
│  │ AdminPanelScreen (solo si BuildConfig.ENABLE_ADMIN)   │  │
│  │  - Simular compra Gumroad (inject license)            │  │
│  │  - Resetear contador de 5 usos                        │  │
│  │  - Desbloquear features premium                       │  │
│  │  - Ver logs de auditoría                              │  │
│  └──────────────────────────────────────────────────────┘  │
└─────────────────────────────────────────────────────────────┘
```

### Flujo de Seguridad Recomendado (Paso a Paso)

**Paso 1 — Acceso (Build-gated):**
```kotlin
// Solo visible/compilado en debug o "internal" build variant
if (BuildConfig.ENABLE_ADMIN_PANEL) {
    navController.navigate(Screen.AdminGate.route)
}
```

**Paso 2 — Autenticación criptográfica del PIN:**
```kotlin
// El PIN NUNCA se guarda en claro. Se almacena como hash PBKDF2.
fun verifyPin(input: String): Boolean {
    val storedHash = encryptedPrefs.getString("admin_pin_hash", null) ?: return false
    val (salt, hash) = decodeSaltAndHash(storedHash)
    val inputHash = pbkdf2Hash(input, salt, iterations = 210_000)
    return constantTimeEquals(hash, inputHash)
}
```

**Paso 3 — Sesión efímera:**
- Tras login correcto, se emite un token de sesión efímero en memoria (no persistente) con TTL de 15 minutos.
- Después del TTL, el admin debe re-autenticarse.

**Paso 4 — Auditoría:**
- Cada acción admin (reset contador, simular compra) se registra en `AdminAuditLog` con timestamp + acción + hash de integridad.

---

## 1.3 🔧 Implementación Incluida en este Proyecto

Se han creado los siguientes archivos:

| Archivo | Propósito |
|---------|-----------|
| `core/security/AdminAuthManager.kt` | Verificación criptográfica del PIN admin |
| `core/security/SecureUsageCounter.kt` | Contador de 5 usos con firma HMAC (anti-tampering) |
| `core/security/SecurityConstants.kt` | Constantes de seguridad centralizadas |
| `core/data/local/entity/AdminAuditLog.kt` | Registro auditable de acciones admin |
| `core/data/local/entity/License.kt` | Licencia Gumroad persistida |
| `core/data/local/dao/AdminAuditLogDao.kt` | DAO para logs |
| `core/data/local/dao/LicenseDao.kt` | DAO para licencias |
| `core/network/GumroadApiService.kt` | Cliente Retrofit para `verify` license |
| `core/network/GumroadRepository.kt` | Repositorio con lógica de verificación |
| `core/network/NetworkModule.kt` | Módulo Hilt para Retrofit |
| `core/domain/usecase/admin/*.kt` | Use cases admin (reset counter, simulate purchase, unlock) |
| `feature/admin/AdminGateScreen.kt` | Pantalla de login admin |
| `feature/admin/AdminPanelScreen.kt` | Panel con acciones admin |
| `feature/admin/AdminViewModel.kt` | ViewModel del panel |
| `feature/legal/LegalScreen.kt` | Pantalla para mostrar políticas |
| `res/raw/privacy_policy.html` | Plantilla Política de Privacidad |
| `res/raw/terms_of_service.html` | Plantilla Términos de Uso |
| `res/raw/cookie_policy.html` | Plantilla Política de Cookies |

### 🔐 PIN Admin por Defecto (Debug)
```
PIN: 737287  (mapeo STOIC-U en teclado telefónico)
```
> ⚠️ **CAMBIAR ANTES DE PRODUCCIÓN.** El hash PBKDF2 se genera con `AdminPinSetupHelper` en primera ejecución. En release, el panel se deshabilita vía `BuildConfig.ENABLE_ADMIN_PANEL = false`.

---

# 2. DOCUMENTOS LEGALES (PLANTILLAS)

> **Instrucciones de uso:** Reemplaza todos los marcadores `[PLACEHOLDER]` con tus datos reales antes de publicar. Las plantillas están adaptadas a **GDPR (UE)** y **CCPA (California)**.  
> **Disclaimer:** Estas plantillas son una base profesional pero **no constituyen asesoramiento legal**. Consúltalas con un abogado antes de producción.

## 2.1 📋 POLÍTICA DE PRIVACIDAD (Privacy Policy)

```html
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="UTF-8">
<title>Política de Privacidad - [NOMBRE_DE_LA_APP]</title>
</head>
<body>

<h1>Política de Privacidad</h1>
<p><strong>Última actualización:</strong> [FECHA_DE_ACTUALIZACION]</p>

<p>Esta Política de Privacidad describe cómo <strong>[NOMBRE_DE_LA_EMPRESA_O_DESARROLLADOR]</strong> 
("nosotros", "nuestro", "nos") recopila, usa y protege su información cuando utiliza 
la aplicación móvil <strong>[NOMBRE_DE_LA_APP]</strong> (en adelante, la "Aplicación"). 
Al utilizar la Aplicación, usted acepta las prácticas descritas en esta política.</p>

<h2>1. Responsable del Tratamiento</h2>
<p><strong>Responsable:</strong> [NOMBRE_DE_LA_EMPRESA_O_DESARROLLADOR]<br>
<strong>Correo de contacto:</strong> [EMAIL_DE_SOPORTE]<br>
<strong>Dirección:</strong> [DIRECCION_FISICA_O_JURIDICA]</p>

<h2>2. Datos que Recopilamos</h2>

<h3>2.1 Correo Electrónico</h3>
<p>Recopilamos su <strong>correo electrónico</strong> exclusivamente para:</p>
<ul>
  <li>Validar y activar su licencia de uso adquirida a través de la plataforma externa 
      <strong>Gumroad</strong> (<a href="https://gumroad.com">https://gumroad.com</a>).</li>
  <li>Enviar notificaciones esenciales relacionadas con su licencia (ej. confirmación de activación).</li>
</ul>
<p>El correo se transmite a la API de Licencias de Gumroad 
(<code>https://api.gumroad.com/v2/licenses/verify</code>) únicamente para verificar 
la validez de la compra. No lo utilizamos para marketing sin su consentimiento expreso.</p>

<h3>2.2 Datos Técnicos del Dispositivo</h3>
<p>Para la gestión del modelo de "5 usos gratuitos", recopilamos y almacenamos localmente:</p>
<ul>
  <li><strong>Identificador único del dispositivo</strong> (generado mediante 
      <code>android.provider.Settings.Secure.ANDROID_ID</code> o un UUID generado por la app).</li>
  <li><strong>Contador de usos</strong> (número de veces que se ha accedido a la funcionalidad gratuita).</li>
  <li><strong>Sello de tiempo</strong> de cada uso.</li>
</ul>
<p>Estos datos se almacenan <strong>exclusivamente en su dispositivo</strong> mediante 
<code>EncryptedSharedPreferences</code> / <code>DataStore</code> cifrado y <strong>NO se envían 
a nuestros servidores</strong>, salvo para la verificación de licencia descrita en la sección 2.1.</p>

<h3>2.3 Datos de Uso (No Identificativos)</h3>
<p>La Aplicación puede registrar métricas agregadas y anonimizadas tales como:</p>
<ul>
  <li>Frecuencia de uso de funciones (rituales, citas, galería).</li>
  <li>Preferencias de configuración (horarios de notificación).</li>
</ul>
<p>Estos datos <strong>no permiten identificarle personalmente</strong>.</p>

<h2>3. Base Legal del Tratamiento (GDPR)</h2>
<p>Conforme al Reglamento General de Protección de Datos (UE) 2016/679 (GDPR), 
el tratamiento de sus datos se basa en:</p>
<ul>
  <li><strong>Art. 6(1)(b) GDPR — Ejecución de un contrato:</strong> validación de la licencia 
      que usted adquiere para usar la Aplicación.</li>
  <li><strong>Art. 6(1)(f) GDPR — Interés legítimo:</strong> prevención del fraude y abuso 
      del sistema de usos gratuitos.</li>
  <li><strong>Art. 6(1)(a) GDPR — Consentimiento:</strong> para cualquier comunicación 
      de marketing opcional.</li>
</ul>

<h2>4. Derechos CCPA (California)</h2>
<p>Si es residente de California, conforme al California Consumer Privacy Act (CCPA), usted tiene derecho a:</p>
<ul>
  <li><strong>Saber</strong> qué datos personales recopilamos (categorías y piezas específicas).</li>
  <li><strong>Eliminar</strong> sus datos personales.</li>
  <li><strong>Oponerse</strong> a la venta de datos (no vendemos sus datos).</li>
  <li><strong>No ser discriminado</strong> por ejercer sus derechos.</li>
</ul>
<p>Para ejercer estos derechos, contacte a [EMAIL_DE_SOPORTE].</p>

<h2>5. Compartición de Datos</h2>
<p>Sus datos pueden ser compartidos con:</p>
<ul>
  <li><strong>Gumroad, Inc.</strong> — para la verificación de licencias 
      (<a href="https://gumroad.com/privacy">política de privacidad</a>).</li>
  <li><strong>Google Play Services</strong> — para distribución y actualizaciones.</li>
  <li><strong>Proveedores de infraestructura</strong> — en caso de implementarse backend 
      (ej. Firebase, AWS), bajo acuerdos de confidencialidad.</li>
</ul>
<p><strong>No vendemos ni alquilamos sus datos personales a terceros.</strong></p>

<h2>6. Transferencias Internacionales</h2>
<p>Sus datos pueden procesarse en servidores ubicados fuera de su país de residencia 
(incluyendo Estados Unidos). Aplicamos cláusulas contractuales tipo y salvaguardas 
adecuadas conforme al GDPR.</p>

<h2>7. Retención de Datos</h2>
<ul>
  <li><strong>Correo electrónico:</strong> mientras mantenga una licencia activa + 30 días.</li>
  <li><strong>Contador de usos:</strong> almacenado localmente hasta que se verifique 
      una licencia válida o se desinstale la Aplicación.</li>
  <li><strong>Datos de auditoría admin:</strong> 90 días.</li>
</ul>

<h2>8. Seguridad</h2>
<p>Implementamos medidas técnicas y organizativas razonables, incluyendo:</p>
<ul>
  <li>Cifrado de almacenamiento local (<code>EncryptedSharedPreferences</code>).</li>
  <li>Comunicaciones TLS/HTTPS.</li>
  <li>Firma HMAC del contador de usos para prevenir manipulación.</li>
  <li>Verificación criptográfica del acceso administrativo.</li>
</ul>

<h2>9. Privacidad de Menores</h2>
<p>La Aplicación no está dirigida a menores de 13 años (o 16 en la UE). 
No recopilamos conscientemente datos de menores.</p>

<h2>10. Cambios a esta Política</h2>
<p>Podemos actualizar esta política. Notificaremos cambios materiales 
por correo o dentro de la Aplicación.</p>

<h2>11. Contacto</h2>
<p>Para preguntas sobre privacidad: <strong>[EMAIL_DE_SOPORTE]</strong><br>
Para el Delegado de Protección de Datos (DPO): [EMAIL_DPO_SI_APLICA]</p>

</body>
</html>
```

---

## 2.2 📜 TÉRMINOS Y CONDICIONES DE USO (Terms of Service)

```html
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="UTF-8">
<title>Términos y Condiciones - [NOMBRE_DE_LA_APP]</title>
</head>
<body>

<h1>Términos y Condiciones de Uso</h1>
<p><strong>Última actualización:</strong> [FECHA_DE_ACTUALIZACION]</p>

<p>Bienvenido a <strong>[NOMBRE_DE_LA_APP]</strong>. Al descargar, instalar o usar 
esta Aplicación, usted ("el Usuario") acepta quedar vinculado por los presentes 
Términos y Condiciones ("Términos"). Si no está de acuerdo, no use la Aplicación.</p>

<h2>1. Descripción del Servicio</h2>
<p>[NOMBRE_DE_LA_APP] es una aplicación de filosofía práctica estoica que ofrece 
rituales matutinos y nocturnos, micro-acciones diarias, biblioteca de citas, 
galería de imágenes y música de enfoque, con el fin de ayudarle a cultivar 
virtudes estoicas en su vida cotidiana.</p>

<h2>2. Modelo de Licenciamiento</h2>

<h3>2.1 Versión Gratuita — "5 Usos"</h3>
<p>La Aplicación ofrece un modelo de prueba de <strong>cinco (5) usos gratuitos</strong>:</p>
<ul>
  <li>Al agotar los 5 usos, el acceso a las funcionalidades se <strong>bloquea</strong>.</li>
  <li>El contador se gestiona localmente mediante mecanismos de seguridad anti-manipulación.</li>
  <li>Cualquier intento de eludir el contador constituye una violación de estos Términos.</li>
</ul>

<h3>2.2 Versión Completa — Pago Único</h3>
<p>Para desbloquear el uso ilimitado, se ofrece una <strong>licencia perpetua</strong> 
mediante un pago único de <strong>$10 USD</strong> a través de la plataforma externa 
<strong>Gumroad</strong> (<a href="https://[TU_URL_GUMROAD].gumroad.com/l/[PRODUCT_ID]">adquirir aquí</a>).</p>
<ul>
  <li><strong>Precio:</strong> $10.00 USD (puede variar según impuestos locales).</li>
  <li><strong>Método de pago:</strong> procesado exclusivamente por Gumroad; nosotros no 
      accedemos a los datos de su tarjeta.</li>
  <li><strong>Activación:</strong> tras la compra, recibirá un código de licencia por correo. 
      Introdúzcalo en la Aplicación para verificarlo vía la API de Gumroad 
      (<code>https://api.gumroad.com/v2/licenses/verify</code>).</li>
  <li><strong>Naturaleza:</strong> licencia <strong>no exclusiva, intransferible, mundial y perpetua</strong> 
      para uso personal.</li>
</ul>

<h2>3. Uso Permitido y Prohibido</h2>

<h3>3.1 Uso Permitido</h3>
<ul>
  <li>Uso personal, no comercial.</li>
  <li>Instalación en múltiples dispositivos propios asociados a la misma licencia.</li>
</ul>

<h3>3.2 Uso Prohibido</h3>
<ul>
  <li>Modificar, descompilar, realizar ingeniería inversa o distribuir la Aplicación.</li>
  <li>Eludir, hackear o manipular el contador de usos o el sistema de licencias.</li>
  <li>Compartir, revender o sublicenciar su código de licencia.</li>
  <li>Usar herramientas automatizadas (bots, scripts) contra la API de Gumroad.</li>
  <li>Usar la Aplicación para cualquier actividad ilegal o dañina.</li>
</ul>

<h2>4. Renuncia de Responsabilidad</h2>
<p><strong>LA APLICACIÓN SE PROPORCIONA "TAL CUAL" Y "SEGÚN DISPONIBILIDAD", SIN GARANTÍAS 
DE NINGÚN TIPO, EXPRESAS O IMPLÍCITAS.</strong> En la máxima medida permitida por la ley:</p>
<ul>
  <li><strong>No somos responsables</strong> del mal uso de la Aplicación ni de las consecuencias 
      derivadas de la práctica de ejercicios estoicos sin supervisión profesional.</li>
  <li><strong>La Aplicación no sustituye</strong> asesoramiento médico, psicológico, financiero 
      o legal. Consulte a un profesional cualificado para decisiones importantes.</li>
  <li><strong>No garantizamos</strong> que la Aplicación funcione ininterrumpidamente o esté 
      libre de errores.</li>
  <li><strong>El contenido filosófico</strong> es educativo y no constituye prescripción 
      profesional alguna.</li>
</ul>

<h2>5. Limitación de Responsabilidad</h2>
<p>En ningún caso [NOMBRE_DE_LA_EMPRESA_O_DESARROLLADOR] será responsable por:</p>
<ul>
  <li>Daños indirectos, incidentales, especiales o consecuentes.</li>
  <li>Pérdida de datos, beneficios o ingresos.</li>
  <li>Problemas derivados de la plataforma Gumroad o de Google Play.</li>
  <li>La responsabilidad total acumulada no excederá los $10 USD (importe pagado).</li>
</ul>

<h2>6. Propiedad Intelectual</h2>
<ul>
  <li>El código, diseño y marca de la Aplicación son propiedad de 
      [NOMBRE_DE_LA_EMPRESA_O_DESARROLLADOR].</li>
  <li>Las citas filosóficas son de dominio público (obras con más de 100 años).</li>
  <li>Los recursos audiovisuales open source se atribuyen conforme a sus respectivas 
      licencias (CC0, CC-BY, CC-BY-SA). Véase la sección "Créditos" en la Aplicación.</li>
</ul>

<h2>7. Procesamiento de Pagos por Terceros</h2>
<p>Los pagos se procesan exclusivamente por <strong>Gumroad, Inc.</strong>. Aceptamos sus 
<a href="https://gumroad.com/terms">Términos</a> y 
<a href="https://gumroad.com/privacy">Política de Privacidad</a>. No almacenamos datos 
de pago. Los reembolsos se gestionan conforme a la política de Gumroad.</p>

<h2>8. Cancelación y Reembolsos</h2>
<ul>
  <li>Dado el modelo de "pago único", no existe suscripción recurrente que cancelar.</li>
  <li>Solicitudes de reembolso: dentro de los 14 días posteriores a la compra, 
      contactando a [EMAIL_DE_SOPORTE] o a Gumroad directamente.</li>
</ul>

<h2>9. Modificaciones de los Términos</h2>
<p>Podemos actualizar estos Términos en cualquier momento. Notificaremos cambios materiales 
dentro de la Aplicación o por correo. El uso continuado implica aceptación.</p>

<h2>10. Terminación</h2>
<p>Podemos suspender o revocar licencias en caso de: (a) fraude o manipulación del contador; 
(b) redistribución del código de licencia; (c) actividad ilegal.</p>

<h2>11. Ley Aplicable</h2>
<p>Estos Términos se rigen por las leyes de [PAIS_JURISDICCION], sin consideración de 
conflictos de leyes. Las disputas se resolverán en los tribunales de [CIUDAD_JURISDICCION].</p>

<h2>12. Contacto</h2>
<p><strong>Email:</strong> [EMAIL_DE_SOPORTE]<br>
<strong>Sitio web:</strong> [URL_SITIO_WEB_SI_APLICA]</p>

</body>
</html>
```

---

## 2.3 🍪 POLÍTICA DE COOKIES / ALMACENAMIENTO LOCAL

```html
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="UTF-8">
<title>Política de Cookies y Almacenamiento Local - [NOMBRE_DE_LA_APP]</title>
</head>
<body>

<h1>Política de Cookies y Almacenamiento Local</h1>
<p><strong>Última actualización:</strong> [FECHA_DE_ACTUALIZACION]</p>

<h2>1. Introducción</h2>
<p>Al ser una <strong>aplicación móvil nativa de Android</strong>, [NOMBRE_DE_LA_APP] 
<strong>no utiliza cookies web</strong> en el sentido tradicional de los navegadores. 
Sin embargo, utiliza mecanismos de <strong>almacenamiento local</strong> equivalentes 
para gestionar su sesión, preferencias y el contador de usos. Esta política explica 
cómo y por qué utilizamos estas tecnologías.</p>

<h2>2. Tecnologías de Almacenamiento Utilizadas</h2>

<h3>2.1 EncryptedSharedPreferences / DataStore (Cifrado)</h3>
<p><strong>Propósito:</strong> Almacenar de forma segura los siguientes datos:</p>
<ul>
  <li><strong>Estado de la licencia</strong> (activa/inactiva, código, fecha de verificación).</li>
  <li><strong>Contador de usos gratuitos</strong> (0 a 5) con firma de integridad HMAC.</li>
  <li><strong>Token de sesión administrativa</strong> (efímero, cifrado).</li>
  <li><strong>Preferencias del usuario</strong> (horarios, pilares seleccionados, tema).</li>
</ul>
<p><strong>Cifrado:</strong> AES-256 (claves gestionadas por Android Keystore).<br>
<strong>Duración:</strong> persistente hasta desinstalación o verificación de licencia.</p>

<h3>2.2 Room Database (Local)</h3>
<p><strong>Propósito:</strong> Almacenar:</p>
<ul>
  <li>Perfil del usuario (nombre, preferencias).</li>
  <li>Sesiones de rituales, micro-acciones, estados de ánimo.</li>
  <li>Citas filosóficas favoritas, configuración de notificaciones.</li>
</ul>
<p><strong>Duración:</strong> persistente hasta desinstalación.</p>

<h3>2.3 Cookies Técnicas (solo en vistas WebView, si aplica)</h3>
<p>Si la Aplicación incluye vistas embebidas (ej. para mostrar páginas externas), 
se utilizan <strong>cookies técnicas estrictamente necesarias</strong> para:</p>
<ul>
  <li>Recordar su sesión en la pasarela de pago de Gumroad.</li>
</ul>
<p><strong>No utilizamos cookies de publicidad, analíticas o de seguimiento de terceros.</strong></p>

<h2>3. Gestión y Eliminación</h2>
<p>Puede gestionar el almacenamiento local de las siguientes formas:</p>
<ul>
  <li><strong>Desde la app:</strong> Ajustes → "Borrar datos locales".</li>
  <li><strong>Desde Android:</strong> Ajustes → Aplicaciones → [NOMBRE_DE_LA_APP] → 
      Almacenamiento → Borrar datos.</li>
  <li><strong>Desinstalando</strong> la Aplicación (elimina todos los datos).</li>
</ul>
<p>⚠️ <strong>Nota:</strong> Borrar el contador de usos local no reinicia la licencia 
si ésta ya fue verificada.</p>

<h2>4. Conformidad Legal</h2>
<ul>
  <li><strong>GDPR (UE):</strong> El almacenamiento local es "estrictamente necesario" 
      para el funcionamiento del servicio (Art. 5(3) Directiva ePrivacy), por lo que 
      no requiere consentimiento previo.</li>
  <li><strong>CCPA (California):</strong> El almacenamiento local no constituye 
      "venta" ni "uso comercial" de datos personales.</li>
  <li><strong>LGPD (Brasil):</strong> Compatible con los principios de finalidad 
      y necesidad.</li>
</ul>

<h2>5. Cambios</h2>
<p>Cualquier cambio en esta política se notificará dentro de la Aplicación.</p>

<h2>6. Contacto</h2>
<p><strong>Email:</strong> [EMAIL_DE_SOPORTE]</p>

</body>
</html>
```

---

# 3. INTEGRACIÓN DE RECURSOS OPEN SOURCE, CONTADOR Y GUMROAD

## 3.1 🎵 APIs de Música / Sonidos Open Source

| Recurso | Licencia | Endpoint / Uso | Recomendado |
|---------|----------|----------------|-------------|
| **Freesound API** | CC0 / CC-BY (por track) | `https://freesound.org/apiv2/search/text/?query=meditation` | ⭐⭐⭐⭐⭐ Sonidos ambientales |
| **Jamendo API** | Creative Commons | `https://api.jamendo.com/v3.0/tracks/?client_id=YOUR_ID&tags=meditation` | ⭐⭐⭐⭐ Música completa |
| **Internet Archive** | Public Domain | Colecciones de música clásica y meditativa | ⭐⭐⭐ Sin auth |
| **Pixabay Music API** | Pixabay License (libre) | Música de meditación sin atribución obligatoria | ⭐⭐⭐⭐ Simple |
| **Tácticas locales** | Propios / CC0 | Empaquetar 4-8 tracks MP3 cortos en `res/raw/` | ⭐⭐⭐⭐⭐ Recomendado para offline-first |

### Implementación recomendada (ya integrada en el proyecto):
```kotlin
// 1. Bundle inicial en res/raw/ (offline-first):
//    raw/meditation_morning.mp3, raw/ambient_rain.mp3, etc.
//
// 2. Streaming opcional vía Freesound/Jamendo (requiere API key)
// 3. Descarga y cache en almacenamiento interno cifrado
```

## 3.2 🖼️ APIs de Fotos Estoicas Open Source

| Recurso | Licencia | Uso | Recomendado |
|---------|----------|-----|-------------|
| **Wikimedia Commons API** | CC-BY-SA / Public Domain | Estatuas clásicas, manuscritos griegos | ⭐⭐⭐⭐⭐ Mejor para contenido clásico |
| **Unsplash Source API** | Unsplash License (libre) | Fotografías estoicas abstractas | ⭐⭐⭐⭐ |
| **Pexels API** | Pexels License (libre) | Fotografías de mármol, templos | ⭐⭐⭐⭐ |
| **The Met Museum API** | CC0 (obras digitalizadas) | Obras de arte clásicas en dominio público | ⭐⭐⭐⭐⭐ |
| **LSDPL Classic Art** | Public Domain | Pinturas renacentistas y clásicas | ⭐⭐⭐⭐ |

### Implementación (Wikimedia - ya sugerida):
```kotlin
// Endpoint: 
// https://commons.wikimedia.org/w/api.php?action=query&generator=search
//   &gsrsearch=Marcus%20Aurelius%20statue&gsrnamespace=6
//   &prop=imageinfo&iiprop=url&format=json
```

## 3.3 🔒 Seguridad del Contador de 5 Usos

### Arquitectura anti-tampering (implementada en `SecureUsageCounter.kt`):

```
┌──────────────────────────────────────────────────────┐
│  EncryptedSharedPreferences (AES-256, Android Keystore) │
│  ┌──────────────────────────────────────────────────┐ │
│  │ count        = 3                                  │ │
│  │ lastUsedAt   = 1719500000000                      │ │
│  │ deviceId     = a4f2c8...                          │ │
│  │ hmacSignature = HMAC-SHA256(count|lastUsedAt|deviceId) │
│  └──────────────────────────────────────────────────┘ │
└──────────────────────────────────────────────────────┘
```

**Defensas implementadas:**

| Vector de Ataque | Defensa |
|------------------|---------|
| 🗑️ Borrar SharedPreferences | HMAC invalidado → se requiere re-verificación con backend o resetea a 5 (con fingerprint del dispositivo) |
| ✏️ Modificar valor del contador | HMAC-SHA256 detecta modificación → rechaza |
| 📦 Clonar data entre dispositivos | `deviceId` (ANDROID_ID + UUID) difiere → HMAC invalidado |
| 🔄 Reinstalar para resetear | Backend opcional con fingerprint del dispositivo |
| ⏰ Modificar reloj del sistema | `lastUsedAt` con monotonic check + servidor NTP opcional |

### Código clave (ver `SecureUsageCounter.kt`):
```kotlin
fun increment(): UsageResult {
    val current = readAndVerify() ?: return UsageResult.Tampered
    if (current.count >= MAX_FREE_USES && !current.licenseActive) {
        return UsageResult.Locked
    }
    val newCount = current.count + 1
    val newEntry = current.copy(count = newCount, lastUsedAt = now())
    writeSigned(newEntry) // recalcula HMAC
    return if (newCount >= MAX_FREE_USES) UsageResult.Locked else UsageResult.Allowed
}
```

## 3.4 🔑 Flujo de Verificación de Licencia Gumroad

### Endpoint
```
POST https://api.gumroad.com/v2/licenses/verify
Content-Type: application/x-www-form-urlencoded

Body:
  product_id={TU_PRODUCT_ID}&
  license_key={LICENSE_DEL_USUARIO}&
  increment_uses_count=false   // true para contar usos del servidor
```

### Respuesta esperada (200 OK):
```json
{
  "success": true,
  "uses": 3,
  "purchase": {
    "id": "abc-123",
    "email": "usuario@example.com",
    "created_at": "2026-06-15T10:30:00Z",
    "product_name": "[NOMBRE_DE_LA_APP] - Lifetime License",
    "variants": "",
    "refundable": false,
    "subscription_terminated_at": null,
    "subscription_cancelled_at": null,
    "subscription_failed_at": null
  },
  "license_key": {
    "id": "license-xyz",
    "key": "ABC123-DEF456-GHI789",
    "success": true,
    "uses": 3
  }
}
```

### Casos de manejo:
| HTTP / Campo | Acción |
|--------------|--------|
| `success: true` + no terminada | ✅ Activar licencia, desbloquear, persistir localmente |
| `success: false` | ❌ Licencia inválida o inexistente |
| `subscription_terminated_at != null` | ❌ Licencia revocada |
| 401 / 403 | ❌ Error de autenticación (product_id mal) |
| 404 | ❌ License key no encontrada |
| 429 | ⏳ Rate limit — esperar y reintentar |

### Implementación incluida (`GumroadApiService.kt`):
```kotlin
interface GumroadApiService {
    @POST("v2/licenses/verify")
    suspend fun verifyLicense(
        @Field("product_id") productId: String,
        @Field("license_key") licenseKey: String,
        @Field("increment_uses_count") increment: Boolean = false
    ): GumroadVerifyResponse
}
```

### Flujo de Activación en UI:
```
1. Usuario agota 5 usos → Pantalla de bloqueo
2. "Desbloquear con licencia" → WebView a Gumroad o link
3. Usuario paga $10 → recibe email con license_key
4. Vuelve a la app → pega el código
5. App llama verifyLicense() → 200 OK
6. Persiste License en Room + actualiza EncryptedPrefs
7. Desbloquea todas las features
```

---

# 4. RESUMEN EJECUTIVO DE SEGURIDAD

## ✅ Checklist Implementado

| Item | Estado |
|------|--------|
| PIN admin con hash PBKDF2 (210k iteraciones) | ✅ |
| EncryptedSharedPreferences con Android Keystore | ✅ |
| Contador con firma HMAC-SHA256 anti-tampering | ✅ |
| Panel admin deshabilitado en build de release | ✅ |
| Sesión admin efímera con TTL 15 min | ✅ |
| Auditoría de acciones admin | ✅ |
| Comunicación HTTPS/TLS con Gumroad | ✅ |
| Rate limiting en verificación de licencia (client-side) | ✅ |
| No se almacenan datos de pago | ✅ |
| Plantillas legales GDPR/CCPA | ✅ |

## 🚫 Anti-patrones evitados
- ❌ No "backdoor" por toques en versión
- ❌ No PIN en texto plano
- ❌ No contador en SharedPreferences sin cifrar
- ❌ No license_key en logcat
- ❌ No panel admin en release build

## 🔄 Próximos pasos recomendados
1. Configurar `product_id` real de Gumroad en `local.properties` → `BuildConfig`
2. Generar hash real del PIN admin con `AdminPinSetupHelper`
3. Pasar las plantillas HTML por revisión legal
4. Implementar backend JWT si se desea auth remota (opcional)
5. Añadir Firebase App Check para prevenir abuso de API

---

*Documentación generada por auditoría completa del proyecto StoicusApp.*
*Arquitectura revisada: Clean Architecture + MVVM + Hilt + Room v3 + Compose.*