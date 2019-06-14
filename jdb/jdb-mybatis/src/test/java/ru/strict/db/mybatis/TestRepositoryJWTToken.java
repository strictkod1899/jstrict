package ru.strict.db.mybatis;

import org.junit.*;
import org.junit.runners.MethodSorters;
import ru.strict.db.core.common.GenerateIdType;
import ru.strict.models.JWTToken;
import ru.strict.models.User;
import ru.strict.db.core.repositories.IRepositoryNamed;
import ru.strict.db.core.repositories.interfaces.IRepositoryJWTToken;
import ru.strict.db.mybatis.data.TestData;
import ru.strict.db.mybatis.repositories.RepositoryJWTToken;
import ru.strict.db.mybatis.repositories.RepositoryUser;
import ru.strict.db.mybatis.runners.TestRunner;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestRepositoryJWTToken {

    private static IRepositoryJWTToken<Integer> REPOSITORY_NOT_GENERATE_ID;
    private static IRepositoryJWTToken<Integer> REPOSITORY_GENERATE_NUMBER_ID;
    private static IRepositoryJWTToken<UUID> REPOSITORY_GENERATE_UUID_ID;

    @BeforeClass
    public static void prepare(){
        prepareRepositories();
        prepareData();
    }

    /**
     * Подготовить тестовые репозитории
     */
    private static void prepareRepositories(){
        REPOSITORY_NOT_GENERATE_ID = new RepositoryJWTToken<>(TestRunner.CREATE_DB_INTEGER_CONNECTION, GenerateIdType.NONE);
        REPOSITORY_GENERATE_NUMBER_ID = new RepositoryJWTToken<>(TestRunner.CREATE_DB_INTEGER_CONNECTION, GenerateIdType.NUMBER);
        REPOSITORY_GENERATE_UUID_ID = new RepositoryJWTToken<>(TestRunner.CREATE_DB_UUID_CONNECTION, GenerateIdType.UUID);
        TestRunner.repositories.add(REPOSITORY_GENERATE_NUMBER_ID);
        TestRunner.repositories.add(REPOSITORY_GENERATE_UUID_ID);
    }

    /**
     * Подготовить тестовые данные
     */
    private static void prepareData(){
        IRepositoryNamed<Integer, User<Integer>> repositoryCountryNumberId = new RepositoryUser<>(TestRunner.CREATE_DB_INTEGER_CONNECTION, GenerateIdType.NONE);
        IRepositoryNamed<UUID, User<UUID>> repositoryCountryUuidId = new RepositoryUser<>(TestRunner.CREATE_DB_UUID_CONNECTION, GenerateIdType.NONE);

        TestRunner.repositories.add(repositoryCountryNumberId);
        TestRunner.repositories.add(repositoryCountryUuidId);

        repositoryCountryNumberId.create(TestData.USER1);
        repositoryCountryUuidId.create(TestData.USER1_UUID);
        repositoryCountryNumberId.create(TestData.USER2);
        repositoryCountryUuidId.create(TestData.USER2_UUID);
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
        JWTToken dto = new JWTToken<>("accessToken", "refreshToken", new Date(), new Date(), new Date(), TestData.USER2.getId());
        JWTToken createdDto = REPOSITORY_GENERATE_NUMBER_ID.create(dto);
        Assert.assertNotNull(createdDto.getId());
    }

    /**
     * Создание с генерацией uuid идентификатора
     */
    @Test
    public void test002CreateGenerateUuidId(){
        JWTToken dto = new JWTToken<>("accessToken", "refreshToken", new Date(), new Date(), new Date(), TestData.USER2.getId());
        JWTToken createdDto = REPOSITORY_GENERATE_UUID_ID.create(dto);
        Assert.assertNotNull(createdDto.getId());
    }

    /**
     * Создание без генерации integer идентификатора
     */
    @Test
    public void test003CreateNotGenerateId(){
        JWTToken createdDto = REPOSITORY_NOT_GENERATE_ID.create(TestData.JWT_TOKEN1);
        Assert.assertEquals(TestData.JWT_TOKEN1, createdDto);
    }

    /**
     * Чтение одной записи по integer идентификатору
     */
    @Test
    public void test004ReadByInteger(){
        JWTToken dto = REPOSITORY_GENERATE_NUMBER_ID.read(TestData.JWT_TOKEN1.getId());
        Assert.assertEquals(TestData.JWT_TOKEN1, dto);
    }

    /**
     * Чтение всех записей из integer базы данныъх
     */
    @Test
    public void test006ReadAllInteger(){
        List<JWTToken<Integer>> list = REPOSITORY_GENERATE_NUMBER_ID.readAll(null);
        Assert.assertTrue(list.size() == 2);
    }

    /**
     * Чтение всех записей из uuid базы данныъх
     */
    @Test
    public void test007ReadAllUuid(){
        List<JWTToken<UUID>> list = REPOSITORY_GENERATE_UUID_ID.readAll(null);
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
        boolean isRowExists = REPOSITORY_GENERATE_NUMBER_ID.isRowExists(TestData.JWT_TOKEN1.getId());
        Assert.assertTrue(isRowExists);
    }

    /**
     * Тестирование метода создания или чтения существует записи
     */
    @Test
    public void test012CreateOrReadExists(){
        JWTToken dto = REPOSITORY_GENERATE_NUMBER_ID.createOrRead(TestData.JWT_TOKEN1);
        Assert.assertEquals(TestData.JWT_TOKEN1, dto);
    }

    /**
     * Тестирование метода создания или чтения несуществует записи
     */
    @Test
    public void test013CreateOrReadNotExists(){
        JWTToken<Integer> newDto = new JWTToken<>(101, "accessToken10", "refreshToken10", new Date(), new Date(), new Date(), TestData.USER2.getId());
        JWTToken dto = REPOSITORY_GENERATE_NUMBER_ID.createOrRead(newDto);
        Assert.assertEquals(newDto, dto);
    }

    /**
     * Обновление записи
     */
    @Test
    public void test014Update(){
        JWTToken dto = REPOSITORY_GENERATE_NUMBER_ID.update(TestData.JWT_TOKEN1_UPDATED);
        Assert.assertEquals(TestData.JWT_TOKEN1_UPDATED, dto);
    }

    /**
     * Тестирование метода создания или обновления существует записи
     */
    @Test
    public void test015CreateOrUpdateExists(){
        JWTToken dto = REPOSITORY_GENERATE_NUMBER_ID.createOrUpdate(TestData.JWT_TOKEN1);
        Assert.assertEquals(TestData.JWT_TOKEN1, dto);
    }

    /**
     * Тестирование метода создания или обновления несуществует записи
     */
    @Test
    public void test016CreateOrUpdateNotExists(){
        JWTToken<Integer> newDto = new JWTToken<>(102, "accessToken11", "refreshToken11", new Date(), new Date(), new Date(), TestData.USER2.getId());
        JWTToken dto = REPOSITORY_GENERATE_NUMBER_ID.createOrUpdate(newDto);
        Assert.assertEquals(newDto, dto);
    }

    /**
     * Проверка того, что предыдущие методы createOrRead и createOrUpdate успешно выполнены
     */
    @Test
    public void test017ExecuteCreateAndUpdateIsSuccess(){
        List<JWTToken<Integer>> list = REPOSITORY_GENERATE_NUMBER_ID.readAll(null);
        Assert.assertTrue(list.size() == 4);
    }

    /**
     * Удаление записи
     */
    @Test
    public void test018Delete(){
        REPOSITORY_GENERATE_NUMBER_ID.delete(TestData.JWT_TOKEN1.getId());
        List<JWTToken<Integer>> list = REPOSITORY_GENERATE_NUMBER_ID.readAll(null);
        JWTToken<Integer> dto = REPOSITORY_GENERATE_NUMBER_ID.read(TestData.JWT_TOKEN1.getId());
        Assert.assertTrue(list.size() == 3);
        Assert.assertNull(dto);
    }

    @Test
    public void test019ReadByAccessToken(){
        REPOSITORY_GENERATE_NUMBER_ID.create(TestData.JWT_TOKEN2);
        JWTToken<Integer> dto = REPOSITORY_GENERATE_NUMBER_ID.readByAccessToken(TestData.JWT_TOKEN2.getAccessToken());
        Assert.assertTrue(dto.equals(TestData.JWT_TOKEN2));
    }

    @Test
    public void test020ReadByRefreshToken(){
        JWTToken<Integer> dto = REPOSITORY_GENERATE_NUMBER_ID.readByRefreshToken(TestData.JWT_TOKEN2.getRefreshToken());
        Assert.assertTrue(dto.equals(TestData.JWT_TOKEN2));
    }

    @Test
    public void test021ReadByUserId(){
        List<JWTToken<Integer>> list = REPOSITORY_GENERATE_NUMBER_ID.readByUserId(TestData.JWT_TOKEN2.getUserId());
        Assert.assertTrue(list.size() == 1);
        Assert.assertTrue(list.get(0).equals(TestData.JWT_TOKEN2));
    }
}
