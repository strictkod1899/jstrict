# Выполнение публикации собранного проекта: копирование файлов проекта в общую директорию
# Скрипт запускать из корневой директории проекта

param(
	# required
	# example: ./app
	[string]$appProjectRootPath,
	# required
	# example: myapp.jar
	[string]$appFileName
)

Write-Host ""
Write-Host "			- START A PROJECT RELEASE"
Write-Host ""

if($appProjectRootPath -eq $null -Or $appProjectRootPath -eq ''){
	throw "param 'appProjectRootPath' IS NULL"
}
if(!(Test-Path -Path "${appProjectRootPath}")){
	throw "directory does not exists [${appProjectRootPath}]"
}
if($appFileName -eq $null -Or $appFileName -eq ''){
	throw "param 'appFileName' IS NULL"
}

Write-Host ""
Write-Host "[INFO]:			- determine variables"
Write-Host ""

try{
	$app_project_root_path = $appProjectRootPath
	$app_project_target_path = "${app_project_root_path}/target"
	$app_file_path = "${app_project_target_path}/${appFileName}"
	$app_libs_path = "${app_project_target_path}/libs"
	$deploy_folder_root = "./deploy"
	$deploy_folder_target = "${deploy_folder_root}/target"
	$deploy_folder_sources = "${deploy_folder_root}/sources"
	$deploy_folder_libs_path = "${deploy_folder_target}/libs"

	Write-Host "[INFO]: - appFileName = ${appFileName}"
	Write-Host "[INFO]: - app_project_root_path = ${app_project_root_path}"
	Write-Host "[INFO]: - app_project_target_path = ${app_project_target_path}"
	Write-Host "[INFO]: - app_file_path = ${app_file_path}"
	Write-Host "[INFO]: - app_libs_path = ${app_libs_path}"
	Write-Host "[INFO]: - deploy_folder_root = ${deploy_folder_root}"
	Write-Host "[INFO]: - deploy_folder_target = ${deploy_folder_target}"
	Write-Host "[INFO]: - deploy_folder_libs_path = ${deploy_folder_libs_path}"

	Write-Host ""
	Write-Host "[INFO]:			- start deploy folder clean"
	Write-Host ""
	If(Test-Path -Path "${deploy_folder_target}"){
		Remove-Item "${deploy_folder_target}/*" -Recurse -ErrorAction Stop
	} else {
		New-Item $deploy_folder_target -ItemType Directory -ErrorAction Stop
	}

	Write-Host ""
	Write-Host "[INFO]:			- start project copy to deploy folder"
	Write-Host ""
	New-Item $deploy_folder_libs_path -ItemType Directory -ErrorAction Stop
	Copy-Item $app_file_path -Destination $deploy_folder_target
	Copy-Item "${app_libs_path}/*" -Destination "${deploy_folder_libs_path}"
	Copy-Item "${deploy_folder_sources}/*" -Destination $deploy_folder_target

	Write-Host ""
	Write-Host "			- SUCCESS A PROJECT RELEASE"
	Write-Host ""
}catch{
	throw "$($_.Exception)"
}