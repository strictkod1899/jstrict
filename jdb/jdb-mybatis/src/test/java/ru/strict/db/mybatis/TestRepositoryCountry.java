package ru.strict.db.mybatis;

import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.junit.runners.MethodSorters;
import ru.strict.db.core.common.GenerateIdType;
import ru.strict.db.core.dto.DtoCountry;
import ru.strict.db.core.repositories.IRepositoryNamed;
import ru.strict.db.mybatis.repositories.RepositoryCountry;

import java.util.List;
import java.util.UUID;

@RunWith(JUnit4.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestRepositoryCountry {

    private static final Integer STATIC_DTO_ID = 101;
    private static final String STATIC_DTO_CAPTION = "country1";
    private static final String STATIC_DTO_UPDATED_CAPTION = "country1-1";
    private static final DtoCountry STATIC_DTO = new DtoCountry<>(STATIC_DTO_ID, STATIC_DTO_CAPTION);

    private static IRepositoryNamed<Integer, DtoCountry<Integer>> repositoryNotGenerateId;
    private static IRepositoryNamed<Integer, DtoCountry<Integer>> repositoryGenerateNumberId;
    private static IRepositoryNamed<UUID, DtoCountry<UUID>> repositoryGenerateUuidId;

    @BeforeClass
    public static void prepare(){
        repositoryNotGenerateId = new RepositoryCountry<>(TestRunner.createConnectionForDbInteger, GenerateIdType.NONE);
        repositoryGenerateNumberId = new RepositoryCountry<>(TestRunner.createConnectionForDbInteger, GenerateIdType.NUMBER);
        repositoryGenerateUuidId = new RepositoryCountry<>(TestRunner.createConnectionForDbUuid, GenerateIdType.UUID);
        TestRunner.repositories.add(repositoryGenerateNumberId);
        TestRunner.repositories.add(repositoryGenerateUuidId);
    }

    @Test
    public void test001CreateGenerateNumberId(){
        DtoCountry dto = new DtoCountry<>("country2");
        DtoCountry createdDto = repositoryGenerateNumberId.create(dto);
        Assert.assertNotNull(createdDto.getId());
    }

    @Test
    public void test002CreateGenerateUuidId(){
        DtoCountry dto = new DtoCountry<>(UUID.randomUUID(), "country3");
        DtoCountry createdDto = repositoryGenerateUuidId.create(dto);
        Assert.assertEquals(dto, createdDto);
    }

    @Test
    public void test003CreateNotGenerateId(){
        DtoCountry createdDto = repositoryNotGenerateId.create(STATIC_DTO);
        Assert.assertEquals(STATIC_DTO, createdDto);
    }

    @Test
    public void test004ReadByInteger(){
        DtoCountry dto = repositoryGenerateNumberId.read(STATIC_DTO_ID);
        Assert.assertEquals(STATIC_DTO, dto);
    }

    @Test
    public void test005ReadAllByInteger(){
        List<DtoCountry<Integer>> list = repositoryGenerateNumberId.readAll(null);
        Assert.assertTrue(list.size() == 2);
    }

    @Test
    public void test006ReadAllByUuid(){
        List<DtoCountry<UUID>> list = repositoryGenerateUuidId.readAll(null);
        Assert.assertTrue(list.size() == 1);
    }

    @Test
    public void test007ReadCount(){
        Integer count = repositoryGenerateNumberId.readCount(null);
        Assert.assertTrue(count == 2);
    }

    @Test
    public void test008ReadByName(){
        DtoCountry dto = repositoryGenerateNumberId.readByName(STATIC_DTO_CAPTION);
        Assert.assertEquals(STATIC_DTO, dto);
    }

    @Test
    public void test009ReadAllByName(){
        List<DtoCountry<Integer>> list = repositoryGenerateNumberId.readAllByName(STATIC_DTO_CAPTION);
        Assert.assertTrue(list.size() == 1 && list.get(0).equals(STATIC_DTO));
    }

    @Test
    public void test010IsRowExists(){
        boolean isRowExists = repositoryGenerateNumberId.isRowExists(STATIC_DTO_ID);
        Assert.assertTrue(isRowExists);
    }

    @Test
    public void test011CreateOrReadExists(){
        DtoCountry dto = repositoryGenerateNumberId.createOrRead(STATIC_DTO);
        Assert.assertEquals(STATIC_DTO, dto);
    }

    @Test
    public void test012CreateOrReadNotExists(){
        DtoCountry<Integer> newDto = new DtoCountry<>(102, "country4");
        DtoCountry dto = repositoryGenerateNumberId.createOrRead(newDto);
        Assert.assertEquals(newDto, dto);
    }

    @Test
    public void test013Update(){
        STATIC_DTO.setCaption(STATIC_DTO_UPDATED_CAPTION);
        DtoCountry dto = repositoryGenerateNumberId.update(STATIC_DTO);
        Assert.assertEquals(STATIC_DTO, dto);
    }

    @Test
    public void test014CreateOrUpdateExists(){
        STATIC_DTO.setCaption(STATIC_DTO_CAPTION);
        DtoCountry dto = repositoryGenerateNumberId.createOrUpdate(STATIC_DTO);
        Assert.assertEquals(STATIC_DTO, dto);
    }

    @Test
    public void test015CreateOrUpdateNotExists(){
        DtoCountry<Integer> newDto = new DtoCountry<>(103, "country5");
        DtoCountry dto = repositoryGenerateNumberId.createOrUpdate(newDto);
        Assert.assertEquals(newDto, dto);
    }

    /**
     * Проверка того, что предыдущие методы createOrRead и createOrUpdate успешно выполнены
     */
    @Test
    public void test016ExecuteCreateAndUpdateIsSuccess(){
        List<DtoCountry<Integer>> list = repositoryGenerateNumberId.readAll(null);
        Assert.assertTrue(list.size() == 4);
    }

    @Test
    public void test017Delete(){
        repositoryGenerateNumberId.delete(STATIC_DTO_ID);
        List<DtoCountry<Integer>> list = repositoryGenerateNumberId.readAll(null);
        DtoCountry<Integer> dto = repositoryGenerateNumberId.read(STATIC_DTO_ID);
        Assert.assertTrue(list.size() == 3);
        Assert.assertNull(dto);
    }
}
