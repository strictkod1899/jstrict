# Заменить версию в секции parent на переданную ($newVersion)
# Скрипт запускать из корневой директории проекта
# Пример использования скрипта:
#    | ./maven_set_parent_version.ps1 -filePath "./app/pom.xml" -newVersion "1.0.15"

param(
    # required
    # example: ./app/pom.xml
    [string] $filePath,
    # required
    # Новая версия, которую необходимо установить
    # example: 1.0.15
    [string] $newVersion
)

Write-Host ""
Write-Host "            - [START] - SET NEW PARENT VERSION [${newVersion}] INTO FILE '${filePath}'"
Write-Host ""

if ($filePath -eq $null -Or $filePath -eq '') {
	throw "xml filePath for update maven version is NULL"
}

if (!(Test-Path -Path "${filePath}")) {
    throw "file not found [${filePath}]"
}

if ($newVersion -eq $null -Or $newVersion -eq '') {
	throw "newVersion for update maven parent version is NULL"
}

./deploy/version/maven_set_version.ps1 -filePath $filePath -newVersion $newVersion -xmlNodePath "/ns:project/ns:parent/ns:version"
    
Write-Host ""
Write-Host "            - [FINISH] - SET NEW PARENT VERSION [${newVersion}] INTO FILE '${filePath}'"
Write-Host ""