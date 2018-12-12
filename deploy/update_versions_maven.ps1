# Обновить версии указанных проектов в pom.xml файле. В зависимости от параметра 'isIncrement' версия будет либо увеличена на +1, либо уменьшина на -1
# Скрипт запускать из корневой директории проекта

param(
    # required
    # example: ./app/pom.xml, ./db/pom.xml
    [string[]]$modulesPath,
    # optional
    # 0 - null; 1 - increment; 2 - decrement
    # default = 1
    [int]$isIncrement
)

Write-Host ""
Write-Host "            - START A VERSIONS UPDATE"
Write-Host ""

if($modulesPath -eq $null -Or $modulesPath.length -lt 1){
	throw "param 'modulesPath' is NULL"
}

foreach($modulePath in $modulesPath){
    if(!(Test-Path -Path "${modulePath}")){
        throw "file not found [${modulePath}] from param 'modulesPath'"
    }
}

try{
    foreach($modulePath in $modulesPath){
        ./deploy/update_version_maven.ps1 -xmlFilePath $modulePath -isIncrement $isIncrement

        Write-Host ""
        Write-Host "[INFO]:         - success a version update for file '$modulePath'"
        Write-Host ""
    }
    Write-Host ""
    Write-Host "            - SUCCESS A VERSIONS UPDATE"
    Write-Host ""
}catch{
    throw "$($_.Exception)"
}