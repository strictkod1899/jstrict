package ru.strict.db.core.repositories.interfaces;

import ru.strict.db.core.requests.DbSelectItem;
import ru.strict.models.User;
import ru.strict.db.core.repositories.INamedRepository;
import ru.strict.db.core.requests.DbRequests;
import ru.strict.db.core.requests.DbWhereItem;
import ru.strict.validate.ValidateBaseValue;

public interface IUserRepository<ID, T extends User<ID>> extends INamedRepository<ID, T> {
    default T readByEmail(String email){
        if(ValidateBaseValue.isEmptyOrNull(email)){
            throw new IllegalArgumentException("email is NULL");
        }
        DbRequests requests = new DbRequests();
        requests.addWhere(new DbWhereItem(new DbSelectItem(getTable(), "email"), email, "="));

        return readAll(requests).stream().findFirst().orElse(null);
    }

    default boolean isDeleted(ID id){
        if(id == null){
            throw new IllegalArgumentException("id is NULL");
        }
        T user = read(id);
        return user.isDeleted();
    }

    default boolean isBlocked(ID id){
        if(id == null){
            throw new IllegalArgumentException("id is NULL");
        }
        T user = read(id);
        return user.isDeleted();
    }

    default boolean isConfirmEmail(ID id){
        if(id == null){
            throw new IllegalArgumentException("id is NULL");
        }
        T user = read(id);
        return user.isDeleted();
    }
}
