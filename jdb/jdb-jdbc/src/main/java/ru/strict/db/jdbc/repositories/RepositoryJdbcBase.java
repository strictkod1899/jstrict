package ru.strict.db.jdbc.repositories;


import ru.strict.db.core.common.GenerateIdType;
import ru.strict.db.core.connections.ICreateConnection;
import ru.strict.db.core.dto.DtoBase;
import ru.strict.db.core.entities.EntityBase;
import ru.strict.db.core.mappers.dto.MapperDtoBase;
import ru.strict.db.core.mappers.sql.MapperSqlBase;
import ru.strict.db.core.repositories.RepositoryBase;
import ru.strict.db.core.requests.DbRequests;
import ru.strict.db.jdbc.common.JdbcSqlParameter;
import ru.strict.db.jdbc.common.JdbcSqlParameters;

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
        this.sqlMapper = sqlMapper;
    }
    //</editor-fold>

    //<editor-fold defaultState="collapsed" desc="CRUD">
    @Override
    public final DTO create(DTO dto) {
        PreparedStatement statement = null;
        JdbcSqlParameters parameters;
        String sql = null;
        E entity = getDtoMapper().map(dto);
        Connection connection = null;

        GenerateIdType usingGenerateIdType = getGenerateIdType();
        if(dto.getId() != null){
            usingGenerateIdType = GenerateIdType.NONE;
        }

        switch(usingGenerateIdType){
            case NUMBER:
                parameters = getParameters(entity, 0);
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
                            statement.close();
                        } catch (SQLException ex) {
                            throw new RuntimeException(ex);
                        }
                    }

                    if(connection != null) {
                        try {
                            connection.close();
                        } catch (SQLException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                }
                break;
            case UUID:
                parameters = getParameters(entity, 1);
                Object id = UUID.randomUUID();
                parameters.add(0, "id", id);
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
                            statement.close();
                        } catch (SQLException ex) {
                            throw new RuntimeException(ex);
                        }
                    }

                    if(connection != null) {
                        try {
                            connection.close();
                        } catch (SQLException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                }

                dto.setId((ID)id);
                break;
            case NONE:
                parameters = getParameters(entity, 1);
                parameters.add(0, "id", dto.getId());
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
                            statement.close();
                        } catch (SQLException ex) {
                            throw new RuntimeException(ex);
                        }
                    }

                    if(connection != null) {
                        try {
                            connection.close();
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
            statement = connection.prepareStatement(String.format("%s WHERE id = ?", createSqlSelect()));

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
            result = getDtoMapper().map(sqlMapper.map(resultSet));
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
                    resultSet.close();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }

            if(statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }

            if(connection != null) {
                try {
                    connection.close();
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
            String sql = createSqlSelect() + (requests==null ? "" : " " + requests.getSql());
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while(resultSet.next()) {
                result.add(getDtoMapper().map(sqlMapper.map(resultSet)));
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
                    resultSet.close();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }

            if(statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }

            if(connection != null) {
                try {
                    connection.close();
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
        JdbcSqlParameters parameters = getParameters(entity, 0);
        parameters.addLast("id", dto.getId());
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
                    statement.close();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }

            if(connection != null) {
                try {
                    connection.close();
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
        JdbcSqlParameters parameters = new JdbcSqlParameters();
        parameters.addLast("id", id);
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
                    statement.close();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }

            if(connection != null) {
                try {
                    connection.close();
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
            String sql = createSqlCount() + (requests==null ? "" : " " + requests.getSql());
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            resultSet.next();
            result = resultSet.getInt(1);
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
                    resultSet.close();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }

            if(statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }


            if(connection != null) {
                try {
                    connection.close();
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
                    statement.close();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }

            if(connection != null) {
                try {
                    connection.close();
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
        for(String columnName : getColumnsName())
            sql.append(columnName + ", ");
        sql.replace(sql.length()-2, sql.length(), "");
        sql.append(") VALUES (");

        for(int i=0; i<parametersCount; i++)
            sql.append("?, ");
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
        for(String columnName : getColumnsName())
            sql.append(columnName + ", ");
        sql.replace(sql.length()-2, sql.length(), "");
        sql.append(") VALUES (?, ");

        for(int i=0; i<parametersCount; i++)
            sql.append("?, ");
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
            String columnName = getColumnsName()[i];
            sql.append(columnName + " = ?, ");
        }
        sql.replace(sql.length()-2, sql.length(), "");
        sql.append(" WHERE id = ?");
        return sql.toString();
    }

    /**
     * Sql-запрос на удаление записи в таблице
     * @return
     */
    private String createSqlDelete(){
        StringBuilder sql = new StringBuilder("DELETE FROM ");
        sql.append(getTableName());
        sql.append(" WHERE id = ?");

        return sql.toString();
    }
    //</editor-fold>

    /**
     * Сопоставить номер столбца базы данных с полем entity-объекта. Отсчет номера столбца начинать с нуля.
     * ID не учитывается. </br>
     * <p><b>Пример использования:</b></p>
     * <code><pre style="background-color: white; font-family: consolas">
     *      Map<Integer, Object> valuesByColumn = new LinkedHashMap();
     *      valuesByColumn.put(0, entity.getName());
     *      valuesByColumn.put(1, entity.getSurname());
     *      valuesByColumn.put(2, entity.getMiddlename());
     *      return valuesByColumn;
     * </pre></code>
     * @param entity Entity-объект из которого берутся значения для параметров
     * @return
     */
    protected abstract Map getValueByColumn(E entity);

    @Override
    public boolean isRowExists(ID id){
        String sql = "SELECT COUNT(*) FROM " + getTableName() + " WHERE id = ?;";
        JdbcSqlParameters parameters = new JdbcSqlParameters();
        parameters.addLast("id", id);
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        boolean isExists = false;
        Connection connection = null;
        try{
            connection = createConnection();
            statement = connection.prepareStatement(sql);
            statement = setParametersToPrepareStatement(statement, parameters);

            resultSet = statement.executeQuery();
            resultSet.next();
            if(resultSet.getInt(1)>0) {
                isExists = true;
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
                    resultSet.close();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }

            if(statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }

            if(connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
        return isExists;
    }

    //<editor-fold defaultState="collapsed" desc="Determine statement parameters">
    /**
     * Получить параметры sql-запроса на создание/обновление записи. ID не учитывается
     * @param entity           Entity-объект из которого берутся значения для параметров
     * @param startIndex    Начальный индекс полученных параметров.
     *                      Может потребоваться, если в качестве первого элемента требуется установить id, а все остальные сдвинуть на 1, тогда параметру передается значение = 1
     * @return
     */
    protected JdbcSqlParameters getParameters(E entity, int startIndex){
        Map valuesByColumn = getValueByColumn(entity);
        Set<Integer> keys = valuesByColumn.keySet();
        JdbcSqlParameters parameters = new JdbcSqlParameters();
        for(Integer key : keys) {
            parameters.add(key + startIndex, getColumnsName()[key], valuesByColumn.get(key));
        }

        return parameters;
    }

    /**
     * Установить параметры в переданный объект PreparedStatement в зависимости от нужного типа
     * @param statement     Объект PreparedStatement, которому устанвливаются параметры
     * @param parameters    Устанавливаемые параметры
     * @return
     */
    private PreparedStatement setParametersToPrepareStatement(PreparedStatement statement, JdbcSqlParameters parameters){
        for(JdbcSqlParameter parameter : parameters){
            try {
                int index = parameter.getIndex() + 1;
                Object value = parameter.getValue();
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
                else
                    statement.setObject(index, value);
            }catch(SQLException ex){
                throw new RuntimeException(ex);
            }
        }

        return statement;
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
