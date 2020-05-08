package ru.strict.db.jdbc;

import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import ru.strict.db.TestConnectionCreator;
import ru.strict.db.core.common.GenerateIdType;
import ru.strict.db.core.repositories.INamedRepository;
import ru.strict.db.jdbc.repositories.UserRepository;
import ru.strict.db.jdbc.repositories.RoleRepository;
import ru.strict.models.Role;

import java.sql.JDBCType;

import static ru.strict.db.TestData.*;

@RunWith(JUnit4.class)
public class RoleRepositoryTest extends ru.strict.db.RoleRepositoryTest {

    private static TestConnectionCreator connectionCreator;

    @BeforeClass
    public static void setUpClass() {
        connectionCreator = new TestConnectionCreator();
        UserRepository<Long> userRepository = new UserRepository<>(connectionCreator, GenerateIdType.NONE, JDBCType.BIGINT);
        prepare(userRepository);
    }

    @Override
    protected INamedRepository<Long, Role<Long>> getRepository() {
        return new RoleRepository(connectionCreator, GenerateIdType.NONE, JDBCType.BIGINT);
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
    protected Role<Long> getFillPrimaryModel() {
        return FILL_ROLE1;
    }

    @Override
    protected Role<Long> getFillUpdateModel() {
        return UPDATED_FILL_ROLE1;
    }
}
