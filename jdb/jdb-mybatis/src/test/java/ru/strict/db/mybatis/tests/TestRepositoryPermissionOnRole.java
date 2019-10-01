package ru.strict.db.mybatis.tests;

import org.junit.*;
import org.junit.runners.MethodSorters;
import ru.strict.db.core.common.GenerateIdType;
import ru.strict.db.core.repositories.IRepositoryNamed;
import ru.strict.db.core.repositories.interfaces.IRepositoryPermissionOnRole;
import ru.strict.db.mybatis.tests.data.Permission;
import ru.strict.db.mybatis.tests.data.TestData;
import ru.strict.db.mybatis.repositories.RepositoryPermissionOnRole;
import ru.strict.db.mybatis.repositories.RepositoryRole;
import ru.strict.db.mybatis.tests.runners.TestRunner;
import ru.strict.models.ModelBase;
import ru.strict.models.PermissionOnRole;
import ru.strict.models.Role;

import java.util.List;
import java.util.UUID;

import static ru.strict.db.mybatis.tests.data.TestData.*;
import static ru.strict.db.mybatis.tests.runners.TestRunner.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestRepositoryPermissionOnRole {

    private static PermissionOnRole[] data;

    private static IRepositoryPermissionOnRole<Integer, Permission> REPOSITORY_NOT_GENERATE_ID;
    private static IRepositoryPermissionOnRole<Integer, Permission> REPOSITORY_GENERATE_NUMBER_ID;
    private static IRepositoryPermissionOnRole<UUID, Permission> REPOSITORY_GENERATE_UUID_ID;

    @BeforeClass
    public static void setUpClass(){
        prepareRepositories();
        prepareData();
        fillData();
    }

    @AfterClass
    public static void tearDownClass(){
        TestRunner.cleanDatabase();
    }

    /**
     * Подготовить тестовые репозитории
     */
    private static void prepareRepositories(){
        REPOSITORY_NOT_GENERATE_ID = new RepositoryPermissionOnRole<>(CREATE_DB_INTEGER_CONNECTION, GenerateIdType.NONE);
        REPOSITORY_GENERATE_NUMBER_ID = new RepositoryPermissionOnRole<>(CREATE_DB_INTEGER_CONNECTION, GenerateIdType.INT);
        REPOSITORY_GENERATE_UUID_ID = new RepositoryPermissionOnRole<>(CREATE_DB_UUID_CONNECTION, GenerateIdType.UUID);
        TestRunner.repositoriesForClearDb.add(REPOSITORY_GENERATE_NUMBER_ID);
        TestRunner.repositoriesForClearDb.add(REPOSITORY_GENERATE_UUID_ID);
    }

    /**
     * Подготовить тестовые данные
     */
    private static void prepareData(){
        IRepositoryNamed<Integer, Role<Integer>> repositoryRoleNumberId = new RepositoryRole<>(CREATE_DB_INTEGER_CONNECTION, GenerateIdType.NONE);
        IRepositoryNamed<UUID, Role<UUID>> repositoryRoleUuidId = new RepositoryRole<>(CREATE_DB_UUID_CONNECTION, GenerateIdType.NONE);

        TestRunner.repositoriesForClearDb.add(repositoryRoleNumberId);
        TestRunner.repositoriesForClearDb.add(repositoryRoleUuidId);

        repositoryRoleNumberId.create(TestData.ROLE1);
        repositoryRoleUuidId.create(TestData.ROLE1_UUID);
        repositoryRoleNumberId.create(TestData.ROLE2);
        repositoryRoleUuidId.create(TestData.ROLE2_UUID);
    }

    /**
     * Заполнить тестовые данные
     */
    private static void fillData(){
        data = new PermissionOnRole[]{
                new PermissionOnRole(Permission.PERMISSION1.getId(), ROLE3.getId()),
                new PermissionOnRole(Permission.PERMISSION1.getId(), ROLE3_UUID.getId()),
                PERMISSION_ON_ROLE1,
                PERMISSION_ON_ROLE2,
                PERMISSION_ON_ROLE3,
                PERMISSION_ON_ROLE1_UPDATED
        };
    }

    /**
     * Создание с генерацией integer идентификатора
     */
    @Test
    public void test001CreateGenerateNumberId(){
        Integer id = REPOSITORY_GENERATE_NUMBER_ID.create(data[0]);
        data[0].setId(id);
        Assert.assertNotNull(id);
    }

    /**
     * Создание с генерацией uuid идентификатора
     */
    @Test
    public void test002CreateGenerateUuidId(){
        UUID id = REPOSITORY_GENERATE_UUID_ID.create(data[1]);
        data[1].setId(id);
        Assert.assertNotNull(id);
    }

    /**
     * Создание без генерации идентификатора
     */
    @Test
    public void test003CreateNotGenerateId(){
        Integer id = REPOSITORY_NOT_GENERATE_ID.create(data[2]);
        Assert.assertEquals(data[2].getId(), id);
    }

    /**
     * Чтение одной записи по integer идентификатору
     */
    @Test
    public void test004ReadByInteger(){
        ModelBase model = REPOSITORY_GENERATE_NUMBER_ID.read((Integer)data[2].getId());
        Assert.assertEquals(data[2], model);
    }

    /**
     * Чтение одной записи по uuid идентификатору
     */
    @Test
    public void test005ReadByUuid(){
        PermissionOnRole model = REPOSITORY_GENERATE_UUID_ID.read((UUID)data[1].getId());
        convertUUIDModel(model);
        Assert.assertEquals(data[1], model);
    }

    /**
     * Чтение всех записей из integer базы данныъх
     */
    @Test
    public void test006ReadAllInteger(){
        List list = REPOSITORY_GENERATE_NUMBER_ID.readAll(null);
        Assert.assertEquals(2, list.size());
        Assert.assertTrue(list.contains(data[0]));
        Assert.assertTrue(list.contains(data[2]));
    }

    /**
     * Чтение всех записей из uuid базы данныъх
     */
    @Test
    public void test007ReadAllUuid(){
        List<PermissionOnRole<UUID, Permission>> list = REPOSITORY_GENERATE_UUID_ID.readAll(null);
        list.forEach(model -> convertUUIDModel(model));
        Assert.assertEquals(1, list.size());
        Assert.assertTrue(list.contains(data[1]));
    }

    /**
     * Получить количество записей в integer базе данных
     */
    @Test
    public void test008ReadCount(){
        int count = REPOSITORY_GENERATE_NUMBER_ID.readCount(null);
        Assert.assertEquals(2, count);
    }

    /**
     * Проверить существование записи по id
     */
    @Test
    public void test011IsRowExists(){
        boolean isRowExists = REPOSITORY_GENERATE_NUMBER_ID.isRowExists((Integer)data[0].getId());
        Assert.assertTrue(isRowExists);
        isRowExists = REPOSITORY_GENERATE_UUID_ID.isRowExists((UUID)data[1].getId());
        Assert.assertTrue(isRowExists);
        isRowExists = REPOSITORY_GENERATE_UUID_ID.isRowExists(UUID.randomUUID());
        Assert.assertFalse(isRowExists);
    }

    /**
     * Тестирование создания или чтения существующей записи
     */
    @Test
    public void test012CreateOrReadExists(){
        ModelBase model = REPOSITORY_GENERATE_NUMBER_ID.createOrRead(data[0]);
        Assert.assertEquals(data[0], model);
    }

    /**
     * Тестирование метода создания или чтения несуществующей записи
     */
    @Test
    public void test013CreateOrReadNotExists(){
        ModelBase model = REPOSITORY_GENERATE_NUMBER_ID.createOrRead(data[3]);
        data[3].setId(model.getId());
        Assert.assertEquals(data[3], model);
    }

    /**
     * Обновление записи
     */
    @Test
    public void test014Update(){
        REPOSITORY_GENERATE_NUMBER_ID.update(data[5]);
        ModelBase model = REPOSITORY_GENERATE_NUMBER_ID.read((Integer)data[5].getId());
        // Эту модель мы только что обновили, поэтому model2 == model (data[5] == data[2])
        ModelBase model2 = REPOSITORY_GENERATE_NUMBER_ID.read((Integer)data[2].getId());
        Assert.assertEquals(data[5], model);
        Assert.assertNotEquals(data[2], model2);
    }

    /**
     * Тестирование метода создания или обновления существующей записи
     */
    @Test
    public void test015CreateOrUpdateExists(){
        Integer id = REPOSITORY_GENERATE_NUMBER_ID.createOrUpdate(data[2]);
        ModelBase model = REPOSITORY_GENERATE_NUMBER_ID.read(id);
        Assert.assertEquals(data[2].getId(), id);
        Assert.assertEquals(data[2], model);
    }

    /**
     * Тестирование метода создания или обновления несуществующей записи
     */
    @Test
    public void test016CreateOrUpdateNotExists(){
        Integer id = REPOSITORY_GENERATE_NUMBER_ID.createOrUpdate(data[4]);
        ModelBase model = REPOSITORY_GENERATE_NUMBER_ID.read(id);
        data[4].setId(id);
        Assert.assertNotNull(id);
        Assert.assertEquals(data[4], model);
    }

    /**
     * Проверка того, что предыдущие методы createOrRead и createOrUpdate успешно выполнены
     */
    @Test
    public void test017ExecuteCreateAndUpdateIsSuccess(){
        List list = REPOSITORY_GENERATE_NUMBER_ID.readAll(null);
        Assert.assertEquals(4, list.size());
    }

    /**
     * Удаление записи
     */
    @Test
    public void test018Delete(){
        REPOSITORY_GENERATE_NUMBER_ID.delete((Integer)data[0].getId());
        List list = REPOSITORY_GENERATE_NUMBER_ID.readAll(null);
        ModelBase model = REPOSITORY_GENERATE_NUMBER_ID.read((Integer)data[0].getId());
        Assert.assertEquals(3, list.size());
        Assert.assertNull(model);
    }

    @Test
    public void test019ReadByPermissionId(){
        List list = REPOSITORY_GENERATE_NUMBER_ID.readByPermissionId(data[2].getPermissionId());
        Assert.assertEquals(2, list.size());
        Assert.assertTrue(list.contains(data[2]));
        Assert.assertTrue(list.contains(data[4]));
    }

    @Test
    public void test020ReadByRoleId(){
        List<PermissionOnRole<Integer, Permission>> list = REPOSITORY_GENERATE_NUMBER_ID.readByRoleId((Integer)data[2].getRoleId());
        Assert.assertEquals(2, list.size());
        Assert.assertTrue(list.contains(data[2]));
        Assert.assertTrue(list.contains(data[3]));
    }

    private void convertUUIDModel(PermissionOnRole model){
        model.setId(UUID.fromString(model.getId().toString()));
        model.setRoleId(UUID.fromString(model.getRoleId().toString()));
    }
}
