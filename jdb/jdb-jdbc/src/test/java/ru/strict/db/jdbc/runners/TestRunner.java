package ru.strict.db.jdbc.runners;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import ru.strict.db.core.common.ConnectionDbInfo;
import ru.strict.db.core.connections.ConnectionInfo;
import ru.strict.db.core.connections.CreateConnectionByConnectionInfo;
import ru.strict.db.core.repositories.IRepositoryExtension;
import ru.strict.db.jdbc.*;
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
    public static CreateConnectionByConnectionInfo CREATE_DB_INTEGER_CONNECTION;
    public static CreateConnectionByConnectionInfo CREATE_DB_UUID_CONNECTION;
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

        ConnectionInfo connectionInfoForDbInteger = new ConnectionInfo(
                ConnectionDbInfo.SQLITE.getDriver(),
                ConnectionDbInfo.SQLITE.getUrl() + TestRunner.DB_INTEGER_FILE.getAbsolutePath(),
                "",
                "");
        ConnectionInfo connectionInfoForDbUuid = new ConnectionInfo(
                ConnectionDbInfo.SQLITE.getDriver(),
                ConnectionDbInfo.SQLITE.getUrl() + TestRunner.DB_UUID_FILE.getAbsolutePath(),
                "",
                "");

        CREATE_DB_INTEGER_CONNECTION = new CreateConnectionByConnectionInfo(connectionInfoForDbInteger);
        CREATE_DB_UUID_CONNECTION = new CreateConnectionByConnectionInfo(connectionInfoForDbUuid);

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
