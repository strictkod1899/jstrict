package ru.strict.db.mybatis.tests.runners;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import ru.strict.db.core.common.ConnectionDbInfo;
import ru.strict.db.core.repositories.IRepositoryExtension;
import ru.strict.db.mybatis.connection.CreateConnectionByMybatis;
import ru.strict.db.mybatis.connection.MybatisConnectionInfo;
import ru.strict.db.mybatis.mappers.sql.*;
import ru.strict.db.mybatis.tests.*;
import ru.strict.utils.UtilResources;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        TestConnection.class,
        TestRepositoryCountry.class,
        TestRepositoryCity.class,
        TestRepositoryRole.class,
        TestRepositoryUser.class,
        TestRepositoryUserOnRole.class,
        TestRepositoryProfile.class,
        TestRepositoryProfileDetails.class,
        TestRepositoryFileStorage.class,
        TestRepositoryJWTToken.class,
        TestRepositoryPermissionOnRole.class
})
public class TestRunner {

    private static final String DB_INTEGER_FILE_PATH = "testdb_integer.sqlite";
    private static final String DB_UUID_FILE_PATH = "testdb_uuid.sqlite";

    public static File DB_INTEGER_FILE;
    public static File DB_UUID_FILE;
    public static CreateConnectionByMybatis CREATE_DB_INTEGER_CONNECTION;
    public static CreateConnectionByMybatis CREATE_DB_UUID_CONNECTION;
    public static List<IRepositoryExtension> repositoriesForClearDb;

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
                ConnectionDbInfo.SQLITE.getUrl() + DB_INTEGER_FILE.getAbsolutePath(),
                "",
                "");
        MybatisConnectionInfo connectionInfoForDbUuid = new MybatisConnectionInfo(
                ConnectionDbInfo.SQLITE.getDriver(),
                ConnectionDbInfo.SQLITE.getUrl() + DB_UUID_FILE.getAbsolutePath(),
                "",
                "");

        connectionInfoForDbInteger.addMapper(MapperSqlCountry.class);
        connectionInfoForDbInteger.addMapper(MapperSqlCity.class);
        connectionInfoForDbInteger.addMapper(MapperSqlRole.class);
        connectionInfoForDbInteger.addMapper(MapperSqlUser.class);
        connectionInfoForDbInteger.addMapper(MapperSqlFileStorage.class);
        connectionInfoForDbInteger.addMapper(MapperSqlJWTToken.class);
        connectionInfoForDbInteger.addMapper(MapperSqlProfile.class);
        connectionInfoForDbInteger.addMapper(MapperSqlProfileDetails.class);
        connectionInfoForDbInteger.addMapper(MapperSqlUserOnRole.class);
        connectionInfoForDbInteger.addMapper(MapperSqlPermissionOnRole.class);
        connectionInfoForDbUuid.addMapper(MapperSqlCountry.class);
        connectionInfoForDbUuid.addMapper(MapperSqlCity.class);
        connectionInfoForDbUuid.addMapper(MapperSqlRole.class);
        connectionInfoForDbUuid.addMapper(MapperSqlUser.class);
        connectionInfoForDbUuid.addMapper(MapperSqlFileStorage.class);
        connectionInfoForDbUuid.addMapper(MapperSqlJWTToken.class);
        connectionInfoForDbUuid.addMapper(MapperSqlProfile.class);
        connectionInfoForDbUuid.addMapper(MapperSqlProfileDetails.class);
        connectionInfoForDbUuid.addMapper(MapperSqlUserOnRole.class);
        connectionInfoForDbUuid.addMapper(MapperSqlPermissionOnRole.class);

        CREATE_DB_INTEGER_CONNECTION = new CreateConnectionByMybatis(connectionInfoForDbInteger);
        CREATE_DB_UUID_CONNECTION = new CreateConnectionByMybatis(connectionInfoForDbUuid);

        repositoriesForClearDb = new ArrayList<>();
    }

    @AfterClass
    public static void post(){
        cleanDatabase();
    }

    public static void cleanDatabase(){
        repositoriesForClearDb.forEach(repository ->
                repository.executeSql(String.format("DELETE FROM %s", repository.getTable().getTableName()))
        );
        repositoriesForClearDb.clear();
    }
}
