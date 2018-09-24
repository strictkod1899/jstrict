package ru.strict.db.core.connections;

import ru.strict.components.LoggerWrapper;
import ru.strict.utils.UtilLogger;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Конструктор соединения с базой данных, на основе информации переданной в объекте класса ConnectionInfo
 * <p><b>Пример использования:</b></p>
 * <code><pre style="background-color: white; font-family: consolas">
 *     ...
 *     ICreateConnection connectionCreater = new CreateConnectionByConnectionInfo(connectionInfo);
 *     Connection connection = connectionCreater.createConnection();
 * </pre></code>
 */
public class CreateConnectionByConnectionInfo extends CreateConnectionBase<ConnectionInfo, Connection> {

    protected final LoggerWrapper LOGGER = UtilLogger.createLogger(CreateConnectionByConnectionInfo.class);

    public CreateConnectionByConnectionInfo(ConnectionInfo connectionSource) {
        super(connectionSource);
    }

    @Override
    public Connection createConnection() {
        LOGGER.info("Trying a connection create to database by url: '%s' and user: '%s'",
                getConnectionSource().getUrl(),
                getConnectionSource().getUsername());
        try {
            // Путь к базе данных
            String connectUrl = getConnectionSource().getUrl();
            Driver jdbcDriver = (Driver) Class.forName(getConnectionSource().getDriver()).
                    newInstance();
            // Регистрация данного драйвера
            DriverManager.registerDriver(jdbcDriver);
            // Соединение с Базой Данных
            Connection connection = DriverManager.getConnection(connectUrl, getConnectionSource().getUsername(), getConnectionSource().getPassword());
            LOGGER.info("Connection is created");
            return connection;
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            LOGGER.error(ex.getClass().toString(), ex.getMessage());
            return null;
        }
    }
}
