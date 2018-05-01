package ru.strict.db.repositories;

import ru.strict.db.connections.StrictCreateConnectionAny;
import ru.strict.db.dto.StrictDtoBase;
import ru.strict.db.enums.StrictRepositoryDataState;
import ru.strict.db.entities.StrictEntityBase;
import ru.strict.db.mappers.dto.StrictMapperDtoBase;
import ru.strict.db.requests.StrictDbRequests;

import java.sql.Connection;
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
        if(IsRowExists((ID)dto.getId()))
            return update(dto);
        else
            return create(dto);
    }

    @Override
    public DTO createOrRead(DTO dto) {
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
     * <p>Профиль относится к какому-то пользователю и содержит внешниц ключ на пользователя (user)</p>
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
        DTO user = read(id);
        user = fill(user);
        return user;
    }

    @Override
    public List<DTO> readAllFill(StrictDbRequests requests) {
        List<DTO> users = readAll(requests);
        users.stream().forEach((dto)-> dto = fill(dto));
        return users;
    }

    @Override
    public DTO createOrReadFill(DTO dto) {
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

    public StrictMapperDtoBase<E, DTO> getDtoMapper() {
        return dtoMapper;
    }

    public List<DTO> getObjects() {
        return objects;
    }

    public void setObjects(List<DTO> objects) {
        this.objects = objects;
    }

    public StrictRepositoryDataState getState() {
        return state;
    }

    public void setState(StrictRepositoryDataState state) {
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
}
