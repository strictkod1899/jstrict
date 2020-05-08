package ru.strict.db;

import org.junit.Assert;
import org.junit.Test;
import ru.strict.db.core.repositories.IRepository;
import ru.strict.db.core.repositories.interfaces.IDetailsProfileRepository;
import ru.strict.models.City;
import ru.strict.models.DetailsProfile;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static ru.strict.db.TestData.*;

public abstract class DetailsProfileRepositoryTest
        extends ProfileRepositoryBaseTest<DetailsProfile<Long>, IDetailsProfileRepository<Long, DetailsProfile<Long>>> {

    protected static void prepare2(IRepository<Long, City<Long>> cityRepository) {
        cityRepository.create(CITY1);
        cityRepository.create(CITY2);
        cityRepository.create(CITY3);
    }

    @Test
    public void testReadByFio() {
        IDetailsProfileRepository<Long, DetailsProfile<Long>> repository = getRepository();
        DetailsProfile<Long> primaryProfile = getPrimaryModel();
        DetailsProfile<Long>[] profiles = getModels();

        Arrays.stream(profiles).forEach(repository::create);

        List<DetailsProfile<Long>> filteredProfiles = Arrays.stream(profiles)
                .filter(p -> p.getName().equals(primaryProfile.getName())
                        && p.getSurname().equals(primaryProfile.getSurname())
                        && p.getMiddlename().equals(primaryProfile.getMiddlename()))
                .collect(Collectors.toList());

        List<DetailsProfile<Long>> readProfiles = repository.readByFio(primaryProfile.getName(),
                primaryProfile.getSurname(),
                primaryProfile.getMiddlename()
        );
        Assert.assertEquals(filteredProfiles.size(), readProfiles.size());
        Assert.assertTrue(filteredProfiles.containsAll(readProfiles));

        Arrays.stream(profiles)
                .map(DetailsProfile<Long>::getId)
                .forEach(repository::delete);
    }

    @Override
    protected DetailsProfile<Long> getPrimaryModel() {
        return DETAILS_PROFILE1;
    }

    @Override
    protected DetailsProfile<Long>[] getModels() {
        return new DetailsProfile[]{
                DETAILS_PROFILE1,
                DETAILS_PROFILE2,
                DETAILS_PROFILE3
        };
    }

    @Override
    protected DetailsProfile<Long> getUpdateModel() {
        return UPDATED_DETAILS_PROFILE1;
    }

    @Override
    protected DetailsProfile<Long> getFillPrimaryModel() {
        return FILL_DETAILS_PROFILE1;
    }

    @Override
    protected DetailsProfile<Long> getFillUpdateModel() {
        return UPDATED_FILL_DETAILS_PROFILE1;
    }
}
