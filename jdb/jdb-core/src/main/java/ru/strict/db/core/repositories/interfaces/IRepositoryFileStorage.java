package ru.strict.db.core.repositories.interfaces;

import ru.strict.db.core.requests.DbSelectItem;
import ru.strict.models.FileStorageBase;
import ru.strict.db.core.repositories.IRepositoryNamed;
import ru.strict.db.core.requests.DbRequests;
import ru.strict.db.core.requests.DbWhereItem;

import java.util.List;

public interface IRepositoryFileStorage<ID, DTO extends FileStorageBase<ID>> extends IRepositoryNamed<ID, DTO> {
    default List<DTO> readByDisplayName(String displayName){
        DbRequests requests = new DbRequests();
        requests.addWhere(new DbWhereItem(new DbSelectItem(getTable(), "displayname"), displayName, "="));

        List<DTO> result = readAll(requests);
        return result;
    }

    default List<DTO> readByFileNameAndExtension(String fileName, String extension){
        DbRequests requests = new DbRequests();
        requests.addWhere(new DbWhereItem(new DbSelectItem(getTable(), "filename"), fileName, "="));
        requests.addWhere(new DbWhereItem(new DbSelectItem(getTable(), "extension"), extension, "="));

        List<DTO> result = readAll(requests);
        return result;
    }

    default List<DTO> readByDisplayNameAndExtension(String displayName, String extension){
        DbRequests requests = new DbRequests();
        requests.addWhere(new DbWhereItem(new DbSelectItem(getTable(), "displayname"), displayName, "="));
        requests.addWhere(new DbWhereItem(new DbSelectItem(getTable(), "extension"), extension, "="));

        List<DTO> result = readAll(requests);
        return result;
    }

    default DTO readByFilePath(String filePath){
        DbRequests requests = new DbRequests();
        requests.addWhere(new DbWhereItem(new DbSelectItem(getTable(), "filepath"), filePath, "="));

        DTO result = readAll(requests).stream().findFirst().orElse(null);
        return result;
    }

    default List<DTO> readByType(int type){
        DbRequests requests = new DbRequests();
        requests.addWhere(new DbWhereItem(new DbSelectItem(getTable(), "type"), type, "="));

        List<DTO> result = readAll(requests);
        return result;
    }

    default List<DTO> readByStatus(int status){
        DbRequests requests = new DbRequests();
        requests.addWhere(new DbWhereItem(new DbSelectItem(getTable(), "status"), status, "="));

        List<DTO> result = readAll(requests);
        return result;
    }
}
