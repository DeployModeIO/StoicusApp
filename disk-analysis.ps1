# Análisis de espacio en disco y programas instalados
$ErrorActionPreference = 'SilentlyContinue'

Write-Host "========================================" -ForegroundColor Cyan
Write-Host " ESPACIO EN DISCO C:" -ForegroundColor Cyan
Write-Host "========================================" -ForegroundColor Cyan
$disk = Get-CimInstance Win32_LogicalDisk -Filter "DeviceID='C:'"
$totalGB = [math]::Round($disk.Size/1GB, 2)
$freeGB = [math]::Round($disk.FreeSpace/1GB, 2)
$usedGB = [math]::Round(($disk.Size - $disk.FreeSpace)/1GB, 2)
$pctFree = [math]::Round(($disk.FreeSpace/$disk.Size)*100, 1)
Write-Host ("Total: {0} GB" -f $totalGB)
Write-Host ("Usado: {0} GB" -f $usedGB)
Write-Host ("Libre: {0} GB ({1}%)" -f $freeGB, $pctFree)
Write-Host ""

Write-Host "========================================" -ForegroundColor Cyan
Write-Host " CARPETAS GRANDES EN C:" -ForegroundColor Cyan
Write-Host "========================================" -ForegroundColor Cyan
$carpetas = @(
    "C:\Windows",
    "C:\Program Files",
    "C:\Program Files (x86)",
    "C:\ProgramData",
    "C:\Users",
    "C:\Users\$env:USERNAME\AppData\Local",
    "C:\Users\$env:USERNAME\AppData\Roaming",
    "C:\Users\$env:USERNAME\AppData\Local\Temp",
    "C:\Windows\Temp",
    "C:\Windows\SoftwareDistribution",
    "C:\Windows\Installer",
    "C:\Windows\WinSxS"
)
foreach ($p in $carpetas) {
    if (Test-Path $p) {
        $size = (Get-ChildItem $p -Recurse -Force -ErrorAction SilentlyContinue | Measure-Object -Property Length -Sum).Sum
        $sizeGB = [math]::Round($size/1GB, 2)
        Write-Host ("{0,-55} {1,10} GB" -f $p, $sizeGB)
    }
}
Write-Host ""

Write-Host "========================================" -ForegroundColor Cyan
Write-Host " PROGRAMAS INSTALADOS (Registro)" -ForegroundColor Cyan
Write-Host "========================================" -ForegroundColor Cyan
$paths = @(
    "HKLM:\Software\Microsoft\Windows\CurrentVersion\Uninstall\*",
    "HKLM:\Software\WOW6432Node\Microsoft\Windows\CurrentVersion\Uninstall\*",
    "HKCU:\Software\Microsoft\Windows\CurrentVersion\Uninstall\*"
)
$apps = @()
foreach ($path in $paths) {
    $apps += Get-ItemProperty $path -ErrorAction SilentlyContinue |
        Where-Object { $_.DisplayName } |
        Select-Object DisplayName, DisplayVersion, Publisher, InstallDate, EstimatedSize, UninstallString
}
$apps = $apps | Sort-Object DisplayName -Unique
Write-Host ("Total programas detectados: {0}" -f $apps.Count)
Write-Host ""
$apps | Format-Table DisplayName, DisplayVersion, Publisher -AutoSize
Write-Host ""

Write-Host "========================================" -ForegroundColor Cyan
Write-Host " APPS MODERNAS (UWP/Store)" -ForegroundColor Cyan
Write-Host "========================================" -ForegroundColor Cyan
Get-AppxPackage | Where-Object { $_.Name -notlike "*Microsoft.NET*" -and $_.Name -notlike "*Microsoft.VCLibs*" } |
    Select-Object Name, Version | Sort-Object Name | Format-Table -AutoSize