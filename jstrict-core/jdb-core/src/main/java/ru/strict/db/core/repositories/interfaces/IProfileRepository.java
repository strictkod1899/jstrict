package ru.strict.db.core.repositories.interfaces;

import ru.strict.db.core.requests.DbSelectItem;
import ru.strict.models.Profile;
import ru.strict.db.core.repositories.IExtensionRepository;
import ru.strict.db.core.requests.DbRequests;
import ru.strict.db.core.requests.DbWhereItem;
import ru.strict.validate.Validator;

import java.util.List;

public interface IProfileRepository<ID, T extends Profile<ID>> extends IExtensionRepository<ID, T> {
    default List<T> readBySurname(String name, String surname) {
        DbRequests requests = new DbRequests();
        if (name != null) {
            requests.addWhere(new DbWhereItem(new DbSelectItem(getTable(), "name"), name, "="));
        }

        if (surname != null) {
            requests.addWhere(new DbWhereItem(new DbSelectItem(getTable(), "surname"), surname, "="));
        }

        return readAll(requests);
    }

    default List<T> readByUserId(ID userId) {
        Validator.isNull(userId, "userId").onThrow();
        DbRequests requests = new DbRequests();
        requests.addWhere(new DbWhereItem(new DbSelectItem(getTable(), "userx_id"), userId, "="));

        return readAll(requests);
    }
}
