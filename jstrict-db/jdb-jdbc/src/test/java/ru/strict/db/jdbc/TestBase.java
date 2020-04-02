package ru.strict.db.jdbc;

import org.junit.AfterClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import ru.strict.db.core.common.ConnectionDbInfo;
import ru.strict.db.core.connections.ConnectionInfo;
import ru.strict.db.core.connections.CreateConnectionByConnectionInfo;
import ru.strict.db.core.repositories.IRepositoryExtension;
import ru.strict.utils.ResourcesUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@RunWith(JUnit4.class)
public class TestBase {

    private static final String DB_INTEGER_FILE_PATH = "testdb_integer.sqlite";
    private static final String DB_UUID_FILE_PATH = "testdb_uuid.sqlite";

    protected static final File DB_INTEGER_FILE;
    protected static final File DB_UUID_FILE;
    protected static final CreateConnectionByConnectionInfo CREATE_DB_INTEGER_CONNECTION;
    protected static final CreateConnectionByConnectionInfo CREATE_DB_UUID_CONNECTION;
    protected static final List<IRepositoryExtension> repositoriesForClearDb = new ArrayList<>();

    static {
        DB_INTEGER_FILE = ResourcesUtil.getResource(DB_INTEGER_FILE_PATH);
        DB_UUID_FILE = ResourcesUtil.getResource(DB_UUID_FILE_PATH);
        if(DB_INTEGER_FILE == null){
            throw new NullPointerException("not found integer-db file");
        }
        if(DB_UUID_FILE == null){
            throw new NullPointerException("not found uuid-db file");
        }

        ConnectionInfo connectionInfoForDbInteger = new ConnectionInfo(
                ConnectionDbInfo.SQLITE.getDriver(),
                ConnectionDbInfo.SQLITE.getUrl() + DB_INTEGER_FILE.getAbsolutePath(),
                "",
                "");
        ConnectionInfo connectionInfoForDbUuid = new ConnectionInfo(
                ConnectionDbInfo.SQLITE.getDriver(),
                ConnectionDbInfo.SQLITE.getUrl() + DB_UUID_FILE.getAbsolutePath(),
                "",
                "");

        CREATE_DB_INTEGER_CONNECTION = new CreateConnectionByConnectionInfo(connectionInfoForDbInteger);
        CREATE_DB_UUID_CONNECTION = new CreateConnectionByConnectionInfo(connectionInfoForDbUuid);
    }

    protected static void cleanDatabase(){
        repositoriesForClearDb.forEach(repository ->
                repository.executeSql(String.format("DELETE FROM %s", repository.getTable().getTableName()))
        );
        repositoriesForClearDb.clear();
    }

    @Test
    public void ignore() {}
}
