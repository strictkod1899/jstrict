package ru.strict.db.jdbc;

import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import ru.strict.db.TestConnectionCreator;
import ru.strict.db.core.common.GenerateIdType;
import ru.strict.db.core.repositories.interfaces.IFileStorageRepository;
import ru.strict.db.jdbc.repositories.FileStorageRepository;
import ru.strict.models.FileStorage;

import java.sql.JDBCType;

@RunWith(JUnit4.class)
public class FileStorageRepositoryTest extends ru.strict.db.FileStorageRepositoryTest {

    private static TestConnectionCreator connectionCreator;

    @BeforeClass
    public static void setUpClass() {
        connectionCreator = new TestConnectionCreator();
    }

    @Override
    protected IFileStorageRepository<Long, FileStorage<Long>> getRepository() {
        return new FileStorageRepository<>(connectionCreator, GenerateIdType.NONE, JDBCType.BIGINT);
    }
}
