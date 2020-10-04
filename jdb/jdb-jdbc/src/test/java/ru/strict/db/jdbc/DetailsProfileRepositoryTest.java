package ru.strict.db.jdbc;

import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import ru.strict.db.jdbc.components.TestConnectionCreator;
import ru.strict.db.core.common.GenerateIdType;
import ru.strict.db.core.repositories.interfaces.IDetailsProfileRepository;
import ru.strict.db.jdbc.repositories.CityRepository;
import ru.strict.db.jdbc.repositories.DetailsProfileRepository;
import ru.strict.db.jdbc.repositories.UserRepository;
import ru.strict.models.DetailsProfile;

import java.sql.JDBCType;

@RunWith(JUnit4.class)
public class DetailsProfileRepositoryTest extends ru.strict.db.DetailsProfileRepositoryTest {

    private static TestConnectionCreator connectionCreator;

    @BeforeClass
    public static void setUpClass() {
        connectionCreator = new TestConnectionCreator();
        UserRepository<Long> userRepository = new UserRepository<>(connectionCreator, GenerateIdType.NONE, JDBCType.BIGINT);
        CityRepository<Long> cityRepository = new CityRepository<>(connectionCreator, GenerateIdType.NONE, JDBCType.BIGINT);
        prepare(userRepository);
        prepare2(cityRepository);
    }

    @Override
    protected IDetailsProfileRepository<Long, DetailsProfile<Long>> getRepository() {
        return new DetailsProfileRepository<>(connectionCreator, GenerateIdType.NONE, JDBCType.BIGINT);
    }
}
