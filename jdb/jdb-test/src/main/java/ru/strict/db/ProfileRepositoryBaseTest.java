package ru.strict.db;

import org.junit.Assert;
import org.junit.Test;
import ru.strict.db.core.repositories.IRepository;
import ru.strict.db.core.repositories.interfaces.IProfileRepository;
import ru.strict.models.DetailsUser;
import ru.strict.models.Profile;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static ru.strict.db.TestData.*;

public abstract class ProfileRepositoryBaseTest<MODEL extends Profile<Long>, REPOSITORY extends IProfileRepository<Long, MODEL>>
        extends ExtensionRepositoryTest<Long, MODEL, REPOSITORY> {

    protected static void prepare(IRepository<Long, DetailsUser<Long>> userRepository) {
        userRepository.create(USER1);
        userRepository.create(USER2);
        userRepository.create(USER3);
    }

    @Test
    public void testReadBySurname() {
        IProfileRepository<Long, MODEL> repository = getRepository();
        MODEL primaryProfile = getPrimaryModel();
        MODEL[] profiles = getModels();

        Arrays.stream(profiles).forEach(repository::create);

        List<Profile<Long>> filteredProfiles = Arrays.stream(profiles)
                .filter(p -> p.getName().equals(primaryProfile.getName()) && p.getSurname().equals(primaryProfile.getSurname()))
                .collect(Collectors.toList());

        List<MODEL> readProfiles = repository.readBySurname(primaryProfile.getName(), primaryProfile.getSurname());
        Assert.assertEquals(filteredProfiles.size(), readProfiles.size());
        Assert.assertTrue(filteredProfiles.containsAll(readProfiles));

        Arrays.stream(profiles)
                .map(MODEL::getId)
                .forEach(repository::delete);
    }

    @Test
    public void testReadByUserId() {
        IProfileRepository<Long, MODEL> repository = getRepository();
        MODEL primaryProfile = getPrimaryModel();
        MODEL[] profiles = getModels();

        Arrays.stream(profiles).forEach(repository::create);

        List<Profile<Long>> filteredProfiles = Arrays.stream(profiles)
                .filter(p -> p.getUserId().equals(primaryProfile.getUserId()))
                .collect(Collectors.toList());

        List<MODEL> readProfiles = repository.readByUserId(primaryProfile.getUserId());
        Assert.assertEquals(filteredProfiles.size(), readProfiles.size());
        Assert.assertTrue(filteredProfiles.containsAll(readProfiles));

        Arrays.stream(profiles)
                .map(MODEL::getId)
                .forEach(repository::delete);
    }
}
