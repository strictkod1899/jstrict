package ru.strict.db;

import ru.strict.db.core.repositories.INamedRepository;
import ru.strict.db.core.repositories.IRepository;
import ru.strict.models.City;
import ru.strict.models.Country;

import static ru.strict.db.TestData.*;

public abstract class CountryRepositoryTest
        extends NamedRepositoryTest<Long, Country<Long>, INamedRepository<Long, Country<Long>>> {

    protected static void prepare(IRepository<Long, City<Long>> cityRepository) {
        cityRepository.create(CITY1);
        cityRepository.create(CITY2);
        cityRepository.create(CITY3);
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
    protected Country<Long> getFillPrimaryModel() {
        return FILL_COUNTRY1;
    }

    @Override
    protected Country<Long> getFillUpdateModel() {
        return UPDATED_FILL_COUNTRY1;
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
