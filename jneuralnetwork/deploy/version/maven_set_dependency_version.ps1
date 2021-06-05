# Обновить версию, используемой зависимости, в pom.xml файле.
# Скрипт запускать из корневой директории проекта.
# Пример использования скрипта:
#    | ./maven_set_dependency_version -groupId "ru.strict" -artifactId "db" -filePath "./app/pom.xml" -newVersion "1.0.15"

param(
    # required
    # Название группы зависимости, версия которого будет обновлена
    [string] $groupId,
    # required
    # Название артефакта зависимости, версия которого будет обновлена
    [string] $artifactId,
    # required
    # Файл, в котором обновляется версия указанной зависимости
    # example: ./app/pom.xml
    [string] $filePath,
    # required
    # Новая версия, которую необходимо установить
    # example: 1.0.15
    [string] $newVersion
)

Write-Host ""
Write-Host "            - [START]- SET NEW VERSION [${newVersion}] FOR DEPENDENCY [${groupId} : ${artifactId}]"
Write-Host ""

if ($groupId -eq $null -Or $groupId -eq '') {
	throw "param 'groupId' is NULL"
}

if ($artifactId -eq $null -Or $artifactId -eq '') {
	throw "param 'artifactId' is NULL"
}

if ($filePath -eq $null -Or $filePath -eq '') {
	throw "xml filePath for update maven version is NULL"
}

if (!(Test-Path -Path "${filePath}")) {
    throw "file not found [${filePath}]"
}

if ($newVersion -eq $null -Or $newVersion -eq '') {
	throw "newVersion for update maven dependency version is NULL"
}

Write-Host "[INFO]: groupId = ${groupId}"
Write-Host "[INFO]: artifactId = ${artifactId}"

$xmlNodePath = "//*/ns:dependencies/ns:dependency"

[xml] $xmlContent = Get-Content $filePath
$namespaceManager = New-Object Xml.XmlNamespaceManager($xmlContent.NameTable)
$namespace = $xmlContent.DocumentElement.NamespaceURI
Write-Host "[DEBUG]: obtaned a namespace '${namespace}' from xml-file '${filePath}'"
$namespaceManager.AddNamespace('ns', $namespace)
$dependencies = $xmlContent.SelectNodes("${xmlNodePath}", $namespaceManager)

if ($dependencies -eq $null) {
    Write-Warning "[WARN]: fail. Dependencies not found into file '${filePath}'"
    break;
}

foreach ($dependency in $dependencies) {
    $groupTag = $dependency["groupId"]
    $artifactTag = $dependency["artifactId"]
    $versionTag = $dependency["version"]

    if ($versionTag -eq $null -Or $versionTag -eq '') {
        Write-Warning "[WARN]: fail. For dependency [${groupId} : ${artifactId}] not found element 'version'"
        break;
    }

    if ($groupTag.InnerText -eq "${groupId}" -And $artifactTag.InnerText -eq "${artifactId}") {
        $versionTag.InnerText = "${newVersion}"
        $xmlContent.Save($filePath)
        
        ./deploy/other/format_xml.ps1 -filePath $filePath

        Write-Host "[DEBUG]: xml-file '${filePath}' was saved"
    } else {
        Write-Warning "[WARN]: skip. Dependency not found [${groupId} : ${artifactId}]"
    }
}

Write-Host ""
Write-Host "            - [FINISH] - SET NEW VERSION [${newVersion}] FOR DEPENDENCY [${groupId} : ${artifactId}]"
Write-Host ""