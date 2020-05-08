package ru.strict.db.jdbc;

import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import ru.strict.db.TestConnectionCreator;
import ru.strict.db.core.common.GenerateIdType;
import ru.strict.db.core.repositories.INamedRepository;
import ru.strict.db.jdbc.repositories.CityRepository;
import ru.strict.db.jdbc.repositories.CountryRepository;
import ru.strict.models.Country;

import java.sql.JDBCType;

import static ru.strict.db.TestData.*;

@RunWith(JUnit4.class)
public class CountryRepositoryTest extends ru.strict.db.CountryRepositoryTest {

    private static TestConnectionCreator connectionCreator;

    @BeforeClass
    public static void setUpClass() {
        connectionCreator = new TestConnectionCreator();
        CityRepository<Long> cityRepository = new CityRepository<>(connectionCreator, GenerateIdType.NONE, JDBCType.BIGINT);
        prepare(cityRepository);
    }

    @Override
    protected INamedRepository<Long, Country<Long>> getRepository() {
        return new CountryRepository(connectionCreator, GenerateIdType.NONE, JDBCType.BIGINT);
    }

    @Override
    protected Country<Long> getPrimaryModel() {
        return COUNTRY1;
    }

    @Override
    protected Country<Long> getUpdateModel() {
        return UPDATED_COUNTRY1;
    }

    @Override
    protected Country<Long>[] getModels() {
        return new Country[] {
                COUNTRY1,
                COUNTRY2,
                COUNTRY3
        };
    }

    @Override
    protected Country<Long> getFillPrimaryModel() {
        return FILL_COUNTRY1;
    }

    @Override
    protected Country<Long> getFillUpdateModel() {
        return UPDATED_FILL_COUNTRY1;
    }
}
