package ru.strict.db.core.configuration;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import ru.strict.db.core.exceptions.UncorrectedQueryFormatException;

@RunWith(JUnit4.class)
public class SqlConfigurationTest {

    @Test
    public void test() {
        String expectedSql1 = "SELECT * FROM city WHERE city.country_id = ?";
        String expectedSql2 = "WHERE city.country_id_2 = ?";

        SqlConfiguration configuration = new SqlConfiguration();
        configuration.loadResource("CityRepository.xml");
        String readSql1 = configuration.getQuery("CityRepository", "readByCountryId");
        Assert.assertEquals(expectedSql1, readSql1);
        String readSql2 = configuration.getWhere("CityRepository", "readByCountryId2");
        Assert.assertEquals(expectedSql2, readSql2);
    }

    @Test(expected = UncorrectedQueryFormatException.class)
    public void testFail() {
        SqlConfiguration configuration = new SqlConfiguration();
        configuration.loadResource("UncorrectedRepository.xml");
    }
}
