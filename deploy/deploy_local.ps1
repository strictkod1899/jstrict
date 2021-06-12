cd ./jstrict-parent
mvn clean install

cd ../jstrict-core
./deploy/deploy_local.ps1

cd ../jneuralnetwork
./deploy/deploy_local.ps1

cd ../ioc
./deploy/deploy_local.ps1

cd ../jdb
./deploy/deploy_local.ps1
