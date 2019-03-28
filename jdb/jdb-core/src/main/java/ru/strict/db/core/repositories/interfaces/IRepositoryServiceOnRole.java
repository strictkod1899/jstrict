package ru.strict.db.core.repositories.interfaces;

import ru.strict.db.core.dto.DtoServiceOnRole;
import ru.strict.db.core.repositories.IRepositoryExtension;
import ru.strict.db.core.requests.DbRequests;
import ru.strict.db.core.requests.DbWhereItem;

import java.util.List;

public interface IRepositoryServiceOnRole<ID, SERVICE> extends IRepositoryExtension<ID, DtoServiceOnRole<ID, SERVICE>> {
    default List<DtoServiceOnRole<ID, SERVICE>> readByServiceId(ID serviceId){
        DbRequests requests = new DbRequests();
        requests.addWhere(new DbWhereItem(getTableName(), "service_id", serviceId, "="));

        List<DtoServiceOnRole<ID, SERVICE>> result = readAll(requests);
        return result;
    }

    default List<DtoServiceOnRole<ID, SERVICE>> readByRoleId(ID roleId){
        DbRequests requests = new DbRequests();
        requests.addWhere(new DbWhereItem(getTableName(), "roleuser_id", roleId, "="));

        List<DtoServiceOnRole<ID, SERVICE>> result = readAll(requests);
        return result;
    }
}
