package ru.strict.db.core.common;

import ru.strict.validate.Validator;

import java.sql.*;
import java.util.UUID;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 * Класс-утилита для операций над базой данных
 */
public class DatabaseUtil {

    public <T> T mapResultSetValue(SQLType sqlType, ResultSet resultSet, String columnName) {
        Validator.isNull(sqlType, "sqlType").onThrow();

        try {
            if (sqlType.equals(SqlType.UUID)) {
                return (T) UUID.fromString(resultSet.getString(columnName));
            } else if (sqlType.equals(SqlType.TEXT)) {
                return (T) resultSet.getString(columnName);
            } else if (sqlType.equals(JDBCType.BIGINT)) {
                return (T) (Object) resultSet.getLong(columnName);
            } else if (sqlType.equals(JDBCType.INTEGER)) {
                return (T) (Object) resultSet.getInt(columnName);
            } else {
                return (T) resultSet.getObject(columnName);
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

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
}
