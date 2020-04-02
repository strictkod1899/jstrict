package ru.strict.db.spring.repositories;

import org.springframework.dao.EmptyResultDataAccessException;
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
import ru.strict.db.core.requests.DbTable;
import ru.strict.db.core.repositories.RepositoryBase;
import ru.strict.db.core.requests.DbRequests;
import ru.strict.db.spring.mappers.sql.MapperSpringCountRows;
import ru.strict.models.ModelBase;
import ru.strict.utils.UtilData;

import java.sql.Connection;
import java.sql.SQLType;
import java.util.*;

/**
 * Базовый класс репозитория с использованием Spring Jdbc
 */
public abstract class RepositorySpringBase<ID, T extends ModelBase<ID>>
        extends RepositoryBase<ID, Connection, CreateConnectionByDataSource, T> {

    /**
     * Экземпляр Spring Jdbc, с помощью которого, производится взаимодействие с базой данных
     */
    private final NamedParameterJdbcTemplate springJdbc;

    /**
     * Объект для преобразования полученных данных из sql-запроса в объект сущности базы данных (model)
     */
    private RowMapper<T> springMapper;

    //<editor-fold defaultState="collapsed" desc="constructors">
    private void initialize(RowMapper<T> springMapper){
        if(springMapper == null){
            throw new IllegalArgumentException("springMapper is NULL");
        }

        this.springMapper = springMapper;
    }

    public RepositorySpringBase(DbTable table,
                                String[] columns,
                                CreateConnectionByDataSource connectionSource,
                                RowMapper<T> springMapper,
                                GenerateIdType generateIdType,
                                SQLType sqlIdType) {
        super(table, columns, connectionSource, generateIdType, sqlIdType);
        initialize(springMapper);
        this.springJdbc = new NamedParameterJdbcTemplate(getConnectionSource().getConnectionSource());
    }

    public RepositorySpringBase(DbTable table,
                                String[] columns,
                                CreateConnectionByDataSource connectionSource,
                                RowMapper<T> springMapper,
                                GenerateIdType generateIdType) {
        super(table, columns, connectionSource, generateIdType);
        initialize(springMapper);
        this.springJdbc = new NamedParameterJdbcTemplate(getConnectionSource().getConnectionSource());
    }

    RepositorySpringBase(DbTable table,
                         String[] columns,
                         CreateConnectionByDataSource connectionSource,
                         GenerateIdType generateIdType,
                         SQLType sqlIdType) {
        super(table, columns, connectionSource, generateIdType, sqlIdType);
        this.springJdbc = new NamedParameterJdbcTemplate(getConnectionSource().getConnectionSource());
    }
    //</editor-fold>

    //<editor-fold defaultState="collapsed" desc="CRUD">
    @Override
    public final ID create(T model) {
        MapSqlParameterSource parameters = getSpringParameters(model);
        String sql = null;
        GenerateIdType usingGenerateIdType = getGenerateIdType();

        ID generatedId = null;

        if(model.getId() != null){
            usingGenerateIdType = GenerateIdType.NONE;
            generatedId = model.getId();
        }

        switch(usingGenerateIdType){
            case INT:
                KeyHolder keyHolder = new GeneratedKeyHolder();
                sql = createSqlInsertShort(parameters.getParameterNames());
                springJdbc.update(sql, parameters, keyHolder);
                generatedId = (ID) Integer.valueOf(String.valueOf(keyHolder.getKey()));
                break;
            case LONG:
                keyHolder = new GeneratedKeyHolder();
                sql = createSqlInsertShort(parameters.getParameterNames());
                springJdbc.update(sql, parameters, keyHolder);
                generatedId = (ID) Long.valueOf(String.valueOf(keyHolder.getKey()));
                break;
            case UUID:
                sql = createSqlInsertFull(parameters.getParameterNames());
                generatedId = (ID) UUID.randomUUID();
                parameters.addValue(getColumnIdName(), generatedId);
                springJdbc.update(sql, parameters);
                break;
            case NONE:
                sql = createSqlInsertFull(parameters.getParameterNames());
                parameters.addValue(getColumnIdName(), model.getId());
                springJdbc.update(sql, parameters);
                break;
            default:
                throw new IllegalArgumentException("Type for generate id is not determine. Entity was not created into database");
        }

        return generatedId;
    }

    @Override
    public final T read(ID id) {
        SqlParameterSource parameters = new MapSqlParameterSource(getColumnIdName(), id);
        String sql = String.format("%s WHERE %s = :%s", createSqlSelect().getSql(), getColumnIdName(), getColumnIdName());
        try {
            return springJdbc.queryForObject(sql, parameters, springMapper);
        }catch(EmptyResultDataAccessException ex){
            return null;
        }
    }

    @Override
    public final List<T> readAll(DbRequests requests) {
        String sqlRequests = getSpringParametrizedSql(requests);
        String sql = String.format("%s %s", createSqlSelect().getSql(), UtilData.nullToEmpty(sqlRequests)).trim();
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        if(requests != null){
            SqlParameters<?> requestParameters = requests.getParameters();
            for(SqlParameter parameter : requestParameters.getParameters()){
                parameters.addValue(parameter.getName(), parameter.getValue());
            }
        }

        try {
            return springJdbc.query(sql, parameters, springMapper);
        }catch(EmptyResultDataAccessException ex){
            return new ArrayList<>();
        }
    }

    @Override
    public void update(T model) {
        MapSqlParameterSource parameters = getSpringParameters(model);
        String sql = createSqlUpdate(parameters.getParameterNames());
        parameters.addValue(getColumnIdName(), model.getId());
        springJdbc.update(sql, parameters);
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
        String sqlRequests = getSpringParametrizedSql(requests);
        String sql = createSqlCount() + " " +  UtilData.nullToEmpty(sqlRequests);

        MapSqlParameterSource parameters = new MapSqlParameterSource();
        if(requests != null){
            SqlParameters<?> requestParameters = requests.getParameters();
            for(SqlParameter parameter : requestParameters.getParameters()){
                parameters.addValue(parameter.getName(), parameter.getValue());
            }
        }

        Integer result = -1;
        try{
            result = springJdbc.queryForObject(sql, parameters, new MapperSpringCountRows());
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
        StringBuilder sql = new StringBuilder(String.format("INSERT INTO %s (", getTable().getTableName()));
        for(String columnName : getColumns()) {
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
        StringBuilder sql = new StringBuilder(String.format("INSERT INTO %s (%s, ", getTable().getTableName(), getColumnIdName()));
        for(String columnName : getColumns()) {
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
        StringBuilder sql = new StringBuilder(String.format("UPDATE %s SET ", getTable().getTableName()));
        for(int i = 0; i< getColumns().length; i++){
            sql.append(String.format("%s = :%s, ", getColumns()[i], parametersName[i]));
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
        sql.append(getTable().getTableName());
        sql.append(String.format(" WHERE %s = :%s", getColumnIdName(), getColumnIdName()));

        return sql.toString();
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

    /**
     * Получить параметры sql-запроса на создание/обновление записи. ID не учитывается
     * @param model Объект из которого берутся значения для параметров
     * @return
     */
    protected MapSqlParameterSource getSpringParameters(T model){
        SqlParameters<?> parameters = getParameters(model);
        MapSqlParameterSource result = new MapSqlParameterSource();
        for(SqlParameter parameter : parameters.getParameters()) {
            result.addValue(parameter.getName(), parameter.getValue());
        }

        return result;
    }

    private String getSpringParametrizedSql(DbRequests requests){
        String result = null;

        if(requests != null){
            SqlParameters parameters = requests.getParameters();
            result = requests.getParametrizedSql();
            int parameterIndex = 0;
            while (result.contains("?")){
                result = result.replaceFirst("\\?", String.format(":%s", parameters.getByIndex(parameterIndex).getName()));
                parameterIndex++;
            }
        }

        return result;
    }

    //<editor-fold defaultState="collapsed" desc="Get/Set">
    protected NamedParameterJdbcTemplate getSpringJdbc() {
        return springJdbc;
    }

    protected RowMapper<T> getSpringMapper() {
        return springMapper;
    }

    void setSpringMapper(RowMapper<T> springMapper){
        this.springMapper = springMapper;
    }
    //</editor-fold>
}
