package ru.strict.db.jdbc;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import ru.strict.db.TestConnectionCreator;
import ru.strict.db.core.common.GenerateIdType;
import ru.strict.db.core.common.SqlParameters;
import ru.strict.db.core.configuration.SqlConfiguration;
import ru.strict.db.jdbc.repositories.CityRepository;
import ru.strict.models.City;

import java.sql.JDBCType;
import java.util.List;

@RunWith(JUnit4.class)
public class ConfigurableRepositoryTest {

    private CityRepository<Integer> cityRepository;

    @Before
    public void setUp() {
        SqlConfiguration sqlConfiguration = SqlConfiguration.fromResources("CityRepository.xml");

        cityRepository = new CityRepository<>(new TestConnectionCreator(), GenerateIdType.NONE, JDBCType.INTEGER);
        cityRepository.setConfiguration("CityRepository", sqlConfiguration);
    }

    @Test
    public void test() {
        City<Integer> city = new City<>(1, "City1", 123);
        cityRepository.create(city);

        List<City<Integer>> cities =
                cityRepository.readAll("readByCountryId", new SqlParameters("country_id", 123));

        Assert.assertEquals(1, cities.size());
        Assert.assertEquals(city, cities.get(0));

        cityRepository.executeQuery("deleteAll", SqlParameters.empty());

        List<City<Integer>> deletedCities = cityRepository.readAll(null);
        Assert.assertTrue(deletedCities.isEmpty());
    }
}
