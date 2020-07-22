package ru.strict.db.core.repositories.interfaces;

import ru.strict.db.core.common.SqlParameter;
import ru.strict.db.core.requests.components.SingleWhere;
import ru.strict.db.core.requests.components.SqlItem;
import ru.strict.db.core.requests.components.Where;
import ru.strict.db.core.requests.components.WhereType;
import ru.strict.models.Profile;
import ru.strict.db.core.repositories.IRepository;
import ru.strict.validate.Validator;

import java.util.List;

public interface IProfileRepository<ID, T extends Profile<ID>> extends IRepository<ID, T> {
    default List<T> readBySurname(String name, String surname) {
        Where.Builder where = Where.builder();

        if (name != null) {
            String nameWhere = SingleWhere.sql(new SqlItem(getTable(), "name"), "=");
            where.addParameter("name", name);
            where.item(nameWhere);
        }

        if (surname != null) {
            String surnameWhere = SingleWhere.sql(new SqlItem(getTable(), "surname"), "=");
            where.addParameter("surname", surname);
            where.item(WhereType.AND, surnameWhere);
        }

        return readAll(where.build());
    }

    default List<T> readByUserId(ID userId) {
        Validator.isNull(userId, "userId").onThrow();

        SingleWhere where = new SingleWhere(
                new SqlItem(getTable(), "userx_id"),
                "=",
                new SqlParameter<>("userx_id", userId));

        return readAll(where);
    }
}
