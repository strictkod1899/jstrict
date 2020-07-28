package ru.strict.db.core.repositories;

import ru.strict.db.core.common.GenerateIdType;
import ru.strict.db.core.common.SqlParameters;
import ru.strict.db.core.connections.IConnectionCreator;
import ru.strict.db.core.requests.ParameterizedRequest;
import ru.strict.db.core.requests.components.Select;
import ru.strict.db.core.requests.IParameterizedRequest;
import ru.strict.db.core.requests.components.SqlItem;
import ru.strict.db.core.requests.components.Table;
import ru.strict.patterns.mapper.IMapper;
import ru.strict.patterns.model.BaseModel;
import ru.strict.validate.Validator;

import java.sql.ResultSet;
import java.sql.SQLType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Базовый класс репозитория
 *
 * @param <ID> Тип идентификатора
 * @param <SOURCE> Источник для получения соединения с базой данных (ConnectionCreatorByDataSource,
 * ConnectionCreatorByConnectionInfo)
 * @param <MODEL> Модель сущности базы данных
 */
public abstract class BaseRepository
        <ID, CONNECTION, SOURCE extends IConnectionCreator<CONNECTION>, MODEL extends BaseModel<ID>>
        extends ConfigurableRepository<ID, CONNECTION, SOURCE, MODEL>
        implements IRepository<ID, MODEL> {

    /**
     * Наименование таблицы
     */
    private final Table table;

    /**
     * Наименование столбцов таблицы в базе данных, без учета ID
     */
    private final String[] columns;

    /**
     * Метка: если значение true, то идентификатор должен генерироваться на стороне базы данных,
     * иначе при создании записи id будет взято из объекта
     */
    private final GenerateIdType generateIdType;

    /**
     * Тип идентификатора в базе данных. Необязательный параметр. Нужен для универсальных решений
     */
    private final SQLType sqlIdType;

    private List<SqlItem> selectItems;
    private SqlItem countSelectItem;

    //<editor-fold defaultState="collapsed" desc="constructors">
    public BaseRepository(Table table,
            String[] columns,
            SOURCE connectionSource,
            GenerateIdType generateIdType,
            SQLType sqlIdType) {
        super(connectionSource, null, null);
        Validator.isNull(table, "table");
        Validator.isNull(columns, "columns");
        Validator.isNull(connectionSource, "connectionSource");
        Validator.isNull(generateIdType, "generateIdType");

        this.table = table;
        this.columns = columns;
        this.generateIdType = generateIdType;
        this.sqlIdType = sqlIdType;
    }
    //</editor-fold>

    /**
     * Получить конечный класс репозитория, от чьего имени будет вестись логирование.
     * Возвращает текущий класс.
     * Стандартная реализация:
     * return this.getClass();
     */
    protected abstract Class getThisClass();

    /**
     * Выполнить sql-запрос на изменение данных
     */
    protected abstract <ID> ID executeSql(String sql, SqlParameters parameters);

    /**
     * Выполнить sql-запрос на изменение данных
     */
    protected abstract <ID> ID executeSql(String sql,
            SqlParameters parameters,
            boolean autoGenerateKey);

    /**
     * Выполнить sql-запрос на чтение
     */
    protected abstract <T> T executeSqlRead(String sql,
            SqlParameters parameters,
            IMapper<ResultSet, T> resultMapper);

    /**
     * Выполнить sql-запрос на чтение
     */
    protected abstract <T> List<T> executeSqlReadAll(String sql,
            SqlParameters parameters,
            IMapper<ResultSet, T> resultMapper);

    //<editor-fold defaultState="collapsed" desc="CRUD">
    @Override
    public List<MODEL> readAll(String whereName, SqlParameters parameters) {
        String where = getConfiguration().getWhere(getGroup(), whereName);

        ParameterizedRequest request = new ParameterizedRequest(where, parameters);
        return readAll(request);
    }

    @Override
    public <T> List<T> readByQuery(String queryName, SqlParameters parameters, IMapper<ResultSet, T> sqlMapper) {
        String sql = getConfiguration().getQuery(getGroup(), queryName);
        return executeSqlReadAll(sql, parameters, sqlMapper);
    }

    @Override
    public void executeQuery(String queryName, SqlParameters parameters) {
        String sql = getConfiguration().getQuery(getGroup(), queryName);

        executeSql(sql, parameters);
    }

    @Override
    public final ID createOrUpdate(MODEL model) {
        Validator.isNull(model, "model");

        boolean rowExists = isRowExists(model.getId());
        if (rowExists) {
            update(model);
            return model.getId();
        } else {
            return create(model);
        }
    }

    @Override
    public final MODEL createOrRead(MODEL model) {
        Validator.isNull(model, "model");

        boolean rowExists = isRowExists(model.getId());
        if (rowExists) {
            return read(model.getId());
        } else {
            ID savedId = create(model);
            model.setId(savedId);
            return model;
        }
    }

    @Override
    public final MODEL read(ID id) {
        MODEL model = processRead(id);
        model = postRead(model);
        return model;
    }

    @Override
    public final List<MODEL> readAll(IParameterizedRequest requests) {
        List<MODEL> models = processReadAll(requests);
        models = models.stream()
                .map(this::postRead)
                .collect(Collectors.toList());
        return models;
    }

    protected abstract MODEL processRead(ID id);

    protected abstract List<MODEL> processReadAll(IParameterizedRequest requests);

    /**
     * Выполнить преобразование объекта после его чтения. Может придти объект null
     */
    protected MODEL postRead(MODEL model) {
        return model;
    }
    //</editor-fold>

    //<editor-fold defaultState="collapsed" desc="sql generate">
    /**
     * Sql-запрос на чтение записей из таблицы
     */
    protected Select createSqlSelect(IParameterizedRequest request) {
        if (selectItems == null) {
            selectItems = new ArrayList<>(columns.length+1);
            selectItems.add(new SqlItem(table, getIdColumnName()));

            for (String columnName : getColumns()) {
                selectItems.add(new SqlItem(table, columnName));
            }
        }

        return new Select(table, selectItems, request);
    }

    /**
     * Sql-запрос на чтение количества элементов в таблице
     */
    protected Select createSqlCount(IParameterizedRequest request) {
        if (countSelectItem == null) {
            countSelectItem = new SqlItem("COUNT(*)");
        }
        return new Select(table, countSelectItem, request);
    }
    //</editor-fold>

    //<editor-fold defaultState="collapsed" desc="Get/Set">
    public GenerateIdType getGenerateIdType() {
        return generateIdType;
    }

    @Override
    public Table getTable() {
        return table;
    }

    public String[] getColumns() {
        return columns;
    }

    protected SQLType getIdSqlType() {
        return sqlIdType;
    }
    //</editor-fold>

    //<editor-fold defaultState="collapsed" desc="Base override">
    @Override
    public String toString() {
        return String.format("repository [%s]. Connection: %s", getTable(), getConnectionSource().toString());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        BaseRepository<?, ?, ?, ?> that = (BaseRepository<?, ?, ?, ?>) o;
        return Objects.equals(table, that.table) &&
                Arrays.equals(columns, that.columns) &&
                generateIdType == that.generateIdType &&
                Objects.equals(sqlIdType, that.sqlIdType);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(table, generateIdType, sqlIdType);
        result = 31 * result + Arrays.hashCode(columns);
        return result;
    }
    //</editor-fold>
}
