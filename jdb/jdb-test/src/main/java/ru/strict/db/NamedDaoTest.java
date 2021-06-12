package ru.strict.db;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.strict.db.core.dao.INamedDao;
import ru.strict.template.model.BaseModel;

public abstract class NamedDaoTest<ID, MODEL extends BaseModel<ID>, REPOSITORY extends INamedDao<ID, MODEL>>
        extends BaseDaoTest<ID, MODEL, REPOSITORY> {

    @Test
    public void testReadByName() {
        REPOSITORY dao = getDao();
        MODEL model = getPrimaryModel();
        MODEL updateModel = getUpdateModel();

        ID id = dao.create(model);
        MODEL readModel = dao.readByName(getPrimaryCaption());
        Assertions.assertEquals(model, readModel);
        Assertions.assertTrue(dao.isRowExists(id));

        updateModel.setId(id);
        dao.update(updateModel);
        MODEL readUpdateModel = dao.readByName(getUpdatedCaption());
        Assertions.assertEquals(updateModel, readUpdateModel);

        dao.delete(id);
    }

    protected abstract String getPrimaryCaption();
    protected abstract String getUpdatedCaption();
}
