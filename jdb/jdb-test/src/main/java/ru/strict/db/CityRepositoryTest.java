package ru.strict.db;

import org.junit.Assert;
import org.junit.Test;
import ru.strict.db.core.repositories.IRepository;
import ru.strict.db.core.repositories.interfaces.ICityRepository;
import ru.strict.models.City;
import ru.strict.models.Country;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static ru.strict.db.TestData.*;

public abstract class CityRepositoryTest extends NamedRepositoryTest<Long, City<Long>, ICityRepository<Long>> {

    protected static void prepare(IRepository<Long, Country<Long>> countryRepository) {
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
    protected City<Long> getFillPrimaryModel() {
        return FILL_CITY1;
    }

    @Override
    protected City<Long> getFillUpdateModel() {
        return UPDATED_FILL_CITY1;
    }

    @Override
    protected String getPrimaryCaption() {
        return getPrimaryModel().getCaption();
    }

    @Override
    protected String getUpdatedCaption() {
        return getUpdateModel().getCaption();
    }

    @Override
    protected String getFillPrimaryCaption() {
        return getFillPrimaryModel().getCaption();
    }

    @Override
    protected String getFillUpdatedCaption() {
        return getFillUpdateModel().getCaption();
    }
}
