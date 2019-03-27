package ru.strict.db.core.repositories.interfaces;

import ru.strict.db.core.dto.DtoUserOnRole;
import ru.strict.db.core.repositories.IRepositoryExtension;
import ru.strict.db.core.requests.DbRequests;
import ru.strict.db.core.requests.DbWhereItem;

import java.util.List;

public interface IRepositoryUserOnRole<ID> extends IRepositoryExtension<ID, DtoUserOnRole<ID>> {
    default List<DtoUserOnRole<ID>> readByUserId(ID userId){
        DbRequests requests = new DbRequests();
        requests.addWhere(new DbWhereItem(getTableName(), "userx_id", userId, "="));

        List<DtoUserOnRole<ID>> result = readAll(requests);
        return result;
    }

    default List<DtoUserOnRole<ID>> readByRoleId(ID roleId){
        DbRequests requests = new DbRequests();
        requests.addWhere(new DbWhereItem(getTableName(), "roleuser_id", roleId, "="));

        List<DtoUserOnRole<ID>> result = readAll(requests);
        return result;
    }
}
