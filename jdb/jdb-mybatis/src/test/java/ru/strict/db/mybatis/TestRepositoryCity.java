package ru.strict.db.mybatis;

import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.junit.runners.MethodSorters;
import ru.strict.db.core.common.GenerateIdType;
import ru.strict.db.core.dto.DtoCity;
import ru.strict.db.core.dto.DtoCountry;
import ru.strict.db.core.repositories.IRepositoryNamed;
import ru.strict.db.mybatis.repositories.RepositoryCity;
import ru.strict.db.mybatis.repositories.RepositoryCountry;

import java.util.List;
import java.util.UUID;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestRepositoryCity {

    private static final Integer COUNTRY_NUMBER_ID = 101;
    private static final UUID COUNTRY_UUID_ID = UUID.randomUUID();
    private static final String COUNTRY_CAPTION = "country";

    private static final Integer STATIC_DTO_ID = 101;
    private static final String STATIC_DTO_CAPTION = "city1";
    private static final String STATIC_DTO_UPDATED_CAPTION = "city1-1";
    private static final DtoCity STATIC_DTO = new DtoCity<>(STATIC_DTO_ID, STATIC_DTO_CAPTION, COUNTRY_NUMBER_ID);

    private static IRepositoryNamed<Integer, DtoCity<Integer>> repositoryNotGenerateId;
    private static IRepositoryNamed<Integer, DtoCity<Integer>> repositoryGenerateNumberId;
    private static IRepositoryNamed<UUID, DtoCity<UUID>> repositoryGenerateUuidId;

    @BeforeClass
    public static void prepare(){
        repositoryNotGenerateId = new RepositoryCity<>(TestRunner.createConnectionForDbInteger, GenerateIdType.NONE);
        repositoryGenerateNumberId = new RepositoryCity<>(TestRunner.createConnectionForDbInteger, GenerateIdType.NUMBER);
        repositoryGenerateUuidId = new RepositoryCity<>(TestRunner.createConnectionForDbUuid, GenerateIdType.UUID);
        TestRunner.repositories.add(repositoryGenerateNumberId);
        TestRunner.repositories.add(repositoryGenerateUuidId);

        IRepositoryNamed<Integer, DtoCountry<Integer>> repositoryCountryNumberId
                = new RepositoryCountry<>(TestRunner.createConnectionForDbInteger, GenerateIdType.NONE);
        IRepositoryNamed<UUID, DtoCountry<UUID>> repositoryCountryUuidId
                = new RepositoryCountry<>(TestRunner.createConnectionForDbUuid, GenerateIdType.NONE);

        TestRunner.repositories.add(repositoryCountryNumberId);
        TestRunner.repositories.add(repositoryCountryUuidId);

        repositoryCountryNumberId.create(new DtoCountry<>(COUNTRY_NUMBER_ID, COUNTRY_CAPTION));
        repositoryCountryUuidId.create(new DtoCountry<>(COUNTRY_UUID_ID, COUNTRY_CAPTION));
    }

    @Test
    public void test001CreateGenerateNumberId(){
        DtoCity dto = new DtoCity<>("city2", COUNTRY_NUMBER_ID);
        DtoCity createdDto = repositoryGenerateNumberId.create(dto);
        Assert.assertNotNull(createdDto.getId());
    }

    @Test
    public void test002CreateGenerateUuidId(){
        DtoCity dto = new DtoCity<>(UUID.randomUUID(), "city3", COUNTRY_UUID_ID);
        DtoCity createdDto = repositoryGenerateUuidId.create(dto);
        Assert.assertEquals(dto, createdDto);
    }

    @Test
    public void test003CreateNotGenerateId(){
        DtoCity createdDto = repositoryNotGenerateId.create(STATIC_DTO);
        Assert.assertEquals(STATIC_DTO, createdDto);
    }

    @Test
    public void test004ReadByInteger(){
        DtoCity dto = repositoryGenerateNumberId.read(STATIC_DTO_ID);
        Assert.assertEquals(STATIC_DTO, dto);
    }

    @Test
    public void test005ReadAllByInteger(){
        List<DtoCity<Integer>> list = repositoryGenerateNumberId.readAll(null);
        Assert.assertTrue(list.size() == 2);
    }

    @Test
    public void test006ReadAllByUuid(){
        List<DtoCity<UUID>> list = repositoryGenerateUuidId.readAll(null);
        Assert.assertTrue(list.size() == 1);
    }

    @Test
    public void test007ReadCount(){
        Integer count = repositoryGenerateNumberId.readCount(null);
        Assert.assertTrue(count == 2);
    }

    @Test
    public void test008ReadByName(){
        DtoCity dto = repositoryGenerateNumberId.readByName(STATIC_DTO_CAPTION);
        Assert.assertEquals(STATIC_DTO, dto);
    }

    @Test
    public void test009ReadAllByName(){
        List<DtoCity<Integer>> list = repositoryGenerateNumberId.readAllByName(STATIC_DTO_CAPTION);
        Assert.assertTrue(list.size() == 1 && list.get(0).equals(STATIC_DTO));
    }

    @Test
    public void test010IsRowExists(){
        boolean isRowExists = repositoryGenerateNumberId.isRowExists(STATIC_DTO_ID);
        Assert.assertTrue(isRowExists);
    }

    @Test
    public void test011CreateOrReadExists(){
        DtoCity dto = repositoryGenerateNumberId.createOrRead(STATIC_DTO);
        Assert.assertEquals(STATIC_DTO, dto);
    }

    @Test
    public void test012CreateOrReadNotExists(){
        DtoCity<Integer> newDto = new DtoCity<>(102, "city4", COUNTRY_NUMBER_ID);
        DtoCity dto = repositoryGenerateNumberId.createOrRead(newDto);
        Assert.assertEquals(newDto, dto);
    }

    @Test
    public void test013Update(){
        STATIC_DTO.setCaption(STATIC_DTO_UPDATED_CAPTION);
        DtoCity dto = repositoryGenerateNumberId.update(STATIC_DTO);
        Assert.assertEquals(STATIC_DTO, dto);
    }

    @Test
    public void test014CreateOrUpdateExists(){
        STATIC_DTO.setCaption(STATIC_DTO_CAPTION);
        DtoCity dto = repositoryGenerateNumberId.createOrUpdate(STATIC_DTO);
        Assert.assertEquals(STATIC_DTO, dto);
    }

    @Test
    public void test015CreateOrUpdateNotExists(){
        DtoCity<Integer> newDto = new DtoCity<>(103, "city5", COUNTRY_NUMBER_ID);
        DtoCity dto = repositoryGenerateNumberId.createOrUpdate(newDto);
        Assert.assertEquals(newDto, dto);
    }

    /**
     * Проверка того, что предыдущие методы createOrRead и createOrUpdate успешно выполнены
     */
    @Test
    public void test016ExecuteCreateAndUpdateIsSuccess(){
        List<DtoCity<Integer>> list = repositoryGenerateNumberId.readAll(null);
        Assert.assertTrue(list.size() == 4);
    }

    @Test
    public void test017Delete(){
        repositoryGenerateNumberId.delete(STATIC_DTO_ID);
        List<DtoCity<Integer>> list = repositoryGenerateNumberId.readAll(null);
        DtoCity<Integer> dto = repositoryGenerateNumberId.read(STATIC_DTO_ID);
        Assert.assertTrue(list.size() == 3);
        Assert.assertNull(dto);
    }
}
