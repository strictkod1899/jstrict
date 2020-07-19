package ru.strict.db;

import ru.strict.db.core.repositories.interfaces.IFileStorageRepository;
import ru.strict.models.FileStorage;

import static ru.strict.db.TestData.*;

public abstract class FileStorageRepositoryTest
        extends NamedRepositoryTest<Long, FileStorage<Long>, IFileStorageRepository<Long, FileStorage<Long>>> {

    @Override
    protected FileStorage<Long> getPrimaryModel() {
        return FILE_STORAGE1;
    }

    @Override
    protected FileStorage<Long> getUpdateModel() {
        return UPDATED_FILE_STORAGE1;
    }

    @Override
    protected FileStorage<Long>[] getModels() {
        return new FileStorage[]{
                FILE_STORAGE1,
                FILE_STORAGE2,
                FILE_STORAGE3
        };
    }

    @Override
    protected String getPrimaryCaption() {
        return getPrimaryModel().getFilename();
    }

    @Override
    protected String getUpdatedCaption() {
        return getUpdateModel().getFilename();
    }
}
