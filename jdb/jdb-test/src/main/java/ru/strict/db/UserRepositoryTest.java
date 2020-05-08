package ru.strict.db;

import org.junit.Assert;
import org.junit.Test;
import ru.strict.db.core.repositories.IRepository;
import ru.strict.db.core.repositories.interfaces.IUserRepository;
import ru.strict.models.DetailsUser;
import ru.strict.models.JWTToken;
import ru.strict.models.Profile;
import ru.strict.models.Role;
import ru.strict.models.UserOnRole;

import static ru.strict.db.TestData.*;

public abstract class UserRepositoryTest
        extends NamedRepositoryTest<Long, DetailsUser<Long>, IUserRepository<Long, DetailsUser<Long>>> {

    protected static void prepare(IRepository<Long, Role<Long>> roleRepository,
            IRepository<Long, UserOnRole<Long>> userOnRoleRepository,
            IRepository<Long, Profile<Long>> profileRepository,
            IRepository<Long, JWTToken<Long>> tokenRepository) {
        roleRepository.create(ROLE1);
        roleRepository.create(ROLE2);
        roleRepository.create(ROLE3);

        userOnRoleRepository.create(USER_ON_ROLE1);
        userOnRoleRepository.create(USER_ON_ROLE2);
        userOnRoleRepository.create(USER_ON_ROLE3);

        profileRepository.create(PROFILE1);
        profileRepository.create(PROFILE2);
        profileRepository.create(PROFILE3);

        tokenRepository.create(JWT_TOKEN1);
        tokenRepository.create(JWT_TOKEN2);
        tokenRepository.create(JWT_TOKEN3);
    }

    @Test
    public void testReadByEmail() {
        IUserRepository<Long, DetailsUser<Long>> repository = getRepository();
        DetailsUser<Long> model = getPrimaryModel();
        DetailsUser<Long> updateModel = getUpdateModel();

        Long id = repository.create(model);
        DetailsUser<Long> readModel = repository.readByEmail(model.getEmail());
        Assert.assertEquals(model, readModel);
        Assert.assertTrue(repository.isRowExists(id));

        updateModel.setId(id);
        repository.update(updateModel);
        DetailsUser<Long> readUpdateModel = repository.readByEmail(updateModel.getEmail());
        Assert.assertEquals(updateModel, readUpdateModel);

        repository.delete(id);
    }

    @Test
    public void testIsStatus() {
        IUserRepository<Long, DetailsUser<Long>> repository = getRepository();
        DetailsUser<Long> model = getPrimaryModel();
        DetailsUser<Long> updateModel = getUpdateModel();

        Long id = repository.create(model);
        boolean isBlocked = repository.isBlocked(id);
        boolean isDeleted = repository.isDeleted(id);
        boolean isConfirmEmail = repository.isConfirmEmail(id);
        Assert.assertTrue(isBlocked);
        Assert.assertTrue(isDeleted);
        Assert.assertTrue(isConfirmEmail);

        updateModel.setId(id);
        repository.update(updateModel);
        boolean isUpdatedBlocked = repository.isBlocked(id);
        boolean isUpdatedDeleted = repository.isDeleted(id);
        boolean isUpdatedConfirmEmail = repository.isConfirmEmail(id);
        Assert.assertFalse(isUpdatedBlocked);
        Assert.assertFalse(isUpdatedDeleted);
        Assert.assertFalse(isUpdatedConfirmEmail);

        repository.delete(id);
    }

    @Override
    protected DetailsUser<Long> getPrimaryModel() {
        return USER1;
    }

    @Override
    protected DetailsUser<Long> getUpdateModel() {
        return UPDATED_USER1;
    }

    @Override
    protected DetailsUser<Long>[] getModels() {
        return new DetailsUser[] {
                USER1,
                USER2,
                USER3
        };
    }

    @Override
    protected DetailsUser<Long> getFillPrimaryModel() {
        return FILL_USER1;
    }

    @Override
    protected DetailsUser<Long> getFillUpdateModel() {
        return UPDATED_FILL_USER1;
    }

    @Override
    protected String getPrimaryCaption() {
        return getPrimaryModel().getUsername();
    }

    @Override
    protected String getUpdatedCaption() {
        return getUpdateModel().getUsername();
    }

    @Override
    protected String getFillPrimaryCaption() {
        return getFillPrimaryModel().getUsername();
    }

    @Override
    protected String getFillUpdatedCaption() {
        return getFillUpdateModel().getUsername();
    }
}
