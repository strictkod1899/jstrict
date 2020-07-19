package ru.strict.db.core.repositories.interfaces;

import ru.strict.db.core.common.SqlParameter;
import ru.strict.db.core.repositories.IRepository;
import ru.strict.db.core.requests.components.SingleWhere;
import ru.strict.db.core.requests.components.SqlItem;
import ru.strict.models.JWTToken;
import ru.strict.validate.Validator;

import java.util.List;

public interface IJWTTokenRepository<ID> extends IRepository<ID, JWTToken<ID>> {
    default JWTToken<ID> readByAccessToken(String accessToken) {
        Validator.isEmptyOrNull(accessToken, "accessToken").onThrow();

        SingleWhere where = new SingleWhere(
                new SqlItem(getTable(), "access_token"),
                "=",
                new SqlParameter<>("access_token", accessToken));

        return readAll(where).stream().findFirst().orElse(null);
    }

    default JWTToken<ID> readByRefreshToken(String refreshToken) {
        Validator.isEmptyOrNull(refreshToken, "refreshToken").onThrow();

        SingleWhere where = new SingleWhere(
                new SqlItem(getTable(), "refresh_token"),
                "=",
                new SqlParameter<>("refresh_token", refreshToken));

        return readAll(where).stream().findFirst().orElse(null);
    }

    default List<JWTToken<ID>> readByUserId(ID userId) {
        Validator.isNull(userId, "userId").onThrow();

        SingleWhere where = new SingleWhere(
                new SqlItem(getTable(), "userx_id"),
                "=",
                new SqlParameter<>("userx_id", userId));

        return readAll(where);
    }
}
