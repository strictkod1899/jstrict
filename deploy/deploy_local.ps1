# Локальная сборка проекта без обновления версии, взаимодействия с scv и т.д.
# Скрипт запускать из корневой директории проекта

try{
	./deploy/download_dependencies.ps1
} catch {
	Write-Warning ""
	Write-Warning "[WARN]: DOWNLOAD DEPENDENCIES ERROR"
	Write-Warning "[WARN]: $($_.Exception)"
	Write-Warning ""
}

try{
	./deploy/test.ps1
} catch {
	Write-Error ""
	Write-Error "[ERROR]: TESTS ERROR"
	Write-Error "[ERROR]: $($_.Exception)"
	Write-Error ""
	exit 1
}

try{
	./deploy/build.ps1
} catch {
	Write-Error ""
	Write-Error "[ERROR]: BUILD ERROR"
	Write-Error "[ERROR]: $($_.Exception)"
	Write-Error ""
	exit 1
}

try{
	#./deploy/release.ps1 -appProjectRootPath
} catch {
	Write-Error ""
	Write-Error "[ERROR]: RELEASE ERROR"
	Write-Error "[ERROR]: $($_.Exception)"
	Write-Error ""
	exit 1
}
