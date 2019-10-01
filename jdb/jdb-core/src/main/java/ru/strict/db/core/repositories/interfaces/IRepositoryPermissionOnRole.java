package ru.strict.db.core.repositories.interfaces;

import ru.strict.db.core.requests.DbSelectItem;
import ru.strict.models.PermissionOnRole;
import ru.strict.db.core.repositories.IRepositoryExtension;
import ru.strict.db.core.requests.DbRequests;
import ru.strict.db.core.requests.DbWhereItem;

import java.util.List;

public interface IRepositoryPermissionOnRole<ID, PERMISSION> extends IRepositoryExtension<ID, PermissionOnRole<ID, PERMISSION>> {
    default List<PermissionOnRole<ID, PERMISSION>> readByPermissionId(Integer permissionId){
        if(permissionId == null){
            throw new IllegalArgumentException("permissionId is NULL");
        }
        DbRequests requests = new DbRequests();
        requests.addWhere(new DbWhereItem(new DbSelectItem(getTable(), "permission_id"), permissionId, "="));

        return readAll(requests);
    }

    default List<PermissionOnRole<ID, PERMISSION>> readByRoleId(ID roleId){
        if(roleId == null){
            throw new IllegalArgumentException("roleId is NULL");
        }
        DbRequests requests = new DbRequests();
        requests.addWhere(new DbWhereItem(new DbSelectItem(getTable(), "role_id"), roleId, "="));

        return readAll(requests);
    }
}
