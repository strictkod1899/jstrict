package ru.strict.db.core.repositories.interfaces;

import ru.strict.db.core.dto.DtoCity;
import ru.strict.db.core.repositories.IRepositoryExtension;
import ru.strict.db.core.requests.DbRequests;
import ru.strict.db.core.requests.DbWhereItem;

import java.util.List;

public interface IRepositoryCity<ID> extends IRepositoryExtension<ID, DtoCity<ID>> {
    default List<DtoCity<ID>> readByCountryId(ID countryId){
        DbRequests requests = new DbRequests();
        requests.addWhere(new DbWhereItem(getTableName(), "country_id", countryId, "="));

        List<DtoCity<ID>> result = readAll(requests);
        return result;
    }
}
