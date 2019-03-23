package ru.strict.db.mybatis;

import org.junit.*;
import org.junit.runners.MethodSorters;
import ru.strict.db.core.common.GenerateIdType;
import ru.strict.db.core.dto.DtoUser;
import ru.strict.db.core.entities.EntityUser;
import ru.strict.db.core.mappers.dto.MapperDtoFactory;
import ru.strict.db.core.repositories.IRepositoryNamed;
import ru.strict.db.mybatis.repositories.RepositoryUser;
import ru.strict.db.mybatis.runners.TestRunner;

import java.util.List;
import java.util.UUID;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestRepositoryUser {

    private static final Integer STATIC_DTO_ID = 101;
    private static final String STATIC_DTO_USERNAME = "user1";
    private static final String STATIC_DTO_EMAIL = "user1@mail.ru";
    private static final String STATIC_DTO_PASSWORD = "password1";
    private static final String STATIC_DTO_UPDATED_USERNAME = "user1-1";
    private static final DtoUser STATIC_DTO = new DtoUser<>(STATIC_DTO_ID, STATIC_DTO_USERNAME, STATIC_DTO_EMAIL, STATIC_DTO_PASSWORD);

    private static IRepositoryNamed<Integer, DtoUser<Integer>> repositoryNotGenerateId;
    private static IRepositoryNamed<Integer, DtoUser<Integer>> repositoryGenerateNumberId;
    private static IRepositoryNamed<UUID, DtoUser<UUID>> repositoryGenerateUuidId;

    @BeforeClass
    public static void prepare(){
        repositoryNotGenerateId = new RepositoryUser<>(TestRunner.createDbIntegerConnection,
                new MapperDtoFactory().instance(EntityUser.class, DtoUser.class),
                GenerateIdType.NONE);
        repositoryGenerateNumberId = new RepositoryUser<>(TestRunner.createDbIntegerConnection,
                new MapperDtoFactory().instance(EntityUser.class, DtoUser.class),
                GenerateIdType.NUMBER);
        repositoryGenerateUuidId = new RepositoryUser<>(TestRunner.createDbUuidConnection,
                new MapperDtoFactory().instance(EntityUser.class, DtoUser.class),
                GenerateIdType.UUID);
        TestRunner.repositories.add(repositoryGenerateNumberId);
        TestRunner.repositories.add(repositoryGenerateUuidId);
    }

    @AfterClass
    public static void post(){
        TestRunner.postProcess();
    }

    @Test
    public void test001CreateGenerateNumberId(){
        DtoUser dto = new DtoUser<>("role2", "email2", "password2");
        DtoUser createdDto = repositoryGenerateNumberId.create(dto);
        Assert.assertNotNull(createdDto.getId());
    }

    @Test
    public void test002CreateGenerateUuidId(){
        DtoUser dto = new DtoUser<>( "role3", "email3", "password3");
        DtoUser createdDto = repositoryGenerateUuidId.create(dto);
        Assert.assertNotNull(createdDto.getId());
    }

    @Test
    public void test003CreateNotGenerateId(){
        DtoUser createdDto = repositoryNotGenerateId.create(STATIC_DTO);
        Assert.assertEquals(STATIC_DTO, createdDto);
    }

    @Test
    public void test004ReadByInteger(){
        DtoUser dto = repositoryGenerateNumberId.read(STATIC_DTO_ID);
        Assert.assertEquals(STATIC_DTO, dto);
    }

    @Test
    public void test005ReadAllByInteger(){
        List<DtoUser<Integer>> list = repositoryGenerateNumberId.readAll(null);
        Assert.assertTrue(list.size() == 2);
    }

    @Test
    public void test006ReadAllByUuid(){
        List<DtoUser<UUID>> list = repositoryGenerateUuidId.readAll(null);
        Assert.assertTrue(list.size() == 1);
    }

    @Test
    public void test007ReadCount(){
        Integer count = repositoryGenerateNumberId.readCount(null);
        Assert.assertTrue(count == 2);
    }

    @Test
    public void test008ReadByName(){
        DtoUser dto = repositoryGenerateNumberId.readByName(STATIC_DTO_USERNAME);
        Assert.assertEquals(STATIC_DTO, dto);
    }

    @Test
    public void test009ReadAllByName(){
        List<DtoUser<Integer>> list = repositoryGenerateNumberId.readAllByName(STATIC_DTO_USERNAME);
        Assert.assertTrue(list.size() == 1 && list.get(0).equals(STATIC_DTO));
    }

    @Test
    public void test010IsRowExists(){
        boolean isRowExists = repositoryGenerateNumberId.isRowExists(STATIC_DTO_ID);
        Assert.assertTrue(isRowExists);
    }

    @Test
    public void test011CreateOrReadExists(){
        DtoUser dto = repositoryGenerateNumberId.createOrRead(STATIC_DTO);
        Assert.assertEquals(STATIC_DTO, dto);
    }

    @Test
    public void test012CreateOrReadNotExists(){
        DtoUser<Integer> newDto = new DtoUser<>(102, "role4", "email4", "password4");
        DtoUser dto = repositoryGenerateNumberId.createOrRead(newDto);
        Assert.assertEquals(newDto, dto);
    }

    @Test
    public void test013Update(){
        STATIC_DTO.setUsername(STATIC_DTO_UPDATED_USERNAME);
        DtoUser dto = repositoryGenerateNumberId.update(STATIC_DTO);
        Assert.assertEquals(STATIC_DTO, dto);
    }

    @Test
    public void test014CreateOrUpdateExists(){
        STATIC_DTO.setUsername(STATIC_DTO_USERNAME);
        DtoUser dto = repositoryGenerateNumberId.createOrUpdate(STATIC_DTO);
        Assert.assertEquals(STATIC_DTO, dto);
    }

    @Test
    public void test015CreateOrUpdateNotExists(){
        DtoUser<Integer> newDto = new DtoUser<>(103, "role5", "email5", "password5");
        DtoUser dto = repositoryGenerateNumberId.createOrUpdate(newDto);
        Assert.assertEquals(newDto, dto);
    }

    /**
     * Проверка того, что предыдущие методы createOrRead и createOrUpdate успешно выполнены
     */
    @Test
    public void test016ExecuteCreateAndUpdateIsSuccess(){
        List<DtoUser<Integer>> list = repositoryGenerateNumberId.readAll(null);
        Assert.assertTrue(list.size() == 4);
    }

    @Test
    public void test017Delete(){
        repositoryGenerateNumberId.delete(STATIC_DTO_ID);
        List<DtoUser<Integer>> list = repositoryGenerateNumberId.readAll(null);
        DtoUser<Integer> dto = repositoryGenerateNumberId.read(STATIC_DTO_ID);
        Assert.assertTrue(list.size() == 3);
        Assert.assertNull(dto);
    }
}
