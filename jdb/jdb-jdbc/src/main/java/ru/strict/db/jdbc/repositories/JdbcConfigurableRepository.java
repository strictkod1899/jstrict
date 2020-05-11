package ru.strict.db.jdbc.repositories;

import ru.strict.db.core.common.SqlParameter;
import ru.strict.db.core.common.SqlParameters;
import ru.strict.db.core.common.SqlType;
import ru.strict.db.core.configuration.SqlConfiguration;
import ru.strict.db.core.connections.IConnectionCreator;
import ru.strict.db.core.repositories.ConfigurableRepository;
import ru.strict.patterns.IMapper;

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
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;

public class JdbcConfigurableRepository
        extends ConfigurableRepository<Connection, IConnectionCreator<Connection>> {

    public JdbcConfigurableRepository(IConnectionCreator<Connection> connectionSource,
            SqlConfiguration configuration,
            String group) {
        super(connectionSource, configuration, group);
    }

    @Override
    protected final void executeSql(String sql) {

    }

    @Override
    protected <T> T executeSqlRead(String sql,
            SqlParameters parameters,
            IMapper<ResultSet, T> resultMapper) {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Connection connection = null;

        try {
            connection = createConnection();

            statement = connection.prepareStatement(sql);
            setParametersToPrepareStatement(statement, parameters);

            resultSet = statement.executeQuery();
            if (!resultSet.isClosed()) {
                return resultMapper.map(resultSet);
            }
            return null;
        } catch (SQLException ex) {
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException e) {
                    throw new RuntimeException(ex);
                }
            }
            throw new RuntimeException(ex);
        } finally {
            if (resultSet != null) {
                try {
                    if (!resultSet.isClosed()) {
                        resultSet.close();
                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }

            if (statement != null) {
                try {
                    if (!statement.isClosed()) {
                        statement.close();
                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }

            if (connection != null) {
                try {
                    if (!connection.isClosed()) {
                        connection.close();
                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
    }

    /**
     * Установить параметры в переданный объект PreparedStatement в зависимости от нужного типа
     *
     * @param statement  Объект PreparedStatement, которому устанвливаются параметры
     * @param parameters Устанавливаемые параметры
     */
    private void setParametersToPrepareStatement(PreparedStatement statement, SqlParameters parameters) {
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
                    } else if (sqlType.equals(SqlType.TEXT)) {
                        statement.setString(index, value.toString());
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
                    } else if (value instanceof Array) {
                        statement.setArray(index, (Array) value);
                    } else if (value instanceof NClob) {
                        statement.setNClob(index, (NClob) value);
                    } else if (value instanceof Date || value instanceof java.sql.Date) {
                        statement.setDate(index, new java.sql.Date(((Date) value).getTime()));
                    } else if (value instanceof Time) {
                        statement.setTime(index, (Time) value);
                    } else if (value instanceof Timestamp) {
                        statement.setTimestamp(index, (Timestamp) value);
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
                throw new RuntimeException(ex);
            }
        }
    }
}
