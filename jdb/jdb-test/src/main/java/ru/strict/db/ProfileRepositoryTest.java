package ru.strict.db;

import ru.strict.db.core.repositories.interfaces.IProfileRepository;
import ru.strict.models.Profile;

import static ru.strict.db.TestData.*;

public abstract class ProfileRepositoryTest
        extends ProfileRepositoryBaseTest<Profile<Long>, IProfileRepository<Long, Profile<Long>>> {

    @Override
    protected Profile<Long> getPrimaryModel() {
        return PROFILE1;
    }

    @Override
    protected Profile<Long>[] getModels() {
        return new Profile[] {
                PROFILE1,
                PROFILE2,
                PROFILE3
        };
    }

    @Override
    protected Profile<Long> getUpdateModel() {
        return UPDATED_PROFILE1;
    }

    @Override
    protected Profile<Long> getFillPrimaryModel() {
        return FILL_PROFILE1;
    }

    @Override
    protected Profile<Long> getFillUpdateModel() {
        return UPDATED_FILL_PROFILE1;
    }
}
