package ru.strict.db.jdbc.components;

import ru.strict.db.core.configuration.SqlConfiguration;

public class TestSqlConfiguration extends SqlConfiguration {

    public TestSqlConfiguration() {
        super();
        loadResource("CountryRepository.xml");
    }
}
