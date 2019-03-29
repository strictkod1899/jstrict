package ru.strict.db.spring;

import org.junit.*;
import org.junit.runners.MethodSorters;
import ru.strict.db.core.common.GenerateIdType;
import ru.strict.db.core.dto.DtoProfile;
import ru.strict.db.core.dto.DtoUser;
import ru.strict.db.core.repositories.IRepositoryExtension;
import ru.strict.db.core.repositories.IRepositoryNamed;
import ru.strict.db.spring.data.TestData;
import ru.strict.db.spring.repositories.RepositoryProfile;
import ru.strict.db.spring.repositories.RepositoryUser;
import ru.strict.db.spring.runners.TestRunner;

import java.util.List;
import java.util.UUID;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestRepositoryProfile {

    private static IRepositoryExtension<Integer, DtoProfile<Integer>> REPOSITORY_NOT_GENERATE_ID;
    private static IRepositoryExtension<Integer, DtoProfile<Integer>> REPOSITORY_GENERATE_NUMBER_ID;
    private static IRepositoryExtension<UUID, DtoProfile<UUID>> REPOSITORY_GENERATE_UUID_ID;

    @BeforeClass
    public static void prepare(){
        prepareRepositories();
        prepareData();
    }

    /**
     * Подготовить тестовые репозитории
     */
    private static void prepareRepositories(){
        REPOSITORY_NOT_GENERATE_ID = new RepositoryProfile<>(TestRunner.CREATE_DB_INTEGER_CONNECTION, GenerateIdType.NONE);
        REPOSITORY_GENERATE_NUMBER_ID = new RepositoryProfile<>(TestRunner.CREATE_DB_INTEGER_CONNECTION, GenerateIdType.NUMBER);
        REPOSITORY_GENERATE_UUID_ID = new RepositoryProfile<>(TestRunner.CREATE_DB_UUID_CONNECTION, GenerateIdType.UUID);
        TestRunner.repositories.add(REPOSITORY_GENERATE_NUMBER_ID);
        TestRunner.repositories.add(REPOSITORY_GENERATE_UUID_ID);
    }

    /**
     * Подготовить тестовые данные
     */
    private static void prepareData(){
        IRepositoryNamed<Integer, DtoUser<Integer>> repositoryUserNumberId = new RepositoryUser<>(TestRunner.CREATE_DB_INTEGER_CONNECTION, GenerateIdType.NONE);
        IRepositoryNamed<UUID, DtoUser<UUID>> repositoryUserUuidId = new RepositoryUser<>(TestRunner.CREATE_DB_UUID_CONNECTION, GenerateIdType.NONE);

        TestRunner.repositories.add(repositoryUserNumberId);
        TestRunner.repositories.add(repositoryUserUuidId);

        repositoryUserNumberId.create(TestData.USER1);
        repositoryUserUuidId.create(TestData.USER1_UUID);
        repositoryUserNumberId.create(TestData.USER2);
        repositoryUserUuidId.create(TestData.USER2_UUID);
        repositoryUserNumberId.create(TestData.USER3);
        repositoryUserUuidId.create(TestData.USER3_UUID);
        repositoryUserNumberId.create(TestData.USER4);
        repositoryUserUuidId.create(TestData.USER4_UUID);
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
        DtoProfile dto = new DtoProfile<>("name", "surname", "middlename", TestData.USER2.getId());
        DtoProfile createdDto = REPOSITORY_GENERATE_NUMBER_ID.create(dto);
        Assert.assertNotNull(createdDto.getId());
    }

    /**
     * Создание с генерацией uuid идентификатора
     */
    @Test
    public void test002CreateGenerateUuidId(){
        DtoProfile dto = new DtoProfile<>("name", "surname", "middlename", TestData.USER2_UUID.getId());
        DtoProfile createdDto = REPOSITORY_GENERATE_UUID_ID.create(dto);
        Assert.assertNotNull(createdDto.getId());
    }

    /**
     * Создание без генерации integer идентификатора
     */
    @Test
    public void test003CreateNotGenerateId(){
        DtoProfile createdDto = REPOSITORY_NOT_GENERATE_ID.create(TestData.PROFILE1);
        Assert.assertEquals(TestData.PROFILE1, createdDto);
    }

    /**
     * Чтение одной записи по integer идентификатору
     */
    @Test
    public void test004ReadByInteger(){
        DtoProfile dto = REPOSITORY_GENERATE_NUMBER_ID.read(TestData.PROFILE1.getId());
        Assert.assertEquals(TestData.PROFILE1, dto);
    }

    /**
     * Чтение всех записей из integer базы данныъх
     */
    @Test
    public void test006ReadAllInteger(){
        List<DtoProfile<Integer>> list = REPOSITORY_GENERATE_NUMBER_ID.readAll(null);
        Assert.assertTrue(list.size() == 2);
    }

    /**
     * Чтение всех записей из uuid базы данныъх
     */
    @Test
    public void test007ReadAllUuid(){
        List<DtoProfile<UUID>> list = REPOSITORY_GENERATE_UUID_ID.readAll(null);
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
        boolean isRowExists = REPOSITORY_GENERATE_NUMBER_ID.isRowExists(TestData.PROFILE1.getId());
        Assert.assertTrue(isRowExists);
    }

    /**
     * Тестирование метода создания или чтения существует записи
     */
    @Test
    public void test012CreateOrReadExists(){
        DtoProfile dto = REPOSITORY_GENERATE_NUMBER_ID.createOrRead(TestData.PROFILE1);
        Assert.assertEquals(TestData.PROFILE1, dto);
    }

    /**
     * Тестирование метода создания или чтения несуществует записи
     */
    @Test
    public void test013CreateOrReadNotExists(){
        DtoProfile<Integer> newDto = new DtoProfile<>(101, "name10", "surname10", "middlename10", TestData.USER3.getId());
        DtoProfile dto = REPOSITORY_GENERATE_NUMBER_ID.createOrRead(newDto);
        Assert.assertEquals(newDto, dto);
    }

    /**
     * Обновление записи
     */
    @Test
    public void test014Update(){
        DtoProfile dto = REPOSITORY_GENERATE_NUMBER_ID.update(TestData.PROFILE1_UPDATED);
        Assert.assertEquals(TestData.PROFILE1_UPDATED, dto);
    }

    /**
     * Тестирование метода создания или обновления существует записи
     */
    @Test
    public void test015CreateOrUpdateExists(){
        DtoProfile dto = REPOSITORY_GENERATE_NUMBER_ID.createOrUpdate(TestData.PROFILE1);
        Assert.assertEquals(TestData.PROFILE1, dto);
    }

    /**
     * Тестирование метода создания или обновления несуществует записи
     */
    @Test
    public void test016CreateOrUpdateNotExists(){
        DtoProfile<Integer> newDto = new DtoProfile<>(102, "name11", "surname11", "middlename11", TestData.USER4.getId());
        DtoProfile dto = REPOSITORY_GENERATE_NUMBER_ID.createOrUpdate(newDto);
        Assert.assertEquals(newDto, dto);
    }

    /**
     * Проверка того, что предыдущие методы createOrRead и createOrUpdate успешно выполнены
     */
    @Test
    public void test017ExecuteCreateAndUpdateIsSuccess(){
        List<DtoProfile<Integer>> list = REPOSITORY_GENERATE_NUMBER_ID.readAll(null);
        Assert.assertTrue(list.size() == 4);
    }

    /**
     * Удаление записи
     */
    @Test
    public void test018Delete(){
        REPOSITORY_GENERATE_NUMBER_ID.delete(TestData.PROFILE1.getId());
        List<DtoProfile<Integer>> list = REPOSITORY_GENERATE_NUMBER_ID.readAll(null);
        DtoProfile<Integer> dto = REPOSITORY_GENERATE_NUMBER_ID.read(TestData.PROFILE1.getId());
        Assert.assertTrue(list.size() == 3);
        Assert.assertNull(dto);
    }
}
