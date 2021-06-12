package ru.strict.db;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.strict.db.core.dao.IDao;
import ru.strict.template.model.BaseModel;

public abstract class BaseDaoTest<ID, MODEL extends BaseModel<ID>, REPOSITORY extends IDao<ID, MODEL>> {

    @Test
    public void testCRUD() {
        REPOSITORY dao = getDao();
        MODEL model = getPrimaryModel();
        MODEL updateModel = getUpdateModel();

        long count = dao.readCount(null);
        Assertions.assertEquals(0L, count);
        ID id = dao.create(model);
        Assertions.assertNotNull(id);
        MODEL readModel = dao.read(id);
        Assertions.assertEquals(model, readModel);
        Assertions.assertTrue(dao.isRowExists(id));

        updateModel.setId(id);
        dao.update(updateModel);
        MODEL readUpdateModel = dao.read(id);
        Assertions.assertEquals(updateModel, readUpdateModel);
        count = dao.readCount(null);
        Assertions.assertEquals(1L, count);

        dao.delete(id);
        MODEL deletedModel = dao.read(id);
        Assertions.assertNull(deletedModel);
        count = dao.readCount(null);
        Assertions.assertEquals(0L, count);
        Assertions.assertFalse(dao.isRowExists(id));
    }

    @Test
    public void testCreateOrUpdate() {
        REPOSITORY dao = getDao();
        MODEL model = getPrimaryModel();
        MODEL updateModel = getUpdateModel();

        long count = dao.readCount(null);
        Assertions.assertEquals(0L, count);
        ID id = dao.createOrUpdate(model);
        Assertions.assertNotNull(id);
        MODEL readModel = dao.read(id);
        Assertions.assertEquals(model, readModel);

        updateModel.setId(id);
        dao.createOrUpdate(updateModel);
        MODEL readUpdateModel = dao.read(id);
        Assertions.assertEquals(updateModel, readUpdateModel);
        count = dao.readCount(null);
        Assertions.assertEquals(1L, count);

        dao.delete(id);
        MODEL deletedModel = dao.read(id);
        Assertions.assertNull(deletedModel);
        count = dao.readCount(null);
        Assertions.assertEquals(0L, count);
    }

    @Test
    public void testCreateOrRead() {
        REPOSITORY dao = getDao();
        MODEL model = getPrimaryModel();
        MODEL updateModel = getUpdateModel();

        long count = dao.readCount(null);
        Assertions.assertEquals(0L, count);
        MODEL readModel = dao.createOrRead(model);
        Assertions.assertEquals(model, readModel);

        updateModel.setId(readModel.getId());
        dao.createOrUpdate(updateModel);
        MODEL readUpdateModel = dao.createOrRead(updateModel);
        Assertions.assertEquals(updateModel, readUpdateModel);
        count = dao.readCount(null);
        Assertions.assertEquals(1L, count);

        dao.delete(updateModel.getId());
        MODEL deletedModel = dao.read(updateModel.getId());
        Assertions.assertNull(deletedModel);
        count = dao.readCount(null);
        Assertions.assertEquals(0L, count);
    }

    @Test
    public void testReadAll() {
        REPOSITORY dao = getDao();
        MODEL[] models = getModels();
        MODEL updateModel = getUpdateModel();

        long count = dao.readCount(null);
        Assertions.assertEquals(0L, count);
        createModels(models);

        updateModel.setId(models[0].getId());
        dao.update(updateModel);
        MODEL readUpdateModel = dao.read(models[0].getId());
        Assertions.assertEquals(updateModel, readUpdateModel);
        count = dao.readCount(null);
        Assertions.assertEquals(models.length, count);

        dao.delete(models[0].getId());
        MODEL deletedModel = dao.read(models[0].getId());
        Assertions.assertNull(deletedModel);
        count = dao.readCount(null);
        Assertions.assertEquals(models.length-1, count);

        deleteModels(models);
    }

    protected void createModels(MODEL[] models) {
        REPOSITORY dao = getDao();

        for (MODEL model : models) {
            ID id = dao.create(model);
            Assertions.assertNotNull(id);
            MODEL readModel = dao.read(id);
            Assertions.assertEquals(model, readModel);
            model.setId(id);
        }
        long count = dao.readCount(null);
        Assertions.assertEquals(models.length, count);
    }

    protected void deleteModels(MODEL[] models) {
        REPOSITORY dao = getDao();

        for (MODEL model : models) {
            dao.delete(model.getId());
        }
        long count = dao.readCount(null);
        Assertions.assertEquals(0L, count);
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
    protected abstract REPOSITORY getDao();
}
