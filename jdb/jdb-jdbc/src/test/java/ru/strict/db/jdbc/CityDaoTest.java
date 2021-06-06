package ru.strict.db.jdbc;

import org.junit.jupiter.api.BeforeAll;
import ru.strict.db.NamedDaoTest;
import ru.strict.db.jdbc.components.City;
import ru.strict.db.jdbc.components.CityDao;
import ru.strict.db.jdbc.components.CountryDao;
import ru.strict.db.jdbc.components.TestConnectionCreator;
import ru.strict.db.core.common.GenerateIdType;

import java.sql.JDBCType;

import static ru.strict.db.jdbc.components.TestData.*;

public class CityDaoTest extends NamedDaoTest<Long, City<Long>, CityDao<Long>> {

    private static TestConnectionCreator connectionCreator;

    @BeforeAll
    public static void setUpClass() {
        connectionCreator = new TestConnectionCreator();
        CountryDao<Long> countryDao = new CountryDao<>(connectionCreator, GenerateIdType.NONE, JDBCType.BIGINT);

        countryDao.create(COUNTRY1);
        countryDao.create(COUNTRY2);
        countryDao.create(COUNTRY3);
    }

    @Override
    protected CityDao<Long> getDao() {
        return new CityDao<>(connectionCreator, GenerateIdType.NONE, JDBCType.BIGINT);
    }

    @Override
    protected City<Long> getPrimaryModel() {
        return CITY1;
    }

    @Override
    protected City<Long> getUpdateModel() {
        return UPDATED_CITY1;
    }

    @Override
    protected City<Long>[] getModels() {
        return new City[] {
                CITY1,
                CITY2,
                CITY3
        };
    }

    @Override
    protected String getPrimaryCaption() {
        return getPrimaryModel().getName();
    }

    @Override
    protected String getUpdatedCaption() {
        return getUpdateModel().getName();
    }
}
