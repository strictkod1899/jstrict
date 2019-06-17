package ru.strict.db.core.repositories.interfaces;

import ru.strict.db.core.requests.DbSelectItem;
import ru.strict.models.UserOnRole;
import ru.strict.db.core.repositories.IRepositoryExtension;
import ru.strict.db.core.requests.DbRequests;
import ru.strict.db.core.requests.DbWhereItem;

import java.util.List;

public interface IRepositoryUserOnRole<ID> extends IRepositoryExtension<ID, UserOnRole<ID>> {
    default List<UserOnRole<ID>> readByUserId(ID userId){
        DbRequests requests = new DbRequests();
        requests.addWhere(new DbWhereItem(new DbSelectItem(getTable(), "userx_id"), userId, "="));

        List<UserOnRole<ID>> result = readAll(requests);
        return result;
    }

    default List<UserOnRole<ID>> readByRoleId(ID roleId){
        DbRequests requests = new DbRequests();
        requests.addWhere(new DbWhereItem(new DbSelectItem(getTable(), "roleuser_id"), roleId, "="));

        List<UserOnRole<ID>> result = readAll(requests);
        return result;
    }
}
