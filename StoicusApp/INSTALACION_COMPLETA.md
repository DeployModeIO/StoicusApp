# 📱 StoicusApp - Guía de Instalación y Uso

## ✅ Lo que se está instalando

Todo se está instalando en **E:\Android**:

| Componente | Ubicación | Estado |
|------------|-----------|--------|
| Java JDK 17 | `E:\Android\jdk-17.0.2` | ✅ Instalado |
| Android SDK | `E:\Android\android-sdk` | 🔄 Instalando |
| Platform Tools (ADB) | `E:\Android\platform-tools` | ✅ Instalado |
| Emulador | `E:\Android\android-sdk\emulator` | 🔄 Instalando |
| System Image Android 34 | `E:\Android\android-sdk\system-images` | 🔄 Descargando |
| Emulador Pixel 6 | `E:\Android\emulators` | ⏳ Pendiente |

---

## 🚀 Cómo Usar (Una Vez Completada la Instalación)

### **Método 1: Desde VS Code (Recomendado)**

#### Paso 1: Abrir VS Code
```
1. Abre VS Code
2. Abre el proyecto: e:\Github\StoicusApp\StoicusApp
```

#### Paso 2: Iniciar el Emulador
```
1. Presiona Ctrl+Shift+P
2. Escribe: "Tasks: Run Task"
3. Selecciona: "Start Emulator (Pixel 6)"
```

O ejecuta manualmente:
```bash
E:\Android\start-emulator-gui.bat
```

#### Paso 3: Instalar tu App
```
1. Espera a que el emulador termine de iniciar (2-3 minutos)
2. En VS Code: Ctrl+Shift+P
3. Tasks: Run Android App (Debug)
```

O ejecuta:
```bash
e:\Github\StoicusApp\StoicusApp\install-app-on-emulator.bat
```

#### Paso 4: ¡Disfruta tu App!
La app **StoicusApp** se abrirá automáticamente en el emulador.

---

### **Método 2: Usando tu Teléfono Real**

#### Paso 1: Activar Depuración USB
```
1. Ve a Ajustes → Acerca del teléfono
2. Toca "Número de compilación" 7 veces
3. Regresa a Ajustes → Sistema → Opciones de desarrollador
4. Activa "Depuración USB"
```

#### Paso 2: Conectar y Verificar
```
1. Conecta tu teléfono por USB
2. En VS Code terminal:
   adb devices
```

Deberías ver tu dispositivo listado.

#### Paso 3: Instalar App
```bash
cd e:\Github\StoicusApp\StoicusApp
gradlew installDebug
```

¡La app se instalará en tu teléfono!

---

## 📁 Scripts Creados para Ti

### En E:\Android\:

| Archivo | Propósito |
|---------|-----------|
| `start-emulator-gui.bat` | Inicia el emulador Pixel 6 |
| `setup-env.bat` | Configura variables de entorno |
| `install-sdk.ps1` | Script de instalación (ya se ejecutó) |

### En e:\Github\StoicusApp\StoicusApp\:

| Archivo | Propósito |
|---------|-----------|
| `install-app-on-emulator.bat` | Compila e instala la app |
| `start-emulator.bat` | Info sobre emuladores |

---

## ⚡ Comandos Útiles

### Ver dispositivos conectados:
```bash
adb devices
```

### Iniciar emulador:
```bash
emulator -avd Pixel_6_API_34
```

### Instalar app:
```bash
adb install -r e:\Github\StoicusApp\StoicusApp\app\build\outputs\apk\debug\app-debug.apk
```

### Ver logs de la app:
```bash
adb logcat | grep "Stoicus"
```

### Capturar screenshot:
```bash
adb shell screencap -p /sdcard/stoicus.png
adb pull /sdcard/stoicus.png
```

### Grabar pantalla:
```bash
adb shell screenrecord /sdcard/demo.mp4
adb pull /sdcard/demo.mp4
```

---

## 🎯 Tareas de VS Code Configuradas

Presiona `Ctrl+Shift+P` → **Tasks: Run Task**:

- **Run Android App (Debug)** - Compila e instala la app
- **Build APK Debug** - Compila APK de debug
- **Build APK Release** - Compila APK de release
- **List Connected Devices** - Muestra dispositivos
- **Clean Project** - Limpia el proyecto
- **Sync Gradle** - Sincroniza dependencias

---

## 🆘 Solución de Problemas

### ❌ "No devices found"
**Solución:**
```bash
adb kill-server
adb start-server
adb devices
```

### ❌ "Emulator muy lento"
**Solución:**
1. Cierra el emulador
2. Edita el archivo: `E:\Android\emulators\Pixel_6_API_34\config.ini`
3. Cambia: `hw.gpu.enabled = yes`
4. Reinicia el emulador

### ❌ "Gradle build failed"
**Solución:**
```bash
cd e:\Github\StoicusApp\StoicusApp
gradlew clean
gradlew build
```

### ❌ "JAVA_HOME not set"
**Solución:**
1. Ejecuta: `E:\Android\setup-env.bat`
2. **CIERRA y ABRE VS Code** (importante!)

---

## 📊 Estado de la Instalación

La instalación se está ejecutando en segundo plano. Para verificar el progreso:

1. Abre PowerShell
2. Ejecuta:
```powershell
Get-Content E:\Android\android-sdk\licenses\android-sdk-license -ErrorAction SilentlyContinue
```

Si ves contenido, ¡las licencias fueron aceptadas!

Para verificar componentes instalados:
```bash
E:\Android\android-sdk\cmdline-tools\latest\bin\sdkmanager.bat --list_installed
```

---

## ⏱️ Tiempos Estimados

| Proceso | Tiempo |
|---------|--------|
| Descarga SDK | 5-10 min |
| Descarga System Image | 10-15 min |
| Creación del emulador | 2-3 min |
| Primer inicio del emulador | 3-5 min |
| Compilación de la app | 2-5 min |
| Instalación en emulador | 30 seg |

**Total estimado:** 25-40 minutos

---

## ✨ Una Vez Todo Listo

1. **Inicia el emulador:**
   - Doble click en `E:\Android\start-emulator-gui.bat`

2. **Espera a que cargue** (parece un teléfono Android)

3. **Instala tu app:**
   - Doble click en `e:\Github\StoicusApp\StoicusApp\install-app-on-emulator.bat`

4. **¡Listo!** Verás tu app **StoicusApp** corriendo en el emulador.

---

## 🎉 Características de tu App

Tu app ahora incluye:

- ✅ **40+ citas** de Marco Aurelio, Epicteto, Séneca y Spinoza
- ✅ **Galería de imágenes** estoicas
- ✅ **Música ambiental** para meditación
- ✅ **23 tareas diarias** para mente, cuerpo y alma
- ✅ **Rituales** matutinos y nocturnos
- ✅ **Analytics** de progreso
- ✅ **Sistema de rachas**

---

## 📞 ¿Necesitas Ayuda?

Si algo falla, dime:
1. ¿En qué paso estás?
2. ¿Qué error ves?
3. ¿Qué intentaste?

¡Y te ayudo a solucionarlo! 🚀
