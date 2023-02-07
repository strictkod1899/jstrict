# Получить текущую версию сборки из файла versions, по переданному режиму (mode).
# Если парамметр mode не передать, тогда будет использована версия по-умолчанию.
# Пример использования скрипта:
#    | $buildVersion = &"./get_current_version_by_mode.ps1" -versionsFile "version.properties" -prodMode "prod"
#   -devMode "dev" -featureMode "feature" -mode "dev" | Select-Object -Last 1

param(
    # reuired
    # Путь до файла с версиями
    [string] $versionsFile,
    # reuired
    [string] $prodMode,
    # reuired
    [string] $devMode,
    # reuired
    [string] $featureMode,
	# optional
	[string] $mode
)

if ($versionsFile -eq $null -Or $versionsFile -eq '') {
    Throw "versionsFile is NULL"
}
if ($prodMode -eq $null -Or $prodMode -eq '') {
    Throw "prodMode is NULL"
}
if ($devMode -eq $null -Or $devMode -eq '') {
    Throw "devMode is NULL"
}
if ($featureMode -eq $null -Or $featureMode -eq '') {
    Throw "featureMode is NULL"
}

try {
	$prodVersion = &"./deploy/other/read_from_properties.ps1" -filePath $versionsFile -itemName $prodMode | Select-Object -Last 1
	
    $buildVersion
	if ($mode -eq $prodMode) {
		$buildVersion = $prodVersion
	} elseif ($mode -eq $devMode) {
		$devVersion = &"./deploy/other/read_from_properties.ps1" -filePath $versionsFile -itemName $devMode | Select-Object -Last 1

		$buildVersion = "$prodVersion-DEV-$devVersion"
	} elseif ($mode -eq $featureMode) {
		$featureVersion = &"./deploy/other/read_from_properties.ps1" -filePath $versionsFile -itemName $featureMode | Select-Object -Last 1

		$buildVersion = "$prodVersion-FEATURE-$featureVersion"
	} else {
		$buildVersion="1.0-SNAPSHOT"
	}

    return $buildVersion
} catch {
	Write-Error "[ERROR]: GET CURRENT VERSION FAILED - $($_.Exception)"
	exit 1
}
