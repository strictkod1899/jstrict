package ru.strict.db.jdbc.repositories;

import ru.strict.db.core.common.GenerateIdType;
import ru.strict.db.core.configuration.SqlConfiguration;
import ru.strict.db.core.connections.IConnectionCreator;
import ru.strict.db.core.repositories.BaseRepository;
import ru.strict.db.core.requests.IParameterizedRequest;
import ru.strict.db.core.requests.components.Select;
import ru.strict.db.core.requests.components.Table;
import ru.strict.db.core.requests.components.Where;
import ru.strict.db.jdbc.mappers.sql.BaseSqlMapper;
import ru.strict.db.core.common.SqlParameter;
import ru.strict.db.core.common.SqlParameters;
import ru.strict.db.jdbc.mappers.sql.CountSqlMapper;
import ru.strict.db.jdbc.utils.JdbcUtil;
import ru.strict.patterns.BaseModel;
import ru.strict.patterns.IMapper;
import java.sql.*;
import java.util.*;

import static ru.strict.db.jdbc.utils.JdbcUtil.shiftParameters;

/**
 * Базовый класс репозитория с использованием Jdbc
 *
 * @param <ID> Тип идентификатора
 * @param <T> Тип сущности базы данных
 */
public abstract class JdbcRepository
        <ID, T extends BaseModel<ID>>
        extends BaseRepository<ID, Connection, IConnectionCreator<Connection>, T> {

    private static final CountSqlMapper COUNT_MAPPER = new CountSqlMapper();

    /**
     * Объект для преобразования полученных данных из sql-запроса в объект сущности базы данных (model)
     */
    private BaseSqlMapper<T> sqlMapper;

    private String sqlInsertWithoutId;
    private String sqlInsertWithId;
    private String sqlUpdate;
    private String sqlDeleteById;
    private String sqlWhereById;

    //<editor-fold defaultState="collapsed" desc="constructors">
    public JdbcRepository(Table table,
            String[] columns,
            IConnectionCreator<Connection> connectionSource,
            BaseSqlMapper<T> sqlMapper,
            GenerateIdType generateIdType) {
        this(table, columns, connectionSource, sqlMapper, generateIdType, null, null, null);
    }

    /**
     * Если используется, этот конструктор, то необходимо вручную вызвать метод setSqlMapper
     */
    protected JdbcRepository(Table table,
            String[] columns,
            IConnectionCreator<Connection> connectionSource,
            GenerateIdType generateIdType,
            SQLType sqlIdType) {
        this(table, columns, connectionSource, null, generateIdType, sqlIdType, null, null);
    }

    public JdbcRepository(Table table,
            String[] columns,
            IConnectionCreator<Connection> connectionSource,
            BaseSqlMapper<T> sqlMapper,
            GenerateIdType generateIdType,
            SQLType sqlIdType,
            SqlConfiguration configuration,
            String group) {
        super(table, columns, connectionSource, generateIdType, sqlIdType, configuration, group);

        this.sqlMapper = sqlMapper;
    }
    //</editor-fold>

    //<editor-fold defaultState="collapsed" desc="CRUD">
    @Override
    public final ID create(T model) {
        SqlParameters parameters = getParameters(model);;
        String sql;

        ID generatedId;
        GenerateIdType generateIdType = getGenerateIdType();

        switch (generateIdType) {
            case INT:
            case LONG:
                sql = getSqlInsertWithoutId(parameters.size());

                generatedId = executeSql(sql, parameters, true);
                break;
            case UUID:
                shiftParameters(parameters, 1);
                generatedId = (ID) UUID.randomUUID();
                parameters.set(0, getIdColumnName(), generatedId);
                sql = getSqlInsertWithId(parameters.size() - 1);

                executeSql(sql, parameters);
                break;
            case NONE:
                shiftParameters(parameters, 1);
                parameters.set(0, getIdColumnName(), model.getId());
                sql = getSqlInsertWithId(parameters.size() - 1);

                executeSql(sql, parameters);
                generatedId = model.getId();
                break;
            default:
                throw new UnsupportedOperationException(
                        "Type for generate id is not determine. Entity was not created into");
        }

        return generatedId;
    }

    @Override
    protected final T processRead(ID id) {
        Select select = createSqlSelect(
                new Where(
                        getWhereById(),
                        new SqlParameters(new SqlParameter<ID>(0, getIdColumnName(), id, getSqlIdType()))
                )
        );

        SqlParameter<ID> parameter = new SqlParameter<>(0, getIdColumnName(), id, getSqlIdType());
        SqlParameters parameters = new SqlParameters(parameter);

        return executeSqlRead(select.getParameterizedSql(), parameters, sqlMapper);
    }

    @Override
    protected final List<T> processReadAll(IParameterizedRequest requests) {
        Select select = createSqlSelect(requests);
        return executeSqlReadAll(select.getParameterizedSql(), select.getParameters(), sqlMapper);
    }

    @Override
    public final void update(T model) {
        String sql = getSqlUpdate();
        SqlParameters parameters = getParameters(model);
        parameters.addLast(getIdColumnName(), model.getId());

        executeSql(sql, parameters);
    }

    @Override
    public final void delete(ID id) {
        String sql = getSqlDelete();
        SqlParameters parameters = new SqlParameters();
        parameters.addLast(getIdColumnName(), id);

        executeSql(sql, parameters);
    }
    //</editor-fold>

    @Override
    public final long readCount(IParameterizedRequest request) {
        Select select = createSqlCount(request);
        return executeSqlRead(select.getParameterizedSql(), select.getParameters(), COUNT_MAPPER);
    }

    //<editor-fold defaultState="collapsed" desc="executeSql">
    @Override
    protected final <ID> ID executeSql(String sql, SqlParameters parameters) {
        return executeSql(sql, parameters, false);
    }

    @Override
    protected final <ID> ID executeSql(String sql,
            SqlParameters parameters,
            boolean autoGenerateKey) {
        return JdbcUtil.<ID>executeSql(sql, parameters, this::createConnection, autoGenerateKey);
    }

    @Override
    protected final <T> T executeSqlRead(String sql,
            SqlParameters parameters,
            IMapper<ResultSet, T> resultMapper) {
        return JdbcUtil.<T>executeSqlRead(sql, parameters, resultMapper, this::createConnection);
    }

    @Override
    protected final <T> List<T> executeSqlReadAll(String sql,
            SqlParameters parameters,
            IMapper<ResultSet, T> resultMapper) {
        return JdbcUtil.<T>executeSqlReadAll(sql, parameters, resultMapper, this::createConnection);
    }
    //</editor-fold>

    //<editor-fold defaultState="collapsed" desc="sql generate">

    /**
     * Sql-запрос на создание записи в таблице (без учета ID)
     */
    private String getSqlInsertWithoutId(int parametersCount) {
        if (sqlInsertWithoutId == null) {
            StringBuilder sql = new StringBuilder(String.format("INSERT INTO %s (", getTable().getTableName()));
            for (String columnName : getColumns()) {
                sql.append(columnName);
                sql.append(", ");
            }
            sql.replace(sql.length() - 2, sql.length(), "");
            sql.append(") VALUES (");

            for (int i = 0; i < parametersCount; i++) {
                sql.append("?, ");
            }
            sql.replace(sql.length() - 2, sql.length(), "");
            sql.append(");");
            sqlInsertWithoutId = sql.toString();
        }

        return sqlInsertWithoutId;
    }

    /**
     * Sql-запрос на создание записи в таблице (с учетом добавления ID)
     */
    private String getSqlInsertWithId(int parametersCount) {
        if (sqlInsertWithId == null) {
            StringBuilder sql =
                    new StringBuilder(String.format("INSERT INTO %s (%s, ",
                            getTable().getTableName(),
                            getIdColumnName()));
            for (String columnName : getColumns()) {
                sql.append(columnName);
                sql.append(", ");
            }
            sql.replace(sql.length() - 2, sql.length(), "");
            sql.append(") VALUES (?, ");

            for (int i = 0; i < parametersCount; i++) {
                sql.append("?, ");
            }
            sql.replace(sql.length() - 2, sql.length(), "");
            sql.append(")");
            sqlInsertWithId = sql.toString();
        }

        return sqlInsertWithId;
    }

    /**
     * Sql-запрос на обновление записи в таблице
     */
    private String getSqlUpdate() {
        if (sqlUpdate == null) {
            StringBuilder sql = new StringBuilder(String.format("UPDATE %s SET ", getTable().getTableName()));
            for (int i = 0; i < getColumns().length; i++) {
                sql.append(getColumns()[i]);
                sql.append(" = ?, ");
            }
            sql.replace(sql.length() - 2, sql.length(), "");
            sql.append(" WHERE ");
            sql.append(getIdColumnName());
            sql.append(" = ?");
            sqlUpdate = sql.toString();
        }
        return sqlUpdate;
    }

    /**
     * Sql-запрос на удаление записи в таблице по id
     */
    private String getSqlDelete() {
        if (sqlDeleteById == null) {
            sqlDeleteById = String.format("DELETE FROM %s WHERE %s = ?", getTable().getTableName(), getIdColumnName());
        }
        return sqlDeleteById;
    }

    private String getWhereById() {
        if (sqlWhereById == null) {
            sqlWhereById = String.format("%s = ?", getIdColumnName());
        }

        return sqlWhereById;
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
     *
     * @param model Объект из которого берутся значения для параметров
     * @return
     */
    protected abstract SqlParameters getParameters(T model);

    //<editor-fold defaultState="collapsed" desc="Get/Set">
    protected void setSqlMapper(BaseSqlMapper<T> sqlMapper) {
        this.sqlMapper = sqlMapper;
    }

    public BaseSqlMapper<T> getSqlMapper() {
        return sqlMapper;
    }
    //</editor-fold>
}
