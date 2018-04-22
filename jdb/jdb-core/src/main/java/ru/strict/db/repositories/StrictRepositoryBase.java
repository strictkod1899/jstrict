package ru.strict.db.repositories;

import ru.strict.db.connections.StrictCreateConnectionAny;
import ru.strict.db.dto.StrictDtoBase;
import ru.strict.db.enums.StrictDataState;
import ru.strict.db.entities.StrictEntityBase;
import ru.strict.db.mappers.dto.StrictMapperDtoBase;

import java.sql.Connection;
import java.util.LinkedList;
import java.util.List;

/**
 * Базовый класс репозитория
 * @param <ID> Тип идентификатора
 * @param <SOURCE> Источник для получения соединения с базой данных
 * @param <E> Тип сущности базы данных (entity)
 * @param <DTO> Тип Dto-сущности базы данных
 */
public abstract class StrictRepositoryBase
        <ID, SOURCE extends StrictCreateConnectionAny, E extends StrictEntityBase, DTO extends StrictDtoBase>
        implements StrictRepositoryAny<ID, E, DTO>{

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
     * Кэшированный список объектов
     */
    private List<DTO> objects;

    /**
     * Текущее состояние кэшированный значений
     */
    private StrictDataState state;

    /**
     * Метка: если значение true, то идентификатор должен генерироваться на стороне базы данных,
     * иначе при создании записи id будет взято из dto-объекта
     */
    private boolean isGenerateId;

    //<editor-fold defaultState="collapsed" desc="constructors">
    public StrictRepositoryBase(SOURCE connectionSource, StrictMapperDtoBase<E, DTO> dtoMapper, boolean isGenerateId) {
        this.connectionSource = connectionSource;
        this.dtoMapper = dtoMapper;
        objects = new LinkedList<>();
        state = StrictDataState.NONE;
        this.isGenerateId = isGenerateId;
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

    public StrictDataState getState() {
        return state;
    }

    public void setState(StrictDataState state) {
        this.state = state;
    }

    public boolean isGenerateId() {
        return isGenerateId;
    }
    //</editor-fold>
}
