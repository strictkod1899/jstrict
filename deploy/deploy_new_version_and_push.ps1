# Полный deploy проекта с обновлением версии в режиме prod и push на удаленный репозиторий
# Скрипт запускать из корневой директории проекта

./deploy/deploy_new_version.ps1

git checkout develop
git merge --no-ff master
git push origin master
git push origin develop
git push origin --tags
