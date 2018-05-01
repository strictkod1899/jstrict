package ru.strict.db.connections;

import ru.strict.utils.StrictUtilLogger;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Конструктор соединения с базой данных, на основе информации переданной в объекте класса StrictConnectionInfo
 */
public class StrictCreateConnectionByConnectionInfo extends StrictCreateConnectionBase<StrictConnectionInfo> {

    public StrictCreateConnectionByConnectionInfo(StrictConnectionInfo connectionSource) {
        super(connectionSource);
    }

    @Override
    public Connection createConnection() {
        StrictUtilLogger.info(StrictCreateConnectionByConnectionInfo.class, "createConnection - started");
        try {
            // Путь к базе данных
            String connectUrl = getConnectionSource().getUrl() + getConnectionSource().getDbCaption();
            Driver jdbcDriver = (Driver) Class.forName(getConnectionSource().getDriver()).
                    newInstance();
            // Регистрация данного драйвера
            DriverManager.registerDriver(jdbcDriver);
            // Соединение с Базой Данных
            return DriverManager.getConnection(connectUrl, getConnectionSource().getUsername(), getConnectionSource().getPassword());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            StrictUtilLogger.error(StrictCreateConnectionByConnectionInfo.class, ex.getClass().toString(), ex.getMessage());
        }
        return null;
    }
}
