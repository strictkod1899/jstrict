package ru.strict.db;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.strict.db.core.dao.INamedDao;
import ru.strict.patterns.model.BaseModel;

public abstract class NamedRepositoryTest<ID, MODEL extends BaseModel<ID>, REPOSITORY extends INamedDao<ID, MODEL>>
        extends BaseDaoTest<ID, MODEL, REPOSITORY> {

    @Test
    public void testReadByName() {
        REPOSITORY repository = getRepository();
        MODEL model = getPrimaryModel();
        MODEL updateModel = getUpdateModel();

        ID id = repository.create(model);
        MODEL readModel = repository.readByName(getPrimaryCaption());
        Assertions.assertEquals(model, readModel);
        Assertions.assertTrue(repository.isRowExists(id));

        updateModel.setId(id);
        repository.update(updateModel);
        MODEL readUpdateModel = repository.readByName(getUpdatedCaption());
        Assertions.assertEquals(updateModel, readUpdateModel);

        repository.delete(id);
    }

    protected abstract String getPrimaryCaption();
    protected abstract String getUpdatedCaption();
}
