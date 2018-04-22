package ru.strict.db.repositories;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import ru.strict.db.connections.StrictCreateConnectionByDataSource;
import ru.strict.db.dto.StrictDtoBase;
import ru.strict.db.entities.StrictEntityBase;
import ru.strict.db.mappers.dto.StrictMapperDtoBase;
import ru.strict.db.requests.StrictDbRequests;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * Базовый класс репозитория с использованием Spring Jdbc
 */
public abstract class StrictRepositorySpringBase
        <ID, E extends StrictEntityBase, DTO extends StrictDtoBase>
        extends StrictRepositoryBase<ID, StrictCreateConnectionByDataSource, E, DTO> {

    /**
     * Экземпляр Spring Jdbc, с помощью которого, производится взаимодействие с базой данных
     */
    private NamedParameterJdbcTemplate springJdbc;

    /**
     * Объект для преобразования полученных данных из sql-запроса в объект сущности азы данных (entity)
     */
    private RowMapper<E> springMapper;

    /**
     * Наименование таблицы
     */
    private String tableName;

    /**
     * Наименование столбцов таблицы в базе данных
     */
    private String[] columnsName;

    //<editor-fold defaultState="collapsed" desc="constructors">
    public StrictRepositorySpringBase(String tableName, String[] columnsName,
                                      StrictCreateConnectionByDataSource connectionSource,
                                      StrictMapperDtoBase dtoMapper, boolean isGenerateId, RowMapper<E> springMapper) {
        super(connectionSource, dtoMapper, isGenerateId);
        this.springJdbc = new NamedParameterJdbcTemplate(getConnectionSource().getConnectionSource());
        this.springMapper = springMapper;
        this.tableName = tableName;
        this.columnsName = columnsName;
    }
    //</editor-fold>

    //<editor-fold defaultState="collapsed" desc="CRUD">
    @Override
    public final DTO create(DTO dto) {
        MapSqlParameterSource parameters = getParameters(dto);

        if(isGenerateId()) {
            if(dto.getId() instanceof Number) {
                KeyHolder keyHolder = new GeneratedKeyHolder();
                String sql = createSqlInsertShort(parameters.getParameterNames());
                springJdbc.update(sql, parameters, keyHolder);
                dto.setId(keyHolder.getKey());
            }else if(dto.getId() instanceof String || dto.getId() instanceof UUID){
                String sql = createSqlInsertFull(parameters.getParameterNames());
                Object id = UUID.randomUUID();
                parameters.addValue("id", id);
                springJdbc.update(sql, parameters);
                dto.setId(id);
            }
        }else{
            String sql = createSqlInsertFull(parameters.getParameterNames());
            parameters.addValue("id", dto.getId());
            springJdbc.update(sql, parameters);
        }

        return dto;
    }

    @Override
    public final DTO read(ID id) {
        SqlParameterSource parameters = new MapSqlParameterSource("id", id);
        String sql = String.format("%s WHERE id = :id", createSqlSelect());
        E entity = springJdbc.queryForObject(sql, parameters, springMapper);
        return getDtoMapper().map(entity);
    }

    @Override
    public final List<DTO> readAll(StrictDbRequests requests) {
        String sql = createSqlSelect() + (requests==null?"":requests.getSql());
        List<DTO> result = new LinkedList<>();
        List<E> entities = springJdbc.query(sql, springMapper);
        for(E entity : entities)
            result.add(getDtoMapper().map(entity));
        return result;
    }

    @Override
    public DTO update(DTO dto) {
        MapSqlParameterSource parameters = getParameters(dto);
        String sql = createSqlUpdate(parameters.getParameterNames());
        parameters.addValue("id", dto.getId());
        springJdbc.update(sql, parameters);
        return dto;
    }

    @Override
    public boolean delete(ID id) {
        String sql = createSqlDelete();
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("id", id);
        int columnAffect = springJdbc.update(sql, parameters);
        if(columnAffect>0)
            return true;
        else
            return false;
    }

    @Override
    public DTO createOrUpdate(DTO dto) {
        if(IsRowExists((ID)dto.getId()))
            return update(dto);
        else
            return create(dto);
    }

    @Override
    public DTO createOrRead(DTO dto) {
        if(IsRowExists((ID)dto.getId()))
            return read((ID)dto.getId());
        else
            return create(dto);
    }
    //</editor-fold>

    /**
     * Проверить существование записи в базе данных с переданным идентификатором
     * @param id
     * @return
     */
    public boolean IsRowExists(ID id){
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("id", id);
        return springJdbc
                .<Boolean>execute("SELECT COUNT(*) FROM " + tableName + " WHERE id = :id;",
                        parameters,
                        new CallbackIsExists());
    }

    /**
     * Проверка существования записи в базе данных
     */
    private class CallbackIsExists implements PreparedStatementCallback {

        @Override
        public Object doInPreparedStatement(PreparedStatement preparedStatement) throws SQLException, DataAccessException {
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            if(resultSet.getInt(1)>0)
                return true;
            else
                return false;
        }
    }

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

    /**
     * Получить параметры sql-запроса на создание/обновление записи. ID не учитывается
     * @param dto DTO-объект из которого берутся значения для параметров
     * @return
     */
    protected MapSqlParameterSource getParameters(DTO dto){
        Map valuesByColumn = getValueByColumn(dto);
        Set<Integer> keys = valuesByColumn.keySet();
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        for(Integer key : keys)
            parameters.addValue(getColumnsName()[key], valuesByColumn.get(key));

        return parameters;
    }

    //<editor-fold defaultState="collapsed" desc="sql generate">
    /**
     * Sql-запрос на создание записи в таблице (без учета ID)
     * @return
     */
    private String createSqlSelect(){
        StringBuilder sql = new StringBuilder("SELECT id, ");
        for(String columnName : getColumnsName())
            sql.append(columnName + ", ");
        sql.replace(sql.length()-2, sql.length(), "");
        sql.append(" FROM " + getTableName());

        return sql.toString();
    }

    /**
     * Sql-запрос на создание записи в таблице (без учета ID)
     * @return
     */
    private String createSqlInsertShort(String[] parametersName){
        StringBuilder sql = new StringBuilder(String.format("INSERT INTO %s (", getTableName()));
        for(String columnName : getColumnsName())
            sql.append(columnName + ", ");
        sql.replace(sql.length()-2, sql.length(), "");
        sql.append(") VALUES (");

        for(String parameterName : parametersName)
            sql.append(":" + parameterName + ", ");
        sql.replace(sql.length()-2, sql.length(), "");
        sql.append(");");
        return sql.toString();
    }

    /**
     * Sql-запрос на создание записи в таблице (с учетом добавления ID)
     * @return
     */
    private String createSqlInsertFull(String[] parametersName){
        StringBuilder sql = new StringBuilder(String.format("INSERT INTO %s (id, ", getTableName()));
        for(String columnName : getColumnsName())
            sql.append(columnName + ", ");
        sql.replace(sql.length()-2, sql.length(), "");
        sql.append(") VALUES (:id, ");

        for(String parameterName : parametersName)
            sql.append(":" + parameterName + ", ");
        sql.replace(sql.length()-2, sql.length(), "");
        sql.append(")");
        return sql.toString();
    }

    /**
     * Sql-запрос на обновление записи в таблице
     * @return
     */
    private String createSqlUpdate(String[] parametersName){
        StringBuilder sql = new StringBuilder(String.format("UPDATE %s SET ", getTableName()));
        for(int i=0; i<getColumnsName().length; i++){
            String columnName = getColumnsName()[i];
            String parameterName = parametersName[i];
            sql.append(columnName + " = :" + parameterName + ", ");
        }
        sql.replace(sql.length()-2, sql.length(), "");
        sql.append(" WHERE id = :id");
        return sql.toString();
    }

    /**
     * Sql-запрос на удаление записи в таблице
     * @return
     */
    private String createSqlDelete(){
        StringBuilder sql = new StringBuilder("DELETE FROM ");
        sql.append(tableName);
        sql.append(" WHERE id = :id");

        return sql.toString();
    }
    //</editor-fold>

    //<editor-fold defaultState="collapsed" desc="Get/Set">
    protected NamedParameterJdbcTemplate getSpringJdbc() {
        return springJdbc;
    }

    protected RowMapper<E> getSpringMapper() {
        return springMapper;
    }

    public String getTableName() {
        return tableName;
    }

    public String[] getColumnsName() {
        return columnsName;
    }
    //</editor-fold>
}
