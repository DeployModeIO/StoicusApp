@echo off
echo ========================================
echo   Iniciando Emulador Android
echo ========================================

REM Verificar si hay emuladores instalados
echo Buscando emuladores instalados...
emulator -list-avds

echo.
echo Para crear un nuevo emulador, usa Android Studio:
echo 1. Tools -^> Device Manager
echo 2. Create Device
echo 3. Selecciona un dispositivo (ej. Pixel 6)
echo 4. Descarga una imagen del sistema (ej. Android 13)
echo 5. Finaliza la creacion
echo.

REM Intentar listar dispositivos conectados
echo Dispositivos conectados:
adb devices

echo.
echo Para iniciar un emulador especifico:
echo emulator -avd NOMBRE_DEL_EMULADOR
echo.
echo O usa Android Studio para ejecutar tu app directamente!
echo ========================================

pause
