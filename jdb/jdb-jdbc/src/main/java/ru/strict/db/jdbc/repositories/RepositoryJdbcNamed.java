package ru.strict.db.jdbc.repositories;

import ru.strict.db.core.common.GenerateIdType;
import ru.strict.db.core.connections.ICreateConnection;
import ru.strict.db.jdbc.mappers.sql.MapperSqlBase;
import ru.strict.db.core.repositories.IRepositoryNamed;
import ru.strict.db.core.requests.DbTable;
import ru.strict.models.ModelBase;

import java.sql.Connection;
import java.sql.SQLType;
import java.util.List;

public abstract class RepositoryJdbcNamed<ID, T extends ModelBase<ID>>
        extends RepositoryJdbcBase<ID, T>
        implements IRepositoryNamed<ID, T> {

    public RepositoryJdbcNamed(DbTable table,
                               String[] columns,
                               ICreateConnection<Connection> connectionSource,
                               MapperSqlBase<ID, T> sqlMapper,
                               GenerateIdType generateIdType,
                               SQLType sqlIdType) {
        super(table, columns, connectionSource, sqlMapper, generateIdType, sqlIdType);
    }

    public RepositoryJdbcNamed(DbTable table,
                              String[] columns,
                              ICreateConnection<Connection> connectionSource,
                              MapperSqlBase<ID, T> sqlMapper,
                              GenerateIdType generateIdType) {
        super(table, columns, connectionSource, sqlMapper, generateIdType);
    }

    RepositoryJdbcNamed(DbTable table,
                        String[] columns,
                        ICreateConnection<Connection> connectionSource,
                        GenerateIdType generateIdType,
                        SQLType sqlIdType) {
        super(table, columns, connectionSource, generateIdType, sqlIdType);
    }

    @Override
    public T readByNameFill(String caption) {
        T model = readByName(caption);
        if(model != null) {
            model = fill(model);
        }
        return model;
    }

    @Override
    public List<T> readAllByNameFill(String caption) {
        List<T> models = readAllByName(caption);
        if(models != null) {
            models.forEach((model) -> model = fill(model));
        }
        return models;
    }
}
