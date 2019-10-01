package ru.strict.db.core.repositories.interfaces;

import ru.strict.db.core.requests.DbSelectItem;
import ru.strict.models.City;
import ru.strict.db.core.repositories.IRepositoryNamed;
import ru.strict.db.core.requests.DbRequests;
import ru.strict.db.core.requests.DbWhereItem;

import java.util.List;

public interface IRepositoryCity<ID> extends IRepositoryNamed<ID, City<ID>> {
    default List<City<ID>> readByCountryId(ID countryId){
        if(countryId == null){
            throw new IllegalArgumentException("countryId is NULL");
        }
        DbRequests requests = new DbRequests();
        requests.addWhere(new DbWhereItem(new DbSelectItem(getTable(), "country_id"), countryId, "="));

        return readAll(requests);
    }
}
