# Получить имя текущей ветки git
# Пример использования скрипта:
#    | $branchName = &"./get_current_branch.ps1" | Select-Object -Last 1

return git rev-parse --abbrev-ref HEAD