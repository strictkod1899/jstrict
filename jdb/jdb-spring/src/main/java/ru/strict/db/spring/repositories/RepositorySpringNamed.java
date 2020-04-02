package ru.strict.db.spring.repositories;

import org.springframework.jdbc.core.RowMapper;
import ru.strict.db.core.common.GenerateIdType;
import ru.strict.db.core.connections.CreateConnectionByDataSource;
import ru.strict.db.core.repositories.IRepositoryNamed;
import ru.strict.db.core.requests.DbTable;
import ru.strict.models.ModelBase;

import java.sql.SQLType;
import java.util.List;

public abstract class RepositorySpringNamed<ID, T extends ModelBase<ID>>
        extends RepositorySpringBase<ID, T>
        implements IRepositoryNamed<ID, T> {

    public RepositorySpringNamed(DbTable table,
                                 String[] columns,
                                 CreateConnectionByDataSource connectionSource,
                                 RowMapper<T> springMapper,
                                 GenerateIdType generateIdType,
                                 SQLType sqlIdType) {
        super(table, columns, connectionSource, springMapper, generateIdType, sqlIdType);
    }

    public RepositorySpringNamed(DbTable table,
                                 String[] columns,
                                 CreateConnectionByDataSource connectionSource,
                                 RowMapper<T> springMapper,
                                 GenerateIdType generateIdType) {
        super(table, columns, connectionSource, springMapper, generateIdType);
    }

    public RepositorySpringNamed(DbTable table,
                                 String[] columns,
                                 CreateConnectionByDataSource connectionSource,
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
