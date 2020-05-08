package ru.strict.db.jdbc;

import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import ru.strict.db.TestConnectionCreator;
import ru.strict.db.core.common.GenerateIdType;
import ru.strict.db.core.repositories.interfaces.IProfileRepository;
import ru.strict.db.jdbc.repositories.ProfileRepository;
import ru.strict.db.jdbc.repositories.UserRepository;
import ru.strict.models.Profile;

import java.sql.JDBCType;

@RunWith(JUnit4.class)
public class ProfileRepositoryTest extends ru.strict.db.ProfileRepositoryTest {

    private static TestConnectionCreator connectionCreator;

    @BeforeClass
    public static void setUpClass() {
        connectionCreator = new TestConnectionCreator();
        UserRepository<Long> userRepository = new UserRepository<>(connectionCreator, GenerateIdType.NONE, JDBCType.BIGINT);
        prepare(userRepository);
    }

    @Override
    protected IProfileRepository<Long, Profile<Long>> getRepository() {
        return new ProfileRepository<>(connectionCreator, GenerateIdType.NONE, JDBCType.BIGINT);
    }
}
