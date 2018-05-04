package ru.strict.db.connections;

import ru.strict.utils.StrictUtilLogger;
import ru.strict.utils.components.StrictLogger;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Конструктор соединения с базой данных, на основе объекта класса DataSource
 */
public class StrictCreateConnectionByDataSource extends StrictCreateConnectionBase<DataSource> {

    protected final StrictLogger LOGGER = StrictUtilLogger.createLogger(StrictCreateConnectionByDataSource.class);

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
