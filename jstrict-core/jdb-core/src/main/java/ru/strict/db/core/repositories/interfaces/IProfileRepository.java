package ru.strict.db.core.repositories.interfaces;

import ru.strict.db.core.requests.DbSelectItem;
import ru.strict.models.Profile;
import ru.strict.db.core.repositories.IExtensionRepository;
import ru.strict.db.core.requests.DbRequests;
import ru.strict.db.core.requests.DbWhereIsNull;
import ru.strict.db.core.requests.DbWhereItem;

import java.util.List;

public interface IProfileRepository<ID, T extends Profile<ID>> extends IExtensionRepository<ID, T> {
    default List<T> readBySurname(String name, String surname){
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

        return readAll(requests);
    }

    default List<T> readByUserId(ID userId){
        if(userId == null){
            throw new IllegalArgumentException("userId is NULL");
        }
        DbRequests requests = new DbRequests();
        requests.addWhere(new DbWhereItem(new DbSelectItem(getTable(), "userx_id"), userId, "="));

        return readAll(requests);
    }
}
