# Commit всех изменений
# Скрипт запускать из корневой директории проекта
# Пример использования скрипта:
#    | ./git_commit.ps1 -message "update version"

param(
    # Required
    # Сообщение коммита
    [string] $message
)

Write-Host ""
Write-Host "            - [START] - GIT COMMIT"
Write-Host ""

if ($message -eq $null -Or $message -eq '') {
    throw "git message is NULL"
}

Write-Host "[INFO]: message = ${message}"

git add *
git commit -m "${message}"

Write-Host ""
Write-Host "            - [FINISH] - GIT COMMIT"
Write-Host ""