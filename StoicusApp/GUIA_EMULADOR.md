# 📱 Guía: Ver tu App en VS Code como un Teléfono

## ✅ Lo que ya tienes instalado
- ✅ ADB (Android Debug Bridge) en `C:\Users\luisr\platform-tools\`
- ✅ Gradle configurado
- ✅ Tasks de VS Code configurados

---

## 🚀 Opción 1: Usar Android Studio (RECOMENDADA)

Esta es la forma más fácil y estable de ver tu app:

### Pasos:
1. **Abre Android Studio**
2. **File → Open** → Selecciona la carpeta `StoicusApp`
3. **Espera a que Gradle sync termine**
4. **Click en el botón "Run" (▶️ verde)** en la barra superior
5. **Selecciona o crea un emulador**:
   - Si no tienes emuladores: Click en "Create New Device"
   - Elige: **Pixel 6** o **Pixel 7**
   - Sistema: **Android 13 (API 33)** o **Android 14 (API 34)**
   - Click en **Finish**
6. **¡La app se instalará y ejecutará automáticamente!**

### Ventajas:
- ✅ Emulador oficial de Google
- ✅ Hot reload (cambios en tiempo real)
- ✅ Debugger integrado
- ✅ Logcat para ver logs
- ✅ Profiling de rendimiento

---

## 🖥️ Opción 2: VS Code + Emulador Externo

Si prefieres usar VS Code:

### Paso 1: Instalar Extensiones Útiles

Abre VS Code y presiona `Ctrl+Shift+X`, busca e instala:

```
1. Android iOS Emulator (de various publishers - busca la mejor calificada)
2. Flutter (opcional, pero trae herramientas útiles)
3. Gradle Language Support
```

### Paso 2: Crear un Emulador desde Android Studio

1. Abre **Android Studio**
2. **Tools → Device Manager**
3. **Create Device**
4. Elige: **Pixel 6**
5. Descarga: **System Image API 34 (Android 14)**
6. **Finish**

### Paso 3: Iniciar Emulador desde VS Code

En VS Code:
1. Presiona `Ctrl+Shift+P`
2. Escribe: **Tasks: Run Task**
3. Selecciona: **Start Emulator (Pixel 6)**

O ejecuta manualmente:
```bash
emulator -avd Pixel_6_API_34
```

### Paso 4: Instalar tu App

En VS Code:
1. `Ctrl+Shift+P`
2. **Tasks: Run Task**
3. **Run Android App (Debug)**

O en la terminal:
```bash
cd e:\Github\StoicusApp\StoicusApp
./gradlew installDebug
```

---

## 📱 Opción 3: Usar tu Teléfono Real (¡Muy Fácil!)

### Requisitos:
- Teléfono Android físico
- Cable USB

### Pasos:
1. **En tu teléfono**:
   - Ve a **Ajustes → Acerca del teléfono**
   - Toca **Número de compilación** 7 veces
   - Regresa a **Ajustes → Sistema → Opciones de desarrollador**
   - Activa **Depuración USB**

2. **Conecta tu teléfono por USB**

3. **En VS Code o terminal**:
   ```bash
   adb devices
   ```
   Deberías ver tu dispositivo listado

4. **Ejecuta tu app**:
   ```bash
   cd e:\Github\StoicusApp\StoicusApp
   ./gradlew installDebug
   ```

5. **¡Abre la app en tu teléfono!**

---

## ⚡ Comandos Útiles

### Desde VS Code (Ctrl+Shift+P → Tasks):
- `Run Android App (Debug)` - Instala y ejecuta la app
- `Build APK Debug` - Compila el APK de debug
- `Build APK Release` - Compila el APK de release
- `List Connected Devices` - Muestra dispositivos conectados
- `Clean Project` - Limpia el proyecto

### Desde Terminal:
```bash
# Ver dispositivos conectados
adb devices

# Instalar app
adb install app/build/outputs/apk/debug/app-debug.apk

# Ver logs de la app
adb logcat | grep "Stoicus"

# Reiniciar app
adb shell am start -n com.stoicus.app/.MainActivity

# Capturar screenshot
adb shell screencap -p /sdcard/screenshot.png
adb pull /sdcard/screenshot.png

# Grabar pantalla
adb shell screenrecord /sdcard/demo.mp4
adb pull /sdcard/demo.mp4
```

---

## 🎯 Mi Recomendación

**Usa Android Studio para desarrollar:**
1. Abre Android Studio
2. Click en **Run (▶️)**
3. La app se ejecuta en el emulador automáticamente
4. Haz cambios en el código
5. La app se actualiza sola (Hot Reload)

**Usa VS Code para:**
- Editar código rápidamente
- Compilar APKs
- Comandos específicos

---

## 🆘 Problemas Comunes

### ❌ "No devices found"
**Solución:**
```bash
adb kill-server
adb start-server
adb devices
```

### ❌ "Emulator no inicia"
**Solución:**
1. Abre Android Studio
2. Device Manager
3. Wipe Data del emulador
4. Cold Boot Now

### ❌ "Gradle sync failed"
**Solución:**
```bash
cd e:\Github\StoicusApp\StoicusApp
./gradlew clean
./gradlew --refresh-dependencies
```

---

## 📞 ¿Necesitas Ayuda?

Si tienes problemas, dime:
1. ¿Qué opción intentaste?
2. ¿Qué error ves?
3. ¿Tienes Android Studio instalado?

¡Y te ayudo a configurarlo! 🚀
