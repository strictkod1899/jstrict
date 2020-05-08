package ru.strict.db.jdbc.repositories;

import ru.strict.db.core.common.GenerateIdType;
import ru.strict.db.core.connections.IConnectionCreator;
import ru.strict.db.core.repositories.INamedRepository;
import ru.strict.db.jdbc.mappers.sql.BaseSqlMapper;
import ru.strict.db.core.requests.DbTable;
import ru.strict.models.BaseModel;

import java.sql.Connection;
import java.sql.SQLType;
import java.util.List;

public abstract class NamedJdbcRepository<ID, T extends BaseModel<ID>>
        extends JdbcRepository<ID, T>
        implements INamedRepository<ID, T> {

    public NamedJdbcRepository(DbTable table,
            String[] columns,
            IConnectionCreator<Connection> connectionSource,
            BaseSqlMapper<ID, T> sqlMapper,
            GenerateIdType generateIdType,
            SQLType sqlIdType) {
        super(table, columns, connectionSource, sqlMapper, generateIdType, sqlIdType);
    }

    public NamedJdbcRepository(DbTable table,
            String[] columns,
            IConnectionCreator<Connection> connectionSource,
            BaseSqlMapper<ID, T> sqlMapper,
            GenerateIdType generateIdType) {
        super(table, columns, connectionSource, sqlMapper, generateIdType);
    }

    protected NamedJdbcRepository(DbTable table,
            String[] columns,
            IConnectionCreator<Connection> connectionSource,
            GenerateIdType generateIdType,
            SQLType sqlIdType) {
        super(table, columns, connectionSource, generateIdType, sqlIdType);
    }

    /**
     * Если используется, этот конструктор, то необходимо вручную вызвать метод setSqlMapper
     */
    protected NamedJdbcRepository(DbTable table,
            String[] columns,
            IConnectionCreator<Connection> connectionSource,
            GenerateIdType generateIdType) {
        super(table, columns, connectionSource, generateIdType);
    }

    @Override
    public T readByNameFill(String caption) {
        T model = readByName(caption);
        if (model != null) {
            model = fill(model);
        }
        return model;
    }

    @Override
    public List<T> readAllByNameFill(String caption) {
        List<T> models = readAllByName(caption);
        if (models != null) {
            models.forEach((model) -> model = fill(model));
        }
        return models;
    }
}