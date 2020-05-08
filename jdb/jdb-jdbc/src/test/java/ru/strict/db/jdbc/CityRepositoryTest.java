package ru.strict.db.jdbc;

import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import ru.strict.db.TestConnectionCreator;
import ru.strict.db.core.common.GenerateIdType;
import ru.strict.db.core.repositories.interfaces.ICityRepository;
import ru.strict.db.jdbc.repositories.CityRepository;
import ru.strict.db.jdbc.repositories.CountryRepository;

import java.sql.JDBCType;

@RunWith(JUnit4.class)
public class CityRepositoryTest extends ru.strict.db.CityRepositoryTest {

    private static TestConnectionCreator connectionCreator;

    @BeforeClass
    public static void setUpClass() {
        connectionCreator = new TestConnectionCreator();
        CountryRepository<Long> countryRepository = new CountryRepository<>(connectionCreator, GenerateIdType.NONE, JDBCType.BIGINT);
        prepare(countryRepository);
    }

    @Override
    protected ICityRepository<Long> getRepository() {
        return new CityRepository<>(connectionCreator, GenerateIdType.NONE, JDBCType.BIGINT);
    }
}
