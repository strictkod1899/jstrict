# Скачивание зависимостей проекта
# Скрипт запускать из корневой директории проекта

Write-Host ""
Write-Host "            - START A DEPENDENCIES DOWLOAD"
Write-Host ""

try{
    mvn dependency:copy-dependencies
}catch{
    throw "$($_.Exception)"
}

Write-Host ""
Write-Host "            - SUCCESS A DEPENDENCIES DOWLOAD"
Write-Host ""
