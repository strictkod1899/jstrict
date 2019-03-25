package ru.strict.db.mybatis;

import org.junit.*;
import org.junit.runners.MethodSorters;
import ru.strict.db.core.common.GenerateIdType;
import ru.strict.db.core.dto.DtoCountry;
import ru.strict.db.core.repositories.IRepositoryNamed;
import ru.strict.db.mybatis.data.TestData;
import ru.strict.db.mybatis.repositories.RepositoryCountry;
import ru.strict.db.mybatis.runners.TestRunner;

import java.util.List;
import java.util.UUID;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestRepositoryCountry {

    private static IRepositoryNamed<Integer, DtoCountry<Integer>> REPOSITORY_NOT_GENERATE_ID;
    private static IRepositoryNamed<Integer, DtoCountry<Integer>> REPOSITORY_GENERATE_NUMBER_ID;
    private static IRepositoryNamed<UUID, DtoCountry<UUID>> REPOSITORY_GENERATE_UUID_ID;

    @BeforeClass
    public static void prepare(){
        REPOSITORY_NOT_GENERATE_ID = new RepositoryCountry<>(TestRunner.CREATE_DB_INTEGER_CONNECTION, GenerateIdType.NONE);
        REPOSITORY_GENERATE_NUMBER_ID = new RepositoryCountry<>(TestRunner.CREATE_DB_INTEGER_CONNECTION, GenerateIdType.NUMBER);
        REPOSITORY_GENERATE_UUID_ID = new RepositoryCountry<>(TestRunner.CREATE_DB_UUID_CONNECTION, GenerateIdType.UUID);
        TestRunner.repositories.add(REPOSITORY_GENERATE_NUMBER_ID);
        TestRunner.repositories.add(REPOSITORY_GENERATE_UUID_ID);
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
        DtoCountry dto = new DtoCountry<>("country");
        DtoCountry createdDto = REPOSITORY_GENERATE_NUMBER_ID.create(dto);
        Assert.assertNotNull(createdDto.getId());
    }

    /**
     * Создание с генерацией uuid идентификатора
     */
    @Test
    public void test002CreateGenerateUuidId(){
        DtoCountry dto = new DtoCountry<>("country");
        DtoCountry createdDto = REPOSITORY_GENERATE_UUID_ID.create(dto);
        Assert.assertNotNull(createdDto.getId());
    }

    /**
     * Создание без генерации integer идентификатора
     */
    @Test
    public void test003CreateNotGenerateId(){
        DtoCountry createdDto = REPOSITORY_NOT_GENERATE_ID.create(TestData.COUNTRY1);
        Assert.assertEquals(TestData.COUNTRY1, createdDto);
    }

    /**
     * Чтение одной записи по integer идентификатору
     */
    @Test
    public void test004ReadByInteger(){
        DtoCountry dto = REPOSITORY_GENERATE_NUMBER_ID.read(TestData.COUNTRY1.getId());
        Assert.assertEquals(TestData.COUNTRY1, dto);
    }

    /**
     * Чтение всех записей из integer базы данныъх
     */
    @Test
    public void test006ReadAllInteger(){
        List<DtoCountry<Integer>> list = REPOSITORY_GENERATE_NUMBER_ID.readAll(null);
        Assert.assertTrue(list.size() == 2);
    }

    /**
     * Чтение всех записей из uuid базы данныъх
     */
    @Test
    public void test007ReadAllUuid(){
        List<DtoCountry<UUID>> list = REPOSITORY_GENERATE_UUID_ID.readAll(null);
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
        DtoCountry dto = REPOSITORY_GENERATE_NUMBER_ID.readByName(TestData.COUNTRY1.getCaption());
        Assert.assertEquals(TestData.COUNTRY1, dto);
    }

    /**
     * Чтение нескольких записей по названию
     */
    @Test
    public void test010ReadAllByName(){
        List<DtoCountry<Integer>> list = REPOSITORY_GENERATE_NUMBER_ID.readAllByName(TestData.COUNTRY1.getCaption());
        Assert.assertTrue(list.size() == 1 && list.get(0).equals(TestData.COUNTRY1));
    }

    /**
     * Проверить существование записи по id
     */
    @Test
    public void test011IsRowExists(){
        boolean isRowExists = REPOSITORY_GENERATE_NUMBER_ID.isRowExists(TestData.COUNTRY1.getId());
        Assert.assertTrue(isRowExists);
    }

    /**
     * Тестирование метода создания или чтения существует записи
     */
    @Test
    public void test012CreateOrReadExists(){
        DtoCountry dto = REPOSITORY_GENERATE_NUMBER_ID.createOrRead(TestData.COUNTRY1);
        Assert.assertEquals(TestData.COUNTRY1, dto);
    }

    /**
     * Тестирование метода создания или чтения несуществует записи
     */
    @Test
    public void test013CreateOrReadNotExists(){
        DtoCountry<Integer> newDto = new DtoCountry<>(101, "country10");
        DtoCountry dto = REPOSITORY_GENERATE_NUMBER_ID.createOrRead(newDto);
        Assert.assertEquals(newDto, dto);
    }

    /**
     * Обновление записи
     */
    @Test
    public void test014Update(){
        DtoCountry dto = REPOSITORY_GENERATE_NUMBER_ID.update(TestData.COUNTRY1_UPDATED);
        Assert.assertEquals(TestData.COUNTRY1_UPDATED, dto);
    }

    /**
     * Тестирование метода создания или обновления существует записи
     */
    @Test
    public void test015CreateOrUpdateExists(){
        DtoCountry dto = REPOSITORY_GENERATE_NUMBER_ID.createOrUpdate(TestData.COUNTRY1_UPDATED);
        Assert.assertEquals(TestData.COUNTRY1_UPDATED, dto);
    }

    /**
     * Тестирование метода создания или обновления несуществует записи
     */
    @Test
    public void test016CreateOrUpdateNotExists(){
        DtoCountry<Integer> newDto = new DtoCountry<>(102, "country11");
        DtoCountry dto = REPOSITORY_GENERATE_NUMBER_ID.createOrUpdate(newDto);
        Assert.assertEquals(newDto, dto);
    }

    /**
     * Проверка того, что предыдущие методы createOrRead и createOrUpdate успешно выполнены
     */
    @Test
    public void test017ExecuteCreateAndUpdateIsSuccess(){
        List<DtoCountry<Integer>> list = REPOSITORY_GENERATE_NUMBER_ID.readAll(null);
        Assert.assertTrue(list.size() == 4);
    }

    /**
     * Удаление записи
     */
    @Test
    public void test018Delete(){
        REPOSITORY_GENERATE_NUMBER_ID.delete(TestData.COUNTRY1.getId());
        List<DtoCountry<Integer>> list = REPOSITORY_GENERATE_NUMBER_ID.readAll(null);
        DtoCountry<Integer> dto = REPOSITORY_GENERATE_NUMBER_ID.read(TestData.COUNTRY1.getId());
        Assert.assertTrue(list.size() == 3);
        Assert.assertNull(dto);
    }
}
