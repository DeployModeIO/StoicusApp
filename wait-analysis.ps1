# Esperar a que terminen otros procesos powershell
$me = $PID
$start = Get-Date
while ($true) {
    $otros = Get-Process powershell -ErrorAction SilentlyContinue | Where-Object { $_.Id -ne $me }
    if (-not $otros) { break }
    $elapsed = (Get-Date) - $start
    Write-Host ("Esperando... {0:mm\:ss}" -f $elapsed)
    Start-Sleep -Seconds 5
}
Write-Host "OK - no hay otros procesos powershell corriendo"