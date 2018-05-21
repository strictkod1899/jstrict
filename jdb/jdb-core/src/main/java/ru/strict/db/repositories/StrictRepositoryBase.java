package ru.strict.db.repositories;

import ru.strict.db.connections.StrictCreateConnectionAny;
import ru.strict.db.dto.StrictDtoBase;
import ru.strict.db.entities.StrictEntityBase;
import ru.strict.db.mappers.dto.StrictMapperDtoBase;
import ru.strict.db.requests.StrictDbRequests;
import ru.strict.utils.StrictUtilClassName;
import ru.strict.utils.StrictUtilLogger;
import ru.strict.utils.components.StrictLogger;
import ru.strict.utils.StrictUtilHashCode;

import java.sql.Connection;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Базовый класс репозитория
 * @param <ID> Тип идентификатора
 * @param <SOURCE> Источник для получения соединения с базой данных,
 *                например, StrictCreateConnectionByDataSource, StrictCreateConnectionByConnectionInfo и др.
 * @param <E> Тип сущности базы данных (entity)
 * @param <DTO> Тип Dto-сущности базы данных
 */
public abstract class StrictRepositoryBase
        <ID, SOURCE extends StrictCreateConnectionAny, E extends StrictEntityBase, DTO extends StrictDtoBase>
        implements StrictRepositoryAny<ID, DTO>, StrictRepositoryExtension<ID, DTO>{

    protected final StrictLogger LOGGER = StrictUtilLogger.createLogger(StrictUtilClassName.getCurrentClassname());

    /**
     * Источник подключения к базе данных (используется для получения объекта Connection),
     * является реализацией интерфейса StrictCreateConnectionAny,
     * например, StrictCreateConnectionByDataSource, StrictCreateConnectionByConnectionInfo и др.
     */
    private SOURCE connectionSource;

    /**
     * Маппер связанной сущности/dto
     */
    private StrictMapperDtoBase<E, DTO> dtoMapper;

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
    private StrictRepositoryDataState state;

    /**
     * Метка: если значение true, то идентификатор должен генерироваться на стороне базы данных,
     * иначе при создании записи id будет взято из dto-объекта
     */
    private boolean isGenerateId;

    //<editor-fold defaultState="collapsed" desc="constructors">
    public StrictRepositoryBase(String tableName, String[] columnsName, SOURCE connectionSource
            , StrictMapperDtoBase<E, DTO> dtoMapper, boolean isGenerateId) {
        this.connectionSource = connectionSource;
        this.dtoMapper = dtoMapper;
        objects = new LinkedList<>();
        state = StrictRepositoryDataState.NONE;
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
     *      StrictRepositorySpringBase<ID, StrictEntityUserOnRole, StrictDtoUserOnRole> rUserOnRole =
     *              new StrictRepositoryUserOnRole(...);
     *      StrictDbRequests requests = new StrictDbRequests(rUserOnRole.getTableName(), true);
     *      requests.add(new StrictDbWhere(getTableName(), "id", dto.getId(), "="));
     *      List<StrictDtoUserOnRole> userOnRoles = rUserOnRole.readAll(requests);
     *
     *      StrictRepositoryAny<ID, StrictDtoRoleuser> rRoleuser = new StrictRepositoryRoleuser<>(...);
     *      for(StrictDtoUserOnRole<ID> userOnRole : userOnRoles)
     *          dto.addRoleuser(rRoleuser.read(userOnRole.getRoleId()));
     *      return dto;
     * </pre></code>
     *
     * <p><b>Пример использования:</b></p>
     * <p>Профиль относится к какому-то пользователю и содержит внешний ключ на пользователя (user)</p>
     * <code><pre style="background-color: white; font-family: consolas">
     *     StrictRepositoryAny<ID, StrictDtoUser> rUser = new StrictRepositoryUser<>(...);
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
    public List<DTO> readAllFill(StrictDbRequests requests) {
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

    protected StrictMapperDtoBase<E, DTO> getDtoMapper() {
        return dtoMapper;
    }

    public List<DTO> getObjects() {
        return objects;
    }

    protected void setObjects(List<DTO> objects) {
        this.objects = objects;
    }

    public StrictRepositoryDataState getState() {
        return state;
    }

    protected void setState(StrictRepositoryDataState state) {
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
        if(obj instanceof StrictRepositoryBase) {
            StrictRepositoryBase object = (StrictRepositoryBase) obj;
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
        return StrictUtilHashCode.createHashCode(tableName, connectionSource, state, isGenerateId, columnsName);
    }
    //</editor-fold>
}
