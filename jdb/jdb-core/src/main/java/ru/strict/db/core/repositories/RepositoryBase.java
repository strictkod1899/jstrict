package ru.strict.db.core.repositories;

import ru.strict.db.core.common.GenerateIdType;
import ru.strict.db.core.connections.ICreateConnection;
import ru.strict.db.core.dto.DtoBase;
import ru.strict.db.core.mappers.dto.MapperDtoBase;
import ru.strict.db.core.requests.DbRequests;
import ru.strict.db.core.requests.DbWhereItem;
import ru.strict.db.core.requests.WhereType;

import java.util.List;
import java.util.Objects;

/**
 * Базовый класс репозитория
 * @param <ID> Тип идентификатора
 * @param <SOURCE> Источник для получения соединения с базой данных (CreateConnectionByDataSource, CreateConnectionByConnectionInfo)
 * @param <E> Тип сущности базы данных (entity)
 * @param <DTO> Тип Dto-сущности базы данных
 */
public abstract class RepositoryBase
        <ID, CONNECTION, SOURCE extends ICreateConnection<CONNECTION>, E, DTO extends DtoBase<ID>>
        implements IRepositoryExtension<ID, DTO> {

    /**
     * Источник подключения к базе данных (используется для получения объекта Connection),
     * является реализацией интерфейса ICreateConnection (CreateConnectionByDataSource, CreateConnectionByConnectionInfo)
     */
    private SOURCE connectionSource;

    /**
     * Маппер связанной сущности/dto
     */
    private MapperDtoBase<ID, E, DTO> dtoMapper;

    /**
     * Наименование таблицы
     */
    private String tableName;

    /**
     * Наименование столбцов таблицы в базе данных, без учета ID
     */
    private String[] columnsName;

    /**
     * Метка: если значение true, то идентификатор должен генерироваться на стороне базы данных,
     * иначе при создании записи id будет взято из dto-объекта
     */
    private GenerateIdType generateIdType;

    //<editor-fold defaultState="collapsed" desc="constructors">
    public RepositoryBase(String tableName, String[] columnsName, SOURCE connectionSource,
                          MapperDtoBase<ID, E, DTO> dtoMapper, GenerateIdType generateIdType) {
        if(tableName == null){
            throw new IllegalArgumentException("tableName is NULL");
        } else if(columnsName == null){
            throw new IllegalArgumentException("columnsName is NULL");
        } else if(connectionSource == null){
            throw new IllegalArgumentException("connectionSource is NULL");
        } else if(dtoMapper == null){
            throw new IllegalArgumentException("dtoMapper is NULL");
        } else if(generateIdType == null){
            throw new IllegalArgumentException("generateIdType is NULL");
        }

        this.connectionSource = connectionSource;
        this.dtoMapper = dtoMapper;
        this.generateIdType = generateIdType;
        this.tableName = tableName;
        this.columnsName = columnsName;
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
    public DTO createOrUpdate(DTO dto) {
        if(dto == null){
            throw new IllegalArgumentException("dto is NULL");
        }
        if(isRowExists(dto.getId()))
            return update(dto);
        else
            return create(dto);
    }

    @Override
    public DTO createOrRead(DTO dto) {
        if(dto == null){
            throw new IllegalArgumentException("dto is NULL");
        }
        if(isRowExists(dto.getId()))
            return read(dto.getId());
        else
            return create(dto);
    }
    //</editor-fold>

    //<editor-fold defaultState="collapsed" desc="CRUD extension">
    /**
     * Добавление объектов внешних ключей к прочитанной ранее сущности из базы данных.
     * Если сущность не имеет внешних ключей, то рекомендуется возвращать переданный dto-объект.
     *
     * <p><b>Пример использования:</b></p>
     * <p>Чтение ролей пользователя.
     * Пользователь содержит роли. Внешний ключи хранятся в промежуточной таблице (пользовать = роль).
     * После чтения записей промежуточной таблицы считываем все записи соответствующих ролей)</p>
     * <code><pre style="background-color: white; font-family: consolas">
     *      RepositoryJdbcBase<ID, SOURCE, EntityUserOnRole, DtoUserOnRole> repositoryUserOnRole =
     *                 new RepositoryUserOnRole(getConnectionSource(), GenerateIdType.NONE);
     *      DbRequests requests = new DbRequests(true);
     *      requests.addWhere(new DbWhereItem(repositoryUserOnRole.getTableName(), "userx_id", dto.getId(), "="));
     *      List<DtoUserOnRole> userOnRoles = repositoryUserOnRole.readAll(requests);
     *
     *      IRepository<ID, DtoRoleuser> repositoryRoleuser = new RepositoryRoleuser<>(getConnectionSource(), GenerateIdType.NONE);
     *      Collection<DtoRoleuser> roleusers = new ArrayList<>();
     *      for(DtoUserOnRole<ID> userOnRole : userOnRoles) {
     *          roleusers.add(repositoryRoleuser.read(userOnRole.getRoleId()));
     *      }
     *      dto.setRoles(roleusers);
     *      return dto;
     * </pre></code>
     * <p><b>Пример использования:</b></p>
     * <p>К одной стране относится несколько городов и необходимо получить все города связанные со страной</p>
     * <code><pre style="background-color: white; font-family: consolas">
     *     RepositoryJdbcBase<ID, SOURCE, EntityCity, DtoCity> repositoryCity =
     *             new RepositoryCity(getConnectionSource(), GenerateIdType.NONE);
     *     DbRequests requests = new DbRequests(true);
     *     requests.addWhere(new DbWhereItem(repositoryCity.getTableName(), "country_id", dto.getId(), "="));
     *
     *     List<DtoCity> cities = repositoryCity.readAll(requests);
     *     dto.setCities(cities);
     *
     *     return dto;
     * </pre></code>
     * <p><b>Пример использования:</b></p>
     * <p>Профиль относится к какому-то пользователю и содержит внешний ключ на пользователя (user)</p>
     * <code><pre style="background-color: white; font-family: consolas">
     *     IRepository<ID, DtoCountry> repositoryCountry =
     *         new RepositoryCountry(getConnectionSource(), GenerateIdType.NONE);
     *     dto.setCountry(repositoryCountry.read((ID) dto.getCountryId()));
     *     return dto;
     * </pre></code>
     * @param dto Сущность прочитанная из базы данных (без внешних ключей)
     * @return Сущность с внешними ключами
     */
    protected abstract DTO fill(DTO dto);

    @Override
    public DTO readFill(ID id) {
        DTO user = read(id);
        user = fill(user);
        return user;
    }

    @Override
    public List<DTO> readAllFill(DbRequests requests) {
        List<DTO> users = readAll(requests);
        users.stream().forEach((dto)-> dto = fill(dto));
        return users;
    }

    @Override
    public DTO createOrReadFill(DTO dto) {
        if(dto == null){
            throw new IllegalArgumentException("dto is NULL");
        }
        if(isRowExists(dto.getId()))
            return readFill(dto.getId());
        else
            return create(dto);
    }

    @Override
    public boolean isRowExists(ID id) {
        if(id == null){
            return false;
        }

        boolean result = false;

        DbRequests requests = new DbRequests();
        requests.addWhere(new DbWhereItem(getTableName(), getColumnIdName(), id, "="));

        int count = readCount(requests);
        if(count > 0){
            result = true;
        }

        return result;
    }
    //</editor-fold>

    //<editor-fold defaultState="collapsed" desc="sql generate">
    /**
     * Sql-запрос на создание записи в таблице (без учета ID)
     * @return
     */
    protected String createSqlSelect(){
        StringBuilder sql = new StringBuilder(String.format("SELECT %s.%s, ", getTableName(), getColumnIdName()));
        for(String columnName : getColumnsName()){
            sql.append(getTableName());
            sql.append(".");
            sql.append(columnName);
            sql.append(", ");
        }
        sql.replace(sql.length()-2, sql.length(), "");
        sql.append(" FROM ");
        sql.append(getTableName());

        return sql.toString();
    }

    /**
     * Sql-запрос на создание записи в таблице (без учета ID)
     * @return
     */
    protected String createSqlCount(){
        StringBuilder sql = new StringBuilder(String.format("SELECT COUNT(1) FROM %s", getTableName()));
        return sql.toString();
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

    protected MapperDtoBase<ID, E, DTO> getDtoMapper() {
        return dtoMapper;
    }

    public SOURCE getConnectionSource() {
        return connectionSource;
    }

    public GenerateIdType getGenerateIdType() {
        return generateIdType;
    }

    @Override
    public String getTableName() {
        return tableName;
    }

    public String[] getColumnsName() {
        return columnsName;
    }
    //</editor-fold>

    //<editor-fold defaultState="collapsed" desc="Base override">
    @Override
    public String toString(){
        return String.format("repository [%s]. Connection: %s", getTableName(), getConnectionSource().toString());
    }

    @Override
    public boolean equals(Object obj){
        if(obj!=null && obj instanceof RepositoryBase) {
            RepositoryBase object = (RepositoryBase) obj;
            return Objects.equals(connectionSource, object.getConnectionSource())
                    && Objects.equals(dtoMapper, object.getDtoMapper())
                    && Objects.equals(tableName, object.getTableName())
                    && Objects.equals(columnsName, object.getColumnsName())
                    && Objects.equals(generateIdType, object.getGenerateIdType());
        }else{
            return false;
        }
    }

    @Override
    public int hashCode(){
        return Objects.hash(connectionSource, dtoMapper, tableName, columnsName, generateIdType);
    }
    //</editor-fold>
}
