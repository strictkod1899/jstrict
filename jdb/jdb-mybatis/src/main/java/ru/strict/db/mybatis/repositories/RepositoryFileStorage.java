package ru.strict.db.mybatis.repositories;

import ru.strict.db.core.common.GenerateIdType;
import ru.strict.db.core.dto.DtoFileStorage;
import ru.strict.db.core.dto.DtoFileStorageBase;
import ru.strict.db.core.entities.EntityFileStorage;
import ru.strict.db.core.mappers.dto.MapperDtoBase;
import ru.strict.db.core.mappers.dto.MapperDtoFactory;
import ru.strict.db.core.repositories.interfaces.IRepositoryFileStorage;
import ru.strict.db.mybatis.connection.CreateConnectionByMybatis;
import ru.strict.db.mybatis.mappers.sql.MapperSqlFileStorage;
import ru.strict.utils.UtilClass;

public class RepositoryFileStorage<ID, DTO extends DtoFileStorageBase<ID>>
        extends RepositoryNamedBase<ID, EntityFileStorage<ID>, DTO, MapperSqlFileStorage<ID>>
        implements IRepositoryFileStorage<ID, DTO> {

    private static final String[] COLUMNS_NAME = new String[] {"filename", "extension", "displayname", "content", "filepath",
            "create_date", "type", "status"};

    public RepositoryFileStorage(CreateConnectionByMybatis connectionSource, GenerateIdType generateIdType) {
        super("file_storage",
                COLUMNS_NAME,
                connectionSource,
                UtilClass.<MapperSqlFileStorage<ID>>castClass(MapperSqlFileStorage.class),
                new MapperDtoFactory<ID>().instance(UtilClass.castClass(EntityFileStorage.class), UtilClass.castClass(DtoFileStorage.class)),
                generateIdType);
    }

    public RepositoryFileStorage(CreateConnectionByMybatis connectionSource,
                                 MapperDtoBase<ID, EntityFileStorage<ID>, DTO> dtoMapper,
                                 GenerateIdType generateIdType) {
        super("file_storage",
                COLUMNS_NAME,
                connectionSource,
                UtilClass.<MapperSqlFileStorage<ID>>castClass(MapperSqlFileStorage.class),
                dtoMapper,
                generateIdType);
    }

    @Override
    public String getColumnWithName() {
        return COLUMNS_NAME[0];
    }

    @Override
    protected Class getThisClass() {
        return this.getClass();
    }
}
