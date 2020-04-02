package ru.strict.db.core.connections;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Конструктор соединения с базой данных, на основе объекта класса DataSource
 * <p><b>Пример использования:</b></p>
 * <code><pre style="background-color: white; font-family: consolas">
 *     ...
 *     IConnectionCreator connectionCreater = new ConnectionCreatorByDataSource(dataSource);
 *     Connection connection = connectionCreater.createConnection();
 * </pre></code>
 *
 * <p><b>Создание DataSource используя commons-dbcp:</b></p>
 * <code><pre style="background-color: white; font-family: consolas">
 *     BasicDataSource dataSource = new BasicDataSource();
 *     dataSource.setDriverClassName("org.sqlite.JDBC");
 *     dataSource.setUrl("jdbc:sqlite:C:/path/to/db.sqlite");
 *     dataSource.setUsername("");
 *     dataSource.setPassword("");
 * </pre></code>
 */
public class ConnectionCreatorByDataSource extends BaseConnectionCreator<DataSource, Connection> {

    public ConnectionCreatorByDataSource(DataSource connectionSource) {
        super(connectionSource);
    }

    @Override
    public Connection createConnection() {
        try {
            Connection connection = getConnectionSource().getConnection();
            return connection;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
}
