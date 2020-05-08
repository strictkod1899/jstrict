package ru.strict.db;

import org.junit.Assert;
import org.junit.Test;
import ru.strict.db.core.repositories.INamedRepository;
import ru.strict.models.BaseModel;

public abstract class NamedRepositoryTest<ID, MODEL extends BaseModel<ID>, REPOSITORY extends INamedRepository<ID, MODEL>>
        extends ExtensionRepositoryTest<ID, MODEL, REPOSITORY> {

    @Test
    public void testReadByName() {
        REPOSITORY repository = getRepository();
        MODEL model = getPrimaryModel();
        MODEL updateModel = getUpdateModel();

        ID id = repository.create(model);
        MODEL readModel = repository.readByName(getPrimaryCaption());
        Assert.assertEquals(model, readModel);
        Assert.assertTrue(repository.isRowExists(id));

        updateModel.setId(id);
        repository.update(updateModel);
        MODEL readUpdateModel = repository.readByName(getUpdatedCaption());
        Assert.assertEquals(updateModel, readUpdateModel);

        repository.delete(id);
    }

    @Test
    public void testReadByNameFill() {
        REPOSITORY repository = getRepository();
        MODEL model = getFillPrimaryModel();
        MODEL updateModel = getFillUpdateModel();

        ID id = repository.create(model);
        MODEL readModel = repository.readByNameFill(getFillPrimaryCaption());
        Assert.assertEquals(model, readModel);
        Assert.assertTrue(repository.isRowExists(id));

        updateModel.setId(id);
        repository.update(updateModel);
        MODEL readUpdateModel = repository.readByNameFill(getFillUpdatedCaption());
        Assert.assertEquals(updateModel, readUpdateModel);

        repository.delete(id);
    }

    protected abstract String getPrimaryCaption();
    protected abstract String getUpdatedCaption();
    protected abstract String getFillPrimaryCaption();
    protected abstract String getFillUpdatedCaption();
}
