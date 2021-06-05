# Сборка проекта без запуска тестов (обновление версии и push в ветку git не выполняется)
# Скрипт запускать из корневой директории проекта

$mode = &"./deploy/other/get_deploy_mode_by_git_branch.ps1" | Select-Object -Last 1
./deploy/deploy.ps1 -mode "${mode}" -skipGit $True -skipTests $True -skipUpdateVersion $True
