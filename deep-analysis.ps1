$ErrorActionPreference = 'SilentlyContinue'

function Get-FolderSize {
    param([string]$Path, [int]$Depth = 0)
    $items = Get-ChildItem $Path -Force -ErrorAction SilentlyContinue | Where-Object { $_.PSIsContainer }
    $results = @()
    foreach ($item in $items) {
        $size = (Get-ChildItem $item.FullName -Recurse -Force -ErrorAction SilentlyContinue |
                 Measure-Object -Property Length -Sum).Sum
        if ($size -gt 100MB) {
            $results += [PSCustomObject]@{
                Path = $item.FullName
                SizeGB = [math]::Round($size/1GB, 2)
            }
        }
    }
    return $results | Sort-Object SizeGB -Descending
}

Write-Host "========================================" -ForegroundColor Yellow
Write-Host " TOP CARPETAS EN C:\Users\luisr (>100MB)" -ForegroundColor Yellow
Write-Host "========================================" -ForegroundColor Yellow
Get-FolderSize "C:\Users\luisr" | Format-Table -AutoSize
Write-Host ""

Write-Host "========================================" -ForegroundColor Yellow
Write-Host " TOP CARPETAS EN AppData\Local (>100MB)" -ForegroundColor Yellow
Write-Host "========================================" -ForegroundColor Yellow
Get-FolderSize "C:\Users\luisr\AppData\Local" | Format-Table -AutoSize
Write-Host ""

Write-Host "========================================" -ForegroundColor Yellow
Write-Host " TOP CARPETAS EN AppData\Roaming (>100MB)" -ForegroundColor Yellow
Write-Host "========================================" -ForegroundColor Yellow
Get-FolderSize "C:\Users\luisr\AppData\Roaming" | Format-Table -AutoSize
Write-Host ""

Write-Host "========================================" -ForegroundColor Yellow
Write-Host " MODELOS OLLAMA (.ollama)" -ForegroundColor Yellow
Write-Host "========================================" -ForegroundColor Yellow
if (Test-Path "C:\Users\luisr\.ollama") {
    $ollamaSize = (Get-ChildItem "C:\Users\luisr\.ollama" -Recurse -Force -ErrorAction SilentlyContinue | Measure-Object -Property Length -Sum).Sum
    Write-Host ("Ollama total: {0} GB" -f [math]::Round($ollamaSize/1GB,2))
    Get-ChildItem "C:\Users\luisr\.ollama\models\blobs" -Force -ErrorAction SilentlyContinue |
        Sort-Object Length -Descending | Select-Object -First 10 |
        ForEach-Object { Write-Host ("  {0,8} MB - {1}" -f [math]::Round($_.Length/1MB,1), $_.Name) }
} else { Write-Host "No encontrado" }
Write-Host ""

Write-Host "========================================" -ForegroundColor Yellow
Write-Host " CACHÉS DE NAVEGADORES" -ForegroundColor Yellow
Write-Host "========================================" -ForegroundColor Yellow
$browsers = @(
    @{Name="Chrome";  Path="C:\Users\luisr\AppData\Local\Google\Chrome\User Data"},
    @{Name="Brave";   Path="C:\Users\luisr\AppData\Local\BraveSoftware\Brave-Browser\User Data"},
    @{Name="Edge";    Path="C:\Users\luisr\AppData\Local\Microsoft\Edge\User Data"},
    @{Name="Firefox"; Path="C:\Users\luisr\AppData\Local\Mozilla\Firefox\Profiles"}
)
foreach ($b in $browsers) {
    if (Test-Path $b.Path) {
        $s = (Get-ChildItem $b.Path -Recurse -Force -ErrorAction SilentlyContinue | Measure-Object -Property Length -Sum).Sum
        Write-Host ("{0,-10}: {1,8} GB  ({2})" -f $b.Name, [math]::Round($s/1GB,2), $b.Path)
    }
}
Write-Host ""

Write-Host "========================================" -ForegroundColor Yellow
Write-Host " JUEGOS / APPS GRANDES" -ForegroundColor Yellow
Write-Host "========================================" -ForegroundColor Yellow
$juegos = @(
    "C:\Program Files (x86)\Steam",
    "C:\Program Files\Epic Games",
    "C:\Program Files (x86)\Battle.net",
    "C:\Program Files (x86)\Call of Duty",
    "C:\Program Files\Call of Duty",
    "C:\Users\luisr\AppData\Local\Programs\GameLoop",
    "C:\Users\luisr\AppData\Local\Roblox"
)
foreach ($j in $juegos) {
    if (Test-Path $j) {
        $s = (Get-ChildItem $j -Recurse -Force -ErrorAction SilentlyContinue | Measure-Object -Property Length -Sum).Sum
        Write-Host ("{0,8} GB  - {1}" -f [math]::Round($s/1GB,2), $j)
    }
}

Write-Host ""
Write-Host "========================================" -ForegroundColor Yellow
Write-Host " PAPELERA DE RECICLAJE" -ForegroundColor Yellow
Write-Host "========================================" -ForegroundColor Yellow
$shell = New-Object -ComObject Shell.Application
$rb = $shell.NameSpace(0xA)
$rbSize = ($rb.Items() | ForEach-Object { $_.ExtendedProperty("Size") } | Measure-Object -Sum).Sum
Write-Host ("Papelera: {0} GB" -f [math]::Round($rbSize/1GB,2))
Write-Host ""
Write-Host "DONE"