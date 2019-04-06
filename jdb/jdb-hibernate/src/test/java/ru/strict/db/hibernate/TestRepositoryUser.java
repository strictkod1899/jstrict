package ru.strict.db.hibernate;

import org.junit.*;
import org.junit.runners.MethodSorters;
import ru.strict.db.core.common.GenerateIdType;
import ru.strict.db.core.dto.DtoUser;
import ru.strict.db.core.entities.EntityUser;
import ru.strict.db.core.mappers.dto.MapperDtoFactory;
import ru.strict.db.core.repositories.IRepositoryNamed;
import ru.strict.db.hibernate.data.TestData;
import ru.strict.db.hibernate.repositories.RepositoryUser;
import ru.strict.db.hibernate.runners.TestRunner;

import java.util.List;
import java.util.UUID;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestRepositoryUser {

    private static IRepositoryNamed<Integer, DtoUser<Integer>> REPOSITORY_NOT_GENERATE_ID;
    private static IRepositoryNamed<Integer, DtoUser<Integer>> REPOSITORY_GENERATE_NUMBER_ID;
    private static IRepositoryNamed<UUID, DtoUser<UUID>> REPOSITORY_GENERATE_UUID_ID;

    @BeforeClass
    public static void prepare(){
        prepareRepositories();
    }

    /**
     * Подготовить тестовые репозитории
     */
    private static void prepareRepositories(){
        REPOSITORY_NOT_GENERATE_ID = new RepositoryUser<>(TestRunner.CREATE_DB_INTEGER_CONNECTION,
                new MapperDtoFactory().instance(EntityUser.class, DtoUser.class),
                GenerateIdType.NONE);
        REPOSITORY_GENERATE_NUMBER_ID = new RepositoryUser<>(TestRunner.CREATE_DB_INTEGER_CONNECTION,
                new MapperDtoFactory().instance(EntityUser.class, DtoUser.class),
                GenerateIdType.NUMBER);
        REPOSITORY_GENERATE_UUID_ID = new RepositoryUser<>(TestRunner.CREATE_DB_UUID_CONNECTION,
                new MapperDtoFactory().instance(EntityUser.class, DtoUser.class),
                GenerateIdType.UUID);
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
        DtoUser dto = new DtoUser<>("user", "user@mail.ru", "password");
        DtoUser createdDto = REPOSITORY_GENERATE_NUMBER_ID.create(dto);
        Assert.assertNotNull(createdDto.getId());
    }

    /**
     * Создание с генерацией uuid идентификатора
     */
    @Test
    public void test002CreateGenerateUuidId(){
        DtoUser dto = new DtoUser<>("user", "user@mail.ru", "password");
        DtoUser createdDto = REPOSITORY_GENERATE_UUID_ID.create(dto);
        Assert.assertNotNull(createdDto.getId());
    }

    /**
     * Создание без генерации integer идентификатора
     */
    @Test
    public void test003CreateNotGenerateId(){
        DtoUser createdDto = REPOSITORY_NOT_GENERATE_ID.create(TestData.USER1);
        Assert.assertEquals(TestData.USER1, createdDto);
    }

    /**
     * Чтение одной записи по integer идентификатору
     */
    @Test
    public void test004ReadByInteger(){
        DtoUser dto = REPOSITORY_GENERATE_NUMBER_ID.read(TestData.USER1.getId());
        Assert.assertEquals(TestData.USER1, dto);
    }

    /**
     * Чтение всех записей из integer базы данныъх
     */
    @Test
    public void test006ReadAllInteger(){
        List<DtoUser<Integer>> list = REPOSITORY_GENERATE_NUMBER_ID.readAll(null);
        Assert.assertTrue(list.size() == 2);
    }

    /**
     * Чтение всех записей из uuid базы данныъх
     */
    @Test
    public void test007ReadAllUuid(){
        List<DtoUser<UUID>> list = REPOSITORY_GENERATE_UUID_ID.readAll(null);
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
        DtoUser dto = REPOSITORY_GENERATE_NUMBER_ID.readByName(TestData.USER1.getUsername());
        Assert.assertEquals(TestData.USER1, dto);
    }

    /**
     * Чтение нескольких записей по названию
     */
    @Test
    public void test010ReadAllByName(){
        List<DtoUser<Integer>> list = REPOSITORY_GENERATE_NUMBER_ID.readAllByName(TestData.USER1.getUsername());
        Assert.assertTrue(list.size() == 1 && list.get(0).equals(TestData.USER1));
    }

    /**
     * Проверить существование записи по id
     */
    @Test
    public void test011IsRowExists(){
        boolean isRowExists = REPOSITORY_GENERATE_NUMBER_ID.isRowExists(TestData.USER1.getId());
        Assert.assertTrue(isRowExists);
    }

    /**
     * Тестирование метода создания или чтения существует записи
     */
    @Test
    public void test012CreateOrReadExists(){
        DtoUser dto = REPOSITORY_GENERATE_NUMBER_ID.createOrRead(TestData.USER1);
        Assert.assertEquals(TestData.USER1, dto);
    }

    /**
     * Тестирование метода создания или чтения несуществует записи
     */
    @Test
    public void test013CreateOrReadNotExists(){
        DtoUser<Integer> newDto = new DtoUser<>(101, "user10", "user10@mail.ru", "password10");
        DtoUser dto = REPOSITORY_GENERATE_NUMBER_ID.createOrRead(newDto);
        Assert.assertEquals(newDto, dto);
    }

    /**
     * Обновление записи
     */
    @Test
    public void test014Update(){
        DtoUser dto = REPOSITORY_GENERATE_NUMBER_ID.update(TestData.USER1_UPDATED);
        Assert.assertEquals(TestData.USER1_UPDATED, dto);
    }

    /**
     * Тестирование метода создания или обновления существует записи
     */
    @Test
    public void test015CreateOrUpdateExists(){
        DtoUser dto = REPOSITORY_GENERATE_NUMBER_ID.createOrUpdate(TestData.USER1);
        Assert.assertEquals(TestData.USER1, dto);
    }

    /**
     * Тестирование метода создания или обновления несуществует записи
     */
    @Test
    public void test016CreateOrUpdateNotExists(){
        DtoUser<Integer> newDto = new DtoUser<>(102, "user11", "user11@mail.ru", "password11");
        DtoUser dto = REPOSITORY_GENERATE_NUMBER_ID.createOrUpdate(newDto);
        Assert.assertEquals(newDto, dto);
    }

    /**
     * Проверка того, что предыдущие методы createOrRead и createOrUpdate успешно выполнены
     */
    @Test
    public void test017ExecuteCreateAndUpdateIsSuccess(){
        List<DtoUser<Integer>> list = REPOSITORY_GENERATE_NUMBER_ID.readAll(null);
        Assert.assertTrue(list.size() == 4);
    }

    /**
     * Удаление записи
     */
    @Test
    public void test018Delete(){
        REPOSITORY_GENERATE_NUMBER_ID.delete(TestData.USER1.getId());
        List<DtoUser<Integer>> list = REPOSITORY_GENERATE_NUMBER_ID.readAll(null);
        DtoUser<Integer> dto = REPOSITORY_GENERATE_NUMBER_ID.read(TestData.USER1.getId());
        Assert.assertTrue(list.size() == 3);
        Assert.assertNull(dto);
    }
}