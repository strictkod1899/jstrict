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
import ru.strict.db.mybatis.mappers.sql.*;
import ru.strict.utils.UtilResources;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        TestConnection.class,
        TestRepositoryCountry.class,
        TestRepositoryCity.class,
        TestRepositoryRoleuser.class,
        TestRepositoryUser.class,
        TestRepositoryUserOnRole.class,
        TestRepositoryProfile.class
})
public class TestRunner {

    private static final String DB_INTEGER_FILE_PATH = "testdb_integer.sqlite";
    private static final String DB_UUID_FILE_PATH = "testdb_uuid.sqlite";

    private static File DB_INTEGER_FILE;
    private static File DB_UUID_FILE;
    public static CreateConnectionByMybatis CREATE_DB_INTEGER_CONNECTION;
    public static CreateConnectionByMybatis CREATE_DB_UUID_CONNECTION;
    public static List<IRepositoryExtension> repositories;

    @BeforeClass
    public static void prepare(){
        DB_INTEGER_FILE = UtilResources.getResource(DB_INTEGER_FILE_PATH);
        DB_UUID_FILE = UtilResources.getResource(DB_UUID_FILE_PATH);
        if(DB_INTEGER_FILE == null){
            throw new NullPointerException("not found integer-db file");
        }
        if(DB_UUID_FILE == null){
            throw new NullPointerException("not found uuid-db file");
        }

        MybatisConnectionInfo connectionInfoForDbInteger = new MybatisConnectionInfo(
                ConnectionDbInfo.SQLITE.getDriver(),
                ConnectionDbInfo.SQLITE.getUrl() + TestRunner.DB_INTEGER_FILE.getAbsolutePath(),
                "",
                "");
        MybatisConnectionInfo connectionInfoForDbUuid = new MybatisConnectionInfo(
                ConnectionDbInfo.SQLITE.getDriver(),
                ConnectionDbInfo.SQLITE.getUrl() + TestRunner.DB_UUID_FILE.getAbsolutePath(),
                "",
                "");


        connectionInfoForDbInteger.addMapper(MapperSqlCountry.class);
        connectionInfoForDbInteger.addMapper(MapperSqlCity.class);
        connectionInfoForDbInteger.addMapper(MapperSqlRoleuser.class);
        connectionInfoForDbInteger.addMapper(MapperSqlUser.class);
        connectionInfoForDbInteger.addMapper(MapperSqlFileStorage.class);
        connectionInfoForDbInteger.addMapper(MapperSqlJWTToken.class);
        connectionInfoForDbInteger.addMapper(MapperSqlProfile.class);
        connectionInfoForDbInteger.addMapper(MapperSqlProfileInfo.class);
        connectionInfoForDbInteger.addMapper(MapperSqlUserOnRole.class);
        connectionInfoForDbUuid.addMapper(MapperSqlCountry.class);
        connectionInfoForDbUuid.addMapper(MapperSqlCity.class);
        connectionInfoForDbUuid.addMapper(MapperSqlRoleuser.class);
        connectionInfoForDbUuid.addMapper(MapperSqlUser.class);
        connectionInfoForDbUuid.addMapper(MapperSqlFileStorage.class);
        connectionInfoForDbUuid.addMapper(MapperSqlJWTToken.class);
        connectionInfoForDbUuid.addMapper(MapperSqlProfile.class);
        connectionInfoForDbUuid.addMapper(MapperSqlProfileInfo.class);
        connectionInfoForDbUuid.addMapper(MapperSqlUserOnRole.class);

        CREATE_DB_INTEGER_CONNECTION = new CreateConnectionByMybatis(connectionInfoForDbInteger);
        CREATE_DB_UUID_CONNECTION = new CreateConnectionByMybatis(connectionInfoForDbUuid);

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
