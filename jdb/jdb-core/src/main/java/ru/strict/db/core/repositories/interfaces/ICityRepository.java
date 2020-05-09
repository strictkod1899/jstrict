package ru.strict.db.core.repositories.interfaces;

import ru.strict.db.core.common.SqlParameter;
import ru.strict.db.core.repositories.DefaultColumns;
import ru.strict.db.core.requests.components.SingleWhere;
import ru.strict.db.core.requests.components.SqlItem;
import ru.strict.models.City;
import ru.strict.db.core.repositories.INamedRepository;
import ru.strict.validate.Validator;

import java.util.List;

public interface ICityRepository<ID> extends INamedRepository<ID, City<ID>> {
    default List<City<ID>> readByCountryId(ID countryId) {
        Validator.isNull(countryId, "countryId").onThrow();

        SingleWhere where = new SingleWhere(
                new SqlItem(getTable(), DefaultColumns.CITY.get(1)),
                "=",
                new SqlParameter<>(0, DefaultColumns.CITY.get(1), countryId));

        return readAll(where);
    }
}
