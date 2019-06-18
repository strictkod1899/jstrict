package ru.strict.db.core.repositories.interfaces;

import ru.strict.db.core.requests.DbSelectItem;
import ru.strict.models.ServiceOnRole;
import ru.strict.db.core.repositories.IRepositoryExtension;
import ru.strict.db.core.requests.DbRequests;
import ru.strict.db.core.requests.DbWhereItem;

import java.util.List;

public interface IRepositoryServiceOnRole<ID, SERVICE> extends IRepositoryExtension<ID, ServiceOnRole<ID, SERVICE>> {
    default List<ServiceOnRole<ID, SERVICE>> readByServiceId(Integer serviceId){
        DbRequests requests = new DbRequests();
        requests.addWhere(new DbWhereItem(new DbSelectItem(getTable(), "service_id"), serviceId, "="));

        List<ServiceOnRole<ID, SERVICE>> result = readAll(requests);
        return result;
    }

    default List<ServiceOnRole<ID, SERVICE>> readByRoleId(ID roleId){
        DbRequests requests = new DbRequests();
        requests.addWhere(new DbWhereItem(new DbSelectItem(getTable(), "roleuser_id"), roleId, "="));

        List<ServiceOnRole<ID, SERVICE>> result = readAll(requests);
        return result;
    }
}
