package ru.strict.db.jdbc.repositories;

import ru.strict.db.core.common.GenerateIdType;
import ru.strict.db.core.connections.IConnectionCreator;
import ru.strict.db.core.repositories.INamedRepository;
import ru.strict.db.jdbc.mappers.sql.BaseSqlMapper;
import ru.strict.db.core.requests.components.Table;
import ru.strict.patterns.model.BaseModel;

import java.sql.Connection;
import java.sql.SQLType;

public abstract class NamedJdbcRepository<ID, T extends BaseModel<ID>>
        extends JdbcRepository<ID, T>
        implements INamedRepository<ID, T> {

    public NamedJdbcRepository(Table table,
            String[] columns,
            IConnectionCreator<Connection> connectionSource,
            BaseSqlMapper<T> sqlMapper,
            GenerateIdType generateIdType,
            SQLType sqlIdType) {
        super(table, columns, connectionSource, sqlMapper, generateIdType, sqlIdType);
    }

    public NamedJdbcRepository(Table table,
            String[] columns,
            IConnectionCreator<Connection> connectionSource,
            BaseSqlMapper<T> sqlMapper,
            GenerateIdType generateIdType) {
        super(table, columns, connectionSource, sqlMapper, generateIdType);
    }

    /**
     * Если используется, этот конструктор, то необходимо вручную вызвать метод setSqlMapper
     */
    protected NamedJdbcRepository(Table table,
            String[] columns,
            IConnectionCreator<Connection> connectionSource,
            GenerateIdType generateIdType,
            SQLType sqlIdType) {
        super(table, columns, connectionSource, generateIdType, sqlIdType);
    }
}
