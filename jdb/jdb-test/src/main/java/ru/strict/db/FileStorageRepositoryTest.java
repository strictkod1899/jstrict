package ru.strict.db;

import org.junit.Assert;
import org.junit.Test;
import ru.strict.db.core.repositories.interfaces.IFileStorageRepository;
import ru.strict.models.FileStorage;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static ru.strict.db.TestData.*;

public abstract class FileStorageRepositoryTest
        extends NamedRepositoryTest<Long, FileStorage<Long>, IFileStorageRepository<Long, FileStorage<Long>>> {

    /*
    @Test
    public void testReadByCountryId() {
        IFileStorageRepository<Long> repository = getRepository();
        FileStorage<Long> primaryFileStorage = getPrimaryModel();
        FileStorage<Long>[] cities = getModels();

        Arrays.stream(cities).forEach(repository::create);

        List<FileStorage<Long>> filteredCities = Arrays.stream(cities)
                .filter(c -> c.getCountryId().equals(primaryFileStorage.getCountryId()))
                .collect(Collectors.toList());

        List<FileStorage<Long>> readCities = repository.readByCountryId(primaryFileStorage.getCountryId());
        Assert.assertEquals(filteredCities.size(), readCities.size());
        Assert.assertTrue(filteredCities.containsAll(readCities));

        Arrays.stream(cities)
                .map(FileStorage<Long>::getId)
                .forEach(repository::delete);
    }
     */

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
    protected FileStorage<Long> getFillPrimaryModel() {
        return FILL_FILE_STORAGE1;
    }

    @Override
    protected FileStorage<Long> getFillUpdateModel() {
        return UPDATED_FILL_FILE_STORAGE1;
    }

    @Override
    protected String getPrimaryCaption() {
        return getPrimaryModel().getFilename();
    }

    @Override
    protected String getUpdatedCaption() {
        return getUpdateModel().getFilename();
    }

    @Override
    protected String getFillPrimaryCaption() {
        return getFillPrimaryModel().getFilename();
    }

    @Override
    protected String getFillUpdatedCaption() {
        return getFillUpdateModel().getFilename();
    }
}
