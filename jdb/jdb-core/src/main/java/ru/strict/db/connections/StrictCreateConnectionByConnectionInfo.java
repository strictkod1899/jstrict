package ru.strict.db.connections;

import ru.strict.utils.StrictUtilLogger;
import ru.strict.utils.components.StrictLogger;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Конструктор соединения с базой данных, на основе информации переданной в объекте класса StrictConnectionInfo
 */
public class StrictCreateConnectionByConnectionInfo extends StrictCreateConnectionBase<StrictConnectionInfo> {

    protected final StrictLogger LOGGER = StrictUtilLogger.createLogger(StrictCreateConnectionByConnectionInfo.class);

    public StrictCreateConnectionByConnectionInfo(StrictConnectionInfo connectionSource) {
        super(connectionSource);
    }

    @Override
    public Connection createConnection() {
        LOGGER.info("Trying a connection create");
        try {
            // Путь к базе данных
            String connectUrl = getConnectionSource().getUrl() + getConnectionSource().getDbCaption();
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
