package ru.strict.db.jdbc;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.strict.db.jdbc.components.City;
import ru.strict.db.jdbc.components.CityDao;
import ru.strict.db.jdbc.components.TestConnectionCreator;
import ru.strict.db.core.common.GenerateIdType;
import ru.strict.db.core.common.SqlParameters;
import ru.strict.db.core.configuration.SqlConfiguration;
import ru.strict.db.jdbc.components.DefaultColumns;
import ru.strict.db.jdbc.components.CitySqlMapper;

import java.sql.JDBCType;
import java.util.List;

public class ConfigurableRepositoryTest {

    private CityDao<Integer> cityDao;

    @BeforeEach
    public void setUp() {
        SqlConfiguration sqlConfiguration = SqlConfiguration.fromResources("CityDao.xml");

        cityDao = new CityDao<>(new TestConnectionCreator(), GenerateIdType.NONE, JDBCType.INTEGER);
        cityDao.setConfiguration("CityDao", sqlConfiguration);
    }

    @Test
    public void test() {
        City<Integer> city = new City<>(1, "City1", 123);
        cityDao.create(city);

        List<City<Integer>> cities =
                cityDao.readAll("readByCountryId", new SqlParameters("country_id", 123));

        Assertions.assertEquals(1, cities.size());
        Assertions.assertEquals(city, cities.get(0));

        cities = cityDao.readByQuery("readByCountryId",
                new SqlParameters("country_id", 123),
                new CitySqlMapper<Integer>(DefaultColumns.CITY.columns(), JDBCType.INTEGER, "id"));

        Assertions.assertEquals(1, cities.size());
        Assertions.assertEquals(city, cities.get(0));

        cityDao.executeQuery("deleteAll", SqlParameters.empty());
        List<City<Integer>> deletedCities = cityDao.readAll(null);
        Assertions.assertTrue(deletedCities.isEmpty());
    }
}
