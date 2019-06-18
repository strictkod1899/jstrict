package ru.strict.db.spring.repositories;

import ru.strict.db.core.common.GenerateIdType;
import ru.strict.db.core.common.SqlParameters;
import ru.strict.db.core.connections.CreateConnectionByDataSource;
import ru.strict.db.core.entities.EntityFileStorage;
import ru.strict.db.core.mappers.dto.MapperDtoBase;
import ru.strict.db.core.mappers.dto.MapperDtoFactory;
import ru.strict.db.core.repositories.interfaces.IRepositoryFileStorage;
import ru.strict.db.core.requests.DbTable;
import ru.strict.db.spring.mappers.sql.MapperSqlFileStorage;
import ru.strict.models.FileStorage;
import ru.strict.models.FileStorageBase;
import ru.strict.utils.UtilClass;

public class RepositoryFileStorage<ID, DTO extends FileStorageBase<ID>>
        extends RepositorySpringBase<ID, EntityFileStorage<ID>, DTO>
        implements IRepositoryFileStorage<ID, DTO> {

    private static final String[] COLUMNS_NAME = new String[] {"filename", "extension", "displayname", "content", "filepath",
            "create_date", "type", "status"};

    public RepositoryFileStorage(CreateConnectionByDataSource connectionSource,
                                 GenerateIdType generateIdType) {
        super(new DbTable("file_storage", "fs"),
                COLUMNS_NAME,
                connectionSource,
                new MapperDtoFactory<ID>().instance(UtilClass.castClass(EntityFileStorage.class), UtilClass.castClass(FileStorage.class)),
                new MapperSqlFileStorage<ID>(COLUMNS_NAME),
                generateIdType);
    }

    public RepositoryFileStorage(CreateConnectionByDataSource connectionSource,
                                 MapperDtoBase<ID, EntityFileStorage<ID>, DTO> dtoMapper,
                                 GenerateIdType generateIdType) {
        super(new DbTable("file_storage", "fs"),
                COLUMNS_NAME,
                connectionSource,
                dtoMapper,
                new MapperSqlFileStorage<ID>(COLUMNS_NAME),
                generateIdType);
    }

    @Override
    protected SqlParameters getParameters(EntityFileStorage<ID> entity){
        SqlParameters parameters = new SqlParameters();
        parameters.add(0, COLUMNS_NAME[0], entity.getFilename());
        parameters.add(1, COLUMNS_NAME[1], entity.getExtension());
        parameters.add(2, COLUMNS_NAME[2], entity.getDisplayName());
        parameters.add(3, COLUMNS_NAME[3], entity.getContent());
        parameters.add(4, COLUMNS_NAME[4], entity.getFilePath());
        parameters.add(5, COLUMNS_NAME[5], entity.getCreateDate());
        parameters.add(6, COLUMNS_NAME[6], entity.getType());
        parameters.add(7, COLUMNS_NAME[7], entity.getStatus());
        return parameters;
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
    protected Class getThisClass() {
        return this.getClass();
    }
}
