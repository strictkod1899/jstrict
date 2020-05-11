package ru.strict.db.core.common;

import ru.strict.db.core.connections.ConnectionInfo;

import java.sql.*;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 * Класс-утилита для операций над базой данных
 */
public class DatabaseUtil {

    /**
     * Получить объект DataSource базы данных
     *
     * @param nameLookUp Строка получения DataSource
     */
    public static DataSource createDataSource(String nameLookUp) {
        try {
            InitialContext initialContext = new InitialContext();
            return (DataSource) initialContext.lookup(nameLookUp);
        } catch (NamingException ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     * Получить объект соединения с базой данных по строке получения DataSource
     *
     * @param nameLookUp Строка получения DataSource
     * @return
     */
    public static Connection createConnectionIC(String nameLookUp) {
        try {
            InitialContext initialContext = new InitialContext();
            DataSource dataSource = (DataSource) initialContext.lookup(nameLookUp);
            return dataSource.getConnection();
        } catch (SQLException | NamingException ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     * Создание подключения к базе данных
     *
     * @param connectionInfo Информация для подключения к базе данных
     */
    public static Connection createConnection(ConnectionInfo connectionInfo) {
        Connection connection = null;
        try {
            Driver jdbcDriver = (Driver) Class.forName(connectionInfo.getDriver()).
                    newInstance();
            // Регистрация драйвера
            DriverManager.registerDriver(jdbcDriver);
            // Создание соединения с базой данных
            connection = DriverManager.getConnection(connectionInfo.getUrl(),
                    connectionInfo.getUsername(),
                    connectionInfo.getPassword());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            throw new RuntimeException(ex);
        }
        return connection;
    }

    /**
     * Выполнить запрос на выборку данных
     *
     * @param connection Соединение с базой данных
     * @param sql Sql запрос на выборку данных
     * @return
     */
    public static ResultSet qSelect(Connection connection, String sql) throws SQLException {
        return connection.createStatement().executeQuery(sql);
    }
}
