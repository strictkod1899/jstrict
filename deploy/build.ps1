# Выполнение сборки проекта без запуска тестов
# Скрипт запускать из корневой директории проекта

Write-Host ""
Write-Host "            - START A PROJECT BUILD"
Write-Host ""

try{
    mvn clean install -DskipTests
}catch{
    throw "$($_.Exception)"
}

Write-Host ""
Write-Host "            - SUCCESS A PROJECT BUILD"
Write-Host ""
