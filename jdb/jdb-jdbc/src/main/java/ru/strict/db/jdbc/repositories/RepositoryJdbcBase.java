package ru.strict.db.jdbc.repositories;

import ru.strict.db.core.connections.ICreateConnection;
import ru.strict.db.core.dto.DtoBase;
import ru.strict.db.core.entities.EntityBase;
import ru.strict.db.core.mappers.dto.MapperDtoBase;
import ru.strict.db.core.mappers.sql.MapperSqlBase;
import ru.strict.db.core.repositories.RepositoryBase;
import ru.strict.db.core.requests.DbRequests;
import ru.strict.db.jdbc.common.JdbcSqlParameter;
import ru.strict.db.jdbc.common.JdbcSqlParameters;
import ru.strict.utils.UtilLogger;

import java.math.BigDecimal;
import java.net.URL;
import java.sql.*;
import java.util.*;
import java.util.Date;

/**
 * Базовый класс репозитория с использованием Jdbc
 */
public abstract class RepositoryJdbcBase
        <ID, SOURCE extends ICreateConnection, E extends EntityBase, DTO extends DtoBase>
        extends RepositoryBase<ID, SOURCE, E, DTO> {

    /**
     * Объект для преобразования полученных данных из sql-запроса в объект сущности азы данных (entity)
     */
    private MapperSqlBase<E> sqlMapper;

    //<editor-fold defaultState="collapsed" desc="constructors">
    public RepositoryJdbcBase(String tableName, String[] columnsName,
                              SOURCE connectionSource,
                              MapperDtoBase<E, DTO> dtoMapper,
                              MapperSqlBase<E> sqlMapper, boolean isGenerateId) {
        super(tableName, columnsName, connectionSource, dtoMapper, isGenerateId);
        this.sqlMapper = sqlMapper;
    }
    //</editor-fold>

    //<editor-fold defaultState="collapsed" desc="CRUD">
    @Override
    public final DTO create(DTO dto) {
        LOGGER.info("Trying a db entity create");
        PreparedStatement statement;
        JdbcSqlParameters parameters;

        if(isGenerateId()) {
            if(dto.getId() instanceof Number) {
                parameters = getParameters(dto, 0);
                String sql = createSqlInsertFull(parameters.size());

                try {
                    statement = createConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                    statement = setParametersToPrepareStatement(statement, parameters);
                    statement.executeUpdate(sql);

                    try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                        if (generatedKeys.next())
                            dto.setId(generatedKeys.getLong(1));
                        else
                            throw new SQLException("Creating user failed, no ID obtained");
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }else if(dto.getId() instanceof String || dto.getId() instanceof UUID){
                parameters = getParameters(dto, 1);
                Object id = UUID.randomUUID();
                parameters.add(0, "id", id);
                String sql = createSqlInsertFull(parameters.size());

                try {
                    statement = createConnection().prepareStatement(sql);
                    statement = setParametersToPrepareStatement(statement, parameters);
                    statement.executeUpdate(sql);
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                dto.setId(id);
            }
        }else{
            parameters = getParameters(dto, 1);
            parameters.add(0, "id", dto.getId());
            String sql = createSqlInsertFull(parameters.size());
            try {
                statement = createConnection().prepareStatement(sql);
                statement = setParametersToPrepareStatement(statement, parameters);
                statement.executeUpdate(sql);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        LOGGER.info("Successful a db entity created");

        return dto;
    }

    @Override
    public DTO read(ID id) {
        PreparedStatement statement;
        ResultSet resultSet;
        try {
            statement = createConnection().prepareStatement(String.format("%s WHERE id = ?", createSqlSelect()));

            if(id instanceof Integer)
                statement.setInt(1, (Integer) id);
            else if(id instanceof Long)
                statement.setLong(1, (Long) id);
            else if(id instanceof UUID)
                statement.setString(1, ((UUID)id).toString());
            else if(id instanceof String)
                statement.setString(1, (String)id);
            else{
                UtilLogger.error(RepositoryJdbcBase.class, "Error sql-query [read]: ID type not supported");
                throw new IllegalArgumentException("Error sql-query [read]: ID type not supported");
            }

            resultSet = statement.executeQuery();
            return getDtoMapper().map(sqlMapper.map(resultSet));
        } catch (SQLException ex) {
            UtilLogger.error(RepositoryJdbcBase.class, ex.getClass().toString(), ex.getMessage());
            return null;
        }
    }

    @Override
    public List<DTO> readAll(DbRequests requests) {
        PreparedStatement statement;
        ResultSet resultSet;
        List<DTO> result = new LinkedList<>();
        try {
            String sql = createSqlSelect() + (requests==null ? "" : requests.getSql());
            statement = createConnection().prepareStatement(sql);
            resultSet = statement.executeQuery();
            while(resultSet.next())
                result.add(getDtoMapper().map(sqlMapper.map(resultSet)));
        } catch (SQLException ex) {
            UtilLogger.error(RepositoryJdbcBase.class, ex.getClass().toString(), ex.getMessage());
            return null;
        }
        setObjects(result);

        return result;
    }

    @Override
    public DTO update(DTO dto) {
        LOGGER.info("Trying a db entity update");
        JdbcSqlParameters parameters = getParameters(dto, 0);
        parameters.addLast("id", dto.getId());
        String sql = createSqlUpdate();

        PreparedStatement statement;

        try {
            statement = createConnection().prepareStatement(sql);
            statement = setParametersToPrepareStatement(statement, parameters);
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        LOGGER.info("Successful a db entity updated");
        return dto;
    }

    @Override
    public void delete(ID id) {
        LOGGER.info("Trying a db entity delete");
        String sql = createSqlDelete();
        JdbcSqlParameters parameters = new JdbcSqlParameters();
        parameters.addLast("id", id);
        PreparedStatement statement;

        try {
            statement = createConnection().prepareStatement(sql);
            statement = setParametersToPrepareStatement(statement, parameters);
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        LOGGER.info("Successful a db entity deleted");
    }
    //</editor-fold>

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
     * Сопоставить номер столбца базы данных с полем dto-объекта. Отсчет номера столбца начинать с нуля.
     * ID не учитывается. </br>
     * <i><b>Примечание:</b> Должен передаваться DTO-объект, который полностью отражает структуру entity-объекта</i>
     * <p><b>Пример использования:</b></p>
     * <code><pre style="background-color: white; font-family: consolas">
     *      Map<Integer, Object> valuesByColumn = new LinkedHashMap();
     *      valuesByColumn.put(0, dto.getName());
     *      valuesByColumn.put(1, dto.getSurname());
     *      valuesByColumn.put(2, dto.getMiddlename());
     *      return valuesByColumn;
     * </pre></code>
     * @param dto DTO-объект из которого берутся значения для параметров
     * @return
     */
    protected abstract Map getValueByColumn(DTO dto);

    @Override
    public boolean IsRowExists(ID id){
        LOGGER.info("Trying a determine is db row exists");

        String sql = "SELECT COUNT(*) FROM " + getTableName() + " WHERE id = ?;";
        JdbcSqlParameters parameters = new JdbcSqlParameters();
        parameters.addLast("id", id);
        PreparedStatement statement;
        boolean isExists = false;
        try {
            statement = createConnection().prepareStatement(sql);
            statement = setParametersToPrepareStatement(statement, parameters);

            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            if(resultSet.getInt(1)>0)
                isExists = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isExists;
    }

    /**
     * Получить параметры sql-запроса на создание/обновление записи. ID не учитывается
     * @param dto DTO-объект из которого берутся значения для параметров
     * @return
     */
    protected JdbcSqlParameters getParameters(DTO dto, int startIndex){
        Map valuesByColumn = getValueByColumn(dto);
        Set<Integer> keys = valuesByColumn.keySet();
        JdbcSqlParameters parameters = new JdbcSqlParameters();
        for(Integer key : keys)
            parameters.add(key+startIndex, getColumnsName()[key], valuesByColumn.get(key));

        return parameters;
    }

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
                else if (value instanceof Array)
                    statement.setArray(index, (Array)value);
                else if (value instanceof byte[])
                    statement.setBytes(index, (byte[])value);
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
                ex.getStackTrace();
                return null;
            }
        }

        return statement;
    }

    //<editor-fold defaultState="collapsed" desc="Get/Set"
    public MapperSqlBase<E> getSqlMapper() {
        return sqlMapper;
    }
    //</editor-fold>
}
