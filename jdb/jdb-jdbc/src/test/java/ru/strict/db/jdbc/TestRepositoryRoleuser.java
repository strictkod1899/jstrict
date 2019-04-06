package ru.strict.db.jdbc;

import org.junit.*;
import org.junit.runners.MethodSorters;
import ru.strict.db.core.common.GenerateIdType;
import ru.strict.db.core.dto.DtoRoleuser;
import ru.strict.db.core.repositories.IRepositoryNamed;
import ru.strict.db.jdbc.data.TestData;
import ru.strict.db.jdbc.repositories.RepositoryRoleuser;
import ru.strict.db.jdbc.runners.TestRunner;

import java.util.List;
import java.util.UUID;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestRepositoryRoleuser {

    private static IRepositoryNamed<Integer, DtoRoleuser<Integer>> REPOSITORY_NOT_GENERATE_ID;
    private static IRepositoryNamed<Integer, DtoRoleuser<Integer>> REPOSITORY_GENERATE_NUMBER_ID;
    private static IRepositoryNamed<UUID, DtoRoleuser<UUID>> REPOSITORY_GENERATE_UUID_ID;

    @BeforeClass
    public static void prepare(){
        prepareRepositories();
    }

    /**
     * Подготовить тестовые репозитории
     */
    private static void prepareRepositories(){
        REPOSITORY_NOT_GENERATE_ID = new RepositoryRoleuser<>(TestRunner.CREATE_DB_INTEGER_CONNECTION, GenerateIdType.NONE);
        REPOSITORY_GENERATE_NUMBER_ID = new RepositoryRoleuser<>(TestRunner.CREATE_DB_INTEGER_CONNECTION, GenerateIdType.NUMBER);
        REPOSITORY_GENERATE_UUID_ID = new RepositoryRoleuser<>(TestRunner.CREATE_DB_UUID_CONNECTION, GenerateIdType.UUID);
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
        DtoRoleuser dto = new DtoRoleuser<>("role", "description");
        DtoRoleuser createdDto = REPOSITORY_GENERATE_NUMBER_ID.create(dto);
        Assert.assertNotNull(createdDto.getId());
    }

    /**
     * Создание с генерацией uuid идентификатора
     */
    @Test
    public void test002CreateGenerateUuidId(){
        DtoRoleuser dto = new DtoRoleuser<>("role", "description");
        DtoRoleuser createdDto = REPOSITORY_GENERATE_UUID_ID.create(dto);
        Assert.assertNotNull(createdDto.getId());
    }

    /**
     * Создание без генерации integer идентификатора
     */
    @Test
    public void test003CreateNotGenerateId(){
        DtoRoleuser createdDto = REPOSITORY_NOT_GENERATE_ID.create(TestData.ROLEUSER1);
        Assert.assertEquals(TestData.ROLEUSER1, createdDto);
    }

    /**
     * Чтение одной записи по integer идентификатору
     */
    @Test
    public void test004ReadByInteger(){
        DtoRoleuser dto = REPOSITORY_GENERATE_NUMBER_ID.read(TestData.ROLEUSER1.getId());
        Assert.assertEquals(TestData.ROLEUSER1, dto);
    }

    /**
     * Чтение всех записей из integer базы данныъх
     */
    @Test
    public void test006ReadAllInteger(){
        List<DtoRoleuser<Integer>> list = REPOSITORY_GENERATE_NUMBER_ID.readAll(null);
        Assert.assertTrue(list.size() == 2);
    }

    /**
     * Чтение всех записей из uuid базы данныъх
     */
    @Test
    public void test007ReadAllUuid(){
        List<DtoRoleuser<UUID>> list = REPOSITORY_GENERATE_UUID_ID.readAll(null);
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
        DtoRoleuser dto = REPOSITORY_GENERATE_NUMBER_ID.readByName(TestData.ROLEUSER1.getCode());
        Assert.assertEquals(TestData.ROLEUSER1, dto);
    }

    /**
     * Чтение нескольких записей по названию
     */
    @Test
    public void test010ReadAllByName(){
        List<DtoRoleuser<Integer>> list = REPOSITORY_GENERATE_NUMBER_ID.readAllByName(TestData.ROLEUSER1.getCode());
        Assert.assertTrue(list.size() == 1 && list.get(0).equals(TestData.ROLEUSER1));
    }

    /**
     * Проверить существование записи по id
     */
    @Test
    public void test011IsRowExists(){
        boolean isRowExists = REPOSITORY_GENERATE_NUMBER_ID.isRowExists(TestData.ROLEUSER1.getId());
        Assert.assertTrue(isRowExists);
    }

    /**
     * Тестирование метода создания или чтения существует записи
     */
    @Test
    public void test012CreateOrReadExists(){
        DtoRoleuser dto = REPOSITORY_GENERATE_NUMBER_ID.createOrRead(TestData.ROLEUSER1);
        Assert.assertEquals(TestData.ROLEUSER1, dto);
    }

    /**
     * Тестирование метода создания или чтения несуществует записи
     */
    @Test
    public void test013CreateOrReadNotExists(){
        DtoRoleuser<Integer> newDto = new DtoRoleuser<>(101, "code10", "description10");
        DtoRoleuser dto = REPOSITORY_GENERATE_NUMBER_ID.createOrRead(newDto);
        Assert.assertEquals(newDto, dto);
    }

    /**
     * Обновление записи
     */
    @Test
    public void test014Update(){
        DtoRoleuser dto = REPOSITORY_GENERATE_NUMBER_ID.update(TestData.ROLEUSER1_UPDATED);
        Assert.assertEquals(TestData.ROLEUSER1_UPDATED, dto);
    }

    /**
     * Тестирование метода создания или обновления существует записи
     */
    @Test
    public void test015CreateOrUpdateExists(){
        DtoRoleuser dto = REPOSITORY_GENERATE_NUMBER_ID.createOrUpdate(TestData.ROLEUSER1);
        Assert.assertEquals(TestData.ROLEUSER1, dto);
    }

    /**
     * Тестирование метода создания или обновления несуществует записи
     */
    @Test
    public void test016CreateOrUpdateNotExists(){
        DtoRoleuser<Integer> newDto = new DtoRoleuser<>(102, "code11", "description11");
        DtoRoleuser dto = REPOSITORY_GENERATE_NUMBER_ID.createOrUpdate(newDto);
        Assert.assertEquals(newDto, dto);
    }

    /**
     * Проверка того, что предыдущие методы createOrRead и createOrUpdate успешно выполнены
     */
    @Test
    public void test017ExecuteCreateAndUpdateIsSuccess(){
        List<DtoRoleuser<Integer>> list = REPOSITORY_GENERATE_NUMBER_ID.readAll(null);
        Assert.assertTrue(list.size() == 4);
    }

    /**
     * Удаление записи
     */
    @Test
    public void test018Delete(){
        REPOSITORY_GENERATE_NUMBER_ID.delete(TestData.ROLEUSER1.getId());
        List<DtoRoleuser<Integer>> list = REPOSITORY_GENERATE_NUMBER_ID.readAll(null);
        DtoRoleuser<Integer> dto = REPOSITORY_GENERATE_NUMBER_ID.read(TestData.ROLEUSER1.getId());
        Assert.assertTrue(list.size() == 3);
        Assert.assertNull(dto);
    }
}