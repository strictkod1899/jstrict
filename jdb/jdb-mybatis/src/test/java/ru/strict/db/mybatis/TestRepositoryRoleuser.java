package ru.strict.db.mybatis;

import org.junit.*;
import org.junit.runners.MethodSorters;
import ru.strict.db.core.common.GenerateIdType;
import ru.strict.db.core.dto.DtoRoleuser;
import ru.strict.db.core.repositories.IRepositoryNamed;
import ru.strict.db.mybatis.repositories.RepositoryRoleuser;
import ru.strict.db.mybatis.runners.TestRunner;

import java.util.List;
import java.util.UUID;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestRepositoryRoleuser {

    private static final Integer STATIC_DTO_ID = 101;
    private static final String STATIC_DTO_CODE = "role1";
    private static final String STATIC_DTO_DESCRIPTION = "description1";
    private static final String STATIC_DTO_UPDATED_CODE = "role1-1";
    private static final DtoRoleuser STATIC_DTO = new DtoRoleuser<>(STATIC_DTO_ID, STATIC_DTO_CODE, STATIC_DTO_DESCRIPTION);

    private static IRepositoryNamed<Integer, DtoRoleuser<Integer>> repositoryNotGenerateId;
    private static IRepositoryNamed<Integer, DtoRoleuser<Integer>> repositoryGenerateNumberId;
    private static IRepositoryNamed<UUID, DtoRoleuser<UUID>> repositoryGenerateUuidId;

    @BeforeClass
    public static void prepare(){
        repositoryNotGenerateId = new RepositoryRoleuser<>(TestRunner.createDbIntegerConnection, GenerateIdType.NONE);
        repositoryGenerateNumberId = new RepositoryRoleuser<>(TestRunner.createDbIntegerConnection, GenerateIdType.NUMBER);
        repositoryGenerateUuidId = new RepositoryRoleuser<>(TestRunner.createDbUuidConnection, GenerateIdType.UUID);
        TestRunner.repositories.add(repositoryGenerateNumberId);
        TestRunner.repositories.add(repositoryGenerateUuidId);
    }

    @AfterClass
    public static void post(){
        TestRunner.postProcess();
    }

    @Test
    public void test001CreateGenerateNumberId(){
        DtoRoleuser dto = new DtoRoleuser<>("role2", null);
        DtoRoleuser createdDto = repositoryGenerateNumberId.create(dto);
        Assert.assertNotNull(createdDto.getId());
    }

    @Test
    public void test002CreateGenerateUuidId(){
        DtoRoleuser dto = new DtoRoleuser<>("role3", null);
        DtoRoleuser createdDto = repositoryGenerateUuidId.create(dto);
        Assert.assertNotNull(createdDto.getId());
    }

    @Test
    public void test003CreateNotGenerateId(){
        DtoRoleuser createdDto = repositoryNotGenerateId.create(STATIC_DTO);
        Assert.assertEquals(STATIC_DTO, createdDto);
    }

    @Test
    public void test004ReadByInteger(){
        DtoRoleuser dto = repositoryGenerateNumberId.read(STATIC_DTO_ID);
        Assert.assertEquals(STATIC_DTO, dto);
    }

    @Test
    public void test005ReadAllByInteger(){
        List<DtoRoleuser<Integer>> list = repositoryGenerateNumberId.readAll(null);
        Assert.assertTrue(list.size() == 2);
    }

    @Test
    public void test006ReadAllByUuid(){
        List<DtoRoleuser<UUID>> list = repositoryGenerateUuidId.readAll(null);
        Assert.assertTrue(list.size() == 1);
    }

    @Test
    public void test007ReadCount(){
        Integer count = repositoryGenerateNumberId.readCount(null);
        Assert.assertTrue(count == 2);
    }

    @Test
    public void test008ReadByName(){
        DtoRoleuser dto = repositoryGenerateNumberId.readByName(STATIC_DTO_CODE);
        Assert.assertEquals(STATIC_DTO, dto);
    }

    @Test
    public void test009ReadAllByName(){
        List<DtoRoleuser<Integer>> list = repositoryGenerateNumberId.readAllByName(STATIC_DTO_CODE);
        Assert.assertTrue(list.size() == 1 && list.get(0).equals(STATIC_DTO));
    }

    @Test
    public void test010IsRowExists(){
        boolean isRowExists = repositoryGenerateNumberId.isRowExists(STATIC_DTO_ID);
        Assert.assertTrue(isRowExists);
    }

    @Test
    public void test011CreateOrReadExists(){
        DtoRoleuser dto = repositoryGenerateNumberId.createOrRead(STATIC_DTO);
        Assert.assertEquals(STATIC_DTO, dto);
    }

    @Test
    public void test012CreateOrReadNotExists(){
        DtoRoleuser<Integer> newDto = new DtoRoleuser<>(102, "role4", null);
        DtoRoleuser dto = repositoryGenerateNumberId.createOrRead(newDto);
        Assert.assertEquals(newDto, dto);
    }

    @Test
    public void test013Update(){
        STATIC_DTO.setCode(STATIC_DTO_UPDATED_CODE);
        DtoRoleuser dto = repositoryGenerateNumberId.update(STATIC_DTO);
        Assert.assertEquals(STATIC_DTO, dto);
    }

    @Test
    public void test014CreateOrUpdateExists(){
        STATIC_DTO.setCode(STATIC_DTO_CODE);
        DtoRoleuser dto = repositoryGenerateNumberId.createOrUpdate(STATIC_DTO);
        Assert.assertEquals(STATIC_DTO, dto);
    }

    @Test
    public void test015CreateOrUpdateNotExists(){
        DtoRoleuser<Integer> newDto = new DtoRoleuser<>(103, "role5", null);
        DtoRoleuser dto = repositoryGenerateNumberId.createOrUpdate(newDto);
        Assert.assertEquals(newDto, dto);
    }

    /**
     * Проверка того, что предыдущие методы createOrRead и createOrUpdate успешно выполнены
     */
    @Test
    public void test016ExecuteCreateAndUpdateIsSuccess(){
        List<DtoRoleuser<Integer>> list = repositoryGenerateNumberId.readAll(null);
        Assert.assertTrue(list.size() == 4);
    }

    @Test
    public void test017Delete(){
        repositoryGenerateNumberId.delete(STATIC_DTO_ID);
        List<DtoRoleuser<Integer>> list = repositoryGenerateNumberId.readAll(null);
        DtoRoleuser<Integer> dto = repositoryGenerateNumberId.read(STATIC_DTO_ID);
        Assert.assertTrue(list.size() == 3);
        Assert.assertNull(dto);
    }
}
