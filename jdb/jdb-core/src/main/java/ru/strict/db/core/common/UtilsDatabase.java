package ru.strict.db.core.common;

import ru.strict.db.core.connections.ConnectionInfo;
import ru.strict.utils.UtilLogger;

import java.sql.*;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 * Класс-утилита для операций над базой данных
 */
public class UtilsDatabase {

	/**
     * Получить объект DataSource базы данных
     * @param nameLookUp    Строка получения DataSource
     * @return
     */
    public static DataSource createDataSource(String nameLookUp) {
        UtilLogger.info(UtilsDatabase.class, "Trying a DataSource create");
        try {
            InitialContext initialContext = new InitialContext();
            DataSource dataSource = (DataSource) initialContext.lookup(nameLookUp);
            UtilLogger.info(UtilsDatabase.class, "Connection is created");
            return dataSource;
        } catch (NamingException ex) {
            UtilLogger.error(UtilsDatabase.class, ex.getClass().toString(), ex.getMessage());
            return null;
        }
    }

    /**
     * Получить объект соединения с базой данных по строке получения DataSource
     * @param nameLookUp    Строка получения DataSource
     * @return
     */
    public static Connection createConnectionIC(String nameLookUp) {
        UtilLogger.info(UtilsDatabase.class, "Trying a connection create");
        try {
            InitialContext initialContext = new InitialContext();
            DataSource dataSource = (DataSource) initialContext.lookup(nameLookUp);
            Connection connection = dataSource.getConnection();
            UtilLogger.info(UtilsDatabase.class, "Connection is created");
            return connection;
        } catch (SQLException | NamingException ex) {
            UtilLogger.error(UtilsDatabase.class, ex.getClass().toString(), ex.getMessage());
            return null;
        }
    }

    /**
     * Создание подключения к базе данных
     *
     * @param connectionInfo    Информация для подключения к базе данных
     */
    public static Connection createConnection(ConnectionInfo connectionInfo) {
        UtilLogger.info(UtilsDatabase.class, "Trying a connection create");
        Connection connection = null;
        try {
            Driver jdbcDriver = (Driver) Class.forName(connectionInfo.getDriver()).
                    newInstance();
            // Регистрация драйвера
            DriverManager.registerDriver(jdbcDriver);
            // Создание соединения с базой данных
            connection = DriverManager.getConnection(connectionInfo.getUrl(), connectionInfo.getUsername(), connectionInfo.getPassword());
            UtilLogger.info(UtilsDatabase.class, "Connection is created");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            UtilLogger.error(UtilsDatabase.class, ex.getClass().toString(), ex.getMessage());
        }
        return connection;
    }

    /**
     * Выполнить запрос на выборку данных
     * @param connection    Соединение с базой данных
     * @param sql           Sql запрос на выборку данных
     * @return
     */
    public static ResultSet qSelectValue(Connection connection, String sql){
        UtilLogger.info(UtilsDatabase.class, "Trying a sql query execute");
        ResultSet rs = null;

        try {
            rs = connection.createStatement().executeQuery(sql);
        } catch (SQLException ex) {
            UtilLogger.error(UtilsDatabase.class, ex.getClass().toString(), ex.getMessage());
        }
        return rs;
    }
}
