package ru.strict.db.core.repositories.interfaces;

import ru.strict.db.core.common.SqlParameter;
import ru.strict.db.core.requests.components.SingleWhere;
import ru.strict.db.core.requests.components.SqlItem;
import ru.strict.models.UserOnRole;
import ru.strict.db.core.repositories.IExtensionRepository;
import ru.strict.validate.Validator;

import java.util.List;

public interface IUserOnRoleRepository<ID> extends IExtensionRepository<ID, UserOnRole<ID>> {
    default List<UserOnRole<ID>> readByUserId(ID userId) {
        Validator.isNull(userId, "userId").onThrow();

        SingleWhere where = new SingleWhere(
                new SqlItem(getTable(), "userx_id"),
                "=",
                new SqlParameter<>("userx_id", userId));

        return readAll(where);
    }

    default List<UserOnRole<ID>> readByRoleId(ID roleId) {
        Validator.isNull(roleId, "roleId").onThrow();

        SingleWhere where = new SingleWhere(
                new SqlItem(getTable(), "role_id"),
                "=",
                new SqlParameter<>("role_id", roleId));

        return readAll(where);
    }
}
