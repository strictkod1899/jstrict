package ru.strict.db.jdbc;

import ru.strict.db.core.common.ConnectionByDbType;
import ru.strict.db.core.connections.ConnectionInfo;
import ru.strict.db.core.connections.CreateConnectionByConnectionInfo;
import ru.strict.db.core.connections.ICreateConnection;
import ru.strict.db.core.migration.IMigration;
import ru.strict.db.core.migration.MigrationDatabase;
import ru.strict.db.core.migration.components.MigrationColumn;
import ru.strict.db.core.migration.components.MigrationForeignKey;
import ru.strict.db.core.migration.components.MigrationPrimaryKey;
import ru.strict.db.core.migration.components.MigrationTable;

public class Main {

    public static void main(String[] args){
        ConnectionInfo connectionInfo = new ConnectionInfo("C:\\Users\\strictkod1899\\Strict\\strict\\projects\\testdb.sqlite",
                ConnectionByDbType.SQLITE.getDriver(),
                ConnectionByDbType.SQLITE.getUrl(),
                "",
                "");
        ICreateConnection createConnection = new CreateConnectionByConnectionInfo(connectionInfo);

        migration(createConnection);
        //Connection connection = createConnection.createConnection();
        //IStrictRepositoryExtension repository = new RepositoryUserOnRole(createConnection, false);
    }

    private static void migration(ICreateConnection createConnection){
    }
}
