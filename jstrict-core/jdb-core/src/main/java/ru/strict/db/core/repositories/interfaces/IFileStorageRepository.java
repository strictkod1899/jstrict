package ru.strict.db.core.repositories.interfaces;

import ru.strict.db.core.requests.DbSelectItem;
import ru.strict.models.BaseFileStorage;
import ru.strict.db.core.repositories.INamedRepository;
import ru.strict.db.core.requests.DbRequests;
import ru.strict.db.core.requests.DbWhereItem;

import java.util.List;

public interface IFileStorageRepository<ID, T extends BaseFileStorage<ID>> extends INamedRepository<ID, T> {
    default List<T> readByDisplayName(String displayName){
        if(displayName == null){
            throw new IllegalArgumentException("displayName is NULL");
        }
        DbRequests requests = new DbRequests();
        requests.addWhere(new DbWhereItem(new DbSelectItem(getTable(), "displayname"), displayName, "="));

        return readAll(requests);
    }

    default List<T> readByFileNameAndExtension(String fileName, String extension){
        if(fileName == null){
            throw new IllegalArgumentException("fileName is NULL");
        }
        if(extension == null){
            throw new IllegalArgumentException("extension is NULL");
        }
        DbRequests requests = new DbRequests();
        requests.addWhere(new DbWhereItem(new DbSelectItem(getTable(), "filename"), fileName, "="));
        requests.addWhere(new DbWhereItem(new DbSelectItem(getTable(), "extension"), extension, "="));

        return readAll(requests);
    }

    default List<T> readByDisplayNameAndExtension(String displayName, String extension){
        if(displayName == null){
            throw new IllegalArgumentException("displayName is NULL");
        }
        if(extension == null){
            throw new IllegalArgumentException("extension is NULL");
        }
        DbRequests requests = new DbRequests();
        requests.addWhere(new DbWhereItem(new DbSelectItem(getTable(), "displayname"), displayName, "="));
        requests.addWhere(new DbWhereItem(new DbSelectItem(getTable(), "extension"), extension, "="));

        return readAll(requests);
    }

    default T readByFilePath(String filePath){
        if(filePath == null){
            throw new IllegalArgumentException("filePath is NULL");
        }
        DbRequests requests = new DbRequests();
        requests.addWhere(new DbWhereItem(new DbSelectItem(getTable(), "filepath"), filePath, "="));

        return readAll(requests).stream().findFirst().orElse(null);
    }

    default List<T> readByType(int type){
        DbRequests requests = new DbRequests();
        requests.addWhere(new DbWhereItem(new DbSelectItem(getTable(), "type"), type, "="));

        return readAll(requests);
    }

    default List<T> readByStatus(int status){
        DbRequests requests = new DbRequests();
        requests.addWhere(new DbWhereItem(new DbSelectItem(getTable(), "status"), status, "="));

        return readAll(requests);
    }
}
