package ru.strict.db.jdbc;

import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import ru.strict.db.TestConnectionCreator;
import ru.strict.db.core.common.GenerateIdType;
import ru.strict.db.core.repositories.interfaces.IUserRepository;
import ru.strict.db.jdbc.repositories.JWTTokenRepository;
import ru.strict.db.jdbc.repositories.ProfileRepository;
import ru.strict.db.jdbc.repositories.RoleRepository;
import ru.strict.db.jdbc.repositories.UserOnRoleRepository;
import ru.strict.db.jdbc.repositories.UserRepository;
import ru.strict.models.DetailsUser;

import java.sql.JDBCType;

@RunWith(JUnit4.class)
public class UserRepositoryTest extends ru.strict.db.UserRepositoryTest {

    private static TestConnectionCreator connectionCreator;

    @BeforeClass
    public static void setUpClass() {
        connectionCreator = new TestConnectionCreator();
        RoleRepository<Long> roleRepository = new RoleRepository<>(connectionCreator, GenerateIdType.NONE, JDBCType.BIGINT);
        UserOnRoleRepository<Long> userOnRoleRepository = new UserOnRoleRepository<>(connectionCreator, GenerateIdType.NONE, JDBCType.BIGINT);
        ProfileRepository<Long> profileRepository = new ProfileRepository<>(connectionCreator, GenerateIdType.NONE, JDBCType.BIGINT);
        JWTTokenRepository<Long> tokenRepository = new JWTTokenRepository<>(connectionCreator, GenerateIdType.NONE, JDBCType.BIGINT);
        prepare(roleRepository, userOnRoleRepository, profileRepository, tokenRepository);
    }

    @Override
    protected IUserRepository<Long, DetailsUser<Long>> getRepository() {
        return new UserRepository<>(connectionCreator, GenerateIdType.NONE, JDBCType.BIGINT);
    }
}
