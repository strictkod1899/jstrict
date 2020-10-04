package ru.strict.db;

import ru.strict.db.core.repositories.INamedRepository;
import ru.strict.db.core.repositories.IRepository;
import ru.strict.models.DetailsUser;
import ru.strict.models.Role;

import static ru.strict.db.TestData.*;

public abstract class RoleRepositoryTest extends NamedRepositoryTest<Long, Role<Long>, INamedRepository<Long, Role<Long>>> {

    protected static void prepare(IRepository<Long, DetailsUser<Long>> userRepository) {
        userRepository.create(USER1);
        userRepository.create(USER2);
        userRepository.create(USER3);
    }

    @Override
    protected Role<Long> getPrimaryModel() {
        return ROLE1;
    }

    @Override
    protected Role<Long> getUpdateModel() {
        return UPDATED_ROLE1;
    }

    @Override
    protected Role<Long>[] getModels() {
        return new Role[] {
                ROLE1,
                ROLE2,
                ROLE3
        };
    }

    @Override
    protected String getPrimaryCaption() {
        return getPrimaryModel().getCode();
    }

    @Override
    protected String getUpdatedCaption() {
        return getUpdateModel().getCode();
    }
}
