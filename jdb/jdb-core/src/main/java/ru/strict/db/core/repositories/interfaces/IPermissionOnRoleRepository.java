package ru.strict.db.core.repositories.interfaces;

import ru.strict.db.core.requests.DbSelectItem;
import ru.strict.models.IModel;
import ru.strict.models.PermissionOnRole;
import ru.strict.db.core.repositories.IExtensionRepository;
import ru.strict.db.core.requests.DbRequests;
import ru.strict.db.core.requests.DbWhereItem;
import ru.strict.validate.Validator;

import java.util.List;

public interface IPermissionOnRoleRepository<ID, PERMISSION extends IModel<Integer>>
        extends IExtensionRepository<ID, PermissionOnRole<ID, PERMISSION>> {
    default List<PermissionOnRole<ID, PERMISSION>> readByPermissionId(Integer permissionId) {
        Validator.isNull(permissionId, "permissionId").onThrow();
        DbRequests requests = new DbRequests();
        requests.addWhere(new DbWhereItem(new DbSelectItem(getTable(), "permission_id"), permissionId, "="));

        return readAll(requests);
    }

    default List<PermissionOnRole<ID, PERMISSION>> readByRoleId(ID roleId) {
        Validator.isNull(roleId, "roleId").onThrow();
        DbRequests requests = new DbRequests();
        requests.addWhere(new DbWhereItem(new DbSelectItem(getTable(), "role_id"), roleId, "="));

        return readAll(requests);
    }
}
