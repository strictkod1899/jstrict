package ru.strict.db.jdbc;

import org.junit.*;
import org.junit.runners.MethodSorters;
import ru.strict.db.core.common.GenerateIdType;
import ru.strict.db.core.common.SqlType;
import ru.strict.models.FileStorage;
import ru.strict.db.core.repositories.interfaces.IRepositoryFileStorage;
import ru.strict.db.jdbc.repositories.RepositoryFileStorage;
import ru.strict.models.ModelBase;

import java.sql.JDBCType;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import static ru.strict.db.jdbc.data.TestData.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestRepositoryFileStorage extends TestBase {

    private static FileStorage[] data;

    private static IRepositoryFileStorage<Long, FileStorage<Long>> REPOSITORY_NOT_GENERATE_ID;
    private static IRepositoryFileStorage<Long, FileStorage<Long>> REPOSITORY_GENERATE_NUMBER_ID;
    private static IRepositoryFileStorage<UUID, FileStorage<UUID>> REPOSITORY_GENERATE_UUID_ID;

    @BeforeClass
    public static void setUpClass(){
        prepareRepositories();
        fillData();
    }

    @AfterClass
    public static void tearDownClass(){
        cleanDatabase();
    }

    /**
     * Подготовить тестовые репозитории
     */
    private static void prepareRepositories(){
        REPOSITORY_NOT_GENERATE_ID = new RepositoryFileStorage<>(CREATE_DB_INTEGER_CONNECTION, GenerateIdType.NONE, JDBCType.BIGINT);
        REPOSITORY_GENERATE_NUMBER_ID = new RepositoryFileStorage<>(CREATE_DB_INTEGER_CONNECTION, GenerateIdType.LONG, JDBCType.BIGINT);
        REPOSITORY_GENERATE_UUID_ID = new RepositoryFileStorage<>(CREATE_DB_UUID_CONNECTION, GenerateIdType.UUID, SqlType.UUID);
        repositoriesForClearDb.add(REPOSITORY_GENERATE_NUMBER_ID);
        repositoriesForClearDb.add(REPOSITORY_GENERATE_UUID_ID);
    }

    /**
     * Заполнить тестовые данные
     */
    private static void fillData(){
        data = new FileStorage[]{
                new FileStorage<>("filename",
                        "extension",
                        "displayname",
                        "filepath",
                        new byte[]{ 1, 2, 3, 4, 5, 6, 7, 8 },
                        new Date(),
                        1,
                        1),
                new FileStorage<>("filename",
                        "extension",
                        "displayname",
                        "filepath",
                        new byte[]{ 1, 2, 3, 4, 5, 6, 7, 8 },
                        new Date(),
                        1,
                        1),
                FILE_STORAGE1,
                FILE_STORAGE2,
                FILE_STORAGE3,
                FILE_STORAGE1_UPDATED
        };
    }

    /**
     * Создание с генерацией integer идентификатора
     */
    @Test
    public void test001CreateGenerateNumberId(){
        Long id = REPOSITORY_GENERATE_NUMBER_ID.create(data[0]);
        data[0].setId(id);
        Assert.assertNotNull(id);
    }

    /**
     * Создание с генерацией uuid идентификатора
     */
    @Test
    public void test002CreateGenerateUuidId(){
        UUID id = REPOSITORY_GENERATE_UUID_ID.create(data[1]);
        data[1].setId(id);
        Assert.assertNotNull(id);
    }

    /**
     * Создание без генерации идентификатора
     */
    @Test
    public void test003CreateNotGenerateId(){
        Long id = REPOSITORY_NOT_GENERATE_ID.create(data[2]);
        Assert.assertEquals(data[2].getId(), id);
    }

    /**
     * Чтение одной записи по integer идентификатору
     */
    @Test
    public void test004ReadByInteger(){
        ModelBase model = REPOSITORY_GENERATE_NUMBER_ID.read((Long)data[2].getId());
        Assert.assertEquals(data[2], model);
    }

    /**
     * Чтение одной записи по uuid идентификатору
     */
    @Test
    public void test005ReadByUuid(){
        ModelBase model = REPOSITORY_GENERATE_UUID_ID.read((UUID)data[1].getId());
        Assert.assertEquals(data[1], model);
    }

    /**
     * Чтение всех записей из integer базы данныъх
     */
    @Test
    public void test006ReadAllInteger(){
        List list = REPOSITORY_GENERATE_NUMBER_ID.readAll(null);
        Assert.assertEquals(2, list.size());
        Assert.assertTrue(list.contains(data[0]));
        Assert.assertTrue(list.contains(data[2]));
    }

    /**
     * Чтение всех записей из uuid базы данныъх
     */
    @Test
    public void test007ReadAllUuid(){
        List list = REPOSITORY_GENERATE_UUID_ID.readAll(null);
        Assert.assertEquals(1, list.size());
        Assert.assertTrue(list.contains(data[1]));
    }

    /**
     * Получить количество записей в integer базе данных
     */
    @Test
    public void test008ReadCount(){
        int count = REPOSITORY_GENERATE_NUMBER_ID.readCount(null);
        Assert.assertEquals(2, count);
    }

    /**
     * Проверить существование записи по id
     */
    @Test
    public void test011IsRowExists(){
        boolean isRowExists = REPOSITORY_GENERATE_NUMBER_ID.isRowExists((Long)data[0].getId());
        Assert.assertTrue(isRowExists);
        isRowExists = REPOSITORY_GENERATE_UUID_ID.isRowExists((UUID)data[1].getId());
        Assert.assertTrue(isRowExists);
        isRowExists = REPOSITORY_GENERATE_UUID_ID.isRowExists(UUID.randomUUID());
        Assert.assertFalse(isRowExists);
    }

    /**
     * Тестирование создания или чтения существующей записи
     */
    @Test
    public void test012CreateOrReadExists(){
        ModelBase model = REPOSITORY_GENERATE_NUMBER_ID.createOrRead(data[0]);
        Assert.assertEquals(data[0], model);
    }

    /**
     * Тестирование метода создания или чтения несуществующей записи
     */
    @Test
    public void test013CreateOrReadNotExists(){
        ModelBase model = REPOSITORY_GENERATE_NUMBER_ID.createOrRead(data[3]);
        data[3].setId(model.getId());
        Assert.assertEquals(data[3], model);
    }

    /**
     * Обновление записи
     */
    @Test
    public void test014Update(){
        REPOSITORY_GENERATE_NUMBER_ID.update(data[5]);
        ModelBase model = REPOSITORY_GENERATE_NUMBER_ID.read((Long)data[5].getId());
        // Эту модель мы только что обновили, поэтому model2 == model (data[5] == data[2])
        ModelBase model2 = REPOSITORY_GENERATE_NUMBER_ID.read((Long)data[2].getId());
        Assert.assertEquals(data[5], model);
        Assert.assertNotEquals(data[2], model2);
    }

    /**
     * Тестирование метода создания или обновления существующей записи
     */
    @Test
    public void test015CreateOrUpdateExists(){
        Long id = REPOSITORY_GENERATE_NUMBER_ID.createOrUpdate(data[2]);
        ModelBase model = REPOSITORY_GENERATE_NUMBER_ID.read(id);
        Assert.assertEquals(data[2].getId(), id);
        Assert.assertEquals(data[2], model);
    }

    /**
     * Тестирование метода создания или обновления несуществующей записи
     */
    @Test
    public void test016CreateOrUpdateNotExists(){
        Long id = REPOSITORY_GENERATE_NUMBER_ID.createOrUpdate(data[4]);
        ModelBase model = REPOSITORY_GENERATE_NUMBER_ID.read(id);
        data[4].setId(id);
        Assert.assertNotNull(id);
        Assert.assertEquals(data[4], model);
    }

    /**
     * Проверка того, что предыдущие методы createOrRead и createOrUpdate успешно выполнены
     */
    @Test
    public void test017ExecuteCreateAndUpdateIsSuccess(){
        List list = REPOSITORY_GENERATE_NUMBER_ID.readAll(null);
        Assert.assertEquals(4, list.size());
    }

    /**
     * Удаление записи
     */
    @Test
    public void test018Delete(){
        REPOSITORY_GENERATE_NUMBER_ID.delete((Long)data[0].getId());
        List list = REPOSITORY_GENERATE_NUMBER_ID.readAll(null);
        ModelBase model = REPOSITORY_GENERATE_NUMBER_ID.read((Long)data[0].getId());
        Assert.assertEquals(3, list.size());
        Assert.assertNull(model);
    }

    @Test
    public void test019ReadByDisplayName(){
        List list = REPOSITORY_GENERATE_NUMBER_ID.readByDisplayName(data[3].getDisplayName());
        Assert.assertEquals(1, list.size());
        Assert.assertEquals(data[3], list.get(0));
    }

    @Test
    public void test020ReadByFileNameAndExtension(){
        List list = REPOSITORY_GENERATE_NUMBER_ID.readByFileNameAndExtension(
                data[3].getFilename(),
                data[3].getExtension()
        );
        Assert.assertEquals(1, list.size());
        Assert.assertEquals(data[3], list.get(0));
    }

    @Test
    public void test021ReadByDisplayNameAndExtension(){
        List list = REPOSITORY_GENERATE_NUMBER_ID.readByDisplayNameAndExtension(
                data[3].getDisplayName(),
                data[3].getExtension()
        );
        Assert.assertEquals(1, list.size());
        Assert.assertEquals(data[3], list.get(0));
    }

    @Test
    public void test022ReadByFilePath(){
        FileStorage model = REPOSITORY_GENERATE_NUMBER_ID.readByFilePath(data[3].getFilePath());
        Assert.assertEquals(data[3], model);
    }

    @Test
    public void test023ReadByType(){
        List list = REPOSITORY_GENERATE_NUMBER_ID.readByType(data[3].getType());
        Assert.assertEquals(1, list.size());
        Assert.assertEquals(data[3], list.get(0));
    }

    @Test
    public void test024ReadByStatus(){
        List list = REPOSITORY_GENERATE_NUMBER_ID.readByStatus(data[3].getStatus());
        Assert.assertEquals(1, list.size());
        Assert.assertEquals(data[3], list.get(0));
    }
}
