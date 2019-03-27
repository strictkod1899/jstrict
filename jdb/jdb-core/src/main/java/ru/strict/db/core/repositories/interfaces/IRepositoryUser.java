package ru.strict.db.core.repositories.interfaces;

import ru.strict.db.core.dto.DtoUserBase;
import ru.strict.db.core.repositories.IRepositoryNamed;
import ru.strict.db.core.requests.DbRequests;
import ru.strict.db.core.requests.DbWhereItem;

public interface IRepositoryUser<ID, DTO extends DtoUserBase<ID>> extends IRepositoryNamed<ID, DTO> {
    default DTO readByEmail(String email){
        DbRequests requests = new DbRequests();
        requests.addWhere(new DbWhereItem(getTableName(), "email", email, "="));

        DTO result = readAll(requests).stream().findFirst().orElse(null);
        return result;
    }

    default boolean isDeleted(ID id){
        DTO user = read(id);
        return user.isDeleted();
    }

    default boolean isBlocked(ID id){
        DTO user = read(id);
        return user.isDeleted();
    }

    default boolean isConfirmEmail(ID id){
        DTO user = read(id);
        return user.isDeleted();
    }
}
