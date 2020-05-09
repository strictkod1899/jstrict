package ru.strict.db.core.repositories.interfaces;

import ru.strict.db.core.common.SqlParameter;
import ru.strict.db.core.requests.components.SingleWhere;
import ru.strict.db.core.requests.components.SqlItem;
import ru.strict.db.core.requests.components.Where;
import ru.strict.models.FileStorage;
import ru.strict.db.core.repositories.INamedRepository;
import ru.strict.validate.Validator;

import java.util.List;

public interface IFileStorageRepository<ID, T extends FileStorage<ID>> extends INamedRepository<ID, T> {
    default List<T> readByDisplayName(String displayName) {
        Validator.isNull(displayName, "displayName").onThrow();

        SingleWhere where = new SingleWhere(
                new SqlItem(getTable(), "displayname"),
                "=",
                new SqlParameter<>("displayname", displayName));

        return readAll(where);
    }

    default List<T> readByFileNameAndExtension(String fileName, String extension) {
        Validator.isNull(fileName, "fileName").onThrow();
        Validator.isNull(extension, "extension").onThrow();

        Where where = Where.builder()
                .item(SingleWhere.build(new SqlItem(getTable(), "filename"), "="))
                .addParameter("fileName", fileName)
                .item(SingleWhere.build(new SqlItem(getTable(), "extension"), "="))
                .addParameter("extension", extension)
                .build();

        return readAll(where);
    }

    default List<T> readByDisplayNameAndExtension(String displayName, String extension) {
        Validator.isNull(displayName, "displayName").onThrow();
        Validator.isNull(extension, "extension").onThrow();

        Where where = Where.builder()
                .item(SingleWhere.build(new SqlItem(getTable(), "displayname"), "="))
                .addParameter("displayname", displayName)
                .item(SingleWhere.build(new SqlItem(getTable(), "extension"), "="))
                .addParameter("extension", extension)
                .build();

        return readAll(where);
    }

    default T readByFilePath(String filePath) {
        Validator.isNull(filePath, "filePath").onThrow();

        SingleWhere where = new SingleWhere(
                new SqlItem(getTable(), "filepath"),
                "=",
                new SqlParameter<>("filepath", filePath));

        return readAll(where).stream().findFirst().orElse(null);
    }

    default List<T> readByType(int type) {
        SingleWhere where = new SingleWhere(
                new SqlItem(getTable(), "type"),
                "=",
                new SqlParameter<>("type", type));

        return readAll(where);
    }

    default List<T> readByStatus(int status) {
        SingleWhere where = new SingleWhere(
                new SqlItem(getTable(), "status"),
                "=",
                new SqlParameter<>("status", status));

        return readAll(where);
    }
}
