package ru.strict.db.jdbc;

import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import ru.strict.db.Permission;
import ru.strict.db.PermissionProvider;
import ru.strict.db.TestConnectionCreator;
import ru.strict.db.core.common.GenerateIdType;
import ru.strict.db.core.repositories.interfaces.IPermissionOnRoleRepository;
import ru.strict.db.jdbc.repositories.RoleRepository;
import ru.strict.db.jdbc.repositories.PermissionOnRoleRepository;
import ru.strict.db.jdbc.repositories.UserRepository;

import java.sql.JDBCType;

@RunWith(JUnit4.class)
public class PermissionOnRoleRepositoryTest extends ru.strict.db.PermissionOnRoleRepositoryTest {

    private static TestConnectionCreator connectionCreator;

    @BeforeClass
    public static void setUpClass() {
        connectionCreator = new TestConnectionCreator();
        RoleRepository<Long> roleRepository = new RoleRepository<>(connectionCreator, GenerateIdType.NONE, JDBCType.BIGINT);
        prepare(roleRepository);
    }

    @Override
    protected IPermissionOnRoleRepository<Long, Permission> getRepository() {
        return new PermissionOnRoleRepository<>(connectionCreator, GenerateIdType.NONE, JDBCType.BIGINT, new PermissionProvider());
    }
}
