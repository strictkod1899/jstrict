package ru.strict.db.jdbc.repositories;

import ru.strict.db.core.common.GenerateIdType;
import ru.strict.db.core.connections.ICreateConnection;
import ru.strict.db.core.dto.DtoBase;
import ru.strict.db.core.entities.EntityBase;
import ru.strict.db.core.mappers.dto.MapperDtoBase;
import ru.strict.db.core.mappers.sql.MapperSqlBase;
import ru.strict.db.core.repositories.RepositoryBase;
import ru.strict.db.core.requests.DbRequests;
import ru.strict.db.core.common.SqlParameter;
import ru.strict.db.core.common.SqlParameters;

import java.math.BigDecimal;
import java.net.URL;
import java.sql.*;
import java.util.*;
import java.util.Date;

/**
 * Базовый класс репозитория с использованием Jdbc
 * @param <ID> Тип идентификатора
 * @param <E> Тип сущности базы данных (entity)
 * @param <DTO> Тип Dto-сущности базы данных
 */
public abstract class RepositoryJdbcBase
        <ID, E extends EntityBase<ID>, DTO extends DtoBase<ID>>
        extends RepositoryBase<ID, Connection, ICreateConnection<Connection>, E, DTO> {

    /**
     * Объект для преобразования полученных данных из sql-запроса в объект сущности азы данных (entity)
     */
    private MapperSqlBase<ID, E> sqlMapper;

    //<editor-fold defaultState="collapsed" desc="constructors">
    public RepositoryJdbcBase(String tableName, String[] columnsName,
                              ICreateConnection<Connection> connectionSource,
                              MapperDtoBase<ID, E, DTO> dtoMapper,
                              MapperSqlBase<ID, E> sqlMapper,
                              GenerateIdType generateIdType) {
        super(tableName, columnsName, connectionSource, dtoMapper, generateIdType);
        if(sqlMapper == null){
            throw new IllegalArgumentException("sqlMapper is NULL");
        }

        this.sqlMapper = sqlMapper;
    }
    //</editor-fold>

    //<editor-fold defaultState="collapsed" desc="CRUD">
    @Override
    public final DTO create(DTO dto) {
        PreparedStatement statement = null;
        SqlParameters parameters;
        String sql = null;
        E entity = getDtoMapper().map(dto);
        Connection connection = null;

        GenerateIdType usingGenerateIdType = getGenerateIdType();
        if(dto.getId() != null){
            usingGenerateIdType = GenerateIdType.NONE;
        }

        switch(usingGenerateIdType){
            case NUMBER:
                parameters = getParameters(entity);
                sql = createSqlInsertShort(parameters.size());

                try{
                    connection = createConnection();
                    statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                    statement = setParametersToPrepareStatement(statement, parameters);
                    statement.executeUpdate();

                    try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                        if (generatedKeys.next()) {
                            dto.setId((ID)(Object)generatedKeys.getLong(1));
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
                parameters = shiftParameters(getParameters(entity), 1);
                Object id = UUID.randomUUID();
                parameters.add(0, getColumnIdName(), id);
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

                dto.setId((ID)id);
                break;
            case NONE:
                parameters = shiftParameters(getParameters(entity), 1);
                parameters.add(0, getColumnIdName(), dto.getId());
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
        };

        return dto;
    }

    @Override
    public DTO read(ID id) {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        DTO result = null;
        Connection connection = null;
        try{
            connection = createConnection();
            statement = connection.prepareStatement(String.format("%s WHERE " + getColumnIdName() + " = ?", createSqlSelect()));

            if(id instanceof Integer)
                statement.setInt(1, (Integer) id);
            else if(id instanceof Long)
                statement.setLong(1, (Long) id);
            else if(id instanceof UUID)
                statement.setString(1, ((UUID)id).toString());
            else if(id instanceof String)
                statement.setString(1, (String)id);
            else{
                throw new IllegalArgumentException("Error sql-query [read]: ID type not supported");
            }

            resultSet = statement.executeQuery();
            if(!resultSet.isClosed()) {
                result = getDtoMapper().map(sqlMapper.map(resultSet));
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
    public List<DTO> readAll(DbRequests requests) {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<DTO> result = new ArrayList<>();
        Connection connection = null;
        try{
            connection = createConnection();
            String sql = createSqlSelect() + (requests==null ? "" : " " + requests.getParametrizedSql());
            statement = connection.prepareStatement(sql);
            if(requests != null) {
                setParametersToPrepareStatement(statement, requests.getParameters());
            }
            resultSet = statement.executeQuery();

            if(!resultSet.isClosed()) {
                while (resultSet.next()) {
                    result.add(getDtoMapper().map(sqlMapper.map(resultSet)));
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
    public DTO update(DTO dto) {
        E entity = getDtoMapper().map(dto);
        SqlParameters parameters = getParameters(entity);
        parameters.addLast(getColumnIdName(), dto.getId());
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
        return dto;
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
            String sql = createSqlCount() + (requests==null ? "" : " " + requests.getParametrizedSql());
            statement = connection.prepareStatement(sql);
            if(requests != null) {
                setParametersToPrepareStatement(statement, requests.getParameters());
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
        StringBuilder sql = new StringBuilder(String.format("INSERT INTO %s (", getTableName()));
        for(String columnName : getColumnsName()) {
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
        StringBuilder sql = new StringBuilder(String.format("INSERT INTO %s (id, ", getTableName()));
        for(String columnName : getColumnsName()) {
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
        StringBuilder sql = new StringBuilder(String.format("UPDATE %s SET ", getTableName()));
        for(int i=0; i<getColumnsName().length; i++){
            sql.append(getColumnsName()[i]);
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
        return String.format("DELETE FROM %s WHERE %s = ?", getTableName(), getColumnIdName());
    }
    //</editor-fold>

    /**
     * Сопоставить номер столбца базы данных с полем entity-объекта. Отсчет номера столбца начинать с нуля.
     * ID не учитывается. </br>
     * <p><b>Пример использования:</b></p>
     * <code><pre style="background-color: white; font-family: consolas">
     *      SqlParameters parameters = new SqlParameters();
     *      parameters.add(0, COLUMNS_NAME[0], entity.getCaption());
     *      parameters.add(1, COLUMNS_NAME[1], entity.getCountryId());
     *      return parameters;
     * </pre></code>
     * @param entity Entity-объект из которого берутся значения для параметров
     * @return
     */
    protected abstract SqlParameters getParameters(E entity);

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
    public MapperSqlBase<ID, E> getSqlMapper() {
        return sqlMapper;
    }
    //</editor-fold>

    @Override
    public int hashCode(){
        return Objects.hash(getConnectionSource(), getDtoMapper(), getTableName(), getColumnsName(),
                getGenerateIdType(), sqlMapper);
    }

    @Override
    public void close(){
        sqlMapper = null;
        super.close();
    }
}
