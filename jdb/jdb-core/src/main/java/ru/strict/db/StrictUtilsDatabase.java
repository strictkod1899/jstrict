package ru.strict.db;

import ru.strict.db.enums.StrictConnectionByDbType;
import ru.strict.utils.StrictUtilLogger;

import java.sql.*;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 * Класс-утилита операций с базой данных
 */
public class StrictUtilsDatabase {

	/**
     * Получить объект DataSource с базой данных
     * @param nameLookUp      Строка получения DataSource
     * @return
     */
    public static DataSource createDataSource(String nameLookUp) {
        StrictUtilLogger.info(StrictUtilsDatabase.class, "createDataSource - started");
        try {
            InitialContext initialContext = new InitialContext();
            return (DataSource) initialContext.lookup(nameLookUp);
        } catch (NamingException ex) {
            StrictUtilLogger.error(StrictUtilsDatabase.class, ex.getClass().toString(), ex.getMessage());
            return null;
        }
    }

    /**
     * Получить объект соединения с базой данных
     * @param nameLookUp      Строка получения DataSource
     * @return
     */
    public static Connection createConnectionIC(String nameLookUp) {
        StrictUtilLogger.info(StrictUtilsDatabase.class, "createConnectionIC - started");
        try {
            InitialContext initialContext = new InitialContext();
            DataSource dataSource = (DataSource) initialContext.lookup(nameLookUp);
            return dataSource.getConnection();
        } catch (SQLException | NamingException ex) {
            StrictUtilLogger.error(StrictUtilsDatabase.class, ex.getClass().toString(), ex.getMessage());
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
    public static Connection createConnection(String dbCaption, StrictConnectionByDbType dbType, String user, String password) {
        StrictUtilLogger.info(StrictUtilsDatabase.class, "createConnection - started");
        try {
            // Путь к базе данных
            String connectUrl = dbType.getUrl() + dbCaption;
            Driver jdbcDriver = (Driver) Class.forName(dbType.getDriver()).
                    newInstance();
            // Регистрация данного драйвера
            DriverManager.registerDriver(jdbcDriver);
            // Соединение с Базой Данных
            return DriverManager.getConnection(connectUrl, user, password);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            StrictUtilLogger.error(StrictUtilsDatabase.class, ex.getClass().toString(), ex.getMessage());
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
        ResultSet rs = null;

        try {
            rs = connection.createStatement().executeQuery(sql);
        } catch (SQLException ex) {
            StrictUtilLogger.error(StrictManagerDatabase.class, ex.getClass().toString(), ex.getMessage());
        }
        return rs;
    }
}