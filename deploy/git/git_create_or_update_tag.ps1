# Создать тег. Если тег уже существует, то он будет удален и создан на текущем коммите
# Скрипт запускать из корневой директории проекта
# Пример использования скрипта:
#    | ./git_create_or_update_tag.ps1 -tag "0.1.1"

param(
    # required
    [string] $tag
)

Write-Host ""
Write-Host "            - [START] - GIT CREATE OR UPDATE TAG"
Write-Host ""

if ($tag -eq $null -Or $tag -eq '') {
    throw "tag is NULL"
}

$existsTag = git show-ref --tags "${tag}"
if ($existsTag -cne $null -And $existsTag -cne '') {
    Write-Host "[DEBUG]: tag '${tag}' is exists. This tag will be remove and recreate"
    git tag -d $tag
}

git tag $tag

Write-Host ""
Write-Host "            - [FINISH] - GIT CREATE OR UPDATE TAG"
Write-Host ""
