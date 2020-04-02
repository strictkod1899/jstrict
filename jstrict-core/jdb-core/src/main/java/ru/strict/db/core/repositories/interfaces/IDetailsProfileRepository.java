package ru.strict.db.core.repositories.interfaces;

import ru.strict.db.core.requests.DbRequests;
import ru.strict.db.core.requests.DbSelectItem;
import ru.strict.db.core.requests.DbWhereIsNull;
import ru.strict.db.core.requests.DbWhereItem;
import ru.strict.models.ProfileDetails;

import java.util.List;

public interface IDetailsProfileRepository<ID, T extends ProfileDetails<ID>> extends IProfileRepository<ID, T> {
    default List<T> readByFio(String name, String surname, String middlename){
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

        return readAll(requests);
    }
}
