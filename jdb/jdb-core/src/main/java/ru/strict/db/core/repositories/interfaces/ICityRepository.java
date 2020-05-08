package ru.strict.db.core.repositories.interfaces;

import ru.strict.db.core.requests.DbSelectItem;
import ru.strict.models.City;
import ru.strict.db.core.repositories.INamedRepository;
import ru.strict.db.core.requests.DbRequests;
import ru.strict.db.core.requests.DbWhereItem;
import ru.strict.validate.Validator;

import java.util.List;

public interface ICityRepository<ID> extends INamedRepository<ID, City<ID>> {
    default List<City<ID>> readByCountryId(ID countryId) {
        Validator.isNull(countryId, "countryId").onThrow();

        DbRequests requests = new DbRequests();
        requests.addWhere(new DbWhereItem(new DbSelectItem(getTable(), "country_id"), countryId, "="));

        return readAll(requests);
    }
}
