package ru.strict.db.jdbc.utils;

import ru.strict.db.core.common.SqlParameter;
import ru.strict.db.core.common.SqlParameters;
import ru.strict.db.core.common.SqlType;
import ru.strict.db.core.exceptions.DatabaseException;
import ru.strict.patterns.mapper.IMapper;
import ru.strict.validate.Validator;

import java.math.BigDecimal;
import java.net.URL;
import java.sql.Array;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.JDBCType;
import java.sql.NClob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLType;
import java.sql.Statement;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public final class JdbcUtil {

    private static final AutoGeneratedKeyGetter AUTO_GENERATED_KEY_GETTER = new AutoGeneratedKeyGetter();

    @FunctionalInterface
    public interface CreateConnectionFunction {
        Connection create();
    }

    private JdbcUtil() {}

    public static void executeSql(String sql,
            SqlParameters parameters,
            CreateConnectionFunction createConnectionFunction) {
        executeSql(sql, parameters, createConnectionFunction, false);
    }

    public static <ID> ID executeSql(String sql,
            SqlParameters parameters,
            CreateConnectionFunction createConnectionFunction,
            boolean autoGenerateKey) {
        ID generatedKey = null;
        try (Connection connection = createConnectionFunction.create();
                PreparedStatement statement = autoGenerateKey
                        ? connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)
                        : connection.prepareStatement(sql)) {
            setParametersToPrepareStatement(statement, parameters);
            statement.executeUpdate();

            if (autoGenerateKey) {
                generatedKey = (ID) AUTO_GENERATED_KEY_GETTER.apply(statement);
            }

            return generatedKey;
        } catch (SQLException ex) {
            throw new DatabaseException(ex);
        }
    }

    public static <T> T executeSqlRead(String sql,
            SqlParameters parameters,
            IMapper<ResultSet, T> resultMapper,
            CreateConnectionFunction createConnectionFunction) {
        try (Connection connection = createConnectionFunction.create();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            setParametersToPrepareStatement(statement, parameters);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (!resultSet.isClosed()) {
                    return resultMapper.map(resultSet);
                }
            }
            return null;
        } catch (SQLException ex) {
            throw new DatabaseException(ex);
        }
    }

    public static <T> List<T> executeSqlReadAll(String sql,
            SqlParameters parameters,
            IMapper<ResultSet, T> resultMapper,
            CreateConnectionFunction createConnectionFunction) {
        List<T> list = new ArrayList<>();

        try (Connection connection = createConnectionFunction.create();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            setParametersToPrepareStatement(statement, parameters);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (!resultSet.isClosed()) {
                    while (resultSet.next()) {
                        list.add(resultMapper.map(resultSet));
                    }
                }
            }
            return list;
        } catch (SQLException ex) {
            throw new DatabaseException(ex);
        }
    }

    /**
     * Установить параметры в переданный объект PreparedStatement в зависимости от нужного типа
     *
     * @param statement  Объект PreparedStatement, которому устанвливаются параметры
     * @param parameters Устанавливаемые параметры
     */
    public static void setParametersToPrepareStatement(PreparedStatement statement, SqlParameters parameters) {
        for (SqlParameter<?> parameter : parameters.getParameters()) {
            try {
                int index = parameter.getIndex() + 1;
                Object value = parameter.getValue();
                if (value == null) {
                    statement.setNull(index, JDBCType.NULL.getVendorTypeNumber());
                } else if (parameter.getSqlType() != null) {
                    SQLType sqlType = parameter.getSqlType();
                    if (sqlType.equals(SqlType.UUID)) {
                        statement.setString(index, value.toString());
                    } else if (sqlType.equals(SqlType.TEXT)
                            || sqlType.equals(JDBCType.CHAR)
                            || sqlType.equals(JDBCType.VARCHAR)
                            || sqlType.equals(JDBCType.LONGVARCHAR)) {
                        statement.setString(index, value.toString());
                    } else if (sqlType.equals(JDBCType.BIGINT)) {
                        statement.setLong(index, (Long) value);
                    } else if (sqlType.equals(JDBCType.INTEGER)) {
                        statement.setInt(index, (Integer) value);
                    } else if (sqlType.equals(JDBCType.BIT)) {
                        statement.setBoolean(index, (Boolean) value);
                    } else if (sqlType.equals(JDBCType.NUMERIC)) {
                        statement.setBigDecimal(index, (BigDecimal) value);
                    } else if (sqlType.equals(JDBCType.TINYINT)) {
                        statement.setByte(index, (Byte) value);
                    } else if (sqlType.equals(JDBCType.SMALLINT)) {
                        statement.setShort(index, (Short) value);
                    } else if (sqlType.equals(JDBCType.ARRAY)) {
                        Array sqlArray = getSqlArray(value, statement.getConnection());
                        statement.setArray(index, sqlArray);
                    } else {
                        statement.setObject(index, value, parameter.getSqlType());
                    }
                } else {
                    if (value instanceof Boolean) {
                        statement.setBoolean(index, Boolean.valueOf(value.toString()));
                    } else if (value instanceof Byte) {
                        statement.setByte(index, Byte.valueOf(value.toString()));
                    } else if (value instanceof Short) {
                        statement.setShort(index, Short.valueOf(value.toString()));
                    } else if (value instanceof Integer) {
                        statement.setInt(index, Integer.valueOf(value.toString()));
                    } else if (value instanceof Long) {
                        statement.setLong(index, Long.valueOf(value.toString()));
                    } else if (value instanceof Float) {
                        statement.setFloat(index, Float.valueOf(value.toString()));
                    } else if (value instanceof Double) {
                        statement.setDouble(index, Double.valueOf(value.toString()));
                    } else if (value instanceof BigDecimal) {
                        statement.setBigDecimal(index, (BigDecimal) value);
                    } else if (value instanceof byte[]) {
                        statement.setBytes(index, (byte[]) value);
                    } else if (value instanceof Collection || value.getClass().isArray()) {
                        Array sqlArray = getSqlArray(value, statement.getConnection());
                        statement.setArray(index, sqlArray);
                    } else if (value instanceof NClob) {
                        statement.setNClob(index, (NClob) value);
                    } else if (value instanceof Time) {
                        statement.setTime(index, (Time) value);
                    } else if (value instanceof Timestamp) {
                        statement.setTimestamp(index, (Timestamp) value);
                    } else if (value instanceof Date) {
                        statement.setDate(index, new java.sql.Date(((Date) value).getTime()));
                    } else if (value instanceof LocalDate) {
                        statement.setDate(index, java.sql.Date.valueOf((LocalDate) value));
                    } else if (value instanceof LocalDateTime) {
                        statement.setTimestamp(index, Timestamp.valueOf((LocalDateTime) value));
                    } else if (value instanceof ZonedDateTime) {
                        statement.setTimestamp(index, Timestamp.valueOf(((ZonedDateTime) value).toLocalDateTime()));
                    } else if (value instanceof LocalTime) {
                        statement.setTime(index, Time.valueOf((LocalTime) value));
                    } else if (value instanceof URL) {
                        statement.setURL(index, (URL) value);
                    } else if (value instanceof Clob) {
                        statement.setClob(index, (Clob) value);
                    } else if (value instanceof Blob) {
                        statement.setBlob(index, (Blob) value);
                    } else if (value instanceof String || value instanceof UUID) {
                        statement.setString(index, value.toString());
                    } else {
                        statement.setObject(index, value);
                    }
                }
            } catch (SQLException ex) {
                throw new DatabaseException(ex);
            }
        }
    }

    /**
     * Сдвинуть параметры вправа
     *
     * @param parameters Параметры
     * @param startPosition На сколько элементов сдвигать
     */
    public static void shiftParameters(SqlParameters parameters, int startPosition) {
        parameters.forEach(parameter -> parameter.setIndex(parameter.getIndex() + startPosition));
    }

    public static <T> T getValueBySqlType(ResultSet resultSet, String columnName, SQLType sqlType) throws SQLException {
        if (sqlType == null) {
            throw new IllegalArgumentException("sqlType is NULL");
        }

        Object value = resultSet.getObject(columnName);
        return mapValue(value, sqlType);
    }

    public static <T> T mapValue(Object sourceValue, SQLType sqlType) {
        if (sourceValue == null) {
            return null;
        }

        if (sqlType instanceof SqlType) {
            return SqlType.mapValue(sourceValue, sqlType);
        } else if (sqlType.equals(JDBCType.BIGINT)) {
            return (T) Long.valueOf(String.valueOf(sourceValue));
        } else if (sqlType.equals(JDBCType.INTEGER)) {
            return (T) Integer.valueOf(String.valueOf(sourceValue));
        } else if (sqlType.equals(JDBCType.BOOLEAN)) {
            if (sourceValue.equals(1)) {
                return (T) Boolean.TRUE;
            } else if (sourceValue.equals(0)) {
                return (T) Boolean.FALSE;
            } else {
                return (T) Boolean.valueOf((String)sourceValue);
            }
        } else {
            return (T) sourceValue;
        }
    }

    private static Array getSqlArray(Object value, Connection connection) throws SQLException {
        Object[] array;
        if (value instanceof Collection) {
            Collection<Object> collection = (Collection<Object>) value;
            array = collection.toArray();
        } else if (value.getClass().isArray()) {
            array = (Object[]) value;
        } else {
            throw new UnsupportedOperationException(
                    String.format("Unsupported object type [%s] for set as SqlArray",
                            value.getClass())
            );
        }
        return connection.createArrayOf("TEXT", array);
    }
}
