package ru.strict.db.core.repositories.interfaces;

import ru.strict.db.core.common.SqlParameter;
import ru.strict.db.core.requests.components.SingleWhere;
import ru.strict.db.core.requests.components.SqlItem;
import ru.strict.patterns.model.IModel;
import ru.strict.models.PermissionOnRole;
import ru.strict.db.core.repositories.IRepository;
import ru.strict.validate.Validator;

import java.util.List;

public interface IPermissionOnRoleRepository<ID, PERMISSION extends IModel<Integer>>
        extends IRepository<ID, PermissionOnRole<ID, PERMISSION>> {
    default List<PermissionOnRole<ID, PERMISSION>> readByPermissionId(Integer permissionId) {
        Validator.isNull(permissionId, "permissionId").onThrow();

        SingleWhere where = new SingleWhere(
                new SqlItem(getTable(), "permission_id"),
                "=",
                new SqlParameter<>("permission_id", permissionId));

        return readAll(where);
    }

    default List<PermissionOnRole<ID, PERMISSION>> readByRoleId(ID roleId) {
        Validator.isNull(roleId, "roleId").onThrow();

        SingleWhere where = new SingleWhere(
                new SqlItem(getTable(), "role_id"),
                "=",
                new SqlParameter<>("role_id", roleId));

        return readAll(where);
    }
}
