package ru.strict.db.jdbc;

import org.junit.jupiter.api.BeforeAll;
import ru.strict.db.NamedDaoTest;
import ru.strict.db.jdbc.components.CityDao;
import ru.strict.db.jdbc.components.Country;
import ru.strict.db.jdbc.components.CountryDao;
import ru.strict.db.jdbc.components.TestConnectionCreator;
import ru.strict.db.core.common.GenerateIdType;

import java.sql.JDBCType;

import static ru.strict.db.jdbc.components.TestData.*;

public class CountryRepositoryTest extends NamedDaoTest<Long, Country<Long>, CountryDao<Long>> {

    private static TestConnectionCreator connectionCreator;

    @BeforeAll
    public static void setUpClass() {
        connectionCreator = new TestConnectionCreator();
        CityDao<Long> cityDao = new CityDao<>(connectionCreator, GenerateIdType.NONE, JDBCType.BIGINT);

        cityDao.create(CITY1);
        cityDao.create(CITY2);
        cityDao.create(CITY3);
    }

    @Override
    protected CountryDao<Long> getDao() {
        return new CountryDao<>(connectionCreator, GenerateIdType.NONE, JDBCType.BIGINT);
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
        return new Country[]{
                COUNTRY1,
                COUNTRY2,
                COUNTRY3
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
