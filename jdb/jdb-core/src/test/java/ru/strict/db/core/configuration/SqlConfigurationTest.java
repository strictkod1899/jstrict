package ru.strict.db.core.configuration;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.strict.db.core.exception.UncorrectedQueryFormatException;

public class SqlConfigurationTest {

    @Test
    public void test() {
        String expectedSql1 = "SELECT * FROM city WHERE city.country_id = ?";
        String expectedSql2 = "WHERE city.country_id_2 = ?";

        SqlConfiguration configuration = new SqlConfiguration();
        configuration.loadResource("CityRepository.xml");
        String readSql1 = configuration.getQuery("CityRepository", "readByCountryId");
        Assertions.assertEquals(expectedSql1, readSql1);
        String readSql2 = configuration.getWhere("CityRepository", "readByCountryId2");
        Assertions.assertEquals(expectedSql2, readSql2);
    }

    @Test
    public void testFail() {
        SqlConfiguration configuration = new SqlConfiguration();

        Assertions.assertThrows(UncorrectedQueryFormatException.class, () ->
                configuration.loadResource("UncorrectedRepository.xml"));
    }
}
