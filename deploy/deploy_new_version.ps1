cd ./jstrict-core
./deploy/deploy_new_version.ps1

cd ../jneuralnetwork
./deploy/deploy_new_version.ps1

cd ../ioc
./deploy/deploy_new_version.ps1

cd ../jdb
./deploy/deploy_new_version.ps1

cd ..


# constants
$PROD_MODE = "prod"
$DEV_MODE = "dev"
$FEATURE_MODE = "feature"

$VERSIONS_FILE = "./deploy/versions.properties"

# commit new version
$mode = &"./deploy/other/get_deploy_mode_by_git_branch.ps1" | Select-Object -Last 1
$buildVersion = &"./deploy/version/get_current_version_by_mode.ps1" -versionsFile "${VERSIONS_FILE}" -prodMode "${PROD_MODE}" -devMode "${DEV_MODE}" -featureMode "${FEATURE_MODE}" -mode "${mode}" | Select-Object -Last 1

./deploy/git/git_commit.ps1 -message "chore (deploy): update version ${buildVersion}"
	
if ($mode -eq $PROD_MODE) {
	./deploy/git/git_create_or_update_tag.ps1 -tag "${buildVersion}"
}
