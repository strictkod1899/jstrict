package ru.strict.db.jdbc;

import org.junit.jupiter.api.BeforeAll;
import ru.strict.db.core.dao.IDao;
import ru.strict.db.jdbc.components.City;
import ru.strict.db.jdbc.components.TestConnectionCreator;
import ru.strict.db.core.common.GenerateIdType;
import ru.strict.db.jdbc.repositories.CityRepository;
import ru.strict.db.jdbc.repositories.CountryRepository;

import java.sql.JDBCType;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CityRepositoryTest extends NamedRepositoryTest<Long, City<Long>, ICityRepository<Long>> {

    private static TestConnectionCreator connectionCreator;

    private

    @BeforeAll
    public static void setUpClass() {
        connectionCreator = new TestConnectionCreator();
        CountryRepository<Long> countryRepository = new CountryRepository<>(connectionCreator, GenerateIdType.NONE, JDBCType.BIGINT);
        prepare(countryRepository);
    }

    @Override
    protected ICityRepository<Long> getRepository() {
        return new CityRepository<>(connectionCreator, GenerateIdType.NONE, JDBCType.BIGINT);
    }







    protected static void prepare(IDao<Long, Country<Long>> countryRepository) {
        countryRepository.create(COUNTRY1);
        countryRepository.create(COUNTRY2);
        countryRepository.create(COUNTRY3);
    }

    @Test
    public void testReadByCountryId() {
        ICityRepository<Long> repository = getRepository();
        City<Long> primaryCity = getPrimaryModel();
        City<Long>[] cities = getModels();

        Arrays.stream(cities).forEach(repository::create);

        List<City<Long>> filteredCities = Arrays.stream(cities)
                .filter(c -> c.getCountryId().equals(primaryCity.getCountryId()))
                .collect(Collectors.toList());

        List<City<Long>> readCities = repository.readByCountryId(primaryCity.getCountryId());
        Assert.assertEquals(filteredCities.size(), readCities.size());
        Assert.assertTrue(filteredCities.containsAll(readCities));

        Arrays.stream(cities)
                .map(City<Long>::getId)
                .forEach(repository::delete);
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
