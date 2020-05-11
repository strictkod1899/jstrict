package ru.strict.db.jdbc.components;

import ru.strict.db.TestConnectionCreator;
import ru.strict.db.jdbc.repositories.JdbcConfigurableRepository;

public class ConfigurableRepositoryImpl extends JdbcConfigurableRepository {

    public ConfigurableRepositoryImpl() {
        super(new TestConnectionCreator(), new TestSqlConfiguration(), "CountryRepository");
    }
}
