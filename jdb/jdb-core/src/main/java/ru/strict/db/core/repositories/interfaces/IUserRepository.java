package ru.strict.db.core.repositories.interfaces;

import ru.strict.db.core.common.SqlParameter;
import ru.strict.db.core.requests.components.SingleWhere;
import ru.strict.db.core.requests.components.SqlItem;
import ru.strict.models.User;
import ru.strict.db.core.repositories.INamedRepository;
import ru.strict.validate.Validator;

public interface IUserRepository<ID, T extends User<ID>> extends INamedRepository<ID, T> {
    default T readByEmail(String email) {
        Validator.isEmptyOrNull(email, "email").onThrow();

        SingleWhere where = new SingleWhere(
                new SqlItem(getTable(), "email"),
                "=",
                new SqlParameter<>("email", email));

        return readAll(where).stream().findFirst().orElse(null);
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
