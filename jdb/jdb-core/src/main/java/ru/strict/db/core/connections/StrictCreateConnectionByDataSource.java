package ru.strict.db.core.connections;

import ru.strict.utils.StrictUtilLogger;
import ru.strict.components.StrictWrapperLogger;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Конструктор соединения с базой данных, на основе объекта класса DataSource
 */
public class StrictCreateConnectionByDataSource extends StrictCreateConnectionBase<DataSource> {

    protected final StrictWrapperLogger LOGGER = StrictUtilLogger.createLogger(StrictCreateConnectionByDataSource.class);

    public StrictCreateConnectionByDataSource(DataSource connectionSource) {
        super(connectionSource);
    }

    @Override
    public Connection createConnection() {
        LOGGER.info("Trying a connection create");
        try {
            Connection connection = getConnectionSource().getConnection();
            LOGGER.info("Connection is created");
            return connection;
        } catch (SQLException ex) {
            LOGGER.error(ex.getClass().toString(), ex.getMessage());
            return null;
        }
    }
}
