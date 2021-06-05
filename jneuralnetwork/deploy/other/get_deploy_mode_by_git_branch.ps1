# Получить имя режиме по имени текущей ветки git
# Пример использования скрипта:
#    | $mode = &"./get_deploy_mode_by_git_branch.ps1" | Select-Object -Last 1

$branchName = &"./deploy/git/get_current_branch.ps1" | Select-Object -Last 1

$mode
if ($branchName -eq "master") {
    $mode = "prod"
} elseif ($branchName.StartsWith("feature") -Or $branchName.StartsWith("bugfix")) {
    $mode = "feature"
} else {
    $mode = "dev"
}

Write-Host "            - [INFO] - current git branch = ${branchName}"
Write-Host "            - [INFO] - current mode = ${mode}"

return $mode
