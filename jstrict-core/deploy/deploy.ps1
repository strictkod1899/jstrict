# Выполнение полного развертывания проекта
# Скрипт запускать из корневой директории проекта

param(
	# required
	[string]$branch
)

# modules without root pom.xml
$childModulesPath = "./joffice/pom.xml", "./installer/pom.xml", "./jmq/pom.xml", "./jmq/jmq-rabbit/pom.xml", "./jmq/jmq-kafka/pom.xml", "./jutils/pom.xml", "./ioc/pom.xml", "./jfile/pom.xml", "./jpatterns/pom.xml", "./jfx/pom.xml", "./jswing/pom.xml", "./jneuralnetwork/pom.xml", "./jdb/pom.xml", "./jdb/jdb-models/pom.xml", "./jdb/jdb-core/pom.xml", "./jdb/jdb-jdbc/pom.xml", "./jdb/jdb-spring/pom.xml", "./jdb/jdb-hibernate/pom.xml", "./jdb/jdb-mybatis/pom.xml"
$modulesPath = "./pom.xml", "./joffice/pom.xml", "./installer/pom.xml", "./jmq/pom.xml", "./jmq/jmq-rabbit/pom.xml", "./jmq/jmq-kafka/pom.xml", "./jutils/pom.xml", "./ioc/pom.xml", "./jfile/pom.xml", "./jpatterns/pom.xml", "./jfx/pom.xml", "./jswing/pom.xml", "./jneuralnetwork/pom.xml", "./jdb/pom.xml", "./jdb/jdb-models/pom.xml", "./jdb/jdb-core/pom.xml", "./jdb/jdb-jdbc/pom.xml", "./jdb/jdb-spring/pom.xml", "./jdb/jdb-hibernate/pom.xml", "./jdb/jdb-mybatis/pom.xml"

if($branch -eq $null -Or $branch -eq ''){
	Write-Error ""
	Write-Error "[ERROR]: DEPLOY ERROR"
	Write-Error "[ERROR]: Branch for deploy is NULL"
	Write-Error ""
	exit 1
}

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
	# Update versions
	./deploy/update_versions_maven.ps1 -modulesPath $modulesPath
	
	# Update parent versions
	./deploy/update_parent_version_maven.ps1 -modulesPath $childModulesPath

	# Update dependency versions
	./deploy/update_dependency_version_maven -dependencyGroupId "ru.strict" -dependencyArtifactId "jutils" -modulesPath $modulesPath
	./deploy/update_dependency_version_maven -dependencyGroupId "ru.strict" -dependencyArtifactId "ioc" -modulesPath $modulesPath
	./deploy/update_dependency_version_maven -dependencyGroupId "ru.strict" -dependencyArtifactId "jfile" -modulesPath $modulesPath
	./deploy/update_dependency_version_maven -dependencyGroupId "ru.strict" -dependencyArtifactId "jpatterns" -modulesPath $modulesPath
	./deploy/update_dependency_version_maven -dependencyGroupId "ru.strict" -dependencyArtifactId "jfx" -modulesPath $modulesPath
	./deploy/update_dependency_version_maven -dependencyGroupId "ru.strict" -dependencyArtifactId "jswing" -modulesPath $modulesPath
	./deploy/update_dependency_version_maven -dependencyGroupId "ru.strict" -dependencyArtifactId "jneuralnetwork" -modulesPath $modulesPath
    ./deploy/update_dependency_version_maven -dependencyGroupId "ru.strict" -dependencyArtifactId "jdb-models" -modulesPath $modulesPath
	./deploy/update_dependency_version_maven -dependencyGroupId "ru.strict" -dependencyArtifactId "jdb-core" -modulesPath $modulesPath
	./deploy/update_dependency_version_maven -dependencyGroupId "ru.strict" -dependencyArtifactId "jdb-jdbc" -modulesPath $modulesPath
	./deploy/update_dependency_version_maven -dependencyGroupId "ru.strict" -dependencyArtifactId "jdb-spring" -modulesPath $modulesPath
	./deploy/update_dependency_version_maven -dependencyGroupId "ru.strict" -dependencyArtifactId "jdb-hibernate" -modulesPath $modulesPath
	./deploy/update_dependency_version_maven -dependencyGroupId "ru.strict" -dependencyArtifactId "jdb-mybatis" -modulesPath $modulesPath
	./deploy/update_dependency_version_maven -dependencyGroupId "ru.strict" -dependencyArtifactId "installer" -modulesPath $modulesPath
	./deploy/update_dependency_version_maven -dependencyGroupId "ru.strict" -dependencyArtifactId "joffice" -modulesPath $modulesPath
	./deploy/update_dependency_version_maven -dependencyGroupId "ru.strict" -dependencyArtifactId "jmq-rabbit" -modulesPath $modulesPath
	./deploy/update_dependency_version_maven -dependencyGroupId "ru.strict" -dependencyArtifactId "jmq-kafka" -modulesPath $modulesPath
} catch {
	Write-Error ""
	Write-Error "[ERROR]: UPDATE VERSION ERROR"
	Write-Error "[ERROR]: $($_.Exception)"
	Write-Error ""
	exit 1
}

try{
	./deploy/git_commit.ps1 -branch $branch -message "updated a version"
} catch {
	Write-Error ""
	Write-Error "[ERROR]: GIT COMMIT ERROR"
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
	#./deploy/release.ps1
} catch {
	Write-Error ""
	Write-Error "[ERROR]: RELEASE ERROR"
	Write-Error "[ERROR]: $($_.Exception)"
	Write-Error ""
	exit 1
}
