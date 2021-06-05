package ru.strict.db.jdbc.components;

import ru.strict.db.core.common.ConnectionDriver;
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
        File dbFile = ResourcesUtil.getResourceAsTempFile(DB_INTEGER_FILE_PATH);
        return new ConnectionInfo(
                ConnectionDriver.SQLITE.getDriver(),
                ConnectionDriver.SQLITE.getUrl() + dbFile.getAbsolutePath(),
                "",
                "");
    }
}
