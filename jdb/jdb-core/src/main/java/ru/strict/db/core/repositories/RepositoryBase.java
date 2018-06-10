package ru.strict.db.core.repositories;

import ru.strict.db.core.connections.ICreateConnection;
import ru.strict.db.core.dto.DtoBase;
import ru.strict.db.core.entities.EntityBase;
import ru.strict.db.core.mappers.dto.MapperDtoBase;
import ru.strict.db.core.requests.DbRequests;
import ru.strict.utils.UtilClassName;
import ru.strict.utils.UtilLogger;
import ru.strict.components.WrapperLogger;
import ru.strict.utils.UtilHashCode;

import java.sql.Connection;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Базовый класс репозитория
 * @param <ID> Тип идентификатора
 * @param <SOURCE> Источник для получения соединения с базой данных,
 *                например, CreateConnectionByDataSource, CreateConnectionByConnectionInfo и др.
 * @param <E> Тип сущности базы данных (entity)
 * @param <DTO> Тип Dto-сущности базы данных
 */
public abstract class RepositoryBase
        <ID, SOURCE extends ICreateConnection, E extends EntityBase, DTO extends DtoBase>
        implements IStrictRepositoryExtension<ID, DTO> {

    protected final WrapperLogger LOGGER = UtilLogger.createLogger(UtilClassName.getCurrentClassname());

    /**
     * Источник подключения к базе данных (используется для получения объекта Connection),
     * является реализацией интерфейса ICreateConnection,
     * например, CreateConnectionByDataSource, CreateConnectionByConnectionInfo и др.
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
     * Наименование столбцов таблицы в базе данных
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
    private boolean isGenerateId;

    //<editor-fold defaultState="collapsed" desc="constructors">
    public RepositoryBase(String tableName, String[] columnsName, SOURCE connectionSource
            , MapperDtoBase<E, DTO> dtoMapper, boolean isGenerateId) {
        this.connectionSource = connectionSource;
        this.dtoMapper = dtoMapper;
        objects = new LinkedList<>();
        state = RepositoryDataState.NONE;
        this.isGenerateId = isGenerateId;
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

    //<editor-fold defaultState="collapsed" desc="CRUD">
    @Override
    public DTO createOrUpdate(DTO dto) {
        LOGGER.info("Trying a db entity create or update");
        if(IsRowExists((ID)dto.getId()))
            return update(dto);
        else
            return create(dto);
    }

    @Override
    public DTO createOrRead(DTO dto) {
        LOGGER.info("Trying a db entity create or read");
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
     *      StrictRepositorySpringBase<ID, EntityUserOnRole, DtoUserOnRole> rUserOnRole =
     *              new StrictRepositoryUserOnRole(...);
     *      DbRequests requests = new DbRequests(rUserOnRole.getTableName(), true);
     *      requests.add(new DbWhere(getTableName(), "id", dto.getId(), "="));
     *      List<DtoUserOnRole> userOnRoles = rUserOnRole.readAll(requests);
     *
     *      IRepository<ID, DtoRoleuser> rRoleuser = new StrictRepositoryRoleuser<>(...);
     *      for(DtoUserOnRole<ID> userOnRole : userOnRoles)
     *          dto.addRoleuser(rRoleuser.read(userOnRole.getRoleId()));
     *      return dto;
     * </pre></code>
     *
     * <p><b>Пример использования:</b></p>
     * <p>Профиль относится к какому-то пользователю и содержит внешний ключ на пользователя (user)</p>
     * <code><pre style="background-color: white; font-family: consolas">
     *     IRepository<ID, DtoUser> rUser = new StrictRepositoryUser<>(...);
     *     dto.setUser(rUser.read((ID) dto.getUserId()));
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
    //</editor-fold>

    //<editor-fold defaultState="collapsed" desc="Get/Set">
    /**
     * Создать соединение с базой даннных
     * @return
     */
    protected Connection createConnection(){
        return connectionSource.createConnection();
    }

    public SOURCE getConnectionSource() {
        return connectionSource;
    }

    protected MapperDtoBase<E, DTO> getDtoMapper() {
        return dtoMapper;
    }

    public List<DTO> getObjects() {
        return objects;
    }

    protected void setObjects(List<DTO> objects) {
        this.objects = objects;
    }

    public RepositoryDataState getState() {
        return state;
    }

    protected void setState(RepositoryDataState state) {
        this.state = state;
    }

    public boolean isGenerateId() {
        return isGenerateId;
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
                    && isGenerateId == object.isGenerateId()
                    && (columnsName.length == object.getColumnsName().length
                            && Arrays.asList(columnsName).containsAll(Arrays.asList(object.getColumnsName())));
        }else
            return false;
    }

    @Override
    public int hashCode(){
        return UtilHashCode.createHashCode(tableName, connectionSource, state, isGenerateId, columnsName);
    }
    //</editor-fold>
}
