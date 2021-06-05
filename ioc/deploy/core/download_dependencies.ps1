# Скачивание зависимостей проекта
# Скрипт запускать из корневой директории проекта

Write-Host ""
Write-Host "            - [START] - DOWNLOAD DEPENDENCIES"
Write-Host ""

mvn dependency:copy-dependencies

Write-Host ""
Write-Host "            - [FINISH] - DOWNLOAD DEPENDENCIES"
Write-Host ""
