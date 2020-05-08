package ru.strict.db;

import org.junit.Assert;
import org.junit.Test;
import ru.strict.db.core.repositories.IRepository;
import ru.strict.db.core.repositories.interfaces.IPermissionOnRoleRepository;
import ru.strict.models.DetailsUser;
import ru.strict.models.PermissionOnRole;
import ru.strict.models.Role;
import ru.strict.models.PermissionOnRole;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static ru.strict.db.TestData.*;

public abstract class PermissionOnRoleRepositoryTest
        extends ExtensionRepositoryTest<Long, PermissionOnRole<Long, Permission>, IPermissionOnRoleRepository<Long, Permission>> {

    protected static void prepare(IRepository<Long, Role<Long>> roleRepository) {
        roleRepository.create(ROLE1);
        roleRepository.create(ROLE2);
        roleRepository.create(ROLE3);
    }

    @Test
    public void testReadByPermissionId() {
        IPermissionOnRoleRepository<Long, Permission> repository = getRepository();
        PermissionOnRole<Long, Permission> primaryModel = getPrimaryModel();
        PermissionOnRole<Long, Permission>[] models = getModels();

        Arrays.stream(models).forEach(repository::create);

        List<PermissionOnRole<Long, Permission>> filteredModels = Arrays.stream(models)
                .filter(c -> c.getPermissionId().equals(primaryModel.getPermissionId()))
                .collect(Collectors.toList());

        List<PermissionOnRole<Long, Permission>> readModels = repository.readByPermissionId(primaryModel.getPermissionId());
        Assert.assertEquals(filteredModels.size(), readModels.size());
        Assert.assertTrue(filteredModels.containsAll(readModels));

        Arrays.stream(models)
                .map(PermissionOnRole<Long, Permission>::getId)
                .forEach(repository::delete);
    }

    @Test
    public void testReadByRoleId() {
        IPermissionOnRoleRepository<Long, Permission> repository = getRepository();
        PermissionOnRole<Long, Permission> primaryModel = getPrimaryModel();
        PermissionOnRole<Long, Permission>[] models = getModels();

        Arrays.stream(models).forEach(repository::create);

        List<PermissionOnRole<Long, Permission>> filteredModels = Arrays.stream(models)
                .filter(c -> c.getRoleId().equals(primaryModel.getRoleId()))
                .collect(Collectors.toList());

        List<PermissionOnRole<Long, Permission>> readModels = repository.readByRoleId(primaryModel.getRoleId());
        Assert.assertEquals(filteredModels.size(), readModels.size());
        Assert.assertTrue(filteredModels.containsAll(readModels));

        Arrays.stream(models)
                .map(PermissionOnRole<Long, Permission>::getId)
                .forEach(repository::delete);
    }

    @Override
    protected PermissionOnRole<Long, Permission> getPrimaryModel() {
        return PERMISSION_ON_ROLE1;
    }

    @Override
    protected PermissionOnRole<Long, Permission> getUpdateModel() {
        return UPDATED_PERMISSION_ON_ROLE1;
    }

    @Override
    protected PermissionOnRole<Long, Permission>[] getModels() {
        return new PermissionOnRole[] {
                PERMISSION_ON_ROLE1,
                PERMISSION_ON_ROLE2,
                PERMISSION_ON_ROLE3
        };
    }

    @Override
    protected PermissionOnRole<Long, Permission> getFillPrimaryModel() {
        return FILL_PERMISSION_ON_ROLE1;
    }

    @Override
    protected PermissionOnRole<Long, Permission> getFillUpdateModel() {
        return UPDATED_FILL_PERMISSION_ON_ROLE1;
    }
}
