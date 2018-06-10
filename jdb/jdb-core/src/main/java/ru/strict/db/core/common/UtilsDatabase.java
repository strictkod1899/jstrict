package ru.strict.db.core.common;

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
     * Получить объект DataSource с базой данных
     * @param nameLookUp      Строка получения DataSource
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
     * Получить объект соединения с базой данных
     * @param nameLookUp      Строка получения DataSource
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
     * @param dbCaption наименование базы данных, к которой производится подключение
     * @param dbType    тип подключаемой базы данных
     * @param user      пользователь базы данных
     * @param password  пароль для подключения к базе данных
     */
    public static Connection createConnection(String dbCaption, ConnectionByDbType dbType, String user, String password) {
        UtilLogger.info(UtilsDatabase.class, "Trying a connection create");
        try {
            // Путь к базе данных
            String connectUrl = dbType.getUrl() + dbCaption;
            Driver jdbcDriver = (Driver) Class.forName(dbType.getDriver()).
                    newInstance();
            // Регистрация данного драйвера
            DriverManager.registerDriver(jdbcDriver);
            // Соединение с Базой Данных
            Connection connection = DriverManager.getConnection(connectUrl, user, password);
            UtilLogger.info(UtilsDatabase.class, "Connection is created");
            return connection;
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            UtilLogger.error(UtilsDatabase.class, ex.getClass().toString(), ex.getMessage());
        }
        return null;
    }

    /**
     * Выполнить запрос на выборку данных
     * @param connection Соединение с базой данных
     * @param sql Sql запрос на выборку данных
     * @return
     */
    public ResultSet qSelectValue(Connection connection, String sql){
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
