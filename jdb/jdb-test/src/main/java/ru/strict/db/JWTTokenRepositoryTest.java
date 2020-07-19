package ru.strict.db;

import org.junit.Assert;
import org.junit.Test;
import ru.strict.db.core.repositories.IRepository;
import ru.strict.db.core.repositories.interfaces.IJWTTokenRepository;
import ru.strict.models.JWTToken;
import ru.strict.models.DetailsUser;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static ru.strict.db.TestData.*;

public abstract class JWTTokenRepositoryTest
        extends BaseRepositoryTest<Long, JWTToken<Long>, IJWTTokenRepository<Long>> {

    protected static void prepare(IRepository<Long, DetailsUser<Long>> userRepository) {
        userRepository.create(USER1);
        userRepository.create(USER2);
        userRepository.create(USER3);
    }

    @Test
    public void testReadByAccessToken() {
        IJWTTokenRepository<Long> repository = getRepository();
        JWTToken<Long> model = getPrimaryModel();
        JWTToken<Long> updateModel = getUpdateModel();

        Long id = repository.create(model);
        JWTToken<Long> readModel = repository.readByAccessToken(model.getAccessToken());
        Assert.assertEquals(model, readModel);

        updateModel.setId(id);
        repository.update(updateModel);
        JWTToken<Long> readUpdateModel = repository.readByAccessToken(updateModel.getAccessToken());
        Assert.assertEquals(updateModel, readUpdateModel);

        repository.delete(id);
    }

    @Test
    public void testReadByRefreshToken() {
        IJWTTokenRepository<Long> repository = getRepository();
        JWTToken<Long> model = getPrimaryModel();
        JWTToken<Long> updateModel = getUpdateModel();

        Long id = repository.create(model);
        JWTToken<Long> readModel = repository.readByRefreshToken(model.getRefreshToken());
        Assert.assertEquals(model, readModel);

        updateModel.setId(id);
        repository.update(updateModel);
        JWTToken<Long> readUpdateModel = repository.readByRefreshToken(updateModel.getRefreshToken());
        Assert.assertEquals(updateModel, readUpdateModel);

        repository.delete(id);
    }

    @Test
    public void testReadByUserId() {
        IJWTTokenRepository<Long> repository = getRepository();
        JWTToken<Long> primaryJWTToken = getPrimaryModel();
        JWTToken<Long>[] tokens = getModels();

        Arrays.stream(tokens).forEach(repository::create);

        List<JWTToken<Long>> filteredTokens = Arrays.stream(tokens)
                .filter(c -> c.getUserId().equals(primaryJWTToken.getUserId()))
                .collect(Collectors.toList());

        List<JWTToken<Long>> readTokens = repository.readByUserId(primaryJWTToken.getUserId());
        Assert.assertEquals(filteredTokens.size(), readTokens.size());
        Assert.assertTrue(filteredTokens.containsAll(readTokens));

        Arrays.stream(tokens)
                .map(JWTToken<Long>::getId)
                .forEach(repository::delete);
    }

    @Override
    protected JWTToken<Long> getPrimaryModel() {
        return JWT_TOKEN1;
    }

    @Override
    protected JWTToken<Long> getUpdateModel() {
        return UPDATED_JWT_TOKEN1;
    }

    @Override
    protected JWTToken<Long>[] getModels() {
        return new JWTToken[]{
                JWT_TOKEN1,
                JWT_TOKEN2,
                JWT_TOKEN3
        };
    }
}
