@echo off
echo ===============================================
echo   Instalando StoicusApp en el Emulador
echo ===============================================
echo.

REM Configurar variables
set JAVA_HOME=E:\Android\jdk-17.0.2
set ANDROID_HOME=E:\Android\android-sdk
set PATH=%JAVA_HOME%\bin;%ANDROID_HOME%\platform-tools;%PATH%

cd /d "%~dp0StoicusApp"

echo Verificando dispositivos conectados...
adb devices

echo.
echo Esperando a que el emulador este listo...
adb wait-for-device

echo.
echo Compilando app (esto puede tardar)...
call gradlew assembleDebug

echo.
echo Instalando app en el emulador...
adb install -r app\build\outputs\apk\debug\app-debug.apk

echo.
echo ===============================================
echo   ¡App instalada!
echo ===============================================
echo.
echo Para abrir la app automaticamente:
echo   adb shell am start -n com.stoicus.app/.MainActivity
echo.
pause
