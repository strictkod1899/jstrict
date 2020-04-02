package ru.strict.db.jdbc.repositories;

import ru.strict.db.core.common.GenerateIdType;
import ru.strict.db.core.common.SqlParameters;
import ru.strict.db.core.connections.ICreateConnection;
import ru.strict.db.core.repositories.DefaultColumns;
import ru.strict.db.core.repositories.DefaultTable;
import ru.strict.models.FileStorage;
import ru.strict.db.core.repositories.interfaces.IRepositoryFileStorage;
import ru.strict.db.jdbc.mappers.sql.MapperSqlFileStorage;

import java.sql.Connection;
import java.sql.SQLType;

public class RepositoryFileStorage<ID>
        extends RepositoryJdbcNamed<ID, FileStorage<ID>>
        implements IRepositoryFileStorage<ID, FileStorage<ID>> {

    private static final String[] COLUMNS_NAME = DefaultColumns.FILE_STORAGE.columns();

    public RepositoryFileStorage(ICreateConnection<Connection> connectionSource,
                                 GenerateIdType generateIdType,
                                 SQLType sqlIdType) {
        super(DefaultTable.FILE_STORAGE.table(),
                COLUMNS_NAME,
                connectionSource,
                generateIdType,
                sqlIdType);
        setSqlMapper(new MapperSqlFileStorage<>(COLUMNS_NAME, sqlIdType, getIdColumnName()));
    }

    @Override
    protected SqlParameters getParameters(FileStorage<ID> model){
        SqlParameters parameters = new SqlParameters();
        parameters.add(0, COLUMNS_NAME[0], model.getFilename());
        parameters.add(1, COLUMNS_NAME[1], model.getExtension());
        parameters.add(2, COLUMNS_NAME[2], model.getDisplayName());
        parameters.add(3, COLUMNS_NAME[3], model.getContent());
        parameters.add(4, COLUMNS_NAME[4], model.getFilePath());
        parameters.add(5, COLUMNS_NAME[5], model.getCreateDate());
        parameters.add(6, COLUMNS_NAME[6], model.getType());
        parameters.add(7, COLUMNS_NAME[7], model.getStatus());
        return parameters;
    }

    @Override
    protected FileStorage<ID> fill(FileStorage<ID> model){
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
