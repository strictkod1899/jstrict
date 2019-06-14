package ru.strict.db.hibernate.repositories;

import ru.strict.db.core.common.GenerateIdType;
import ru.strict.models.FileStorage;
import ru.strict.models.FileStorageBase;
import ru.strict.db.core.mappers.dto.MapperDtoBase;
import ru.strict.db.core.repositories.interfaces.IRepositoryFileStorage;
import ru.strict.db.hibernate.connection.CreateConnectionHibernate;
import ru.strict.db.hibernate.entities.EntityFileStorage;
import ru.strict.db.hibernate.mappers.dto.MapperDtoFactory;
import ru.strict.utils.UtilClass;

public class RepositoryFileStorage<DTO extends FileStorageBase<Long>>
        extends RepositoryHibernateBase<Long, EntityFileStorage, DTO>
        implements IRepositoryFileStorage<Long, DTO> {

    private static final String[] COLUMNS_NAME = new String[] {"filename", "extension", "displayname", "content", "filepath",
            "create_date", "type", "status"};

    public RepositoryFileStorage(CreateConnectionHibernate connectionSource,
                                 GenerateIdType generateIdType) {
        super("file_storage",
                COLUMNS_NAME,
                connectionSource,
                new MapperDtoFactory().instance(UtilClass.castClass(EntityFileStorage.class), UtilClass.castClass(FileStorage.class)),
                generateIdType);
    }

    public RepositoryFileStorage(CreateConnectionHibernate connectionSource,
                                 MapperDtoBase<Long, EntityFileStorage, DTO> dtoMapper,
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
    public String getColumnWithName() {
        return COLUMNS_NAME[0];
    }

    @Override
    protected Class<EntityFileStorage> getEntityClass() {
        return UtilClass.castClass(EntityFileStorage.class);
    }

    @Override
    protected Class getThisClass() {
        return this.getClass();
    }
}
