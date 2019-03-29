package ru.strict.db.mybatis;

import org.junit.*;
import org.junit.runners.MethodSorters;
import ru.strict.db.core.common.GenerateIdType;
import ru.strict.db.core.dto.DtoRoleuser;
import ru.strict.db.core.dto.DtoServiceOnRole;
import ru.strict.db.core.entities.EntityRoleuser;
import ru.strict.db.core.mappers.dto.MapperDtoFactory;
import ru.strict.db.core.mappers.dto.MapperDtoServiceOnRole;
import ru.strict.db.core.repositories.IRepositoryExtension;
import ru.strict.db.core.repositories.IRepositoryNamed;
import ru.strict.db.mybatis.data.ServiceModel;
import ru.strict.db.mybatis.data.ServiceProvider;
import ru.strict.db.mybatis.data.TestData;
import ru.strict.db.mybatis.repositories.RepositoryRoleuser;
import ru.strict.db.mybatis.repositories.RepositoryServiceOnRole;
import ru.strict.db.mybatis.runners.TestRunner;

import java.util.List;
import java.util.UUID;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestRepositoryServiceOnRole {

    private static IRepositoryExtension<Integer, DtoServiceOnRole<Integer, ServiceModel>> REPOSITORY_NOT_GENERATE_ID;
    private static IRepositoryExtension<Integer, DtoServiceOnRole<Integer, ServiceModel>> REPOSITORY_GENERATE_NUMBER_ID;
    private static IRepositoryExtension<UUID, DtoServiceOnRole<UUID, ServiceModel>> REPOSITORY_GENERATE_UUID_ID;

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
                new MapperDtoFactory().instance(EntityRoleuser.class, DtoRoleuser.class)
        );
        MapperDtoServiceOnRole<UUID, ServiceModel> mapperUUID = new MapperDtoServiceOnRole<>(
                new ServiceProvider(),
                new MapperDtoFactory().instance(EntityRoleuser.class, DtoRoleuser.class)
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
        IRepositoryNamed<Integer, DtoRoleuser<Integer>> repositoryRoleuserNumberId = new RepositoryRoleuser<>(TestRunner.CREATE_DB_INTEGER_CONNECTION, GenerateIdType.NONE);
        IRepositoryNamed<UUID, DtoRoleuser<UUID>> repositoryRoleuserUuidId = new RepositoryRoleuser<>(TestRunner.CREATE_DB_UUID_CONNECTION, GenerateIdType.NONE);

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
        DtoServiceOnRole dto = new DtoServiceOnRole<>(TestData.SERVICE_ON_ROLE2.getServiceId(), TestData.SERVICE_ON_ROLE2.getRoleId());
        DtoServiceOnRole createdDto = REPOSITORY_GENERATE_NUMBER_ID.create(dto);
        Assert.assertNotNull(createdDto.getId());
    }

    /**
     * Создание с генерацией uuid идентификатора
     */
    @Test
    public void test002CreateGenerateUuidId(){
        DtoServiceOnRole dto = new DtoServiceOnRole<>(TestData.SERVICE_ON_ROLE2_UUID.getServiceId(), TestData.SERVICE_ON_ROLE2_UUID.getRoleId());
        DtoServiceOnRole createdDto = REPOSITORY_GENERATE_UUID_ID.create(dto);
        Assert.assertNotNull(createdDto.getId());
    }

    /**
     * Создание без генерации идентификатора
     */
    @Test
    public void test003CreateNotGenerateId(){
        DtoServiceOnRole createdDto = REPOSITORY_NOT_GENERATE_ID.create(TestData.SERVICE_ON_ROLE1);
        Assert.assertEquals(TestData.SERVICE_ON_ROLE1, createdDto);
    }

    /**
     * Чтение одной записи по integer идентификатору
     */
    @Test
    public void test004ReadByInteger(){
        DtoServiceOnRole dto = REPOSITORY_GENERATE_NUMBER_ID.read(TestData.SERVICE_ON_ROLE1.getId());
        Assert.assertEquals(TestData.SERVICE_ON_ROLE1, dto);
    }

    /**
     * Чтение всех записей из integer базы данныъх
     */
    @Test
    public void test006ReadAllInteger(){
        List<DtoServiceOnRole<Integer, ServiceModel>> list = REPOSITORY_GENERATE_NUMBER_ID.readAll(null);
        Assert.assertTrue(list.size() == 2);
    }

    /**
     * Чтение всех записей из uuid базы данныъх
     */
    @Test
    public void test007ReadAllUuid(){
        List<DtoServiceOnRole<UUID, ServiceModel>> list = REPOSITORY_GENERATE_UUID_ID.readAll(null);
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
        DtoServiceOnRole dto = REPOSITORY_GENERATE_NUMBER_ID.createOrRead(TestData.SERVICE_ON_ROLE1);
        Assert.assertEquals(TestData.SERVICE_ON_ROLE1, dto);
    }

    /**
     * Тестирование метода создания или чтения несуществует записи
     */
    @Test
    public void test013CreateOrReadNotExists(){
        DtoServiceOnRole dto = REPOSITORY_GENERATE_NUMBER_ID.createOrRead(TestData.SERVICE_ON_ROLE3);
        Assert.assertEquals(TestData.SERVICE_ON_ROLE3, dto);
    }

    /**
     * Обновление записи
     */
    @Test
    public void test014Update(){
        DtoServiceOnRole dto = REPOSITORY_GENERATE_NUMBER_ID.update(TestData.SERVICE_ON_ROLE1_UPDATED);
        Assert.assertEquals(TestData.SERVICE_ON_ROLE1_UPDATED, dto);
    }

    /**
     * Тестирование метода создания или обновления существует записи
     */
    @Test
    public void test015CreateOrUpdateExists(){
        DtoServiceOnRole dto = REPOSITORY_GENERATE_NUMBER_ID.createOrUpdate(TestData.SERVICE_ON_ROLE1);
        Assert.assertEquals(TestData.SERVICE_ON_ROLE1, dto);
    }

    /**
     * Тестирование метода создания или обновления несуществует записи
     */
    @Test
    public void test016CreateOrUpdateNotExists(){
        DtoServiceOnRole dto = REPOSITORY_GENERATE_NUMBER_ID.createOrUpdate(TestData.SERVICE_ON_ROLE4);
        Assert.assertEquals(TestData.SERVICE_ON_ROLE4, dto);
    }

    /**
     * Проверка того, что предыдущие методы createOrRead и createOrUpdate успешно выполнены
     */
    @Test
    public void test017ExecuteCreateAndUpdateIsSuccess(){
        List<DtoServiceOnRole<Integer, ServiceModel>> list = REPOSITORY_GENERATE_NUMBER_ID.readAll(null);
        Assert.assertTrue(list.size() == 4);
    }

    /**
     * Удаление записи
     */
    @Test
    public void test018Delete(){
        REPOSITORY_GENERATE_NUMBER_ID.delete(TestData.CITY1.getId());
        List<DtoServiceOnRole<Integer, ServiceModel>> list = REPOSITORY_GENERATE_NUMBER_ID.readAll(null);
        DtoServiceOnRole<Integer, ServiceModel> dto = REPOSITORY_GENERATE_NUMBER_ID.read(TestData.SERVICE_ON_ROLE1.getId());
        Assert.assertTrue(list.size() == 3);
        Assert.assertNull(dto);
    }
}
