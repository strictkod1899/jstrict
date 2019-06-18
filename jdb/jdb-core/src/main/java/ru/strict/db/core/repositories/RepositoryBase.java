package ru.strict.db.core.repositories;

import ru.strict.db.core.common.GenerateIdType;
import ru.strict.db.core.connections.ICreateConnection;
import ru.strict.db.core.requests.*;
import ru.strict.models.DtoBase;
import ru.strict.db.core.mappers.dto.MapperDtoBase;

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
    private DbTable table;

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
    public RepositoryBase(DbTable table, String[] columnsName, SOURCE connectionSource,
                          MapperDtoBase<ID, E, DTO> dtoMapper, GenerateIdType generateIdType) {
        if(table == null){
            throw new IllegalArgumentException("table is NULL");
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
        this.table = table;
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
        if(isRowExists(dto.getId())) {
            return update(dto);
        } else {
            return create(dto);
        }
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
     *      requests.addWhere(new DbWhereItem(repositoryUserOnRole.getTable(), "userx_id", dto.getId(), "="));
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
     *     requests.addWhere(new DbWhereItem(repositoryCity.getTable(), "country_id", dto.getId(), "="));
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
        requests.addWhere(new DbWhereItem(getTable(), getColumnIdName(), id, "="));

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
    protected DbSelect createSqlSelect(){
        DbSelect select = new DbSelect(table, new DbSelectItem(table, getColumnIdName()));

        for(String columnName : getColumnsName()){
            select.addSelectItem(table, columnName);
        }

        return select;
    }

    /**
     * Sql-запрос на создание записи в таблице (без учета ID)
     * @return
     */
    protected DbSelect createSqlCount(){
        return new DbSelect(table, new DbSelectItem("COUNT(1)"));
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
    public DbTable getTable() {
        return table;
    }

    public String[] getColumnsName() {
        return columnsName;
    }
    //</editor-fold>

    //<editor-fold defaultState="collapsed" desc="Base override">
    @Override
    public String toString(){
        return String.format("repository [%s]. Connection: %s", getTable(), getConnectionSource().toString());
    }

    @Override
    public boolean equals(Object obj){
        if(obj!=null && obj instanceof RepositoryBase) {
            RepositoryBase object = (RepositoryBase) obj;
            return Objects.equals(connectionSource, object.getConnectionSource())
                    && Objects.equals(dtoMapper, object.getDtoMapper())
                    && Objects.equals(table, object.getTable())
                    && Objects.equals(columnsName, object.getColumnsName())
                    && Objects.equals(generateIdType, object.getGenerateIdType());
        }else{
            return false;
        }
    }

    @Override
    public int hashCode(){
        return Objects.hash(connectionSource, dtoMapper, table, columnsName, generateIdType);
    }
    //</editor-fold>
}
