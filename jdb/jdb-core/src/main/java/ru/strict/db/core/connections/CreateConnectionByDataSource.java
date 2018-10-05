package ru.strict.db.core.connections;

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

    public CreateConnectionByDataSource(DataSource connectionSource) {
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
