package ru.strict.db.jdbc.repositories;

import ru.strict.db.core.common.GenerateIdType;
import ru.strict.db.core.connections.ICreateConnection;
import ru.strict.db.core.dto.DtoFileStorage;
import ru.strict.db.core.dto.DtoFileStorageBase;
import ru.strict.db.core.entities.EntityFileStorage;
import ru.strict.db.core.mappers.dto.MapperDtoBase;
import ru.strict.db.core.mappers.dto.MapperDtoFactory;
import ru.strict.db.core.repositories.interfaces.IRepositoryFileStorage;
import ru.strict.db.jdbc.mappers.sql.MapperSqlFileStorage;
import ru.strict.utils.UtilClass;

import java.sql.Connection;
import java.util.*;

public class RepositoryFileStorage<ID, DTO extends DtoFileStorageBase<ID>>
        extends RepositoryJdbcBase<ID, EntityFileStorage<ID>, DTO>
        implements IRepositoryFileStorage<ID, DTO> {

    private static final String[] COLUMNS_NAME = new String[] {"filename", "extension", "displayname", "content", "filepath",
            "create_date", "type", "status"};

    public RepositoryFileStorage(ICreateConnection<Connection> connectionSource,
                                 GenerateIdType generateIdType) {
        super("file_storage", COLUMNS_NAME,
                connectionSource,
                new MapperDtoFactory<ID>().instance(UtilClass.castClass(EntityFileStorage.class), UtilClass.castClass(DtoFileStorage.class)),
                new MapperSqlFileStorage<ID>(COLUMNS_NAME),
                generateIdType);
    }

    public RepositoryFileStorage(ICreateConnection<Connection> connectionSource,
                                 MapperDtoBase<ID, EntityFileStorage<ID>, DTO> dtoMapper,
                                 GenerateIdType generateIdType) {
        super("file_storage", COLUMNS_NAME,
                connectionSource,
                dtoMapper,
                new MapperSqlFileStorage<ID>(COLUMNS_NAME),
                generateIdType);
    }

    @Override
    protected Map<Integer, Object> getValueByColumn(EntityFileStorage<ID> entity){
        Map<Integer, Object> valuesByColumn = new LinkedHashMap();
        valuesByColumn.put(0, entity.getFilename());
        valuesByColumn.put(1, entity.getExtension());
        valuesByColumn.put(2, entity.getDisplayName());
        valuesByColumn.put(3, entity.getContent());
        valuesByColumn.put(4, entity.getFilePath());
        valuesByColumn.put(5, entity.getCreateDate());
        valuesByColumn.put(6, entity.getType());
        valuesByColumn.put(7, entity.getStatus());
        return valuesByColumn;
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
