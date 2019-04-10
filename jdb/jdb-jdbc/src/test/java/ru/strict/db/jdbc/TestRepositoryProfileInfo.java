package ru.strict.db.jdbc;

import org.junit.*;
import org.junit.runners.MethodSorters;
import ru.strict.db.core.common.GenerateIdType;
import ru.strict.db.core.dto.DtoCity;
import ru.strict.db.core.dto.DtoProfileInfo;
import ru.strict.db.core.dto.DtoUser;
import ru.strict.db.core.repositories.IRepositoryExtension;
import ru.strict.db.core.repositories.IRepositoryNamed;
import ru.strict.db.core.repositories.interfaces.IRepositoryProfile;
import ru.strict.db.jdbc.data.TestData;
import ru.strict.db.jdbc.repositories.RepositoryCity;
import ru.strict.db.jdbc.repositories.RepositoryProfileInfo;
import ru.strict.db.jdbc.repositories.RepositoryUser;
import ru.strict.db.jdbc.runners.TestRunner;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestRepositoryProfileInfo {

    private static IRepositoryProfile<Integer, DtoProfileInfo<Integer>> REPOSITORY_NOT_GENERATE_ID;
    private static IRepositoryProfile<Integer, DtoProfileInfo<Integer>> REPOSITORY_GENERATE_NUMBER_ID;
    private static IRepositoryProfile<UUID, DtoProfileInfo<UUID>> REPOSITORY_GENERATE_UUID_ID;

    @BeforeClass
    public static void prepare(){
        prepareRepositories();
        prepareData();
    }

    /**
     * Подготовить тестовые репозитории
     */
    private static void prepareRepositories(){
        REPOSITORY_NOT_GENERATE_ID = new RepositoryProfileInfo<>(TestRunner.CREATE_DB_INTEGER_CONNECTION, GenerateIdType.NONE);
        REPOSITORY_GENERATE_NUMBER_ID = new RepositoryProfileInfo<>(TestRunner.CREATE_DB_INTEGER_CONNECTION, GenerateIdType.NUMBER);
        REPOSITORY_GENERATE_UUID_ID = new RepositoryProfileInfo<>(TestRunner.CREATE_DB_UUID_CONNECTION, GenerateIdType.UUID);
        TestRunner.repositories.add(REPOSITORY_GENERATE_NUMBER_ID);
        TestRunner.repositories.add(REPOSITORY_GENERATE_UUID_ID);
    }

    /**
     * Подготовить тестовые данные
     */
    private static void prepareData(){
        IRepositoryNamed<Integer, DtoUser<Integer>> repositoryUserNumberId = new RepositoryUser<>(TestRunner.CREATE_DB_INTEGER_CONNECTION, GenerateIdType.NONE);
        IRepositoryNamed<UUID, DtoUser<UUID>> repositoryUserUuidId = new RepositoryUser<>(TestRunner.CREATE_DB_UUID_CONNECTION, GenerateIdType.NONE);
        IRepositoryNamed<Integer, DtoCity<Integer>> repositoryCityNumberId = new RepositoryCity<>(TestRunner.CREATE_DB_INTEGER_CONNECTION, GenerateIdType.NONE);
        IRepositoryNamed<UUID, DtoCity<UUID>> repositoryCityUuidId = new RepositoryCity<>(TestRunner.CREATE_DB_UUID_CONNECTION, GenerateIdType.NONE);

        TestRunner.repositories.add(repositoryUserNumberId);
        TestRunner.repositories.add(repositoryUserUuidId);
        TestRunner.repositories.add(repositoryCityNumberId);
        TestRunner.repositories.add(repositoryCityUuidId);

        repositoryUserNumberId.create(TestData.USER1);
        repositoryUserUuidId.create(TestData.USER1_UUID);
        repositoryUserNumberId.create(TestData.USER2);
        repositoryUserUuidId.create(TestData.USER2_UUID);
        repositoryUserNumberId.create(TestData.USER3);
        repositoryUserUuidId.create(TestData.USER3_UUID);
        repositoryUserNumberId.create(TestData.USER4);
        repositoryUserUuidId.create(TestData.USER4_UUID);

        repositoryCityNumberId.create(TestData.CITY1);
        repositoryCityUuidId.create(TestData.CITY1_UUID);
        repositoryCityNumberId.create(TestData.CITY2);
        repositoryCityUuidId.create(TestData.CITY2_UUID);
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
        DtoProfileInfo dto = new DtoProfileInfo<>("name", "surname", "middlename", TestData.USER2.getId(), new Date(), "phone", TestData.CITY1.getId());
        DtoProfileInfo createdDto = REPOSITORY_GENERATE_NUMBER_ID.create(dto);
        Assert.assertNotNull(createdDto.getId());
    }

    /**
     * Создание с генерацией uuid идентификатора
     */
    @Test
    public void test002CreateGenerateUuidId(){
        DtoProfileInfo dto = new DtoProfileInfo<>("name", "surname", "middlename", TestData.USER2_UUID.getId(), new Date(), "phone", TestData.CITY1_UUID.getId());
        DtoProfileInfo createdDto = REPOSITORY_GENERATE_UUID_ID.create(dto);
        Assert.assertNotNull(createdDto.getId());
    }

    /**
     * Создание без генерации integer идентификатора
     */
    @Test
    public void test003CreateNotGenerateId(){
        DtoProfileInfo createdDto = REPOSITORY_NOT_GENERATE_ID.create(TestData.PROFILE_INFO1);
        Assert.assertEquals(TestData.PROFILE_INFO1, createdDto);
    }

    /**
     * Чтение одной записи по integer идентификатору
     */
    @Test
    public void test004ReadByInteger(){
        DtoProfileInfo dto = REPOSITORY_GENERATE_NUMBER_ID.read(TestData.PROFILE_INFO1.getId());
        Assert.assertEquals(TestData.PROFILE_INFO1, dto);
    }

    /**
     * Чтение всех записей из integer базы данныъх
     */
    @Test
    public void test006ReadAllInteger(){
        List<DtoProfileInfo<Integer>> list = REPOSITORY_GENERATE_NUMBER_ID.readAll(null);
        Assert.assertTrue(list.size() == 2);
    }

    /**
     * Чтение всех записей из uuid базы данныъх
     */
    @Test
    public void test007ReadAllUuid(){
        List<DtoProfileInfo<UUID>> list = REPOSITORY_GENERATE_UUID_ID.readAll(null);
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
        boolean isRowExists = REPOSITORY_GENERATE_NUMBER_ID.isRowExists(TestData.PROFILE_INFO1.getId());
        Assert.assertTrue(isRowExists);
    }

    /**
     * Тестирование метода создания или чтения существует записи
     */
    @Test
    public void test012CreateOrReadExists(){
        DtoProfileInfo dto = REPOSITORY_GENERATE_NUMBER_ID.createOrRead(TestData.PROFILE_INFO1);
        Assert.assertEquals(TestData.PROFILE_INFO1, dto);
    }

    /**
     * Тестирование метода создания или чтения несуществует записи
     */
    @Test
    public void test013CreateOrReadNotExists(){
        DtoProfileInfo<Integer> newDto = new DtoProfileInfo<>(101, "name10", "surname10", "middlename10", TestData.USER3.getId(), new Date(), "phone10", TestData.CITY1.getId());
        DtoProfileInfo dto = REPOSITORY_GENERATE_NUMBER_ID.createOrRead(newDto);
        Assert.assertEquals(newDto, dto);
    }

    /**
     * Обновление записи
     */
    @Test
    public void test014Update(){
        DtoProfileInfo dto = REPOSITORY_GENERATE_NUMBER_ID.update(TestData.PROFILE_INFO1_UPDATED);
        Assert.assertEquals(TestData.PROFILE_INFO1_UPDATED, dto);
    }

    /**
     * Тестирование метода создания или обновления существует записи
     */
    @Test
    public void test015CreateOrUpdateExists(){
        DtoProfileInfo dto = REPOSITORY_GENERATE_NUMBER_ID.createOrUpdate(TestData.PROFILE_INFO1);
        Assert.assertEquals(TestData.PROFILE_INFO1, dto);
    }

    /**
     * Тестирование метода создания или обновления несуществует записи
     */
    @Test
    public void test016CreateOrUpdateNotExists(){
        DtoProfileInfo<Integer> newDto = new DtoProfileInfo<>(102, "name11", "surname11", "middlename11", TestData.USER4.getId(), new Date(), "phone11", TestData.CITY1.getId());
        DtoProfileInfo dto = REPOSITORY_GENERATE_NUMBER_ID.createOrUpdate(newDto);
        Assert.assertEquals(newDto, dto);
    }

    /**
     * Проверка того, что предыдущие методы createOrRead и createOrUpdate успешно выполнены
     */
    @Test
    public void test017ExecuteCreateAndUpdateIsSuccess(){
        List<DtoProfileInfo<Integer>> list = REPOSITORY_GENERATE_NUMBER_ID.readAll(null);
        Assert.assertTrue(list.size() == 4);
    }

    /**
     * Удаление записи
     */
    @Test
    public void test018Delete(){
        REPOSITORY_GENERATE_NUMBER_ID.delete(TestData.PROFILE_INFO1.getId());
        List<DtoProfileInfo<Integer>> list = REPOSITORY_GENERATE_NUMBER_ID.readAll(null);
        DtoProfileInfo<Integer> dto = REPOSITORY_GENERATE_NUMBER_ID.read(TestData.PROFILE_INFO1.getId());
        Assert.assertTrue(list.size() == 3);
        Assert.assertNull(dto);
    }

    @Test
    public void test019ReadByFio(){
        REPOSITORY_GENERATE_NUMBER_ID.create(TestData.PROFILE_INFO2);
        List<DtoProfileInfo<Integer>> list = REPOSITORY_GENERATE_NUMBER_ID.readByFio(
                TestData.PROFILE_INFO2.getName(),
                TestData.PROFILE_INFO2.getSurname(),
                TestData.PROFILE_INFO2.getMiddlename()
        );
        Assert.assertTrue(list.size() == 1);
        Assert.assertTrue(list.get(0).equals(TestData.PROFILE_INFO2));
    }

    @Test
    public void test020ReadByUserId(){
        List<DtoProfileInfo<Integer>> list = REPOSITORY_GENERATE_NUMBER_ID.readByUserId(TestData.PROFILE_INFO2.getUserId());
        Assert.assertTrue(list.size() == 1);
        Assert.assertTrue(list.get(0).equals(TestData.PROFILE_INFO2));
    }
}
