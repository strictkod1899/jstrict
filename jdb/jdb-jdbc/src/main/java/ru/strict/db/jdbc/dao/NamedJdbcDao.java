package ru.strict.db.jdbc.dao;

import ru.strict.db.core.common.GenerateIdType;
import ru.strict.db.core.connection.IConnectionCreator;
import ru.strict.db.core.dao.INamedDao;
import ru.strict.db.jdbc.mapper.sql.BaseSqlMapper;
import ru.strict.db.core.query.components.Table;
import ru.strict.template.model.BaseModel;

import java.sql.Connection;
import java.sql.SQLType;

public abstract class NamedJdbcDao<ID, T extends BaseModel<ID>>
        extends JdbcDao<ID, T>
        implements INamedDao<ID, T> {

    public NamedJdbcDao(Table table,
            String[] columns,
            IConnectionCreator<Connection> connectionSource,
            BaseSqlMapper<T> sqlMapper,
            GenerateIdType generateIdType,
            SQLType sqlIdType) {
        super(table, columns, connectionSource, sqlMapper, generateIdType, sqlIdType);
    }

    public NamedJdbcDao(Table table,
            String[] columns,
            IConnectionCreator<Connection> connectionSource,
            BaseSqlMapper<T> sqlMapper,
            GenerateIdType generateIdType) {
        super(table, columns, connectionSource, sqlMapper, generateIdType);
    }

    /**
     * Если используется, этот конструктор, то необходимо вручную вызвать метод setSqlMapper
     */
    protected NamedJdbcDao(Table table,
            String[] columns,
            IConnectionCreator<Connection> connectionSource,
            GenerateIdType generateIdType,
            SQLType sqlIdType) {
        super(table, columns, connectionSource, generateIdType, sqlIdType);
    }
}
