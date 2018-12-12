# Обновить версию используемой зависимости в pom.xml файлах указанных модулей. В зависимости от параметра 'isIncrement' версия зависимости будет либо увеличена на +1, либо уменьшина на -1
# Скрипт запускать из корневой директории проекта

param(
    # requred
    # Название группы зависимости, версия которого будет обновлена
    [string]$dependencyGroupId,
    # requred
    # Название артефакта зависимости, версия которого будет обновлена
    [string]$dependencyArtifactId,
    # required
    # example: .\update_dependency_version_maven.ps1 -modulesPath ./db/pom.xml, ./services/pom.xml
    [string[]]$modulesPath,
    # optional
    # 0 - null; 1 - increment; 2 - decrement
    # default = 1
    [int]$isIncrement
)

Write-Host ""
Write-Host "            - START A VERSION UPDATE FOR DEPENDENCY [${dependencyGroupId} / ${dependencyArtifactId}]"
Write-Host ""

if($dependencyGroupId -eq $null -Or $dependencyGroupId -eq ''){
	throw "param 'dependencyGroupId' is NULL"
}

if($dependencyArtifactId -eq $null -Or $dependencyArtifactId -eq ''){
	throw "param 'dependencyArtifactId' is NULL"
}

if($modulesPath -eq $null -Or $modulesPath.length -lt 1){
	throw "param 'modulesPath' is NULL"
}

foreach($modulePath in $modulesPath){
    if(!(Test-Path -Path "${modulePath}")){
        throw "file not found [${modulePath}] from param 'modulesPath'"
    }
}

$xmlNodePath = "//*/ns:dependencies/ns:dependency"

try{
    foreach($modulePath in $modulesPath){
        [xml]$xmlContent = Get-Content $modulePath
        $namespaceManager = New-Object Xml.XmlNamespaceManager($xmlContent.NameTable)
        $namespace = $xmlContent.DocumentElement.NamespaceURI
        Write-Host "[INFO]: obtaned a namespace '${namespace}' from xml-file '${modulePath}'"
        $namespaceManager.AddNamespace('ns', $namespace)
        $dependencies = $xmlContent.SelectNodes("${xmlNodePath}", $namespaceManager)

        if($dependencies -eq $null){
            Write-Warning "[WARN]: For module '${modulePath}' dependencies not found"
            break;
        }

        foreach($dependency in $dependencies){
            $group = $dependency["groupId"]
            $artifact = $dependency["artifactId"]
            $version = $dependency["version"]
            $groupIdValue = $group.InnerText;
            $artifactIdValue = $artifact.InnerText;

            if($version -eq $null -Or $version -eq ''){
                Write-Warning "[WARN]: for dependency [${groupIdValue} / ${artifactIdValue}] not found element 'version'"
                Write-Warning "[WARN]: complete a version update for dependency [${groupIdValue} / ${artifactIdValue}] into module '${modulePath}'"
                break;
            }

            if($group.InnerText -eq "${dependencyGroupId}" -And $artifact.InnerText -eq "${dependencyArtifactId}"){
                [string]$versionValue = $version.InnerText
                Write-Host "[INFO]: current dependency version [${dependencyGroupId} / ${dependencyArtifactId}] = ${versionValue}"

                if($versionValue -eq $null -Or $versionValue -eq ''){
                    throw "current version from file '${xmlFilePath}' is NULL"
                }

                $newVersionValue = &"./deploy/update_version.ps1" -version $versionValue -isIncrement $isIncrement | Select-Object -Last 1
                Write-Host "[INFO]: new dependency version [${dependencyGroupId} / ${dependencyArtifactId}] = ${newVersionValue}"
                $version.InnerText = "${newVersionValue}"
                $xmlContent.Save($modulePath)
                Write-Host "[INFO]: xml-file '${modulePath}' was saved"
            }
        }
    }

    Write-Host ""
    Write-Host "            - SUCCESS A VERSION UPDATE FOR DEPENDENCY [${dependencyGroupId} / ${dependencyArtifactId}]"
    Write-Host ""
}catch{
    throw "$($_.Exception)"
}