# Выполнение сборки проекта
# Скрипт запускать из корневой директории проекта
param(
	# optional
	[string] $version
)

if ($version -eq $null -Or $version -eq '') {
    Throw "version is NULL"
}

Write-Host ""
Write-Host "            - [START] - PROJECT TEST"
Write-Host ""

$output = mvn clean test -Drevision="${version}" | Out-String

if (!$output.contains("BUILD SUCCESS")) {
    Write-Host $output
    Throw "[ERROR]: TEST FAILED"
}

Write-Host ""
Write-Host "            - [FINISH] - PROJECT TEST"
Write-Host ""
