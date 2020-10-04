package ru.strict.db.jdbc;

import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import ru.strict.db.jdbc.components.TestConnectionCreator;
import ru.strict.db.core.common.GenerateIdType;
import ru.strict.db.core.repositories.interfaces.IJWTTokenRepository;
import ru.strict.db.jdbc.repositories.JWTTokenRepository;
import ru.strict.db.jdbc.repositories.UserRepository;

import java.sql.JDBCType;

@RunWith(JUnit4.class)
public class JWTTokenRepositoryTest extends ru.strict.db.JWTTokenRepositoryTest {

    private static TestConnectionCreator connectionCreator;

    @BeforeClass
    public static void setUpClass() {
        connectionCreator = new TestConnectionCreator();
        UserRepository<Long> userRepository = new UserRepository<>(connectionCreator, GenerateIdType.NONE, JDBCType.BIGINT);
        prepare(userRepository);
    }

    @Override
    protected IJWTTokenRepository<Long> getRepository() {
        return new JWTTokenRepository<>(connectionCreator, GenerateIdType.NONE, JDBCType.BIGINT);
    }
}
