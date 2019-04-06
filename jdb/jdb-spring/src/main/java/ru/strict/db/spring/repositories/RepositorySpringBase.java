package ru.strict.db.spring.repositories;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import ru.strict.db.core.common.GenerateIdType;
import ru.strict.db.core.common.SqlParameter;
import ru.strict.db.core.common.SqlParameters;
import ru.strict.db.core.connections.CreateConnectionByDataSource;
import ru.strict.db.core.dto.DtoBase;
import ru.strict.db.core.entities.EntityBase;
import ru.strict.db.core.repositories.RepositoryBase;
import ru.strict.db.core.requests.DbRequests;
import ru.strict.db.core.mappers.dto.MapperDtoBase;
import ru.strict.db.spring.mappers.sql.MapperSqlCountRows;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * Базовый класс репозитория с использованием Spring Jdbc
 */
public abstract class RepositorySpringBase
        <ID, E extends EntityBase<ID>, DTO extends DtoBase<ID>>
        extends RepositoryBase<ID, Connection, CreateConnectionByDataSource, E, DTO> {

    /**
     * Экземпляр Spring Jdbc, с помощью которого, производится взаимодействие с базой данных
     */
    private NamedParameterJdbcTemplate springJdbc;

    /**
     * Объект для преобразования полученных данных из sql-запроса в объект сущности азы данных (entity)
     */
    private RowMapper<E> springMapper;

    //<editor-fold defaultState="collapsed" desc="constructors">
    public RepositorySpringBase(String tableName, String[] columnsName,
                                CreateConnectionByDataSource connectionSource,
                                MapperDtoBase<ID, E, DTO> dtoMapper,
                                RowMapper<E> springMapper,
                                GenerateIdType generateIdType) {
        super(tableName, columnsName, connectionSource, dtoMapper, generateIdType);
        this.springJdbc = new NamedParameterJdbcTemplate(getConnectionSource().getConnectionSource());
        this.springMapper = springMapper;
    }
    //</editor-fold>

    //<editor-fold defaultState="collapsed" desc="CRUD">
    @Override
    public final DTO create(DTO dto) {
        E entity = getDtoMapper().map(dto);
        MapSqlParameterSource parameters = getSpringParameters(entity);
        String sql = null;

        GenerateIdType usingGenerateIdType = getGenerateIdType();
        if(dto.getId() != null){
            usingGenerateIdType = GenerateIdType.NONE;
        }

        switch(usingGenerateIdType){
            case NUMBER:
                KeyHolder keyHolder = new GeneratedKeyHolder();
                sql = createSqlInsertShort(parameters.getParameterNames());
                springJdbc.update(sql, parameters, keyHolder);
                dto.setId((ID) keyHolder.getKey());
                break;
            case UUID:
                sql = createSqlInsertFull(parameters.getParameterNames());
                Object id = UUID.randomUUID();
                parameters.addValue(getColumnIdName(), id);
                springJdbc.update(sql, parameters);
                dto.setId((ID)id);
                break;
            case NONE:
                sql = createSqlInsertFull(parameters.getParameterNames());
                parameters.addValue(getColumnIdName(), dto.getId());
                springJdbc.update(sql, parameters);
                break;
            default:
                throw new IllegalArgumentException("Type for generate id is not determine. Entity was not created into database");
        }

        return dto;
    }

    @Override
    public final DTO read(ID id) {
        SqlParameterSource parameters = new MapSqlParameterSource(getColumnIdName(), id);
        String sql = String.format("%s WHERE %s = :%s", createSqlSelect(), getColumnIdName(), getColumnIdName());
        E entity = null;
        try {
            entity = springJdbc.queryForObject(sql, parameters, springMapper);
        }catch(EmptyResultDataAccessException ex){}
        return getDtoMapper().map(entity);
    }

    @Override
    public final List<DTO> readAll(DbRequests requests) {
        String sql = createSqlSelect() + (requests==null ? "" : " " + requests.getSql());
        List<DTO> result = new ArrayList<>();
        try {
            List<E> entities = springJdbc.query(sql, springMapper);
            for (E entity : entities) {
                result.add(getDtoMapper().map(entity));
            }
        }catch(EmptyResultDataAccessException ex){}
        return result;
    }

    @Override
    public DTO update(DTO dto) {
        E entity = getDtoMapper().map(dto);
        MapSqlParameterSource parameters = getSpringParameters(entity);
        String sql = createSqlUpdate(parameters.getParameterNames());
        parameters.addValue(getColumnIdName(), dto.getId());
        springJdbc.update(sql, parameters);
        return dto;
    }

    @Override
    public void delete(ID id) {
        String sql = createSqlDelete();
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue(getColumnIdName(), id);
        springJdbc.update(sql, parameters);
    }

    @Override
    public int readCount(DbRequests requests) {
        String sql = createSqlCount() + (requests==null ? "" : " " + requests.getSql());
        Integer result = -1;
        try{
            result = springJdbc.queryForObject(sql, new MapSqlParameterSource(), new MapperSqlCountRows());
        }catch(EmptyResultDataAccessException ex){}

        return result;
    }

    @Override
    public void executeSql(String sql) {
        springJdbc.update(sql, new MapSqlParameterSource());
    }

    //</editor-fold>

    //<editor-fold defaultState="collapsed" desc="sql generate">
    /**
     * Sql-запрос на создание записи в таблице (без учета ID)
     * @return
     */
    private String createSqlInsertShort(String[] parametersName){
        StringBuilder sql = new StringBuilder(String.format("INSERT INTO %s (", getTableName()));
        for(String columnName : getColumnsName()) {
            sql.append(columnName);
            sql.append(", ");
        }
        sql.replace(sql.length()-2, sql.length(), "");
        sql.append(") VALUES (");

        for(String parameterName : parametersName) {
            sql.append(String.format(":%s, ", parameterName));
        }
        sql.replace(sql.length()-2, sql.length(), "");
        sql.append(");");
        return sql.toString();
    }

    /**
     * Sql-запрос на создание записи в таблице (с учетом добавления ID)
     * @return
     */
    private String createSqlInsertFull(String[] parametersName){
        StringBuilder sql = new StringBuilder(String.format("INSERT INTO %s (%s, ", getTableName(), getColumnIdName()));
        for(String columnName : getColumnsName()) {
            sql.append(columnName);
            sql.append(", ");
        }
        sql.replace(sql.length()-2, sql.length(), "");
        sql.append(String.format(") VALUES (:%s, ", getColumnIdName()));

        for(String parameterName : parametersName) {
            sql.append(String.format(":%s, ", parameterName));
        }
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
            sql.append(String.format("%s = :%s, ", getColumnsName()[i], parametersName[i]));
        }
        sql.replace(sql.length()-2, sql.length(), "");
        sql.append(String.format(" WHERE %s = :%s", getColumnIdName(), getColumnIdName()));
        return sql.toString();
    }

    /**
     * Sql-запрос на удаление записи в таблице
     * @return
     */
    private String createSqlDelete(){
        StringBuilder sql = new StringBuilder("DELETE FROM ");
        sql.append(getTableName());
        sql.append(String.format(" WHERE %s = :%s", getColumnIdName(), getColumnIdName()));

        return sql.toString();
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

    /**
     * Получить параметры sql-запроса на создание/обновление записи. ID не учитывается
     * @param entity Entity-объект из которого берутся значения для параметров
     * @return
     */
    protected MapSqlParameterSource getSpringParameters(E entity){
        SqlParameters<?> parameters = getParameters(entity);
        MapSqlParameterSource result = new MapSqlParameterSource();
        for(SqlParameter parameter : parameters.getParameters()) {
            result.addValue(parameter.getColumnName(), parameter.getValue());
        }

        return result;
    }

    //<editor-fold defaultState="collapsed" desc="Get/Set">
    protected NamedParameterJdbcTemplate getSpringJdbc() {
        return springJdbc;
    }

    protected RowMapper<E> getSpringMapper() {
        return springMapper;
    }
    //</editor-fold>

    @Override
    public void close(){
        springJdbc = null;
        springMapper = null;
        super.close();
    }
}
