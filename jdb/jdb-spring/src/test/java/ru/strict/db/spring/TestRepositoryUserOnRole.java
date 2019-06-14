package ru.strict.db.spring;

import org.junit.*;
import org.junit.runners.MethodSorters;
import ru.strict.db.core.common.GenerateIdType;
import ru.strict.models.Roleuser;
import ru.strict.models.User;
import ru.strict.models.UserOnRole;
import ru.strict.db.core.repositories.IRepositoryNamed;
import ru.strict.db.core.repositories.interfaces.IRepositoryUserOnRole;
import ru.strict.db.spring.data.TestData;
import ru.strict.db.spring.repositories.RepositoryRoleuser;
import ru.strict.db.spring.repositories.RepositoryUser;
import ru.strict.db.spring.repositories.RepositoryUserOnRole;
import ru.strict.db.spring.runners.TestRunner;

import java.util.List;
import java.util.UUID;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestRepositoryUserOnRole {

    private static IRepositoryUserOnRole<Integer> REPOSITORY_NOT_GENERATE_ID;
    private static IRepositoryUserOnRole<Integer> REPOSITORY_GENERATE_NUMBER_ID;
    private static IRepositoryUserOnRole<UUID> REPOSITORY_GENERATE_UUID_ID;

    @BeforeClass
    public static void prepare(){
        prepareRepositories();
        prepareData();
    }

    /**
     * Подготовить тестовые репозитории
     */
    private static void prepareRepositories(){
        REPOSITORY_NOT_GENERATE_ID = new RepositoryUserOnRole<>(TestRunner.CREATE_DB_INTEGER_CONNECTION, GenerateIdType.NONE);
        REPOSITORY_GENERATE_NUMBER_ID = new RepositoryUserOnRole<>(TestRunner.CREATE_DB_INTEGER_CONNECTION, GenerateIdType.NUMBER);
        REPOSITORY_GENERATE_UUID_ID = new RepositoryUserOnRole<>(TestRunner.CREATE_DB_UUID_CONNECTION, GenerateIdType.UUID);
        TestRunner.repositories.add(REPOSITORY_GENERATE_NUMBER_ID);
        TestRunner.repositories.add(REPOSITORY_GENERATE_UUID_ID);
    }

    /**
     * Подготовить тестовые данные
     */
    private static void prepareData(){
        IRepositoryNamed<Integer, User<Integer>> repositoryUserNumberId = new RepositoryUser<>(TestRunner.CREATE_DB_INTEGER_CONNECTION, GenerateIdType.NONE);
        IRepositoryNamed<UUID, User<UUID>> repositoryUserUuidId = new RepositoryUser<>(TestRunner.CREATE_DB_UUID_CONNECTION, GenerateIdType.NONE);
        IRepositoryNamed<Integer, Roleuser<Integer>> repositoryRoleuserNumberId = new RepositoryRoleuser<>(TestRunner.CREATE_DB_INTEGER_CONNECTION, GenerateIdType.NONE);
        IRepositoryNamed<UUID, Roleuser<UUID>> repositoryRoleuserUuidId = new RepositoryRoleuser<>(TestRunner.CREATE_DB_UUID_CONNECTION, GenerateIdType.NONE);

        TestRunner.repositories.add(repositoryUserNumberId);
        TestRunner.repositories.add(repositoryUserUuidId);
        TestRunner.repositories.add(repositoryRoleuserNumberId);
        TestRunner.repositories.add(repositoryRoleuserUuidId);

        repositoryUserNumberId.create(TestData.USER1);
        repositoryUserUuidId.create(TestData.USER1_UUID);
        repositoryUserNumberId.create(TestData.USER2);
        repositoryUserUuidId.create(TestData.USER2_UUID);
        repositoryRoleuserNumberId.create(TestData.ROLEUSER1);
        repositoryRoleuserUuidId.create(TestData.ROLEUSER1_UUID);
        repositoryRoleuserNumberId.create(TestData.ROLEUSER2);
        repositoryRoleuserUuidId.create(TestData.ROLEUSER2_UUID);
    }

    @AfterClass
    public static void post(){
        TestRunner.postProcess();
    }

    /**
     * Создание с генерацией integer идентификатора
     */
    @Test
    public void test001CreateGenerateNumberId(){
        UserOnRole dto = new UserOnRole<>(TestData.USER_ON_ROLE2.getUserId(), TestData.USER_ON_ROLE2.getRoleId());
        UserOnRole createdDto = REPOSITORY_GENERATE_NUMBER_ID.create(dto);
        Assert.assertNotNull(createdDto.getId());
    }

    /**
     * Создание с генерацией uuid идентификатора
     */
    @Test
    public void test002CreateGenerateUuidId(){
        UserOnRole dto = new UserOnRole<>(TestData.USER_ON_ROLE2_UUID.getUserId(), TestData.USER_ON_ROLE2_UUID.getRoleId());
        UserOnRole createdDto = REPOSITORY_GENERATE_UUID_ID.create(dto);
        Assert.assertNotNull(createdDto.getId());
    }

    /**
     * Создание без генерации идентификатора
     */
    @Test
    public void test003CreateNotGenerateId(){
        UserOnRole createdDto = REPOSITORY_NOT_GENERATE_ID.create(TestData.USER_ON_ROLE1);
        Assert.assertEquals(TestData.USER_ON_ROLE1, createdDto);
    }

    /**
     * Чтение одной записи по integer идентификатору
     */
    @Test
    public void test004ReadByInteger(){
        UserOnRole dto = REPOSITORY_GENERATE_NUMBER_ID.read(TestData.USER_ON_ROLE1.getId());
        Assert.assertEquals(TestData.USER_ON_ROLE1, dto);
    }

    /**
     * Чтение всех записей из integer базы данныъх
     */
    @Test
    public void test006ReadAllInteger(){
        List<UserOnRole<Integer>> list = REPOSITORY_GENERATE_NUMBER_ID.readAll(null);
        Assert.assertTrue(list.size() == 2);
    }

    /**
     * Чтение всех записей из uuid базы данныъх
     */
    @Test
    public void test007ReadAllUuid(){
        List<UserOnRole<UUID>> list = REPOSITORY_GENERATE_UUID_ID.readAll(null);
        Assert.assertTrue(list.size() == 1);
    }

    /**
     * Получить количество записей в базе данных
     */
    @Test
    public void test008ReadCount(){
        Integer count = REPOSITORY_GENERATE_NUMBER_ID.readCount(null);
        Assert.assertTrue(count == 2);
    }

    /**
     * Проверить существование записи по id
     */
    @Test
    public void test011IsRowExists(){
        boolean isRowExists = REPOSITORY_GENERATE_NUMBER_ID.isRowExists(TestData.USER_ON_ROLE1.getId());
        Assert.assertTrue(isRowExists);
    }

    /**
     * Тестирование метода создания или чтения существует записи
     */
    @Test
    public void test012CreateOrReadExists(){
        UserOnRole dto = REPOSITORY_GENERATE_NUMBER_ID.createOrRead(TestData.USER_ON_ROLE1);
        Assert.assertEquals(TestData.USER_ON_ROLE1, dto);
    }

    /**
     * Тестирование метода создания или чтения несуществует записи
     */
    @Test
    public void test013CreateOrReadNotExists(){
        UserOnRole dto = REPOSITORY_GENERATE_NUMBER_ID.createOrRead(TestData.USER_ON_ROLE3);
        Assert.assertEquals(TestData.USER_ON_ROLE3, dto);
    }

    /**
     * Обновление записи
     */
    @Test
    public void test014Update(){
        UserOnRole dto = REPOSITORY_GENERATE_NUMBER_ID.update(TestData.USER_ON_ROLE1_UPDATED);
        Assert.assertEquals(TestData.USER_ON_ROLE1_UPDATED, dto);
    }

    /**
     * Тестирование метода создания или обновления существует записи
     */
    @Test
    public void test015CreateOrUpdateExists(){
        UserOnRole dto = REPOSITORY_GENERATE_NUMBER_ID.createOrUpdate(TestData.USER_ON_ROLE1);
        Assert.assertEquals(TestData.USER_ON_ROLE1, dto);
    }

    /**
     * Тестирование метода создания или обновления несуществует записи
     */
    @Test
    public void test016CreateOrUpdateNotExists(){
        UserOnRole dto = REPOSITORY_GENERATE_NUMBER_ID.createOrUpdate(TestData.USER_ON_ROLE4);
        Assert.assertEquals(TestData.USER_ON_ROLE4, dto);
    }

    /**
     * Проверка того, что предыдущие методы createOrRead и createOrUpdate успешно выполнены
     */
    @Test
    public void test017ExecuteCreateAndUpdateIsSuccess(){
        List<UserOnRole<Integer>> list = REPOSITORY_GENERATE_NUMBER_ID.readAll(null);
        Assert.assertTrue(list.size() == 4);
    }

    /**
     * Удаление записи
     */
    @Test
    public void test018Delete(){
        REPOSITORY_GENERATE_NUMBER_ID.delete(TestData.USER_ON_ROLE1.getId());
        List<UserOnRole<Integer>> list = REPOSITORY_GENERATE_NUMBER_ID.readAll(null);
        UserOnRole<Integer> dto = REPOSITORY_GENERATE_NUMBER_ID.read(TestData.USER_ON_ROLE1.getId());
        Assert.assertTrue(list.size() == 3);
        Assert.assertNull(dto);
    }

    @Test
    public void test019ReadByUserId(){
        List<UserOnRole<Integer>> list = REPOSITORY_GENERATE_NUMBER_ID.readByUserId(TestData.USER_ON_ROLE4.getUserId());
        Assert.assertTrue(list.size() == 1);
        Assert.assertTrue(list.get(0).equals(TestData.USER_ON_ROLE4));
    }

    @Test
    public void test020ReadByRoleId(){
        List<UserOnRole<Integer>> list = REPOSITORY_GENERATE_NUMBER_ID.readByRoleId(TestData.USER_ON_ROLE4.getRoleId());
        Assert.assertTrue(list.size() == 2);
    }
}
