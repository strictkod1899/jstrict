# Выполнение сборку проекта и установку в локальный репозиторий (без запуска тестов)
# Скрипт запускать из корневой директории проекта
param(
	# required
	[string] $version
)

if ($version -eq $null -Or $version -eq '') {
    Throw "version is NULL"
}

Write-Host ""
Write-Host "            - [START] - PROJECT INSTALL"
Write-Host ""

try {
    mvn clean install -DskipTests -Drevision="${version}"
} catch {
	Throw "[ERROR]: INSTALL FAILED - $($_.Exception)"
}

Write-Host ""
Write-Host "            - [FINISH] - PROJECT INSTALL"
Write-Host ""
