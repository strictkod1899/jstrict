package ru.strict.db.jdbc.repositories;

import ru.strict.db.core.common.SqlParameters;
import ru.strict.db.core.configuration.SqlConfiguration;
import ru.strict.db.core.connections.IConnectionCreator;
import ru.strict.db.core.repositories.ConfigurableRepository;
import ru.strict.db.jdbc.utils.JdbcUtil;
import ru.strict.patterns.IMapper;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.List;

public class JdbcConfigurableRepository
        extends ConfigurableRepository<Connection, IConnectionCreator<Connection>> {

    public JdbcConfigurableRepository(IConnectionCreator<Connection> connectionSource,
            SqlConfiguration configuration,
            String group) {
        super(connectionSource, configuration, group);
    }

    @Override
    protected final <ID> ID executeSql(String sql, SqlParameters parameters) {
        return executeSql(sql, parameters, false);
    }

    @Override
    protected final <ID> ID executeSql(String sql,
            SqlParameters parameters,
            boolean autoGenerateKey) {
        return JdbcUtil.<ID>executeSql(sql, parameters, this::createConnection, autoGenerateKey);
    }

    @Override
    protected final <T> T executeSqlRead(String sql,
            SqlParameters parameters,
            IMapper<ResultSet, T> resultMapper) {
        return JdbcUtil.<T>executeSqlRead(sql, parameters, resultMapper, this::createConnection);
    }

    @Override
    protected final <T> List<T> executeSqlReadAll(String sql,
            SqlParameters parameters,
            IMapper<ResultSet, T> resultMapper) {
        return JdbcUtil.<T>executeSqlReadAll(sql, parameters, resultMapper, this::createConnection);
    }
}
