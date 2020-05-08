package ru.strict.db.core.repositories.interfaces;

import ru.strict.db.core.requests.DbRequests;
import ru.strict.db.core.requests.DbSelectItem;
import ru.strict.db.core.requests.DbWhereItem;
import ru.strict.models.DetailsProfile;

import java.util.List;

public interface IDetailsProfileRepository<ID, T extends DetailsProfile<ID>> extends IProfileRepository<ID, T> {
    default List<T> readByFio(String name, String surname, String middlename) {
        DbRequests requests = new DbRequests();
        if (name != null) {
            requests.addWhere(new DbWhereItem(new DbSelectItem(getTable(), "name"), name, "="));
        }

        if (surname != null) {
            requests.addWhere(new DbWhereItem(new DbSelectItem(getTable(), "surname"), surname, "="));
        }

        if (middlename != null) {
            requests.addWhere(new DbWhereItem(new DbSelectItem(getTable(), "middlename"), middlename, "="));
        }

        return readAll(requests);
    }
}
