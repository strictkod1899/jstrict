package ru.strict.db.core.repositories;

import ru.strict.db.core.configuration.SqlConfiguration;
import ru.strict.db.core.connections.IConnectionCreator;
import ru.strict.patterns.model.BaseModel;
import ru.strict.validate.Validator;

public abstract class ConfigurableRepository
        <ID, CONNECTION, SOURCE extends IConnectionCreator<CONNECTION>, MODEL extends BaseModel<ID>>
        implements IConfigurableRepository<ID, MODEL>{

    /**
     * Источник подключения к базе данных (используется для получения объекта Connection),
     * является реализацией интерфейса IConnectionCreator (ConnectionCreatorByDataSource,
     * ConnectionCreatorByConnectionInfo)
     */
    private final SOURCE connectionSource;

    private SqlConfiguration configuration;
    private String group;

    public ConfigurableRepository(SOURCE connectionSource,
            SqlConfiguration configuration,
            String group) {
        Validator.isNull(connectionSource, "connectionSource");

        this.connectionSource = connectionSource;
        this.configuration = configuration;
        this.group = group;
    }

    public SqlConfiguration getConfiguration() {
        return configuration;
    }

    /**
     * Создать соединение с базой даннных
     */
    protected CONNECTION createConnection() {
        return connectionSource.createConnection();
    }

    protected SOURCE getConnectionSource() {
        return connectionSource;
    }

    protected String getGroup() {
        return group;
    }

    public void setConfiguration(String group, SqlConfiguration configuration) {
        Validator.isNull(group, "group");
        Validator.isNull(configuration, "configuration");

        this.group = group;
        this.configuration = configuration;
    }
}
