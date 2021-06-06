package ru.strict.db.core.common;

import ru.strict.db.core.exception.DatabaseException;

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
            throw new DatabaseException(ex);
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
            throw new DatabaseException(ex);
        }
    }
}
