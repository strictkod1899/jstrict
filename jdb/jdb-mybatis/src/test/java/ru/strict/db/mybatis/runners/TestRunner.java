package ru.strict.db.mybatis.runners;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import ru.strict.db.core.common.ConnectionDbInfo;
import ru.strict.db.core.repositories.IRepositoryExtension;
import ru.strict.db.mybatis.*;
import ru.strict.db.mybatis.connection.CreateConnectionByMybatis;
import ru.strict.db.mybatis.connection.MybatisConnectionInfo;
import ru.strict.db.mybatis.mappers.sql.MapperSqlCity;
import ru.strict.db.mybatis.mappers.sql.MapperSqlCountry;
import ru.strict.db.mybatis.mappers.sql.MapperSqlRoleuser;
import ru.strict.db.mybatis.mappers.sql.MapperSqlUser;
import ru.strict.utils.UtilResources;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        TestConnection.class,
        TestRepositoryCountry.class,
        TestRepositoryCity.class,
        TestRepositoryRoleuser.class//,
        //TestRepositoryUser.class
})
public class TestRunner {

    private static final String DB_INTEGER_FILE_PATH = "testdb_integer.sqlite";
    private static final String DB_UUID_FILE_PATH = "testdb_uuid.sqlite";

    private static File dbIntegerFile;
    private static File dbUuidFile;
    public static CreateConnectionByMybatis createConnectionForDbInteger;
    public static CreateConnectionByMybatis createConnectionForDbUuid;
    public static List<IRepositoryExtension> repositories;

    @BeforeClass
    public static void prepare(){
        dbIntegerFile = UtilResources.getResource(DB_INTEGER_FILE_PATH);
        dbUuidFile = UtilResources.getResource(DB_UUID_FILE_PATH);
        if(dbIntegerFile == null){
            throw new NullPointerException("not found integer-db file");
        }
        if(dbUuidFile == null){
            throw new NullPointerException("not found uuid-db file");
        }

        MybatisConnectionInfo connectionInfoForDbInteger = new MybatisConnectionInfo(
                ConnectionDbInfo.SQLITE.getDriver(),
                ConnectionDbInfo.SQLITE.getUrl() + TestRunner.dbIntegerFile.getAbsolutePath(),
                "",
                "");
        MybatisConnectionInfo connectionInfoForDbUuid = new MybatisConnectionInfo(
                ConnectionDbInfo.SQLITE.getDriver(),
                ConnectionDbInfo.SQLITE.getUrl() + TestRunner.dbUuidFile.getAbsolutePath(),
                "",
                "");


        connectionInfoForDbInteger.addMapper(MapperSqlCountry.class);
        connectionInfoForDbInteger.addMapper(MapperSqlCity.class);
        connectionInfoForDbInteger.addMapper(MapperSqlRoleuser.class);
        connectionInfoForDbInteger.addMapper(MapperSqlUser.class);
        connectionInfoForDbUuid.addMapper(MapperSqlCountry.class);
        connectionInfoForDbUuid.addMapper(MapperSqlCity.class);
        connectionInfoForDbUuid.addMapper(MapperSqlRoleuser.class);
        connectionInfoForDbUuid.addMapper(MapperSqlUser.class);

        createConnectionForDbInteger = new CreateConnectionByMybatis(connectionInfoForDbInteger);
        createConnectionForDbUuid = new CreateConnectionByMybatis(connectionInfoForDbUuid);

        repositories = new ArrayList<>();
    }

    @AfterClass
    public static void post(){
        postProcess();
    }

    public static void postProcess(){
        for(IRepositoryExtension repository : repositories){
            repository.executeSql("DELETE FROM " + repository.getTableName());
        }
        repositories.clear();
    }
}
