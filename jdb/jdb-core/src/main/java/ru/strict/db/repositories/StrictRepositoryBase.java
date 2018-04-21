package ru.strict.db.repositories;

import ru.strict.db.connections.StrictCreateConnectionAny;
import ru.strict.db.dto.StrictDtoBase;
import ru.strict.db.enums.StrictDataState;
import ru.strict.db.mappers.StrictMapperBase;
import ru.strict.db.entities.StrictEntityBase;

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
    private StrictMapperBase<E, DTO> dtoMapper;

    /**
     * Кэшированный список объектов
     */
    private List<DTO> objects;

    /**
     * Текущее состояние кэшированный значений
     */
    private StrictDataState state;

    //<editor-fold defaultState="collapsed" desc="constructors">
    public StrictRepositoryBase(SOURCE connectionSource, StrictMapperBase<E, DTO> dtoMapper) {
        this.connectionSource = connectionSource;
        this.dtoMapper = dtoMapper;
        objects = new LinkedList<>();
        state = StrictDataState.NONE;
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

    public StrictMapperBase<E, DTO> getDtoMapper() {
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
    //</editor-fold>
}
