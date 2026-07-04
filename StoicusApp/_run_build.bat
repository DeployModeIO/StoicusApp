@echo off
set "JAVA_HOME=E:\jdk17\jdk-17.0.13+11"
set "ANDROID_SDK_ROOT=E:\Android\android-sdk"
cd /d E:\Github\StoicusApp\StoicusApp
call gradlew.bat assembleDebug
exit /b %ERRORLEVEL%