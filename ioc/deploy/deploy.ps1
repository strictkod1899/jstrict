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
$newVersion

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

try {
	./deploy/core/download_dependencies.ps1
} catch {
	Write-Warning ""
	Write-Warning "[WARN]: DOWNLOAD DEPENDENCIES ERROR"
	Write-Warning "[WARN]: $($_.Exception)"
	Write-Warning ""
}

if ($skipTests -eq $True) {
	Write-Warning ""
	Write-Warning "[WARN]: PROJECT BUILD WITHOUT TEST"
	Write-Warning ""
} else {
	./deploy/core/test.ps1
}

if ($skipUpdateVersion -eq $True) {
	Write-Warning ""
	Write-Warning "[WARN]: PROJECT BUILD WITHOUT UPDATE VERSION"
	Write-Warning ""

	$currentVersion = &"./deploy/other/read_from_properties.ps1" -filePath $VERSIONS_FILE -itemName $mode | Select-Object -Last 1
	$newVersion = $currentVersion
} else {
	
	if ($mode -eq $null -Or $mode -eq '') {
		Write-Error "[ERROR]: DEPLOY ERROR - mode for update version is NULL"
		exit 1
	}

	# create new newVersion
	try {
		$prevModeVersion = &"./deploy/other/read_from_properties.ps1" -filePath $VERSIONS_FILE -itemName $mode | Select-Object -Last 1
		$newModeVersion = &"./deploy/version/increment_version.ps1" -version $prevModeVersion | Select-Object -Last 1
		./deploy/other/set_properties_value.ps1 -filePath $VERSIONS_FILE -itemName $mode -value $newModeVersion
		
		$prodVersion = &"./deploy/other/read_from_properties.ps1" -filePath $VERSIONS_FILE -itemName $PROD_MODE | Select-Object -Last 1
			
		if ($mode -eq $PROD_MODE) {
			$newVersion = $prodVersion
		} elseif ($mode -eq $DEV_MODE) {
			$devVersion = &"./deploy/other/read_from_properties.ps1" -filePath $VERSIONS_FILE -itemName $DEV_MODE | Select-Object -Last 1

			$newVersion = "$prodVersion-SNAPSHOT-$devVersion"
		} elseif ($mode -eq $FEATURE_MODE) {
			$featureVersion = &"./deploy/other/read_from_properties.ps1" -filePath $VERSIONS_FILE -itemName $FEATURE_MODE | Select-Object -Last 1

			$newVersion = "$prodVersion-FEATURE-$featureVersion"
		}
	} catch {
		Write-Error "[ERROR]: CREATE NEW VERSION ERROR - $($_.Exception)"
		exit 1
	}
}

try {
	if ($skipGit -eq $True) {
		Write-Warning ""
		Write-Warning "[WARN]: GIT SKIP"
		Write-Warning ""
	} else {
		./deploy/git/git_commit.ps1 -branch "${branch}" -message "update version"

		if ($mode -eq $PROD_MODE) {
			git tag $newVersion
			git push origin $newVersion
		}
	}
} catch {
	Write-Error "[ERROR]: GIT COMMIT ERROR - $($_.Exception)"
	exit 1
}

try {
	./deploy/core/install.ps1 -version "${newVersion}"
} catch {
	Write-Error "[ERROR]: INSTALL ERROR - $($_.Exception)"
	exit 1
}
