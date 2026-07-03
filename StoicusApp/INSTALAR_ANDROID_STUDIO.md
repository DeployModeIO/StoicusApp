# 📥 Instalación Rápida: Android Studio + Emulador

## Paso 1: Descargar Android Studio

1. Ve a: https://developer.android.com/studio
2. Click en **"Download Android Studio"**
3. Acepta los términos
4. Descarga: `android-studio-202x.x.x.xx-windows.exe` (~1.1 GB)

## Paso 2: Instalar Android Studio

1. Ejecuta el instalador descargado
2. Next → Next → Next
3. **IMPORTANTE**: Marca estas opciones:
   - ✅ Android Studio
   - ✅ Android SDK
   - ✅ Android SDK Platform
   - ✅ Android Virtual Device (AVD) ← ¡Necesario para el emulador!
4. Finaliza la instalación

## Paso 3: Configurar el SDK

1. Abre **Android Studio**
2. **More Actions → SDK Manager**
3. En **SDK Platforms**:
   - ✅ Android 14.0 (API 34) - Recommended
   - ✅ Android 13.0 (API 33)
4. En **SDK Tools**:
   - ✅ Android SDK Build-Tools
   - ✅ Android Emulator
   - ✅ Android SDK Platform-tools
   - ✅ Intel x86 Emulator Accelerator (si tu CPU es Intel)
5. Click en **Apply**

## Paso 4: Crear tu Primer Emulador

1. **More Actions → Device Manager**
2. **Create Device**
3. Elige un dispositivo:
   - **Pixel 6** (Recomendado)
   - o **Pixel 7**
4. **Next**
5. Selecciona imagen del sistema:
   - **Android 14.0 (API 34)** - Recommended
   - o **Android 13.0 (API 33)**
6. **Next → Finish**

## Paso 5: Probar el Emulador

1. En **Device Manager**, click en ▶️ junto a tu emulador
2. Espera a que inicie (parece un teléfono real)
3. ¡Listo!

## Paso 6: Abrir tu Proyecto StoicusApp

1. **File → Open**
2. Navega a: `e:\Github\StoicusApp\StoicusApp`
3. Click en **OK**
4. Espera a que **Gradle sync** termine
5. Click en el botón **▶️ Run** (verde, arriba a la derecha)
6. Selecciona tu emulador
7. ¡La app se instalará y ejecutará!

---

## 🎯 Atajo Rápido

Una vez instalado Android Studio:

1. Abre Android Studio
2. Abre el proyecto `StoicusApp`
3. Presiona **Shift + F10** (Run)
4. ¡Listo!

---

## 💡 Tips

### Ejecutar desde VS Code:
Después de tener Android Studio instalado:
1. Inicia el emulador desde Android Studio
2. En VS Code: `Ctrl+Shift+P` → Tasks → **Run Android App (Debug)**
3. La app se instala en el emulador

### Usar tu teléfono real:
1. Activa **Depuración USB** en tu teléfono
2. Conéctalo por USB
3. En VS Code: Tasks → **Run Android App (Debug)**
4. ¡Se instala en tu teléfono!

---

## ⚡ ¿Ya tienes Android Studio?

Si ya lo tienes instalado pero no lo encontré:

1. Búscalo en el menú inicio: "Android Studio"
2. Ábrelo
3. Sigue desde el **Paso 4** de arriba

---

## 🆘 Problemas Comunes

### ❌ "HAXM is not installed"
**Solución:**
1. SDK Manager → SDK Tools
2. Instala: **Intel x86 Emulator Accelerator (HAXM)**
3. O usa una imagen **ARM** en lugar de x86

### ❌ "Emulator muy lento"
**Solución:**
1. Device Manager → Click en ⚙️ de tu emulador
2. Show Advanced Settings
3. Graphics: **Hardware - GLES 2.0**
4. RAM: **2048 MB** o más
5. VM Heap: **576 MB**

### ❌ "Gradle sync failed"
**Solución:**
```bash
cd e:\Github\StoicusApp\StoicusApp
./gradlew clean
./gradlew build
```

---

¡Una vez instalado, avísame y te ayudo a ejecutar tu app! 🚀
