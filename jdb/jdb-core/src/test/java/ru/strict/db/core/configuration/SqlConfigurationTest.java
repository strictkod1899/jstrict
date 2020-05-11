package ru.strict.db.core.configuration;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class SqlConfigurationTest {

    @Test
    public void test() {
        String expectedSql1 = "WHERE city.country_id = ?";
        String expectedSql2 = "WHERE city.country_id_2 = ?";

        SqlConfiguration configuration = new SqlConfiguration();
        configuration.loadResource("CityRepository.xml");
        String readSql1 = configuration.getSql("CityRepository", "readByCountryId");
        Assert.assertEquals(expectedSql1, readSql1);
        String readSql2 = configuration.getSql("CityRepository", "readByCountryId2");
        Assert.assertEquals(expectedSql2, readSql2);
    }
}
