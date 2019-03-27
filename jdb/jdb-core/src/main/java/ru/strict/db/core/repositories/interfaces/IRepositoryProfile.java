package ru.strict.db.core.repositories.interfaces;

import ru.strict.db.core.dto.DtoProfile;
import ru.strict.db.core.repositories.IRepositoryExtension;
import ru.strict.db.core.requests.DbRequests;
import ru.strict.db.core.requests.DbWhereIsNull;
import ru.strict.db.core.requests.DbWhereItem;

import java.util.List;

public interface IRepositoryProfile<ID, DTO extends DtoProfile<ID>> extends IRepositoryExtension<ID, DTO> {
    default List<DTO> readByFio(String name, String surname, String middlename){
        DbRequests requests = new DbRequests();
        if(name != null) {
            requests.addWhere(new DbWhereItem(getTableName(), "name", name, "="));
        }else{
            requests.addWhere(new DbWhereIsNull(getTableName(), "name"));
        }

        if(surname != null) {
            requests.addWhere(new DbWhereItem(getTableName(), "surname", surname, "="));
        }else{
            requests.addWhere(new DbWhereIsNull(getTableName(), "surname"));
        }

        if(middlename != null) {
            requests.addWhere(new DbWhereItem(getTableName(), "middlename", middlename, "="));
        }else{
            requests.addWhere(new DbWhereIsNull(getTableName(), "middlename"));
        }

        List<DTO> result = readAll(requests);
        return result;
    }

    default List<DTO> readByUserId(ID userId){
        DbRequests requests = new DbRequests();
        requests.addWhere(new DbWhereItem(getTableName(), "userx_id", userId, "="));

        List<DTO> result = readAll(requests);
        return result;
    }
}
