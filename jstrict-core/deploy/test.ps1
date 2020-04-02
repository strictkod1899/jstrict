# Выполнение тестов проекта
# Скрипт запускать из корневой директории проекта

Write-Host ""
Write-Host "            - START A PROJECT TESTS"
Write-Host ""

try{
    mvn clean test
}catch{
    throw "$($_.Exception)"
}

Write-Host ""
Write-Host "            - SUCCESS A PROJECT TESTS"
Write-Host ""
