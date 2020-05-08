package ru.strict.db;

import ru.strict.db.core.common.ConnectionDbInfo;
import ru.strict.db.core.connections.ConnectionCreatorByConnectionInfo;
import ru.strict.db.core.connections.ConnectionInfo;
import ru.strict.utils.ResourcesUtil;

import java.io.File;

public class TestConnectionCreator extends ConnectionCreatorByConnectionInfo {

    private static final String DB_INTEGER_FILE_PATH = "testdb_integer.sqlite";

    public TestConnectionCreator() {
        super(getConnectionInfo());
    }

    private static ConnectionInfo getConnectionInfo() {
        File dbFile = ResourcesUtil.getResourceAsFileTemp(DB_INTEGER_FILE_PATH);
        return new ConnectionInfo(
                ConnectionDbInfo.SQLITE.getDriver(),
                ConnectionDbInfo.SQLITE.getUrl() + dbFile.getAbsolutePath(),
                "",
                "");
    }
}
