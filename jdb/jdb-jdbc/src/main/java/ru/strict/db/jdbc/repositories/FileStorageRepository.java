package ru.strict.db.jdbc.repositories;

import ru.strict.db.core.common.GenerateIdType;
import ru.strict.db.core.common.SqlParameters;
import ru.strict.db.core.connections.IConnectionCreator;
import ru.strict.db.core.repositories.DefaultColumns;
import ru.strict.db.core.repositories.DefaultTable;
import ru.strict.db.core.repositories.interfaces.IFileStorageRepository;
import ru.strict.models.FileStorage;
import ru.strict.db.jdbc.mappers.sql.FileStorageSqlMapper;

import java.sql.Connection;
import java.sql.SQLType;

public class FileStorageRepository<ID>
        extends NamedJdbcRepository<ID, FileStorage<ID>>
        implements IFileStorageRepository<ID, FileStorage<ID>> {

    private static final String[] COLUMNS_NAME = DefaultColumns.FILE_STORAGE.columns();

    public FileStorageRepository(IConnectionCreator<Connection> connectionSource,
            GenerateIdType generateIdType,
            SQLType sqlIdType) {
        super(DefaultTable.FILE_STORAGE.table(),
                COLUMNS_NAME,
                connectionSource,
                generateIdType,
                sqlIdType);
        setSqlMapper(new FileStorageSqlMapper<>(COLUMNS_NAME, sqlIdType, getIdColumnName()));
    }

    @Override
    protected SqlParameters getParameters(FileStorage<ID> model) {
        SqlParameters parameters = new SqlParameters();
        parameters.set(0, COLUMNS_NAME[0], model.getFilename());
        parameters.set(1, COLUMNS_NAME[1], model.getExtension());
        parameters.set(2, COLUMNS_NAME[2], model.getDisplayName());
        parameters.set(3, COLUMNS_NAME[3], model.getContent());
        parameters.set(4, COLUMNS_NAME[4], model.getFilePath());
        parameters.set(5, COLUMNS_NAME[5], model.getCreateDate());
        parameters.set(6, COLUMNS_NAME[6], model.getType());
        parameters.set(7, COLUMNS_NAME[7], model.getStatus());
        return parameters;
    }

    @Override
    protected FileStorage<ID> fill(FileStorage<ID> model) {
        return model;
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
