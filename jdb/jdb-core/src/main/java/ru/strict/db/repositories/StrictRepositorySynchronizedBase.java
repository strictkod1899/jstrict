package ru.strict.db.repositories;

import ru.strict.db.connections.StrictCreateConnectionAny;
import ru.strict.db.dto.StrictDtoBase;
import ru.strict.db.entities.StrictEntityBase;
import ru.strict.db.enums.StrictRepositoryDataState;
import ru.strict.db.mappers.dto.StrictMapperDtoBase;

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
public abstract class StrictRepositorySynchronizedBase
        <ID, SOURCE extends StrictCreateConnectionAny, E extends StrictEntityBase, DTO extends StrictDtoBase>
        extends StrictRepositoryBase<ID, SOURCE, E, DTO>{

    //<editor-fold defaultState="collapsed" desc="constructors">
    public StrictRepositorySynchronizedBase(String tableName, String[] columnsName, SOURCE connectionSource
            , StrictMapperDtoBase<E, DTO> dtoMapper, boolean isGenerateId) {
        super(tableName, columnsName, connectionSource, dtoMapper, isGenerateId);
    }
    //</editor-fold>

    @Override
    public synchronized DTO create(DTO dto) {
        return implementCreate(dto);
    }

    @Override
    public synchronized DTO update(DTO dto) {
        return implementUpdate(dto);
    }

    @Override
    public synchronized boolean delete(ID id) {
        return implementDelete(id);
    }

    @Override
    public synchronized DTO createOrUpdate(DTO dto) {
        return implementCreateOrUpdate(dto);
    }

    @Override
    public synchronized DTO createOrRead(DTO dto) {
        return implementCreateOrRead(dto);
    }

    public abstract DTO implementCreate(DTO dto);

    public abstract DTO implementUpdate(DTO dto);

    public abstract boolean implementDelete(ID id);

    public abstract DTO implementCreateOrUpdate(DTO dto);

    public abstract DTO implementCreateOrRead(DTO dto);
}
