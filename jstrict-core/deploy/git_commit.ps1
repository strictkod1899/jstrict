# Commit всех изменений с использованием указанной ветки и push в репозиторий
# Скрипт запускать из корневой директории проекта

param(
    # required
    [string]$branch,
    # optional
    # default = 'update files'
    [string]$message,
    # optional
    [string]$username,
    # optional
    # example: github.com
    [string]$gitProvider,
    # optional
    # example: myrepository
    [string]$repositoryName
)

Write-Host ""
Write-Host "            - START A GIT COMMIT TO BRANCH [${branch}]"
Write-Host ""

if($branch -eq $null -Or $branch -eq ''){
    throw "branch is null"
}

if($message -eq $null -Or $message -eq ''){
    Write-Warning "[WARN]: param 'message' is NULL, so will be using a default value"
    $message = 'update files'
}
Write-Host "[INFO]: message = ${message}"

try{
    Write-Host "[INFO]: start checkout to branch [${branch}]"
    git checkout $branch
    $current_branch = git rev-parse --abbrev-ref HEAD
    if($current_branch -ne $branch){
        throw "alert branch checkout"
    }

    git add *
    
    git commit -m \"$message\"
    if($username -eq $null -Or $username -eq '' -Or $gitProvider -eq $null -Or $gitProvider -eq '' -Or $repositoryName -eq $null -Or $repositoryName -eq ''){
        Write-Host "[INFO]: start push to origin"
        git push origin $branch
    } else {
        Write-Host "[INFO]: start push to repository [${repositoryName}] by user [${username}]"
        git push git@${gitProvider}:${username}/${repositoryName}.git $branch
    }

    Write-Host ""
    Write-Host "            - SUCCESS A GIT COMMIT TO BRANCH [${branch}]"
    Write-Host ""
}catch{
    throw "$($_.Exception)"
}