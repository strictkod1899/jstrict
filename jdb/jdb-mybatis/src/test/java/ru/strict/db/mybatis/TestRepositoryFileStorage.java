package ru.strict.db.mybatis;

import org.junit.*;
import org.junit.runners.MethodSorters;
import ru.strict.db.core.common.GenerateIdType;
import ru.strict.db.core.dto.DtoFileStorage;
import ru.strict.db.core.repositories.interfaces.IRepositoryFileStorage;
import ru.strict.db.mybatis.data.TestData;
import ru.strict.db.mybatis.repositories.RepositoryFileStorage;
import ru.strict.db.mybatis.runners.TestRunner;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestRepositoryFileStorage {

    private static IRepositoryFileStorage<Integer, DtoFileStorage<Integer>> REPOSITORY_NOT_GENERATE_ID;
    private static IRepositoryFileStorage<Integer, DtoFileStorage<Integer>> REPOSITORY_GENERATE_NUMBER_ID;
    private static IRepositoryFileStorage<UUID, DtoFileStorage<UUID>> REPOSITORY_GENERATE_UUID_ID;

    @BeforeClass
    public static void prepare(){
        prepareRepositories();
    }

    /**
     * Подготовить тестовые репозитории
     */
    private static void prepareRepositories(){
        REPOSITORY_NOT_GENERATE_ID = new RepositoryFileStorage<>(TestRunner.CREATE_DB_INTEGER_CONNECTION, GenerateIdType.NONE);
        REPOSITORY_GENERATE_NUMBER_ID = new RepositoryFileStorage<>(TestRunner.CREATE_DB_INTEGER_CONNECTION, GenerateIdType.NUMBER);
        REPOSITORY_GENERATE_UUID_ID = new RepositoryFileStorage<>(TestRunner.CREATE_DB_UUID_CONNECTION, GenerateIdType.UUID);
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
        DtoFileStorage dto = new DtoFileStorage<>("filename", "extension", "displayname", new Date(), 1, 1, "filepath", new byte[]{ 1, 2 });
        DtoFileStorage createdDto = REPOSITORY_GENERATE_NUMBER_ID.create(dto);
        Assert.assertNotNull(createdDto.getId());
    }

    /**
     * Создание с генерацией uuid идентификатора
     */
    @Test
    public void test002CreateGenerateUuidId(){
        DtoFileStorage dto = new DtoFileStorage<>("filename", "extension", "displayname", new Date(), 1, 1, "filepath", new byte[]{ 1, 2 });
        DtoFileStorage createdDto = REPOSITORY_GENERATE_UUID_ID.create(dto);
        Assert.assertNotNull(createdDto.getId());
    }

    /**
     * Создание без генерации integer идентификатора
     */
    @Test
    public void test003CreateNotGenerateId(){
        DtoFileStorage createdDto = REPOSITORY_NOT_GENERATE_ID.create(TestData.FILE_STORAGE1);
        Assert.assertEquals(TestData.FILE_STORAGE1, createdDto);
    }

    /**
     * Чтение одной записи по integer идентификатору
     */
    @Test
    public void test004ReadByInteger(){
        DtoFileStorage dto = REPOSITORY_GENERATE_NUMBER_ID.read(TestData.FILE_STORAGE1.getId());
        Assert.assertEquals(TestData.FILE_STORAGE1, dto);
    }

    /**
     * Чтение всех записей из integer базы данныъх
     */
    @Test
    public void test006ReadAllInteger(){
        List<DtoFileStorage<Integer>> list = REPOSITORY_GENERATE_NUMBER_ID.readAll(null);
        Assert.assertTrue(list.size() == 2);
    }

    /**
     * Чтение всех записей из uuid базы данныъх
     */
    @Test
    public void test007ReadAllUuid(){
        List<DtoFileStorage<UUID>> list = REPOSITORY_GENERATE_UUID_ID.readAll(null);
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
        DtoFileStorage dto = REPOSITORY_GENERATE_NUMBER_ID.readByName(TestData.FILE_STORAGE1.getFilename());
        Assert.assertEquals(TestData.FILE_STORAGE1, dto);
    }

    /**
     * Чтение нескольких записей по названию
     */
    @Test
    public void test010ReadAllByName(){
        List<DtoFileStorage<Integer>> list = REPOSITORY_GENERATE_NUMBER_ID.readAllByName(TestData.FILE_STORAGE1.getFilename());
        Assert.assertTrue(list.size() == 1);
        Assert.assertTrue(list.get(0).equals(TestData.FILE_STORAGE1));
    }

    /**
     * Проверить существование записи по id
     */
    @Test
    public void test011IsRowExists(){
        boolean isRowExists = REPOSITORY_GENERATE_NUMBER_ID.isRowExists(TestData.FILE_STORAGE1.getId());
        Assert.assertTrue(isRowExists);
    }

    /**
     * Тестирование метода создания или чтения существует записи
     */
    @Test
    public void test012CreateOrReadExists(){
        DtoFileStorage dto = REPOSITORY_GENERATE_NUMBER_ID.createOrRead(TestData.FILE_STORAGE1);
        Assert.assertEquals(TestData.FILE_STORAGE1, dto);
    }

    /**
     * Тестирование метода создания или чтения несуществует записи
     */
    @Test
    public void test013CreateOrReadNotExists(){
        DtoFileStorage<Integer> newDto = new DtoFileStorage<>(101, "filename10", "extension10", "displayname10", new Date(), 1, 1, "filepath10", new byte[]{ 1, 2, 3, 4 });
        DtoFileStorage dto = REPOSITORY_GENERATE_NUMBER_ID.createOrRead(newDto);
        Assert.assertEquals(newDto, dto);
    }

    /**
     * Обновление записи
     */
    @Test
    public void test014Update(){
        DtoFileStorage dto = REPOSITORY_GENERATE_NUMBER_ID.update(TestData.FILE_STORAGE1_UPDATED);
        Assert.assertEquals(TestData.FILE_STORAGE1_UPDATED, dto);
    }

    /**
     * Тестирование метода создания или обновления существует записи
     */
    @Test
    public void test015CreateOrUpdateExists(){
        DtoFileStorage dto = REPOSITORY_GENERATE_NUMBER_ID.createOrUpdate(TestData.FILE_STORAGE1);
        Assert.assertEquals(TestData.FILE_STORAGE1, dto);
    }

    /**
     * Тестирование метода создания или обновления несуществует записи
     */
    @Test
    public void test016CreateOrUpdateNotExists(){
        DtoFileStorage<Integer> newDto = new DtoFileStorage<>(102, "filename11", "extension11", "displayname11", new Date(), 1, 1, "filepath11", new byte[]{ 1, 2, 3, 4 });
        DtoFileStorage dto = REPOSITORY_GENERATE_NUMBER_ID.createOrUpdate(newDto);
        Assert.assertEquals(newDto, dto);
    }

    /**
     * Проверка того, что предыдущие методы createOrRead и createOrUpdate успешно выполнены
     */
    @Test
    public void test017ExecuteCreateAndUpdateIsSuccess(){
        List<DtoFileStorage<Integer>> list = REPOSITORY_GENERATE_NUMBER_ID.readAll(null);
        Assert.assertTrue(list.size() == 4);
    }

    /**
     * Удаление записи
     */
    @Test
    public void test018Delete(){
        REPOSITORY_GENERATE_NUMBER_ID.delete(TestData.FILE_STORAGE1.getId());
        List<DtoFileStorage<Integer>> list = REPOSITORY_GENERATE_NUMBER_ID.readAll(null);
        DtoFileStorage<Integer> dto = REPOSITORY_GENERATE_NUMBER_ID.read(TestData.FILE_STORAGE1.getId());
        Assert.assertTrue(list.size() == 3);
        Assert.assertNull(dto);
    }

    @Test
    public void test019ReadByDisplayName(){
        REPOSITORY_GENERATE_NUMBER_ID.create(TestData.FILE_STORAGE2);
        List<DtoFileStorage<Integer>> list = REPOSITORY_GENERATE_NUMBER_ID.readByDisplayName(TestData.FILE_STORAGE2.getDisplayName());
        Assert.assertTrue(list.size() == 1);
        Assert.assertTrue(list.get(0).equals(TestData.FILE_STORAGE2));
    }

    @Test
    public void test020ReadByFileNameAndExtension(){
        List<DtoFileStorage<Integer>> list = REPOSITORY_GENERATE_NUMBER_ID.readByFileNameAndExtension(
                TestData.FILE_STORAGE2.getFilename(),
                TestData.FILE_STORAGE2.getExtension()
        );
        Assert.assertTrue(list.size() == 1);
        Assert.assertTrue(list.get(0).equals(TestData.FILE_STORAGE2));
    }

    @Test
    public void test021ReadByDisplayNameAndExtension(){
        List<DtoFileStorage<Integer>> list = REPOSITORY_GENERATE_NUMBER_ID.readByDisplayNameAndExtension(
                TestData.FILE_STORAGE2.getDisplayName(),
                TestData.FILE_STORAGE2.getExtension()
        );
        Assert.assertTrue(list.size() == 1);
        Assert.assertTrue(list.get(0).equals(TestData.FILE_STORAGE2));
    }

    @Test
    public void test022ReadByFilePath(){
        DtoFileStorage<Integer> dto = REPOSITORY_GENERATE_NUMBER_ID.readByFilePath(TestData.FILE_STORAGE2.getFilePath());
        Assert.assertTrue(dto.equals(TestData.FILE_STORAGE2));
    }

    @Test
    public void test023ReadByType(){
        List<DtoFileStorage<Integer>> list = REPOSITORY_GENERATE_NUMBER_ID.readByType(TestData.FILE_STORAGE2.getType());
        Assert.assertTrue(list.size() == 1);
        Assert.assertTrue(list.get(0).equals(TestData.FILE_STORAGE2));
    }

    @Test
    public void test024ReadByStatus(){
        List<DtoFileStorage<Integer>> list = REPOSITORY_GENERATE_NUMBER_ID.readByStatus(TestData.FILE_STORAGE2.getStatus());
        Assert.assertTrue(list.size() == 1);
        Assert.assertTrue(list.get(0).equals(TestData.FILE_STORAGE2));
    }
}
