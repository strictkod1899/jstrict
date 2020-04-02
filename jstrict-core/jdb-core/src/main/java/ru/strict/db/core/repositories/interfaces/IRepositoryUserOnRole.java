package ru.strict.db.core.repositories.interfaces;

import ru.strict.db.core.requests.DbSelectItem;
import ru.strict.models.UserOnRole;
import ru.strict.db.core.repositories.IRepositoryExtension;
import ru.strict.db.core.requests.DbRequests;
import ru.strict.db.core.requests.DbWhereItem;

import java.util.List;

public interface IRepositoryUserOnRole<ID> extends IRepositoryExtension<ID, UserOnRole<ID>> {
    default List<UserOnRole<ID>> readByUserId(ID userId){
        if(userId == null){
            throw new IllegalArgumentException("userId is NULL");
        }
        DbRequests requests = new DbRequests();
        requests.addWhere(new DbWhereItem(new DbSelectItem(getTable(), "userx_id"), userId, "="));

        return readAll(requests);
    }

    default List<UserOnRole<ID>> readByRoleId(ID roleId){
        if(roleId == null){
            throw new IllegalArgumentException("roleId is NULL");
        }
        DbRequests requests = new DbRequests();
        requests.addWhere(new DbWhereItem(new DbSelectItem(getTable(), "role_id"), roleId, "="));

        return readAll(requests);
    }
}
