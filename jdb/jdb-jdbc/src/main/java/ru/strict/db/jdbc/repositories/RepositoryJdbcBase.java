package ru.strict.db.jdbc.repositories;

import ru.strict.db.core.common.GenerateIdType;
import ru.strict.db.core.connections.ICreateConnection;
import ru.strict.db.core.requests.*;
import ru.strict.db.jdbc.mappers.sql.MapperSqlBase;
import ru.strict.db.core.repositories.RepositoryBase;
import ru.strict.db.core.common.SqlParameter;
import ru.strict.db.core.common.SqlParameters;
import ru.strict.models.ModelBase;

import java.math.BigDecimal;
import java.net.URL;
import java.sql.*;
import java.util.*;
import java.util.Date;

/**
 * Базовый класс репозитория с использованием Jdbc
 * @param <ID> Тип идентификатора
 * @param <T> Тип сущности базы данных
 */
public abstract class RepositoryJdbcBase
        <ID, T extends ModelBase<ID>>
        extends RepositoryBase<ID, Connection, ICreateConnection<Connection>, T> {

    /**
     * Объект для преобразования полученных данных из sql-запроса в объект сущности базы данных (model)
     */
    private MapperSqlBase<ID, T> sqlMapper;

    //<editor-fold defaultState="collapsed" desc="constructors">
    private void initialize(MapperSqlBase<ID, T> sqlMapper){
        if(sqlMapper == null){
            throw new IllegalArgumentException("sqlMapper is NULL");
        }

        this.sqlMapper = sqlMapper;
    }

    public RepositoryJdbcBase(DbTable table,
                              String[] columns,
                              ICreateConnection<Connection> connectionSource,
                              MapperSqlBase<ID, T> sqlMapper,
                              GenerateIdType generateIdType,
                              SQLType sqlIdType) {
        super(table, columns, connectionSource, generateIdType, sqlIdType);
        initialize(sqlMapper);
    }

    public RepositoryJdbcBase(DbTable table,
                              String[] columns,
                              ICreateConnection<Connection> connectionSource,
                              MapperSqlBase<ID, T> sqlMapper,
                              GenerateIdType generateIdType) {
        super(table, columns, connectionSource, generateIdType);
        initialize(sqlMapper);
    }

    RepositoryJdbcBase(DbTable table,
                       String[] columns,
                       ICreateConnection<Connection> connectionSource,
                       GenerateIdType generateIdType,
                       SQLType sqlIdType) {
        super(table, columns, connectionSource, generateIdType, sqlIdType);
    }
    //</editor-fold>

    //<editor-fold defaultState="collapsed" desc="CRUD">
    @Override
    public final ID create(T model) {
        PreparedStatement statement = null;
        SqlParameters parameters;
        String sql = null;
        Connection connection = null;

        ID generatedId = null;

        GenerateIdType usingGenerateIdType = getGenerateIdType();
        if(model.getId() != null){
            usingGenerateIdType = GenerateIdType.NONE;
            generatedId = model.getId();
        }

        switch(usingGenerateIdType){
            case INT:
                parameters = getParameters(model);
                sql = createSqlInsertShort(parameters.size());

                try{
                    connection = createConnection();
                    statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                    statement = setParametersToPrepareStatement(statement, parameters);
                    statement.executeUpdate();

                    try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                        if (generatedKeys.next()) {
                            generatedId = (ID)(Object)generatedKeys.getInt(1);
                        }else {
                            throw new SQLException("Creating user failed, no ID obtained");
                        }
                    }
                } catch (SQLException ex) {
                    if(connection != null) {
                        try {
                            connection.rollback();
                        } catch (SQLException e) {
                            throw new RuntimeException(ex);
                        }
                    }
                    throw new RuntimeException(ex);
                }finally {
                    if(statement != null) {
                        try {
                            if(!statement.isClosed()) {
                                statement.close();
                            }
                        } catch (SQLException ex) {
                            throw new RuntimeException(ex);
                        }
                    }

                    if(connection != null) {
                        try {
                            if(!connection.isClosed()) {
                                connection.close();
                            }
                        } catch (SQLException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                }
                break;
            case LONG:
                parameters = getParameters(model);
                sql = createSqlInsertShort(parameters.size());

                try{
                    connection = createConnection();
                    statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                    statement = setParametersToPrepareStatement(statement, parameters);
                    statement.executeUpdate();

                    try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                        if (generatedKeys.next()) {
                            generatedId = (ID)(Object)generatedKeys.getLong(1);
                        }else {
                            throw new SQLException("Creating user failed, no ID obtained");
                        }
                    }
                } catch (SQLException ex) {
                    if(connection != null) {
                        try {
                            connection.rollback();
                        } catch (SQLException e) {
                            throw new RuntimeException(ex);
                        }
                    }
                    throw new RuntimeException(ex);
                }finally {
                    if(statement != null) {
                        try {
                            if(!statement.isClosed()) {
                                statement.close();
                            }
                        } catch (SQLException ex) {
                            throw new RuntimeException(ex);
                        }
                    }

                    if(connection != null) {
                        try {
                            if(!connection.isClosed()) {
                                connection.close();
                            }
                        } catch (SQLException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                }
                break;
            case UUID:
                parameters = shiftParameters(getParameters(model), 1);
                generatedId = (ID) UUID.randomUUID();
                parameters.add(0, getColumnIdName(), generatedId);
                sql = createSqlInsertFull(parameters.size()-1);

                try{
                    connection = createConnection();
                    statement = connection.prepareStatement(sql);
                    statement = setParametersToPrepareStatement(statement, parameters);
                    statement.executeUpdate();
                } catch (SQLException ex) {
                    if(connection != null) {
                        try {
                            connection.rollback();
                        } catch (SQLException e) {
                            throw new RuntimeException(ex);
                        }
                    }
                    throw new RuntimeException(ex);
                }finally {
                    if(statement != null) {
                        try {
                            if(!statement.isClosed()) {
                                statement.close();
                            }
                        } catch (SQLException ex) {
                            throw new RuntimeException(ex);
                        }
                    }

                    if(connection != null) {
                        try {
                            if(!connection.isClosed()) {
                                connection.close();
                            }
                        } catch (SQLException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                }
                break;
            case NONE:
                parameters = shiftParameters(getParameters(model), 1);
                parameters.add(0, getColumnIdName(), model.getId());
                sql = createSqlInsertFull(parameters.size()-1);
                try{
                    connection = createConnection();
                    statement = connection.prepareStatement(sql);
                    statement = setParametersToPrepareStatement(statement, parameters);
                    statement.executeUpdate();
                } catch (SQLException ex) {
                    if(connection != null) {
                        try {
                            connection.rollback();
                        } catch (SQLException e) {
                            throw new RuntimeException(ex);
                        }
                    }
                    throw new RuntimeException(ex);
                }finally {
                    if(statement != null) {
                        try {
                            if(!statement.isClosed()) {
                                statement.close();
                            }
                        } catch (SQLException ex) {
                            throw new RuntimeException(ex);
                        }
                    }

                    if(connection != null) {
                        try {
                            if(!connection.isClosed()) {
                                connection.close();
                            }
                        } catch (SQLException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                }
                break;
            default:
                throw new IllegalArgumentException("Type for generate id is not determine. Entity was not created into");
        }

        return generatedId;
    }

    @Override
    public T read(ID id) {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        try{
            connection = createConnection();

            DbSelect select = createSqlSelect();
            select.getRequests().addWhere(new DbWhereEquals(getTable(), getColumnIdName(), id));

            statement = connection.prepareStatement(select.getParametrizedSql());

            if(id instanceof Integer) {
                statement.setInt(1, (Integer) id);
            } else if(id instanceof Long) {
                statement.setLong(1, (Long) id);
            } else if(id instanceof UUID) {
                statement.setString(1, ((UUID) id).toString());
            } else if(id instanceof String) {
                statement.setString(1, (String) id);
            } else {
                throw new IllegalArgumentException("Error sql-query [read]: ID type not supported");
            }

            resultSet = statement.executeQuery();
            if(!resultSet.isClosed()) {
                return sqlMapper.map(resultSet);
            }
            return null;
        } catch (SQLException ex) {
            if(connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException e) {
                    throw new RuntimeException(ex);
                }
            }
            throw new RuntimeException(ex);
        } finally {
            if(resultSet != null){
                try {
                    if(!resultSet.isClosed()) {
                        resultSet.close();
                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }

            if(statement != null) {
                try {
                    if(!statement.isClosed()) {
                        statement.close();
                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }

            if(connection != null) {
                try {
                    if(!connection.isClosed()) {
                        connection.close();
                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
    }

    @Override
    public List<T> readAll(DbRequests requests) {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<T> result = new ArrayList<>();
        Connection connection = null;
        try{
            connection = createConnection();

            DbSelect select = createSqlSelect();
            if(requests != null) {
                select.setRequests(requests);
            }

            statement = connection.prepareStatement(select.getParametrizedSql());
            if(requests != null) {
                setParametersToPrepareStatement(statement, select.getParameters());
            }
            resultSet = statement.executeQuery();

            if(!resultSet.isClosed()) {
                while (resultSet.next()) {
                    result.add(sqlMapper.map(resultSet));
                }
            }
        } catch (SQLException ex) {
            if(connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException e) {
                    throw new RuntimeException(ex);
                }
            }
            throw new RuntimeException(ex);
        }finally {
            if(resultSet != null){
                try {
                    if(!resultSet.isClosed()) {
                        resultSet.close();
                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }

            if(statement != null) {
                try {
                    if(!statement.isClosed()) {
                        statement.close();
                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }

            if(connection != null) {
                try {
                    if(!connection.isClosed()) {
                        connection.close();
                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }

        return result;
    }

    @Override
    public void update(T model) {
        SqlParameters parameters = getParameters(model);
        parameters.addLast(getColumnIdName(), model.getId());
        String sql = createSqlUpdate();

        PreparedStatement statement = null;

        Connection connection = null;
        try{
            connection = createConnection();
            statement = connection.prepareStatement(sql);
            statement = setParametersToPrepareStatement(statement, parameters);
            statement.executeUpdate();
        } catch (SQLException ex) {
            if(connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException e) {
                    throw new RuntimeException(ex);
                }
            }
            throw new RuntimeException(ex);
        }finally {
            if(statement != null) {
                try {
                    if(!statement.isClosed()) {
                        statement.close();
                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }

            if(connection != null) {
                try {
                    if(!connection.isClosed()) {
                        connection.close();
                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
    }

    @Override
    public void delete(ID id) {
        String sql = createSqlDelete();
        SqlParameters parameters = new SqlParameters();
        parameters.addLast(getColumnIdName(), id);
        PreparedStatement statement = null;

        Connection connection = null;
        try{
            connection = createConnection();
            statement = connection.prepareStatement(sql);
            statement = setParametersToPrepareStatement(statement, parameters);
            statement.executeUpdate();
        } catch (SQLException ex) {
            if(connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException e) {
                    throw new RuntimeException(ex);
                }
            }
            throw new RuntimeException(ex);
        }finally {
            if(statement != null) {
                try {
                    if(!statement.isClosed()) {
                        statement.close();
                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }

            if(connection != null) {
                try {
                    if(!connection.isClosed()) {
                        connection.close();
                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
    }
    //</editor-fold>

    @Override
    public int readCount(DbRequests requests) {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        int result = -1;
        Connection connection = null;
        try{
            connection = createConnection();

            DbSelect select = createSqlCount();
            if(requests != null) {
                select.setRequests(requests);
            }

            statement = connection.prepareStatement(select.getParametrizedSql());
            if(requests != null) {
                setParametersToPrepareStatement(statement, select.getParameters());
            }
            resultSet = statement.executeQuery();

            if(!resultSet.isClosed()) {
                resultSet.next();
                result = resultSet.getInt(1);
            }
        } catch (SQLException ex) {
            if(connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException e) {
                    throw new RuntimeException(ex);
                }
            }
            throw new RuntimeException(ex);
        }finally {
            if(resultSet != null){
                try {
                    if(!resultSet.isClosed()) {
                        resultSet.close();
                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }

            if(statement != null) {
                try {
                    if(!statement.isClosed()) {
                        statement.close();
                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }


            if(connection != null) {
                try {
                    if(!connection.isClosed()) {
                        connection.close();
                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }

        return result;
    }

    @Override
    public void executeSql(String sql) {
        PreparedStatement statement = null;

        Connection connection = null;
        try{
            connection = createConnection();
            statement = connection.prepareStatement(sql);
            statement.executeUpdate();
        } catch (SQLException ex) {
            if(connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException e) {
                    throw new RuntimeException(ex);
                }
            }
            throw new RuntimeException(ex);
        } finally {
            if(statement != null) {
                try {
                    if(!statement.isClosed()) {
                        statement.close();
                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }

            if(connection != null) {
                try {
                    if(!connection.isClosed()) {
                        connection.close();
                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
    }

    //<editor-fold defaultState="collapsed" desc="sql generate">
    /**
     * Sql-запрос на создание записи в таблице (без учета ID)
     * @return
     */
    private String createSqlInsertShort(int parametersCount){
        StringBuilder sql = new StringBuilder(String.format("INSERT INTO %s (", getTable().getTableName()));
        for(String columnName : getColumns()) {
            sql.append(columnName);
            sql.append(", ");
        }
        sql.replace(sql.length()-2, sql.length(), "");
        sql.append(") VALUES (");

        for(int i=0; i<parametersCount; i++) {
            sql.append("?, ");
        }
        sql.replace(sql.length()-2, sql.length(), "");
        sql.append(");");
        return sql.toString();
    }

    /**
     * Sql-запрос на создание записи в таблице (с учетом добавления ID)
     * @return
     */
    private String createSqlInsertFull(int parametersCount){
        StringBuilder sql = new StringBuilder(String.format("INSERT INTO %s (%s, ", getTable().getTableName(), getColumnIdName()));
        for(String columnName : getColumns()) {
            sql.append(columnName);
            sql.append(", ");
        }
        sql.replace(sql.length()-2, sql.length(), "");
        sql.append(") VALUES (?, ");

        for(int i=0; i<parametersCount; i++) {
            sql.append("?, ");
        }
        sql.replace(sql.length()-2, sql.length(), "");
        sql.append(")");
        return sql.toString();
    }

    /**
     * Sql-запрос на обновление записи в таблице
     * @return
     */
    private String createSqlUpdate(){
        StringBuilder sql = new StringBuilder(String.format("UPDATE %s SET ", getTable().getTableName()));
        for(int i = 0; i< getColumns().length; i++){
            sql.append(getColumns()[i]);
            sql.append(" = ?, ");
        }
        sql.replace(sql.length()-2, sql.length(), "");
        sql.append(" WHERE ");
        sql.append(getColumnIdName());
        sql.append(" = ?");
        return sql.toString();
    }

    /**
     * Sql-запрос на удаление записи в таблице
     * @return
     */
    private String createSqlDelete(){
        return String.format("DELETE FROM %s WHERE %s = ?", getTable().getTableName(), getColumnIdName());
    }
    //</editor-fold>

    /**
     * Сопоставить номер столбца базы данных с полем model-объекта. Отсчет номера столбца начинать с нуля.
     * ID не учитывается. </br>
     * <p><b>Пример использования:</b></p>
     * <code><pre style="background-color: white; font-family: consolas">
     *      SqlParameters parameters = new SqlParameters();
     *      parameters.add(0, COLUMNS_NAME[0], model.getCaption());
     *      parameters.add(1, COLUMNS_NAME[1], model.getCountryId());
     *      return parameters;
     * </pre></code>
     * @param model Объект из которого берутся значения для параметров
     * @return
     */
    protected abstract SqlParameters getParameters(T model);

    //<editor-fold defaultState="collapsed" desc="Determine statement parameters">
    /**
     * Установить параметры в переданный объект PreparedStatement в зависимости от нужного типа
     * @param statement     Объект PreparedStatement, которому устанвливаются параметры
     * @param parameters    Устанавливаемые параметры
     * @return
     */
    private PreparedStatement setParametersToPrepareStatement(PreparedStatement statement, SqlParameters<?> parameters){
        for(SqlParameter parameter : parameters.getParameters()){
            try {
                int index = parameter.getIndex() + 1;
                Object value = parameter.getValue();
                if(value == null){
                    statement.setNull(index, JDBCType.NULL.getVendorTypeNumber());
                } else if(parameter.getSqlType() != null){
                    statement.setObject(index, value, parameter.getSqlType());
                } else {
                    if (value instanceof Boolean)
                        statement.setBoolean(index, Boolean.valueOf(value.toString()));
                    else if (value instanceof Byte)
                        statement.setByte(index, Byte.valueOf(value.toString()));
                    else if (value instanceof Short)
                        statement.setShort(index, Short.valueOf(value.toString()));
                    else if (value instanceof Integer)
                        statement.setInt(index, Integer.valueOf(value.toString()));
                    else if (value instanceof Long)
                        statement.setLong(index, Long.valueOf(value.toString()));
                    else if (value instanceof Float)
                        statement.setFloat(index, Float.valueOf(value.toString()));
                    else if (value instanceof Double)
                        statement.setDouble(index, Double.valueOf(value.toString()));
                    else if (value instanceof BigDecimal)
                        statement.setBigDecimal(index, (BigDecimal)value);
                    else if (value instanceof byte[])
                        statement.setBytes(index, (byte[])value);
                    else if (value instanceof Array)
                        statement.setArray(index, (Array)value);
                    else if (value instanceof NClob)
                        statement.setNClob(index, (NClob)value);
                    else if (value instanceof Date || value instanceof java.sql.Date)
                        statement.setDate(index, new java.sql.Date(((Date)value).getTime()));
                    else if (value instanceof Time)
                        statement.setTime(index, (Time)value);
                    else if (value instanceof Timestamp)
                        statement.setTimestamp(index, (Timestamp)value);
                    else if (value instanceof URL)
                        statement.setURL(index, (URL)value);
                    else if (value instanceof Clob)
                        statement.setClob(index, (Clob)value);
                    else if (value instanceof Blob)
                        statement.setBlob(index, (Blob)value);
                    else if (value instanceof String)
                        statement.setString(index, value.toString());
                    else {
                        statement.setObject(index, value);
                    }
                }
            }catch(SQLException ex){
                throw new RuntimeException(ex);
            }
        }

        return statement;
    }

    /**
     * Сдвинуть параметры вправа
     * @param parameters Параметры
     * @param startPosition Стартовая позиция
     */
    private SqlParameters shiftParameters(SqlParameters<?> parameters, int startPosition){
        for(SqlParameter parameter : parameters.getParameters()){
            parameter.setIndex(parameter.getIndex() + startPosition);
        }

        return parameters;
    }
    //</editor-fold>

    //<editor-fold defaultState="collapsed" desc="Get/Set">
    void setSqlMapper(MapperSqlBase<ID, T> sqlMapper){
        this.sqlMapper = sqlMapper;
    }

    public MapperSqlBase<ID, T> getSqlMapper() {
        return sqlMapper;
    }
    //</editor-fold>

    @Override
    public int hashCode(){
        return Objects.hash(getConnectionSource(), getTable(), getColumns(),
                getGenerateIdType(), sqlMapper);
    }
}
