package ru.strict.db.core.repositories.interfaces;

import ru.strict.db.core.requests.components.SingleWhere;
import ru.strict.db.core.requests.components.SqlItem;
import ru.strict.db.core.requests.components.Where;
import ru.strict.db.core.requests.components.WhereType;
import ru.strict.models.DetailsProfile;

import java.util.List;

public interface IDetailsProfileRepository<ID, T extends DetailsProfile<ID>> extends IProfileRepository<ID, T> {
    default List<T> readByFio(String name, String surname, String middlename) {
        Where.Builder where = Where.builder();
        if (name != null) {
            String nameWhere = SingleWhere.build(new SqlItem(getTable(), "name"), "=");
            where.addParameter("name", name);
            where.item(nameWhere);
        }

        if (surname != null) {
            String surnameWhere = SingleWhere.build(new SqlItem(getTable(), "surname"), "=");
            where.addParameter("surname", surname);
            where.item(WhereType.AND, surnameWhere);
        }

        if (middlename != null) {
            String middlenameWhere = SingleWhere.build(new SqlItem(getTable(), "middlename"), "=");
            where.addParameter("middlename", middlename);
            where.item(WhereType.AND, middlenameWhere);
        }

        return readAll(where.build());
    }
}
