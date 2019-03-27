package ru.strict.db.core.repositories.interfaces;

import ru.strict.db.core.dto.DtoFileStorageBase;
import ru.strict.db.core.repositories.IRepositoryNamed;
import ru.strict.db.core.requests.DbRequests;
import ru.strict.db.core.requests.DbWhereItem;

import java.util.List;

public interface IRepositoryFileStorage<ID, DTO extends DtoFileStorageBase<ID>> extends IRepositoryNamed<ID, DTO> {
    default List<DTO> readByDisplayName(String displayName){
        DbRequests requests = new DbRequests();
        requests.addWhere(new DbWhereItem(getTableName(), "displayname", displayName, "="));

        List<DTO> result = readAll(requests);
        return result;
    }

    default List<DTO> readByFileNameAndExtension(String fileName, String extension){
        DbRequests requests = new DbRequests();
        requests.addWhere(new DbWhereItem(getTableName(), "filename", fileName, "="));
        requests.addWhere(new DbWhereItem(getTableName(), "extension", extension, "="));

        List<DTO> result = readAll(requests);
        return result;
    }

    default List<DTO> readByDisplayNameAndExtension(String displayName, String extension){
        DbRequests requests = new DbRequests();
        requests.addWhere(new DbWhereItem(getTableName(), "displayname", displayName, "="));
        requests.addWhere(new DbWhereItem(getTableName(), "extension", extension, "="));

        List<DTO> result = readAll(requests);
        return result;
    }

    default DTO readByFilePath(String filePath){
        DbRequests requests = new DbRequests();
        requests.addWhere(new DbWhereItem(getTableName(), "filepath", filePath, "="));

        DTO result = readAll(requests).stream().findFirst().orElse(null);
        return result;
    }

    default List<DTO> readByType(int type){
        DbRequests requests = new DbRequests();
        requests.addWhere(new DbWhereItem(getTableName(), "type", type, "="));

        List<DTO> result = readAll(requests);
        return result;
    }

    default List<DTO> readByStatus(int status){
        DbRequests requests = new DbRequests();
        requests.addWhere(new DbWhereItem(getTableName(), "status", status, "="));

        List<DTO> result = readAll(requests);
        return result;
    }
}
