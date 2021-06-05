# Выполнение сборку проекта и установку в локальный репозиторий (без запуска тестов)
# Скрипт запускать из корневой директории проекта
param(
	# optional
	[string] $version
)

Write-Host ""
Write-Host "            - [START] - PROJECT INSTALL"
Write-Host ""

if ($version -eq $null -Or $version -eq '') {
    mvn clean install -DskipTests -Drevision="1.0-SNAPSHOT"
} else {
    mvn clean install -DskipTests -Drevision="${version}"
}

Write-Host ""
Write-Host "            - [FINISH] - PROJECT INSTALL"
Write-Host ""
