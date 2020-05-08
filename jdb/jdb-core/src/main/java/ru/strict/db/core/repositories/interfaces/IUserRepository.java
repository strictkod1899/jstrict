package ru.strict.db.core.repositories.interfaces;

import ru.strict.db.core.requests.DbSelectItem;
import ru.strict.models.User;
import ru.strict.db.core.repositories.INamedRepository;
import ru.strict.db.core.requests.DbRequests;
import ru.strict.db.core.requests.DbWhereItem;
import ru.strict.validate.Validator;

public interface IUserRepository<ID, T extends User<ID>> extends INamedRepository<ID, T> {
    default T readByEmail(String email) {
        Validator.isEmptyOrNull(email, "email").onThrow();

        DbRequests requests = new DbRequests();
        requests.addWhere(new DbWhereItem(new DbSelectItem(getTable(), "email"), email, "="));

        return readAll(requests).stream().findFirst().orElse(null);
    }

    default boolean isDeleted(ID userId) {
        Validator.isNull(userId, "userId").onThrow();

        T user = read(userId);
        return user.isDeleted();
    }

    default boolean isBlocked(ID userId) {
        Validator.isNull(userId, "userId").onThrow();

        T user = read(userId);
        return user.isDeleted();
    }

    default boolean isConfirmEmail(ID userId) {
        Validator.isNull(userId, "userId").onThrow();

        T user = read(userId);
        return user.isDeleted();
    }
}
