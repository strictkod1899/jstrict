# Выполнение сборки проекта
# Скрипт запускать из корневой директории проекта

Write-Host ""
Write-Host "            - [START] - PROJECT BUILD"
Write-Host ""

$out = mvn clean test | Out-String
if (!$out.contains("BUILD SUCCESS")) {
    Write-Host $out
    Throw "TEST FAILED"
}


Write-Host ""
Write-Host "            - [FINISH] - PROJECT BUILD"
Write-Host ""
