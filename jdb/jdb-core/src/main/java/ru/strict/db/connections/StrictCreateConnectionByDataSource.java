package ru.strict.db.connections;

import ru.strict.utils.StrictUtilLogger;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class StrictCreateConnectionByDataSource extends StrictCreateConnectionBase<DataSource> {

    public StrictCreateConnectionByDataSource(DataSource connectionSource) {
        super(connectionSource);
    }

    @Override
    public Connection createConnection() {
        StrictUtilLogger.info(StrictCreateConnectionByDataSource.class, "createConnection - started");
        try {
            return getConnectionSource().getConnection();
        } catch (SQLException ex) {
            StrictUtilLogger.error(StrictCreateConnectionByDataSource.class, ex.getClass().toString(), ex.getMessage());
            return null;
        }
    }
}
