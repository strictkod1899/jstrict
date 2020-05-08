package ru.strict.db.core.repositories;

import ru.strict.db.core.common.GenerateIdType;
import ru.strict.db.core.connections.IConnectionCreator;
import ru.strict.db.core.requests.*;
import ru.strict.models.BaseModel;

import java.sql.SQLType;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Базовый класс репозитория
 * @param <ID> Тип идентификатора
 * @param <SOURCE> Источник для получения соединения с базой данных (ConnectionCreatorByDataSource, ConnectionCreatorByConnectionInfo)
 * @param <T> Модель сущности базы данных
 */
public abstract class BaseRepository
        <ID, CONNECTION, SOURCE extends IConnectionCreator<CONNECTION>, MODEL extends BaseModel<ID>>
        implements IExtensionRepository<ID, MODEL> {

    /**
     * Источник подключения к базе данных (используется для получения объекта Connection),
     * является реализацией интерфейса IConnectionCreator (ConnectionCreatorByDataSource, ConnectionCreatorByConnectionInfo)
     */
    private final SOURCE connectionSource;

    /**
     * Наименование таблицы
     */
    private final DbTable table;

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

    //<editor-fold defaultState="collapsed" desc="constructors">
    private void validateConstructor(DbTable table,
                                     String[] columns,
                                     SOURCE connectionSource,
                                     GenerateIdType generateIdType){
        if(table == null){
            throw new IllegalArgumentException("table is NULL");
        }
        if(columns == null){
            throw new IllegalArgumentException("columns is NULL");
        }
        if(connectionSource == null){
            throw new IllegalArgumentException("connectionSource is NULL");
        }
        if(generateIdType == null){
            throw new IllegalArgumentException("generateIdType is NULL");
        }
    }

    public BaseRepository(DbTable table,
                          String[] columns,
                          SOURCE connectionSource,
                          GenerateIdType generateIdType,
                          SQLType sqlIdType) {
        validateConstructor(table, columns, connectionSource, generateIdType);
        if(sqlIdType == null){
            throw new IllegalArgumentException("idType is NULL");
        }

        this.table = table;
        this.columns = columns;
        this.connectionSource = connectionSource;
        this.generateIdType = generateIdType;
        this.sqlIdType = sqlIdType;
    }

    public BaseRepository(DbTable table,
                          String[] columns,
                          SOURCE connectionSource,
                          GenerateIdType generateIdType) {
        validateConstructor(table, columns, connectionSource, generateIdType);

        this.table = table;
        this.columns = columns;
        this.connectionSource = connectionSource;
        this.generateIdType = generateIdType;
        this.sqlIdType = null;
    }
    //</editor-fold>

    /**
     * Возвращает конечный класс репозитория, от чьего имени будет вестись логирование.
     * Возвращает текущий класс.
     * Стандартная реализация:
     * return this.getClass();
     * @return
     */
    protected abstract Class getThisClass();

    //<editor-fold defaultState="collapsed" desc="CRUD">
    @Override
    public final ID createOrUpdate(MODEL model) {
        if(model == null){
            throw new IllegalArgumentException("model is NULL");
        }
        boolean rowExists = isRowExists(model.getId());
        if(rowExists){
            update(model);
            return model.getId();
        } else {
            return create(model);
        }
    }

    @Override
    public final MODEL createOrRead(MODEL model) {
        if(model == null){
            throw new IllegalArgumentException("model is NULL");
        }
        boolean rowExists = isRowExists(model.getId());
        if(rowExists){
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
    public final List<MODEL> readAll(DbRequests requests) {
        List<MODEL> models = processReadAll(requests);
        models = models.stream()
                .map(this::postRead)
                .collect(Collectors.toList());
        return models;
    }

    protected abstract MODEL processRead(ID id);
    protected abstract List<MODEL> processReadAll(DbRequests requests);

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
     * @param model Сущность прочитанная из базы данных (без внешних ключей)
     * @return Сущность с внешними ключами
     */
    protected abstract MODEL fill(MODEL model);

    @Override
    public final MODEL readFill(ID id) {
        MODEL model = read(id);
        if(model != null) {
            model = fill(model);
        }
        return model;
    }

    @Override
    public final List<MODEL> readAllFill(DbRequests requests) {
        List<MODEL> models = readAll(requests);
        if(models != null) {
            models.stream().forEach((model) -> model = fill(model));
        }
        return models;
    }
    //</editor-fold>

    //<editor-fold defaultState="collapsed" desc="sql generate">
    /**
     * Sql-запрос на создание записи в таблице (без учета ID)
     * @return
     */
    protected DbSelect createSqlSelect(){
        DbSelect select = new DbSelect(table, new DbSelectItem(table, getIdColumnName()));

        for(String columnName : getColumns()){
            select.addSelectItem(table, columnName);
        }

        return select;
    }

    /**
     * Sql-запрос на создание записи в таблице (без учета ID)
     * @return
     */
    protected DbSelect createSqlCount(){
        return new DbSelect(table, new DbSelectItem("COUNT(*)"));
    }
    //</editor-fold>

    //<editor-fold defaultState="collapsed" desc="Get/Set">
    /**
     * Создать соединение с базой даннных
     * @return
     */
    protected CONNECTION createConnection(){
        return connectionSource.createConnection();
    }

    public SOURCE getConnectionSource() {
        return connectionSource;
    }

    public GenerateIdType getGenerateIdType() {
        return generateIdType;
    }

    @Override
    public DbTable getTable() {
        return table;
    }

    public String[] getColumns() {
        return columns;
    }

    protected SQLType getSqlIdType(){
        return sqlIdType;
    }
    //</editor-fold>

    //<editor-fold defaultState="collapsed" desc="Base override">
    @Override
    public String toString(){
        return String.format("repository [%s]. Connection: %s", getTable(), getConnectionSource().toString());
    }

    @Override
    public boolean equals(Object obj){
        if(this == obj) return true;
        if(obj == null || getClass() != obj.getClass()) return false;

        BaseRepository object = (BaseRepository) obj;
        return Objects.equals(connectionSource, object.getConnectionSource())
                && Objects.equals(table, object.getTable())
                && Objects.equals(columns, object.getColumns())
                && Objects.equals(generateIdType, object.getGenerateIdType());
    }

    @Override
    public int hashCode(){
        return Objects.hash(connectionSource, table, columns, generateIdType);
    }
    //</editor-fold>
}
