# Выполнение публикации собранного проекта: копирование файлов проекта в общую директорию
# Скрипт запускать из корневой директории проекта
# Пример использования скрипта:
#    | ./deploy/release.ps1 -appProjectRootPath "./app" -appFileName "myapp.jar"

param(
	# required
	# example: ./app
	[string]$appProjectRootPath,
	# required
	# example: myapp.jar
	[string]$appFileName,
	# optional
	# Копировать библиотеки проекта
	# default = false
	[bool]$isCopyLibs,
	# optional
	# Название каталога с библиотекаими проекта
	# default = libs
	[string]$libsFolderName
)

Write-Host ""
Write-Host "			- THE PROJECT RELEASE HAS STARTED"
Write-Host ""

if($appProjectRootPath -eq $null -Or $appProjectRootPath -eq ''){
	throw "param 'appProjectRootPath' is NULL"
}
if(!(Test-Path -Path "${appProjectRootPath}")){
	throw "directory does not exists [${appProjectRootPath}]"
}
if($appFileName -eq $null -Or $appFileName -eq ''){
	throw "param 'appFileName' is NULL"
}
if($libsFolderName -eq $null -Or $libsFolderName -eq ''){
    Write-Warning "[WARN]: param 'libsFolderName' is NULL, so will be using a default value"
    $libsFolderName = "libs"
}

Write-Host ""
Write-Host "[INFO]:			- determine variables"
Write-Host ""

try{
	$appProjectTargetPath = "${appProjectRootPath}/target"
	$appFilePath = "${appProjectTargetPath}/${appFileName}"
	$appLibsPath = "${appProjectTargetPath}/${libsFolderName}"
	$deployFolderRoot = "./deploy"
	$deployFolderTarget = "${deployFolderRoot}/target"
	$deployFolderSources = "${deployFolderRoot}/sources"
	$deployFolderLibs = "${deployFolderTarget}/${libsFolderName}"

	Write-Host "[INFO]: - appFileName = ${appFileName}"
	Write-Host "[INFO]: - appProjectRootPath = ${appProjectRootPath}"
	Write-Host "[INFO]: - appProjectTargetPath = ${appProjectTargetPath}"
	Write-Host "[INFO]: - appFilePath = ${appFilePath}"
	Write-Host "[INFO]: - appLibsPath = ${appLibsPath}"
	Write-Host "[INFO]: - deployFolderRoot = ${deployFolderRoot}"
	Write-Host "[INFO]: - deployFolderTarget = ${deployFolderTarget}"
	Write-Host "[INFO]: - deployFolderLibs = ${deployFolderLibs}"
	Write-Host "[INFO]: - isCopyLibs = ${isCopyLibs}"

	Write-Host ""
	Write-Host "[INFO]:			- deploy folder clean has started"
	Write-Host ""
	If(Test-Path -Path "${deployFolderTarget}"){
		Remove-Item "${deployFolderTarget}/*" -Recurse -ErrorAction Stop
	} else {
		New-Item $deployFolderTarget -ItemType Directory -ErrorAction Stop
	}

	Write-Host ""
	Write-Host "[INFO]:			- project copy in deploy folder has started"
	Write-Host ""
	Copy-Item $appFilePath -Destination $deployFolderTarget -ErrorAction Stop
	If((Test-Path -Path "${appLibsPath}")){
		if($isCopyLibs -eq $True){
			New-Item $deployFolderLibs -ItemType Directory -ErrorAction Stop
			Copy-Item "${appLibsPath}/*" -Destination "${deployFolderLibs}"
		}
	} else {
		Write-Warning "[WARN]: libs folder [${appLibsPath}] does not exists"
	}

	If(Test-Path -Path "${deployFolderSources}"){
		Copy-Item "${deployFolderSources}/*" -Destination $deployFolderTarget
	} else {
		Write-Warning "[WARN]: sources folder [${deployFolderSources}] does not exists"
	}

	Write-Host ""
	Write-Host "			- THE PROJECT RELEASE HAS COMPLETED"
	Write-Host ""
}catch{
	throw "$($_.Exception)"
}
