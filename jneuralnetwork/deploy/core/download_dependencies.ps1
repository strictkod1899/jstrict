# Скачивание зависимостей проекта
# Скрипт запускать из корневой директории проекта
param(
	# optional
	[string] $version
)

if ($version -eq $null -Or $version -eq '') {
    Throw "version is NULL"
}

Write-Host ""
Write-Host "            - [START] - DOWNLOAD DEPENDENCIES"
Write-Host ""

$output = mvn dependency:copy-dependencies -Drevision="${version}" | Out-String

if (!$output.contains("BUILD SUCCESS")) {
    Write-Host $output
	Write-Warning ""
    Write-Warning "[WARN]: DOWNLOAD DEPENDENCIES FAILED"
	Write-Warning ""
}

Write-Host ""
Write-Host "            - [FINISH] - DOWNLOAD DEPENDENCIES"
Write-Host ""
