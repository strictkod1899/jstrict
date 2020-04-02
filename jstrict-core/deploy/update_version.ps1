# Процесс обнволения версии. На вход поступает исходная версия, а на выход поступает обновленная версия
# В зависимости от параметра 'isIncrement' версия будет либо увеличена на +1, либо уменьшина на -1
# Пример использования скрипта: $newVersionValue = &"./deploy/update_version.ps1" -version "0.1.3" | Select-Object -Last 1

param(
    # required
    [string]$version,
    # optional
    # 0 - null; 1 - increment; 2 - decrement
    # default = 1
    [int]$isIncrement
)

Write-Host ""
Write-Host "            - START A VERSION UPDATE = ${version}"
Write-Host ""

if($version -eq $null -Or $version -eq ''){
    throw "version is NULL"
}

if($isIncrement -gt 2 -Or $isIncrement -lt 0){
    throw "value for param 'isIncrement' should be equals from range [1; 2]"
}

if($isIncrement -eq $null -Or $isIncrement -eq 0){
    Write-Warning "[WARN]: param 'isIncrement' is NULL, so will be using a default value"
    $isIncrement = 1
}

Write-Host "[INFO]: version = ${version}"
Write-Host "[INFO]: isIncrement = ${isIncrement}"

try{
    $indexLastPoint = $version.LastIndexOf('.');
    Write-Host "[INFO]: indexLastPoint = ${indexLastPoint}"

    $leftPartVersion = ""
    if($indexLastPoint -ne $null -And $indexLastPoint -ne -1){
        $leftPartVersion = $version.Substring(0, $indexLastPoint+1)
    }
    if($indexLastPoint -eq $null){
        $indexLastPoint = -1
    }
    [int]$versionForUpdate = $version.Substring($indexLastPoint+1)

    if($isIncrement -eq 1){
        $versionForUpdate++
    } else {
        $versionForUpdate--
    }
    $newVersionValue = "${leftPartVersion}${versionForUpdate}"

    Write-Host ""
    Write-Host "            - SUCCESS A VERSION UPDATE = ${newVersionValue}"
    Write-Host ""
    return $newVersionValue
}catch{
    throw "$($_.Exception)"
}