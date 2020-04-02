package ru.strict.db.core.connections;

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

    public CreateConnectionByConnectionInfo(ConnectionInfo connectionSource) {
        super(connectionSource);
    }

    @Override
    public Connection createConnection() {
        try {
            // Путь к базе данных
            String connectUrl = getConnectionSource().getUrl();
            Driver jdbcDriver = (Driver) Class.forName(getConnectionSource().getDriver()).
                    newInstance();
            // Регистрация данного драйвера
            DriverManager.registerDriver(jdbcDriver);
            // Соединение с Базой Данных
            Connection connection = DriverManager.getConnection(connectUrl, getConnectionSource().getUsername(), getConnectionSource().getPassword());
            return connection;
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
}
