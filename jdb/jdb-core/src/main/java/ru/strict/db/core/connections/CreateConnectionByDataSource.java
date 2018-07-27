package ru.strict.db.core.connections;

import ru.strict.utils.UtilLogger;
import ru.strict.components.WrapperLogger;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Конструктор соединения с базой данных, на основе объекта класса DataSource
 * <p><b>Пример использования:</b></p>
 * <code><pre style="background-color: white; font-family: consolas">
 *     ...
 *     ICreateConnection connectionCreater = new CreateConnectionByDataSource(dataSource);
 *     Connection connection = connectionCreater.createConnection();
 * </pre></code>
 */
public class CreateConnectionByDataSource extends CreateConnectionBase<DataSource, Connection> {

    protected final WrapperLogger LOGGER = UtilLogger.createLogger(CreateConnectionByDataSource.class);

    public CreateConnectionByDataSource(DataSource connectionSource) {
        super(connectionSource);
    }

    @Override
    public Connection createConnection() {
        LOGGER.info("Trying a connection create to database using DataSource: '%s'",
                getConnectionSource().toString());
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
