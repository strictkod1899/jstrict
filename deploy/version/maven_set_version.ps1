# Заменить версию проекта на переданную ($newVersion).
# Скрипт запускать из корневой директории проекта.
# Пример использования скрипта:
#    | ./maven_set_version.ps1 -filePath "./app/pom.xml" -newVersion "1.0.15"

param(
    # required
    # example: ./app/pom.xml
    [string] $filePath,
    # required
    # Новая версия, которую необходимо установить
    # example: 1.0.15
    [string] $newVersion,
    # optional
    # Структура файла для поиска тега version
    # default = "/ns:project/ns:version"
    [string] $xmlNodePath
)

Write-Host ""
Write-Host "            - [START] - SET NEW VERSION [${newVersion}] INTO MAVEN-FILE '${filePath}'"
Write-Host ""

if ($filePath -eq $null -Or $filePath -eq '') {
	throw "xml filePath for update maven version is NULL"
}

if (!(Test-Path -Path "${filePath}")) {
    throw "file not found [${filePath}]"
}

if ($newVersion -eq $null -Or $newVersion -eq '') {
	throw "newVersion for update maven version is NULL"
}

if ($xmlNodePath -eq $null -Or $xmlNodePath -eq '') {
    Write-Host "[INFO]: param 'xmlNodePath' is NULL, so will be using a default value"
    $xmlNodePath = "/ns:project/ns:version"
}

Write-Host "[INFO]: xmlNodePath = '${xmlNodePath}'"
Write-Host "[INFO]: new version = ${newVersion}"

[xml] $xmlContent = Get-Content $filePath
$namespaceManager = New-Object Xml.XmlNamespaceManager($xmlContent.NameTable)
$namespace = $xmlContent.DocumentElement.NamespaceURI
Write-Host "[DEBUG]: obtaned the namespace '${namespace}' from xml-file '${filePath}'"
$namespaceManager.AddNamespace('ns', $namespace)
$versionTag = $xmlContent.SelectSingleNode("${xmlNodePath}", $namespaceManager)

if ($versionTag -eq $null) {
    throw "not found the versionTag '${xmlNodePath}' in the file '${filePath}'"
}

[string] $currentVersion = $versionTag.InnerText
Write-Host "[DEBUG]: current version = ${currentVersion}"

if ($currentVersion -eq $null -Or $currentVersion -eq '') {
    throw "current version from file '${filePath}' is NULL"
}

$versionTag.InnerText = "${newVersion}"
$xmlContent.Save($filePath)

./deploy/other/format_xml.ps1 -filePath $filePath

Write-Host "[DEBUG]: new version had saved to xml-file '${filePath}'"

Write-Host ""
Write-Host "            - [FINISH] - SET NEW VERSION [${newVersion}] INTO MAVEN-FILE '${filePath}'"
Write-Host ""