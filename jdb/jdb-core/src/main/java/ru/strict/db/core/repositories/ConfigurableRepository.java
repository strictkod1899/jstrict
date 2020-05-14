package ru.strict.db.core.repositories;

import ru.strict.db.core.common.SqlParameters;
import ru.strict.db.core.configuration.SqlConfiguration;
import ru.strict.db.core.connections.IConnectionCreator;
import ru.strict.patterns.IMapper;

import java.sql.ResultSet;
import java.util.List;

public abstract class ConfigurableRepository
        <CONNECTION, SOURCE extends IConnectionCreator<CONNECTION>> {

    /**
     * Источник подключения к базе данных (используется для получения объекта Connection),
     * является реализацией интерфейса IConnectionCreator (ConnectionCreatorByDataSource,
     * ConnectionCreatorByConnectionInfo)
     */
    private final SOURCE connectionSource;

    private final SqlConfiguration configuration;
    private final String group;

    public ConfigurableRepository(SOURCE connectionSource,
            SqlConfiguration configuration,
            String group) {
        this.connectionSource = connectionSource;
        this.configuration = configuration;
        this.group = group;
    }

    /**
     * Создать соединение с базой даннных
     */
    protected CONNECTION createConnection() {
        return connectionSource.createConnection();
    }

    protected String getConfigurationQuery(String queryName) {
        return configuration.getSql(group, queryName);
    }

    public SOURCE getConnectionSource() {
        return connectionSource;
    }

    public SqlConfiguration getConfiguration() {
        return configuration;
    }

    public String getGroup() {
        return group;
    }

    /**
     * Выполнить sql-запрос на изменение данных
     */
    protected abstract <ID> ID executeSql(String sql, SqlParameters parameters);

    /**
     * Выполнить sql-запрос на изменение данных
     */
    protected abstract <ID> ID executeSql(String sql,
            SqlParameters parameters,
            boolean autoGenerateKey);

    /**
     * Выполнить sql-запрос на чтение
     */
    protected abstract <T> T executeSqlRead(String sql,
            SqlParameters parameters,
            IMapper<ResultSet, T> resultMapper);

    /**
     * Выполнить sql-запрос на чтение
     */
    protected abstract <T> List<T> executeSqlReadAll(String sql,
            SqlParameters parameters,
            IMapper<ResultSet, T> resultMapper);
}
