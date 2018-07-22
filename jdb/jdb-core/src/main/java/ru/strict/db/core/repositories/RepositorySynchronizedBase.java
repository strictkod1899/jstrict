package ru.strict.db.core.repositories;

import ru.strict.db.core.common.GenerateIdType;
import ru.strict.db.core.connections.ICreateConnection;
import ru.strict.db.core.dto.DtoBase;
import ru.strict.db.core.entities.EntityBase;
import ru.strict.db.core.mappers.dto.MapperDtoBase;

/**
 * Базовый класс репозитория
 * @param <ID>      Тип идентификатора
 * @param <SOURCE>  Источник для получения соединения с базой данных (CreateConnectionByDataSource, CreateConnectionByConnectionInfo)
 * @param <E>       Тип сущности базы данных (entity)
 * @param <DTO>     Тип Dto-сущности базы данных
 */
public abstract class RepositorySynchronizedBase
        <ID, CONNECTION, SOURCE extends ICreateConnection<CONNECTION>, E extends EntityBase, DTO extends DtoBase>
        extends RepositoryBase<ID, CONNECTION, SOURCE, E, DTO> {

    //<editor-fold defaultState="collapsed" desc="constructors">
    public RepositorySynchronizedBase(String tableName, String[] columnsName, SOURCE connectionSource
            , MapperDtoBase<E, DTO> dtoMapper, GenerateIdType isGenerateId) {
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
    public synchronized void delete(ID id) {
        implementDelete(id);
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

    public abstract void implementDelete(ID id);

    public abstract DTO implementCreateOrUpdate(DTO dto);

    public abstract DTO implementCreateOrRead(DTO dto);
}
