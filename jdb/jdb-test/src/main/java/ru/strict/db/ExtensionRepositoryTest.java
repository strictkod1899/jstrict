package ru.strict.db;

import org.junit.Assert;
import org.junit.Test;
import ru.strict.db.core.repositories.IExtensionRepository;
import ru.strict.patterns.model.BaseModel;

public abstract class ExtensionRepositoryTest<ID, MODEL extends BaseModel<ID>, REPOSITORY extends IExtensionRepository<ID, MODEL>>
        extends RepositoryBaseTest<ID, MODEL, REPOSITORY> {

    @Test
    public void testReadFill() {
        REPOSITORY repository = getRepository();
        MODEL model = getFillPrimaryModel();
        MODEL updateModel = getFillUpdateModel();

        long count = repository.readCount(null);
        Assert.assertEquals(0L, count);
        ID id = repository.create(model);
        Assert.assertNotNull(id);
        MODEL readModel = repository.readFill(id);
        Assert.assertEquals(model, readModel);
        Assert.assertTrue(repository.isRowExists(id));

        updateModel.setId(id);
        repository.update(updateModel);
        MODEL readUpdateModel = repository.readFill(id);
        Assert.assertEquals(updateModel, readUpdateModel);
        count = repository.readCount(null);
        Assert.assertEquals(1L, count);

        repository.delete(id);
        MODEL deletedModel = repository.readFill(id);
        Assert.assertNull(deletedModel);
        count = repository.readCount(null);
        Assert.assertEquals(0L, count);
        Assert.assertFalse(repository.isRowExists(id));
    }

    @Test
    public void testCreateOrReadFill() {
        REPOSITORY repository = getRepository();
        MODEL model = getFillPrimaryModel();
        MODEL updateModel = getFillUpdateModel();

        long count = repository.readCount(null);
        Assert.assertEquals(0L, count);
        MODEL readModel = repository.createOrReadFill(model);
        Assert.assertEquals(model, readModel);

        updateModel.setId(readModel.getId());
        repository.createOrUpdate(updateModel);
        MODEL readUpdateModel = repository.createOrReadFill(updateModel);
        Assert.assertEquals(updateModel, readUpdateModel);
        count = repository.readCount(null);
        Assert.assertEquals(1L, count);

        repository.delete(updateModel.getId());
        MODEL deletedModel = repository.readFill(updateModel.getId());
        Assert.assertNull(deletedModel);
        count = repository.readCount(null);
        Assert.assertEquals(0L, count);
    }

    /**
     * Получить основную заполненную модель
     */
    protected abstract MODEL getFillPrimaryModel();

    /**
     * Получить заполненную модель для выполнения обновления в БД
     */
    protected abstract MODEL getFillUpdateModel();
}
