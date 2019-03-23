package ru.strict.db.hibernate.repositories;

import ru.strict.db.core.common.GenerateIdType;
import ru.strict.db.core.dto.DtoFileStorageBase;
import ru.strict.db.core.mappers.dto.MapperDtoBase;
import ru.strict.db.hibernate.connection.CreateConnectionHibernate;
import ru.strict.db.hibernate.entities.EntityFileStorage;
import ru.strict.utils.UtilClass;

import java.io.Serializable;

public class RepositoryFileStorage<ID extends Serializable, DTO extends DtoFileStorageBase<ID>>
        extends RepositoryNamedBase<ID, EntityFileStorage<ID>, DTO> {

    private static final String[] COLUMNS_NAME = new String[] {"filename", "content", "filepath",
            "create_date", "type"};

    public RepositoryFileStorage(CreateConnectionHibernate connectionSource,
                                 MapperDtoBase<ID, EntityFileStorage<ID>, DTO> dtoMapper,
                                 GenerateIdType generateIdType) {
        super("file_storage",
                COLUMNS_NAME,
                connectionSource,
                dtoMapper,
                generateIdType);
    }

    @Override
    protected DTO fill(DTO dto){
        return dto;
    }

    @Override
    protected String getColumnWithName() {
        return COLUMNS_NAME[0];
    }

    @Override
    protected Class<EntityFileStorage<ID>> getEntityClass() {
        return UtilClass.castClass(EntityFileStorage.class);
    }

    @Override
    protected Class getThisClass() {
        return this.getClass();
    }
}
