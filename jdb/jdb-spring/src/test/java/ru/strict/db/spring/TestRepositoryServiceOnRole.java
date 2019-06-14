package ru.strict.db.spring;

import org.junit.*;
import org.junit.runners.MethodSorters;
import ru.strict.db.core.common.GenerateIdType;
import ru.strict.models.Roleuser;
import ru.strict.models.ServiceOnRole;
import ru.strict.db.core.entities.EntityRoleuser;
import ru.strict.db.core.mappers.dto.MapperDtoFactory;
import ru.strict.db.core.mappers.dto.MapperDtoServiceOnRole;
import ru.strict.db.core.repositories.IRepositoryNamed;
import ru.strict.db.core.repositories.interfaces.IRepositoryServiceOnRole;
import ru.strict.db.spring.data.ServiceModel;
import ru.strict.db.spring.data.ServiceProvider;
import ru.strict.db.spring.data.TestData;
import ru.strict.db.spring.repositories.RepositoryRoleuser;
import ru.strict.db.spring.repositories.RepositoryServiceOnRole;
import ru.strict.db.spring.runners.TestRunner;

import java.util.List;
import java.util.UUID;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestRepositoryServiceOnRole {

    private static IRepositoryServiceOnRole<Integer, ServiceModel> REPOSITORY_NOT_GENERATE_ID;
    private static IRepositoryServiceOnRole<Integer, ServiceModel> REPOSITORY_GENERATE_NUMBER_ID;
    private static IRepositoryServiceOnRole<UUID, ServiceModel> REPOSITORY_GENERATE_UUID_ID;

    @BeforeClass
    public static void prepare(){
        prepareRepositories();
        prepareData();
    }

    /**
     * Подготовить тестовые репозитории
     */
    private static void prepareRepositories(){
        MapperDtoServiceOnRole<Integer, ServiceModel> mapperNumber = new MapperDtoServiceOnRole<>(
                new ServiceProvider(),
                new MapperDtoFactory().instance(EntityRoleuser.class, Roleuser.class)
        );
        MapperDtoServiceOnRole<UUID, ServiceModel> mapperUUID = new MapperDtoServiceOnRole<>(
                new ServiceProvider(),
                new MapperDtoFactory().instance(EntityRoleuser.class, Roleuser.class)
        );

        REPOSITORY_NOT_GENERATE_ID = new RepositoryServiceOnRole<>(TestRunner.CREATE_DB_INTEGER_CONNECTION, mapperNumber, GenerateIdType.NONE);
        REPOSITORY_GENERATE_NUMBER_ID = new RepositoryServiceOnRole<>(TestRunner.CREATE_DB_INTEGER_CONNECTION, mapperNumber, GenerateIdType.NUMBER);
        REPOSITORY_GENERATE_UUID_ID = new RepositoryServiceOnRole<>(TestRunner.CREATE_DB_UUID_CONNECTION, mapperUUID, GenerateIdType.UUID);
        TestRunner.repositories.add(REPOSITORY_GENERATE_NUMBER_ID);
        TestRunner.repositories.add(REPOSITORY_GENERATE_UUID_ID);
    }

    /**
     * Подготовить тестовые данные
     */
    private static void prepareData(){
        IRepositoryNamed<Integer, Roleuser<Integer>> repositoryRoleuserNumberId = new RepositoryRoleuser<>(TestRunner.CREATE_DB_INTEGER_CONNECTION, GenerateIdType.NONE);
        IRepositoryNamed<UUID, Roleuser<UUID>> repositoryRoleuserUuidId = new RepositoryRoleuser<>(TestRunner.CREATE_DB_UUID_CONNECTION, GenerateIdType.NONE);

        TestRunner.repositories.add(repositoryRoleuserNumberId);
        TestRunner.repositories.add(repositoryRoleuserUuidId);

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
        ServiceOnRole dto = new ServiceOnRole<>(TestData.SERVICE_ON_ROLE2.getServiceId(), TestData.SERVICE_ON_ROLE2.getRoleId());
        ServiceOnRole createdDto = REPOSITORY_GENERATE_NUMBER_ID.create(dto);
        Assert.assertNotNull(createdDto.getId());
    }

    /**
     * Создание с генерацией uuid идентификатора
     */
    @Test
    public void test002CreateGenerateUuidId(){
        ServiceOnRole dto = new ServiceOnRole<>(TestData.SERVICE_ON_ROLE2_UUID.getServiceId(), TestData.SERVICE_ON_ROLE2_UUID.getRoleId());
        ServiceOnRole createdDto = REPOSITORY_GENERATE_UUID_ID.create(dto);
        Assert.assertNotNull(createdDto.getId());
    }

    /**
     * Создание без генерации идентификатора
     */
    @Test
    public void test003CreateNotGenerateId(){
        ServiceOnRole createdDto = REPOSITORY_NOT_GENERATE_ID.create(TestData.SERVICE_ON_ROLE1);
        Assert.assertEquals(TestData.SERVICE_ON_ROLE1, createdDto);
    }

    /**
     * Чтение одной записи по integer идентификатору
     */
    @Test
    public void test004ReadByInteger(){
        ServiceOnRole dto = REPOSITORY_GENERATE_NUMBER_ID.read(TestData.SERVICE_ON_ROLE1.getId());
        Assert.assertEquals(TestData.SERVICE_ON_ROLE1, dto);
    }

    /**
     * Чтение всех записей из integer базы данныъх
     */
    @Test
    public void test006ReadAllInteger(){
        List<ServiceOnRole<Integer, ServiceModel>> list = REPOSITORY_GENERATE_NUMBER_ID.readAll(null);
        Assert.assertTrue(list.size() == 2);
    }

    /**
     * Чтение всех записей из uuid базы данныъх
     */
    @Test
    public void test007ReadAllUuid(){
        List<ServiceOnRole<UUID, ServiceModel>> list = REPOSITORY_GENERATE_UUID_ID.readAll(null);
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
        boolean isRowExists = REPOSITORY_GENERATE_NUMBER_ID.isRowExists(TestData.SERVICE_ON_ROLE1.getId());
        Assert.assertTrue(isRowExists);
    }

    /**
     * Тестирование метода создания или чтения существует записи
     */
    @Test
    public void test012CreateOrReadExists(){
        ServiceOnRole dto = REPOSITORY_GENERATE_NUMBER_ID.createOrRead(TestData.SERVICE_ON_ROLE1);
        Assert.assertEquals(TestData.SERVICE_ON_ROLE1, dto);
    }

    /**
     * Тестирование метода создания или чтения несуществует записи
     */
    @Test
    public void test013CreateOrReadNotExists(){
        ServiceOnRole dto = REPOSITORY_GENERATE_NUMBER_ID.createOrRead(TestData.SERVICE_ON_ROLE3);
        Assert.assertEquals(TestData.SERVICE_ON_ROLE3, dto);
    }

    /**
     * Обновление записи
     */
    @Test
    public void test014Update(){
        ServiceOnRole dto = REPOSITORY_GENERATE_NUMBER_ID.update(TestData.SERVICE_ON_ROLE1_UPDATED);
        Assert.assertEquals(TestData.SERVICE_ON_ROLE1_UPDATED, dto);
    }

    /**
     * Тестирование метода создания или обновления существует записи
     */
    @Test
    public void test015CreateOrUpdateExists(){
        ServiceOnRole dto = REPOSITORY_GENERATE_NUMBER_ID.createOrUpdate(TestData.SERVICE_ON_ROLE1);
        Assert.assertEquals(TestData.SERVICE_ON_ROLE1, dto);
    }

    /**
     * Тестирование метода создания или обновления несуществует записи
     */
    @Test
    public void test016CreateOrUpdateNotExists(){
        ServiceOnRole dto = REPOSITORY_GENERATE_NUMBER_ID.createOrUpdate(TestData.SERVICE_ON_ROLE4);
        Assert.assertEquals(TestData.SERVICE_ON_ROLE4, dto);
    }

    /**
     * Проверка того, что предыдущие методы createOrRead и createOrUpdate успешно выполнены
     */
    @Test
    public void test017ExecuteCreateAndUpdateIsSuccess(){
        List<ServiceOnRole<Integer, ServiceModel>> list = REPOSITORY_GENERATE_NUMBER_ID.readAll(null);
        Assert.assertTrue(list.size() == 4);
    }

    /**
     * Удаление записи
     */
    @Test
    public void test018Delete(){
        REPOSITORY_GENERATE_NUMBER_ID.delete(TestData.SERVICE_ON_ROLE1.getId());
        List<ServiceOnRole<Integer, ServiceModel>> list = REPOSITORY_GENERATE_NUMBER_ID.readAll(null);
        ServiceOnRole<Integer, ServiceModel> dto = REPOSITORY_GENERATE_NUMBER_ID.read(TestData.SERVICE_ON_ROLE1.getId());
        Assert.assertTrue(list.size() == 3);
        Assert.assertNull(dto);
    }

    @Test
    public void test019ReadByServiceId(){
        List<ServiceOnRole<Integer, ServiceModel>> list = REPOSITORY_GENERATE_NUMBER_ID.readByServiceId(TestData.SERVICE_ON_ROLE4.getServiceId());
        Assert.assertTrue(list.size() == 1);
        Assert.assertTrue(list.get(0).equals(TestData.SERVICE_ON_ROLE4));
    }

    @Test
    public void test020ReadByRoleId(){
        List<ServiceOnRole<Integer, ServiceModel>> list = REPOSITORY_GENERATE_NUMBER_ID.readByRoleId(TestData.SERVICE_ON_ROLE4.getRoleId());
        Assert.assertTrue(list.size() == 2);
    }
}
