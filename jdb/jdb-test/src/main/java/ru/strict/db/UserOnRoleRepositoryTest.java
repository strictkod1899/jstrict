package ru.strict.db;

import org.junit.Assert;
import org.junit.Test;
import ru.strict.db.core.repositories.IRepository;
import ru.strict.db.core.repositories.interfaces.IUserOnRoleRepository;
import ru.strict.models.DetailsUser;
import ru.strict.models.UserOnRole;
import ru.strict.models.Role;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static ru.strict.db.TestData.*;

public abstract class UserOnRoleRepositoryTest
        extends ExtensionRepositoryTest<Long, UserOnRole<Long>, IUserOnRoleRepository<Long>> {

    protected static void prepare(IRepository<Long, Role<Long>> roleRepository,
            IRepository<Long, DetailsUser<Long>> userRepository) {
        roleRepository.create(ROLE1);
        roleRepository.create(ROLE2);
        roleRepository.create(ROLE3);

        userRepository.create(USER1);
        userRepository.create(USER2);
        userRepository.create(USER3);
    }

    @Test
    public void testReadByUserId() {
        IUserOnRoleRepository<Long> repository = getRepository();
        UserOnRole<Long> primaryModel = getPrimaryModel();
        UserOnRole<Long>[] models = getModels();

        Arrays.stream(models).forEach(repository::create);

        List<UserOnRole<Long>> filteredModels = Arrays.stream(models)
                .filter(c -> c.getUserId().equals(primaryModel.getUserId()))
                .collect(Collectors.toList());

        List<UserOnRole<Long>> readModels = repository.readByUserId(primaryModel.getUserId());
        Assert.assertEquals(filteredModels.size(), readModels.size());
        Assert.assertTrue(filteredModels.containsAll(readModels));

        Arrays.stream(models)
                .map(UserOnRole<Long>::getId)
                .forEach(repository::delete);
    }

    @Test
    public void testReadByRoleId() {
        IUserOnRoleRepository<Long> repository = getRepository();
        UserOnRole<Long> primaryModel = getPrimaryModel();
        UserOnRole<Long>[] models = getModels();

        Arrays.stream(models).forEach(repository::create);

        List<UserOnRole<Long>> filteredModels = Arrays.stream(models)
                .filter(c -> c.getRoleId().equals(primaryModel.getRoleId()))
                .collect(Collectors.toList());

        List<UserOnRole<Long>> readModels = repository.readByRoleId(primaryModel.getRoleId());
        Assert.assertEquals(filteredModels.size(), readModels.size());
        Assert.assertTrue(filteredModels.containsAll(readModels));

        Arrays.stream(models)
                .map(UserOnRole<Long>::getId)
                .forEach(repository::delete);
    }

    @Override
    protected UserOnRole<Long> getPrimaryModel() {
        return USER_ON_ROLE1;
    }

    @Override
    protected UserOnRole<Long> getUpdateModel() {
        return UPDATED_USER_ON_ROLE1;
    }

    @Override
    protected UserOnRole<Long>[] getModels() {
        return new UserOnRole[] {
                USER_ON_ROLE1,
                USER_ON_ROLE2,
                USER_ON_ROLE3
        };
    }

    @Override
    protected UserOnRole<Long> getFillPrimaryModel() {
        return FILL_USER_ON_ROLE1;
    }

    @Override
    protected UserOnRole<Long> getFillUpdateModel() {
        return UPDATED_FILL_USER_ON_ROLE1;
    }
}
