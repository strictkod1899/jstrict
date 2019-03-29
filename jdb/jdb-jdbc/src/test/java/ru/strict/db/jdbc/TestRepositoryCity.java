package ru.strict.db.jdbc;

import org.junit.*;
import org.junit.runners.MethodSorters;
import ru.strict.db.core.common.GenerateIdType;
import ru.strict.db.core.dto.DtoCity;
import ru.strict.db.core.dto.DtoCountry;
import ru.strict.db.core.repositories.IRepositoryNamed;
import ru.strict.db.jdbc.data.TestData;
import ru.strict.db.jdbc.repositories.RepositoryCity;
import ru.strict.db.jdbc.repositories.RepositoryCountry;
import ru.strict.db.jdbc.runners.TestRunner;

import java.util.List;
import java.util.UUID;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestRepositoryCity {

    private static IRepositoryNamed<Integer, DtoCity<Integer>> REPOSITORY_NOT_GENERATE_ID;
    private static IRepositoryNamed<Integer, DtoCity<Integer>> REPOSITORY_GENERATE_NUMBER_ID;
    private static IRepositoryNamed<UUID, DtoCity<UUID>> REPOSITORY_GENERATE_UUID_ID;

    @BeforeClass
    public static void prepare(){
        prepareRepositories();
        prepareData();
    }

    /**
     * Подготовить тестовые репозитории
     */
    private static void prepareRepositories(){
        REPOSITORY_NOT_GENERATE_ID = new RepositoryCity<>(TestRunner.CREATE_DB_INTEGER_CONNECTION, GenerateIdType.NONE);
        REPOSITORY_GENERATE_NUMBER_ID = new RepositoryCity<>(TestRunner.CREATE_DB_INTEGER_CONNECTION, GenerateIdType.NUMBER);
        REPOSITORY_GENERATE_UUID_ID = new RepositoryCity<>(TestRunner.CREATE_DB_UUID_CONNECTION, GenerateIdType.UUID);
        TestRunner.repositories.add(REPOSITORY_GENERATE_NUMBER_ID);
        TestRunner.repositories.add(REPOSITORY_GENERATE_UUID_ID);
    }

    /**
     * Подготовить тестовые данные
     */
    private static void prepareData(){
        IRepositoryNamed<Integer, DtoCountry<Integer>> repositoryCountryNumberId = new RepositoryCountry<>(TestRunner.CREATE_DB_INTEGER_CONNECTION, GenerateIdType.NONE);
        IRepositoryNamed<UUID, DtoCountry<UUID>> repositoryCountryUuidId = new RepositoryCountry<>(TestRunner.CREATE_DB_UUID_CONNECTION, GenerateIdType.NONE);

        TestRunner.repositories.add(repositoryCountryNumberId);
        TestRunner.repositories.add(repositoryCountryUuidId);

        repositoryCountryNumberId.create(TestData.COUNTRY1);
        repositoryCountryUuidId.create(TestData.COUNTRY1_UUID);
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
        DtoCity dto = new DtoCity<>("city", TestData.COUNTRY1.getId());
        DtoCity createdDto = REPOSITORY_GENERATE_NUMBER_ID.create(dto);
        Assert.assertNotNull(createdDto.getId());
    }

    /**
     * Создание с генерацией uuid идентификатора
     */
    @Test
    public void test002CreateGenerateUuidId(){
        DtoCity dto = new DtoCity<>("city", TestData.COUNTRY1_UUID.getId());
        DtoCity createdDto = REPOSITORY_GENERATE_UUID_ID.create(dto);
        Assert.assertNotNull(createdDto.getId());
    }

    /**
     * Создание без генерации идентификатора
     */
    @Test
    public void test003CreateNotGenerateId(){
        DtoCity createdDto = REPOSITORY_NOT_GENERATE_ID.create(TestData.CITY1);
        Assert.assertEquals(TestData.CITY1, createdDto);
    }

    /**
     * Чтение одной записи по integer идентификатору
     */
    @Test
    public void test004ReadByInteger(){
        DtoCity dto = REPOSITORY_GENERATE_NUMBER_ID.read(TestData.CITY1.getId());
        Assert.assertEquals(TestData.CITY1, dto);
    }

    /**
     * Чтение всех записей из integer базы данныъх
     */
    @Test
    public void test006ReadAllInteger(){
        List<DtoCity<Integer>> list = REPOSITORY_GENERATE_NUMBER_ID.readAll(null);
        Assert.assertTrue(list.size() == 2);
    }

    /**
     * Чтение всех записей из uuid базы данныъх
     */
    @Test
    public void test007ReadAllUuid(){
        List<DtoCity<UUID>> list = REPOSITORY_GENERATE_UUID_ID.readAll(null);
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
     * Чтение записи по названию
     */
    @Test
    public void test009ReadByName(){
        DtoCity dto = REPOSITORY_GENERATE_NUMBER_ID.readByName(TestData.CITY1.getCaption());
        Assert.assertEquals(TestData.CITY1, dto);
    }

    /**
     * Чтение нескольких записей по названию
     */
    @Test
    public void test010ReadAllByName(){
        List<DtoCity<Integer>> list = REPOSITORY_GENERATE_NUMBER_ID.readAllByName(TestData.CITY1.getCaption());
        Assert.assertTrue(list.size() == 1 && list.get(0).equals(TestData.CITY1));
    }

    /**
     * Проверить существование записи по id
     */
    @Test
    public void test011IsRowExists(){
        boolean isRowExists = REPOSITORY_GENERATE_NUMBER_ID.isRowExists(TestData.CITY1.getId());
        Assert.assertTrue(isRowExists);
    }

    /**
     * Тестирование метода создания или чтения существует записи
     */
    @Test
    public void test012CreateOrReadExists(){
        DtoCity dto = REPOSITORY_GENERATE_NUMBER_ID.createOrRead(TestData.CITY1);
        Assert.assertEquals(TestData.CITY1, dto);
    }

    /**
     * Тестирование метода создания или чтения несуществует записи
     */
    @Test
    public void test013CreateOrReadNotExists(){
        DtoCity<Integer> newDto = new DtoCity<>(101, "city10", TestData.CITY1.getCountryId());
        DtoCity dto = REPOSITORY_GENERATE_NUMBER_ID.createOrRead(newDto);
        Assert.assertEquals(newDto, dto);
    }

    /**
     * Обновление записи
     */
    @Test
    public void test014Update(){
        DtoCity dto = REPOSITORY_GENERATE_NUMBER_ID.update(TestData.CITY1_UPDATED);
        Assert.assertEquals(TestData.CITY1_UPDATED, dto);
    }

    /**
     * Тестирование метода создания или обновления существует записи
     */
    @Test
    public void test015CreateOrUpdateExists(){
        DtoCity dto = REPOSITORY_GENERATE_NUMBER_ID.createOrUpdate(TestData.CITY1);
        Assert.assertEquals(TestData.CITY1, dto);
    }

    /**
     * Тестирование метода создания или обновления несуществует записи
     */
    @Test
    public void test016CreateOrUpdateNotExists(){
        DtoCity<Integer> newDto = new DtoCity<>(102, "city11", TestData.CITY1.getCountryId());
        DtoCity dto = REPOSITORY_GENERATE_NUMBER_ID.createOrUpdate(newDto);
        Assert.assertEquals(newDto, dto);
    }

    /**
     * Проверка того, что предыдущие методы createOrRead и createOrUpdate успешно выполнены
     */
    @Test
    public void test017ExecuteCreateAndUpdateIsSuccess(){
        List<DtoCity<Integer>> list = REPOSITORY_GENERATE_NUMBER_ID.readAll(null);
        Assert.assertTrue(list.size() == 4);
    }

    /**
     * Удаление записи
     */
    @Test
    public void test018Delete(){
        REPOSITORY_GENERATE_NUMBER_ID.delete(TestData.CITY1.getId());
        List<DtoCity<Integer>> list = REPOSITORY_GENERATE_NUMBER_ID.readAll(null);
        DtoCity<Integer> dto = REPOSITORY_GENERATE_NUMBER_ID.read(TestData.CITY1.getId());
        Assert.assertTrue(list.size() == 3);
        Assert.assertNull(dto);
    }
}
