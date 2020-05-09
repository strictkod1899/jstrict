package ru.strict.db.core.repositories;

import ru.strict.db.core.common.GenerateIdType;
import ru.strict.db.core.connections.IConnectionCreator;
import ru.strict.db.core.requests.components.Select;
import ru.strict.db.core.requests.IParameterizedRequest;
import ru.strict.db.core.requests.components.SqlItem;
import ru.strict.db.core.requests.components.Table;
import ru.strict.models.BaseModel;
import ru.strict.validate.Validator;

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
        implements IExtensionRepository<ID, MODEL> {

    /**
     * Источник подключения к базе данных (используется для получения объекта Connection),
     * является реализацией интерфейса IConnectionCreator (ConnectionCreatorByDataSource,
     * ConnectionCreatorByConnectionInfo)
     */
    private final SOURCE connectionSource;

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
            GenerateIdType generateIdType) {
        this(table, columns, connectionSource, generateIdType, null);
    }

    public BaseRepository(Table table,
            String[] columns,
            SOURCE connectionSource,
            GenerateIdType generateIdType,
            SQLType sqlIdType) {
        Validator.isNull(table, "table")
                .isNull(columns, "columns")
                .isNull(connectionSource, "connectionSource")
                .isNull(generateIdType, "generateIdType")
                .onThrow();

        this.table = table;
        this.columns = columns;
        this.connectionSource = connectionSource;
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

    //<editor-fold defaultState="collapsed" desc="CRUD">
    @Override
    public final ID createOrUpdate(MODEL model) {
        Validator.isNull(model, "model").onThrow();

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
        Validator.isNull(model, "model").onThrow();

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

    //<editor-fold defaultState="collapsed" desc="CRUD extension">
    /**
     * Добавление объектов внешних ключей к прочитанной ранее сущности из базы данных.
     * Если сущность не имеет внешних ключей, то рекомендуется возвращать переданный объект.
     *
     * <p><b>Пример использования:</b></p>
     * <p>Чтение ролей пользователя.
     * Пользователь содержит роли. Внешний ключи хранятся в промежуточной таблице (пользовать = роль).
     * После чтения записей промежуточной таблицы считываем все записи соответствующих ролей)</p>
     * <code><pre style="background-color: white; font-family: consolas">
     *      RepositoryJdbcBase<ID, SOURCE, UserOnRole> repositoryUserOnRole =
     *                 new RepositoryUserOnRole(getConnectionSource(), GenerateIdType.NONE);
     *      DbRequests requests = new DbRequests(true);
     *      requests.addWhere(new DbWhereItem(repositoryUserOnRole.getTable(), "userx_id", model.getId(), "="));
     *      List<UserOnRole> userOnRoles = repositoryUserOnRole.readAll(requests);
     *
     *      IRepository<ID, Role> repositoryRole = new RepositoryRole<>(getConnectionSource(), GenerateIdType.NONE);
     *      Collection<Role> roles = new ArrayList<>();
     *      for(UserOnRole<ID> userOnRole : userOnRoles) {
     *          roles.add(repositoryRole.read(userOnRole.getRoleId()));
     *      }
     *      model.setRoles(roleus);
     *      return model;
     * </pre></code>
     * <p><b>Пример использования:</b></p>
     * <p>К одной стране относится несколько городов и необходимо получить все города связанные со страной</p>
     * <code><pre style="background-color: white; font-family: consolas">
     *     RepositoryJdbcBase<ID, SOURCE, City> repositoryCity =
     *             new RepositoryCity(getConnectionSource(), GenerateIdType.NONE);
     *     DbRequests requests = new DbRequests(true);
     *     requests.addWhere(new DbWhereItem(repositoryCity.getTable(), "country_id", model.getId(), "="));
     *
     *     List<City> cities = repositoryCity.readAll(requests);
     *     model.setCities(cities);
     *
     *     return model;
     * </pre></code>
     * <p><b>Пример использования:</b></p>
     * <p>Профиль относится к какому-то пользователю и содержит внешний ключ на пользователя (user)</p>
     * <code><pre style="background-color: white; font-family: consolas">
     *     IRepository<ID, Country> repositoryCountry =
     *         new RepositoryCountry(getConnectionSource(), GenerateIdType.NONE);
     *     model.setCountry(repositoryCountry.read((ID) model.getCountryId()));
     *     return model;
     * </pre></code>
     *
     * @param model Сущность прочитанная из базы данных (без внешних ключей)
     * @return Сущность с внешними ключами
     */
    protected abstract MODEL fill(MODEL model);

    @Override
    public final MODEL readFill(ID id) {
        MODEL model = read(id);
        if (model != null) {
            model = fill(model);
        }
        return model;
    }

    @Override
    public final List<MODEL> readAllFill(IParameterizedRequest requests) {
        List<MODEL> models = readAll(requests);
        if (models != null) {
            models.stream().forEach((model) -> model = fill(model));
        }
        return models;
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
    /**
     * Создать соединение с базой даннных
     *
     * @return
     */
    protected CONNECTION createConnection() {
        return connectionSource.createConnection();
    }

    public SOURCE getConnectionSource() {
        return connectionSource;
    }

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

    protected SQLType getSqlIdType() {
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
        return Objects.equals(connectionSource, that.connectionSource) &&
                Objects.equals(table, that.table) &&
                Arrays.equals(columns, that.columns) &&
                generateIdType == that.generateIdType &&
                Objects.equals(sqlIdType, that.sqlIdType);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(connectionSource, table, generateIdType, sqlIdType);
        result = 31 * result + Arrays.hashCode(columns);
        return result;
    }

    //</editor-fold>
}
