# Полный deploy проекта с обновлением версии и выполнением push в указанную ветку
# Скрипт запускать из корневой директории проекта
# Пример использования скрипта:
#    | ./deploy/deploy.ps1 -mode "dev" -branch "develop"

param(
	# optional
	# Режим: prod, dev, feature
	[string] $mode,
	# optional
	# git-ветка, в которую будет произведен push
	[string] $branch,
	# optional
	# Пропустить взаимодействие с git
	[Boolean] $skipGit,
	# optional
	# Пропустить тестирование проекта
	[Boolean] $skipTests,
	# optional
	# Пропустить обновление версии
	[Boolean] $skipUpdateVersion
)

# constants
$PROD_MODE = "prod"
$DEV_MODE = "dev"
$FEATURE_MODE = "feature"

$VERSIONS_FILE = "./deploy/versions.properties"

# declare
$currentVersion
$buildVersion

if ($skipGit -eq $null) {
	$skipGit = $False
}

if ($skipTests -eq $null) {
	$skipTests = $False
}

if ($skipUpdateVersion -eq $null) {
	$skipUpdateVersion = $False
}

if ($mode -cne $null -And $mode -cne '') {
	if ($mode -cne $PROD_MODE -And $mode -cne $DEV_MODE -And $mode -cne $FEATURE_MODE) {
		Write-Error "[ERROR]: DEPLOY ERROR - Unsupported mode [$mode]"
		exit 1
	}
}

# create new version
if ($skipUpdateVersion -eq $True) {
	Write-Warning ""
	Write-Warning "[WARN]: PROJECT BUILD WITHOUT UPDATE VERSION"
	Write-Warning ""
} else {
	if ($mode -eq $null -Or $mode -eq '') {
		Throw "mode is NULL"
	}

	$prevModeVersion = &"./deploy/other/read_from_properties.ps1" -filePath $VERSIONS_FILE -itemName $mode | Select-Object -Last 1
	$newModeVersion = &"./deploy/version/increment_version.ps1" -version $prevModeVersion | Select-Object -Last 1
	./deploy/other/set_properties_value.ps1 -filePath $VERSIONS_FILE -itemName $mode -value $newModeVersion
}

# get version
$buildVersion = &"./deploy/version/get_current_version_by_mode.ps1" -versionsFile "${VERSIONS_FILE}" -prodMode "${PROD_MODE}" -devMode "${DEV_MODE}" -featureMode "${FEATURE_MODE}" -mode "${mode}" | Select-Object -Last 1

./deploy/core/download_dependencies.ps1 -version "${buildVersion}"

if ($skipTests -eq $True) {
	Write-Warning ""
	Write-Warning "[WARN]: PROJECT BUILD WITHOUT TEST"
	Write-Warning ""
} else {
	./deploy/core/test.ps1 -version "${buildVersion}"
}

try {
	if ($skipGit -eq $True) {
		Write-Warning ""
		Write-Warning "[WARN]: GIT SKIP"
		Write-Warning ""
	} else {
		./deploy/git/git_commit.ps1 -branch "${branch}" -message "update version"

		if ($mode -eq $PROD_MODE) {
			git tag $buildVersion
			git push origin $buildVersion
		}
	}
} catch {
	Write-Error "[ERROR]: GIT COMMIT ERROR - $($_.Exception)"
	exit 1
}

./deploy/core/install.ps1 -version "${buildVersion}"
