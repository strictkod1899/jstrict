package ru.strict.db.core.repositories.interfaces;

import ru.strict.db.core.requests.DbSelectItem;
import ru.strict.models.Profile;
import ru.strict.db.core.repositories.IRepositoryExtension;
import ru.strict.db.core.requests.DbRequests;
import ru.strict.db.core.requests.DbWhereIsNull;
import ru.strict.db.core.requests.DbWhereItem;

import java.util.List;

public interface IRepositoryProfile<ID, DTO extends Profile<ID>> extends IRepositoryExtension<ID, DTO> {
    default List<DTO> readByFio(String name, String surname, String middlename){
        DbRequests requests = new DbRequests();
        if(name != null) {
            requests.addWhere(new DbWhereItem(new DbSelectItem(getTable(), "name"), name, "="));
        }else{
            requests.addWhere(new DbWhereIsNull(new DbSelectItem(getTable(), "name")));
        }

        if(surname != null) {
            requests.addWhere(new DbWhereItem(new DbSelectItem(getTable(), "surname"), surname, "="));
        }else{
            requests.addWhere(new DbWhereIsNull(new DbSelectItem(getTable(), "surname")));
        }

        if(middlename != null) {
            requests.addWhere(new DbWhereItem(new DbSelectItem(getTable(), "middlename"), middlename, "="));
        }else{
            requests.addWhere(new DbWhereIsNull(new DbSelectItem(getTable(), "middlename")));
        }

        List<DTO> result = readAll(requests);
        return result;
    }

    default List<DTO> readByUserId(ID userId){
        DbRequests requests = new DbRequests();
        requests.addWhere(new DbWhereItem(new DbSelectItem(getTable(), "userx_id"), userId, "="));

        List<DTO> result = readAll(requests);
        return result;
    }
}
