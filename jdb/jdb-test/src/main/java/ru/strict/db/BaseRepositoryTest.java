package ru.strict.db;

import org.junit.Assert;
import org.junit.Test;
import ru.strict.db.core.repositories.IRepository;
import ru.strict.patterns.model.BaseModel;

public abstract class BaseRepositoryTest<ID, MODEL extends BaseModel<ID>, REPOSITORY extends IRepository<ID, MODEL>> {

    @Test
    public void testCRUD() {
        REPOSITORY repository = getRepository();
        MODEL model = getPrimaryModel();
        MODEL updateModel = getUpdateModel();

        long count = repository.readCount(null);
        Assert.assertEquals(0L, count);
        ID id = repository.create(model);
        Assert.assertNotNull(id);
        MODEL readModel = repository.read(id);
        Assert.assertEquals(model, readModel);
        Assert.assertTrue(repository.isRowExists(id));

        updateModel.setId(id);
        repository.update(updateModel);
        MODEL readUpdateModel = repository.read(id);
        Assert.assertEquals(updateModel, readUpdateModel);
        count = repository.readCount(null);
        Assert.assertEquals(1L, count);

        repository.delete(id);
        MODEL deletedModel = repository.read(id);
        Assert.assertNull(deletedModel);
        count = repository.readCount(null);
        Assert.assertEquals(0L, count);
        Assert.assertFalse(repository.isRowExists(id));
    }

    @Test
    public void testCreateOrUpdate() {
        REPOSITORY repository = getRepository();
        MODEL model = getPrimaryModel();
        MODEL updateModel = getUpdateModel();

        long count = repository.readCount(null);
        Assert.assertEquals(0L, count);
        ID id = repository.createOrUpdate(model);
        Assert.assertNotNull(id);
        MODEL readModel = repository.read(id);
        Assert.assertEquals(model, readModel);

        updateModel.setId(id);
        repository.createOrUpdate(updateModel);
        MODEL readUpdateModel = repository.read(id);
        Assert.assertEquals(updateModel, readUpdateModel);
        count = repository.readCount(null);
        Assert.assertEquals(1L, count);

        repository.delete(id);
        MODEL deletedModel = repository.read(id);
        Assert.assertNull(deletedModel);
        count = repository.readCount(null);
        Assert.assertEquals(0L, count);
    }

    @Test
    public void testCreateOrRead() {
        REPOSITORY repository = getRepository();
        MODEL model = getPrimaryModel();
        MODEL updateModel = getUpdateModel();

        long count = repository.readCount(null);
        Assert.assertEquals(0L, count);
        MODEL readModel = repository.createOrRead(model);
        Assert.assertEquals(model, readModel);

        updateModel.setId(readModel.getId());
        repository.createOrUpdate(updateModel);
        MODEL readUpdateModel = repository.createOrRead(updateModel);
        Assert.assertEquals(updateModel, readUpdateModel);
        count = repository.readCount(null);
        Assert.assertEquals(1L, count);

        repository.delete(updateModel.getId());
        MODEL deletedModel = repository.read(updateModel.getId());
        Assert.assertNull(deletedModel);
        count = repository.readCount(null);
        Assert.assertEquals(0L, count);
    }

    @Test
    public void testReadAll() {
        REPOSITORY repository = getRepository();
        MODEL[] models = getModels();
        MODEL updateModel = getUpdateModel();

        long count = repository.readCount(null);
        Assert.assertEquals(0L, count);
        createModels(models);

        updateModel.setId(models[0].getId());
        repository.update(updateModel);
        MODEL readUpdateModel = repository.read(models[0].getId());
        Assert.assertEquals(updateModel, readUpdateModel);
        count = repository.readCount(null);
        Assert.assertEquals(models.length, count);

        repository.delete(models[0].getId());
        MODEL deletedModel = repository.read(models[0].getId());
        Assert.assertNull(deletedModel);
        count = repository.readCount(null);
        Assert.assertEquals(models.length-1, count);

        deleteModels(models);
    }

    protected void createModels(MODEL[] models) {
        REPOSITORY repository = getRepository();

        for (MODEL model : models) {
            ID id = repository.create(model);
            Assert.assertNotNull(id);
            MODEL readModel = repository.read(id);
            Assert.assertEquals(model, readModel);
            model.setId(id);
        }
        long count = repository.readCount(null);
        Assert.assertEquals(models.length, count);
    }

    protected void deleteModels(MODEL[] models) {
        REPOSITORY repository = getRepository();

        for (MODEL model : models) {
            repository.delete(model.getId());
        }
        long count = repository.readCount(null);
        Assert.assertEquals(0L, count);
    }

    /**
     * Получить основную модель
     */
    protected abstract MODEL getPrimaryModel();

    /**
     * Получить модели для тестирования. Первым элементом должна быть основаная модель
     */
    protected abstract MODEL[] getModels();

    /**
     * Получить модель для выполнения обновления в БД
     */
    protected abstract MODEL getUpdateModel();

    /**
     * Получить репозиторий, который не генерирует числовой id
     */
    protected abstract REPOSITORY getRepository();
}
