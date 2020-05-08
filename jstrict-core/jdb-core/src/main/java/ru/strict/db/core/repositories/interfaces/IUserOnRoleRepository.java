package ru.strict.db.core.repositories.interfaces;

import ru.strict.db.core.requests.DbSelectItem;
import ru.strict.models.UserOnRole;
import ru.strict.db.core.repositories.IExtensionRepository;
import ru.strict.db.core.requests.DbRequests;
import ru.strict.db.core.requests.DbWhereItem;
import ru.strict.validate.Validator;

import java.util.List;

public interface IUserOnRoleRepository<ID> extends IExtensionRepository<ID, UserOnRole<ID>> {
    default List<UserOnRole<ID>> readByUserId(ID userId) {
        Validator.isNull(userId, "userId").onThrow();
        DbRequests requests = new DbRequests();
        requests.addWhere(new DbWhereItem(new DbSelectItem(getTable(), "userx_id"), userId, "="));

        return readAll(requests);
    }

    default List<UserOnRole<ID>> readByRoleId(ID roleId) {
        Validator.isNull(roleId, "roleId").onThrow();
        DbRequests requests = new DbRequests();
        requests.addWhere(new DbWhereItem(new DbSelectItem(getTable(), "role_id"), roleId, "="));

        return readAll(requests);
    }
}
