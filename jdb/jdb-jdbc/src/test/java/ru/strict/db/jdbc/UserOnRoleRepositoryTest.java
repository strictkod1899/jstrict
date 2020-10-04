package ru.strict.db.jdbc;

import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import ru.strict.db.jdbc.components.TestConnectionCreator;
import ru.strict.db.core.common.GenerateIdType;
import ru.strict.db.core.repositories.interfaces.IUserOnRoleRepository;
import ru.strict.db.jdbc.repositories.RoleRepository;
import ru.strict.db.jdbc.repositories.UserOnRoleRepository;
import ru.strict.db.jdbc.repositories.UserRepository;

import java.sql.JDBCType;

@RunWith(JUnit4.class)
public class UserOnRoleRepositoryTest extends ru.strict.db.UserOnRoleRepositoryTest {

    private static TestConnectionCreator connectionCreator;

    @BeforeClass
    public static void setUpClass() {
        connectionCreator = new TestConnectionCreator();
        RoleRepository<Long> roleRepository = new RoleRepository<>(connectionCreator, GenerateIdType.NONE, JDBCType.BIGINT);
        UserRepository<Long> userRepository = new UserRepository<>(connectionCreator, GenerateIdType.NONE, JDBCType.BIGINT);
        prepare(roleRepository, userRepository);
    }

    @Override
    protected IUserOnRoleRepository<Long> getRepository() {
        return new UserOnRoleRepository<>(connectionCreator, GenerateIdType.NONE, JDBCType.BIGINT);
    }
}
