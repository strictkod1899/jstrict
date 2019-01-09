package ru.strict.db.mybatis;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import ru.strict.db.core.common.GenerateIdType;
import ru.strict.db.core.dto.DtoCountry;
import ru.strict.db.core.repositories.IRepositoryNamed;
import ru.strict.db.mybatis.connection.CreateConnectionByMybatis;
import ru.strict.db.mybatis.repositories.RepositoryCountry;

import java.util.UUID;

@RunWith(JUnit4.class)
public class TestRepositoryCountry {

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
    public void testCreateGenerateNumberId(){
        DtoCountry dto = new DtoCountry<>("England");
        DtoCountry createdDto = repositoryGenerateNumberId.create(dto);
        Assert.assertNotNull(createdDto.getId());
    }

    @Test
    public void testCreateGenerateUuidId(){
        DtoCountry dto = new DtoCountry<>(UUID.randomUUID(), "France");
        DtoCountry createdDto = repositoryGenerateUuidId.create(dto);
        Assert.assertEquals(dto, createdDto);
    }

    @Test
    public void testCreateNotGenerateId(){
        DtoCountry dto = new DtoCountry<>(101, "Russia");
        DtoCountry createdDto = repositoryNotGenerateId.create(dto);
        Assert.assertEquals(dto, createdDto);
    }
}
