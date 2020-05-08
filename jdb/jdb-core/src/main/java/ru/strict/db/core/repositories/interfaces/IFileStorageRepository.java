package ru.strict.db.core.repositories.interfaces;

import ru.strict.db.core.requests.DbSelectItem;
import ru.strict.models.FileStorage;
import ru.strict.db.core.repositories.INamedRepository;
import ru.strict.db.core.requests.DbRequests;
import ru.strict.db.core.requests.DbWhereItem;
import ru.strict.validate.Validator;

import java.util.List;

public interface IFileStorageRepository<ID, T extends FileStorage<ID>> extends INamedRepository<ID, T> {
    default List<T> readByDisplayName(String displayName) {
        Validator.isNull(displayName, "displayName").onThrow();
        DbRequests requests = new DbRequests();
        requests.addWhere(new DbWhereItem(new DbSelectItem(getTable(), "displayname"), displayName, "="));

        return readAll(requests);
    }

    default List<T> readByFileNameAndExtension(String fileName, String extension) {
        Validator.isNull(fileName, "fileName").onThrow();
        Validator.isNull(extension, "extension").onThrow();
        DbRequests requests = new DbRequests();
        requests.addWhere(new DbWhereItem(new DbSelectItem(getTable(), "filename"), fileName, "="));
        requests.addWhere(new DbWhereItem(new DbSelectItem(getTable(), "extension"), extension, "="));

        return readAll(requests);
    }

    default List<T> readByDisplayNameAndExtension(String displayName, String extension) {
        Validator.isNull(displayName, "displayName").onThrow();
        Validator.isNull(extension, "extension").onThrow();
        DbRequests requests = new DbRequests();
        requests.addWhere(new DbWhereItem(new DbSelectItem(getTable(), "displayname"), displayName, "="));
        requests.addWhere(new DbWhereItem(new DbSelectItem(getTable(), "extension"), extension, "="));

        return readAll(requests);
    }

    default T readByFilePath(String filePath) {
        Validator.isNull(filePath, "filePath").onThrow();
        DbRequests requests = new DbRequests();
        requests.addWhere(new DbWhereItem(new DbSelectItem(getTable(), "filepath"), filePath, "="));

        return readAll(requests).stream().findFirst().orElse(null);
    }

    default List<T> readByType(int type) {
        DbRequests requests = new DbRequests();
        requests.addWhere(new DbWhereItem(new DbSelectItem(getTable(), "type"), type, "="));

        return readAll(requests);
    }

    default List<T> readByStatus(int status) {
        DbRequests requests = new DbRequests();
        requests.addWhere(new DbWhereItem(new DbSelectItem(getTable(), "status"), status, "="));

        return readAll(requests);
    }
}
