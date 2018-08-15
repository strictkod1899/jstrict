package ru.strict.db.core.repositories;

import ru.strict.db.core.common.GenerateIdType;
import ru.strict.db.core.connections.ICreateConnection;
import ru.strict.db.core.dto.DtoBase;
import ru.strict.db.core.mappers.dto.MapperDtoBase;
import ru.strict.db.core.requests.DbRequests;
import ru.strict.utils.UtilLogger;
import ru.strict.components.WrapperLogger;
import ru.strict.utils.UtilHashCode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Базовый класс репозитория
 * @param <ID> Тип идентификатора
 * @param <SOURCE> Источник для получения соединения с базой данных (CreateConnectionByDataSource, CreateConnectionByConnectionInfo)
 * @param <E> Тип сущности базы данных (entity)
 * @param <DTO> Тип Dto-сущности базы данных
 */
public abstract class RepositoryBase
        <ID, CONNECTION, SOURCE extends ICreateConnection<CONNECTION>, E, DTO extends DtoBase>
        implements IRepositoryExtension<ID, DTO> {

    protected final WrapperLogger LOGGER = UtilLogger.createLogger(getThisClass());

    /**
     * Источник подключения к базе данных (используется для получения объекта Connection),
     * является реализацией интерфейса ICreateConnection (CreateConnectionByDataSource, CreateConnectionByConnectionInfo)
     */
    private SOURCE connectionSource;

    /**
     * Маппер связанной сущности/dto
     */
    private MapperDtoBase<E, DTO> dtoMapper;

    /**
     * Наименование таблицы
     */
    private String tableName;

    /**
     * Наименование столбцов таблицы в базе данных, без учета ID
     */
    private String[] columnsName;

    /**
     * Кэшированный список объектов
     */
    private List<DTO> objects;

    /**
     * Текущее состояние кэшированный значений
     */
    private RepositoryDataState state;

    /**
     * Метка: если значение true, то идентификатор должен генерироваться на стороне базы данных,
     * иначе при создании записи id будет взято из dto-объекта
     */
    private GenerateIdType generateIdType;

    //<editor-fold defaultState="collapsed" desc="constructors">
    public RepositoryBase(String tableName, String[] columnsName, SOURCE connectionSource,
                          MapperDtoBase<E, DTO> dtoMapper, GenerateIdType generateIdType) {
        if(tableName == null){
            throw new NullPointerException("tableName is NULL");
        } else if(columnsName == null){
            throw new NullPointerException("columnsName is NULL");
        } else if(connectionSource == null){
            throw new NullPointerException("connectionSource is NULL");
        } else if(dtoMapper == null){
            throw new NullPointerException("dtoMapper is NULL");
        } else if(generateIdType == null){
            throw new NullPointerException("generateIdType is NULL");
        }

        this.connectionSource = connectionSource;
        this.dtoMapper = dtoMapper;
        objects = new LinkedList<>();
        state = RepositoryDataState.NONE;
        this.generateIdType = generateIdType;
        this.tableName = tableName;
        this.columnsName = columnsName;
    }
    //</editor-fold>

    /**
     * Проверить существование записи в базе данных с переданным идентификатором
     * @param id
     * @return
     */
    public abstract boolean IsRowExists(ID id);

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
        LOGGER.info("Trying a db entity create or update");
        if(dto == null){
            throw new NullPointerException("dto is NULL");
        }
        if(IsRowExists((ID)dto.getId()))
            return update(dto);
        else
            return create(dto);
    }

    @Override
    public DTO createOrRead(DTO dto) {
        LOGGER.info("Trying a db entity create or read");
        if(dto == null){
            throw new NullPointerException("dto is NULL");
        }
        if(IsRowExists((ID)dto.getId()))
            return read((ID)dto.getId());
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
     *      DbRequests requests = new DbRequests(repositoryUserOnRole.getTableName(), true);
     *      requests.add(new DbWhere(repositoryUserOnRole.getTableName(), "userx_id", dto.getId(), "="));
     *      List<DtoUserOnRole> userOnRoles = repositoryUserOnRole.readAll(requests);
     *
     *      IRepository<ID, DtoRoleuser> repositoryRoleuser = new RepositoryRoleuser<>(getConnectionSource(), GenerateIdType.NONE);
     *      Collection<DtoRoleuser> roleusers = new LinkedList<>();
     *      for(DtoUserOnRole<ID> userOnRole : userOnRoles) {
     *          roleusers.add(repositoryRoleuser.read(userOnRole.getRoleId()));
     *      }
     *      dto.setRolesuser(roleusers);
     *      return dto;
     * </pre></code>
     * <p><b>Пример использования:</b></p>
     * <p>К одной стране относится несколько городов и необходимо получить все города связанные со страной</p>
     * <code><pre style="background-color: white; font-family: consolas">
     *     RepositoryJdbcBase<ID, SOURCE, EntityCity, DtoCity> repositoryCity =
     *             new RepositoryCity(getConnectionSource(), GenerateIdType.NONE);
     *     DbRequests requests = new DbRequests(repositoryCity.getTableName(), true);
     *     requests.add(new DbWhere(repositoryCity.getTableName(), "country_id", dto.getId(), "="));
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
        LOGGER.info("Trying a db entity read");
        DTO user = read(id);
        user = fill(user);
        LOGGER.info("Successful a db entity read");
        return user;
    }

    @Override
    public List<DTO> readAllFill(DbRequests requests) {
        LOGGER.info("Trying a db entity read all");
        List<DTO> users = readAll(requests);
        users.stream().forEach((dto)-> dto = fill(dto));
        LOGGER.info("Successful a db entity read all");
        return users;
    }

    @Override
    public DTO createOrReadFill(DTO dto) {
        LOGGER.info("Trying a db entity create or read");
        if(dto == null){
            throw new NullPointerException("dto is NULL");
        }
        if(IsRowExists((ID)dto.getId()))
            return readFill((ID)dto.getId());
        else
            return create(dto);
    }
    //</editor-fold>

    //<editor-fold defaultState="collapsed" desc="sql generate">
    /**
     * Sql-запрос на создание записи в таблице (без учета ID)
     * @return
     */
    protected String createSqlSelect(){
        StringBuilder sql = new StringBuilder("SELECT " + getTableName() + ".id, ");
        for(String columnName : getColumnsName())
            sql.append(getTableName() + "."  + columnName + ", ");
        sql.replace(sql.length()-2, sql.length(), "");
        sql.append(" FROM " + getTableName());

        return sql.toString();
    }

    /**
     * Sql-запрос на создание записи в таблице (без учета ID)
     * @return
     */
    protected String createSqlCount(){
        StringBuilder sql = new StringBuilder("SELECT COUNT(*) FROM " + getTableName());
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

    protected MapperDtoBase<E, DTO> getDtoMapper() {
        return dtoMapper;
    }

    protected void setObjects(List<DTO> objects) {
        if(objects == null){
            throw new NullPointerException();
        }

        this.objects = objects;
    }

    protected void setState(RepositoryDataState state) {
        if(state == null){
            throw new NullPointerException();
        }

        this.state = state;
    }

    public SOURCE getConnectionSource() {
        return connectionSource;
    }

    public List<DTO> getObjects() {
        return objects;
    }

    public RepositoryDataState getState() {
        return state;
    }

    public GenerateIdType getGenerateIdType() {
        return generateIdType;
    }

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
            return super.equals(object) && tableName.equals(object.getTableName())
                    && connectionSource.equals(connectionSource) && state.equals(object.getState())
                    && generateIdType == object.getGenerateIdType()
                    && (columnsName.length == object.getColumnsName().length
                            && Arrays.asList(columnsName).containsAll(Arrays.asList(object.getColumnsName())));
        }else
            return false;
    }

    @Override
    public int hashCode(){
        return UtilHashCode.createHashCode(tableName, connectionSource, state, generateIdType, columnsName);
    }
    //</editor-fold>
}
