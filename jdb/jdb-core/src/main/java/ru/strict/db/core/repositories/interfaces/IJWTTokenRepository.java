package ru.strict.db.core.repositories.interfaces;

import ru.strict.db.core.repositories.IExtensionRepository;
import ru.strict.db.core.requests.DbSelectItem;
import ru.strict.models.JWTToken;
import ru.strict.db.core.requests.DbRequests;
import ru.strict.db.core.requests.DbWhereItem;
import ru.strict.validate.ValidateBaseValue;
import ru.strict.validate.Validator;

import java.util.List;

public interface IJWTTokenRepository<ID> extends IExtensionRepository<ID, JWTToken<ID>> {
    default JWTToken<ID> readByAccessToken(String accessToken) {
        Validator.isEmptyOrNull(accessToken, "accessToken").onThrow();
        DbRequests requests = new DbRequests();
        requests.addWhere(new DbWhereItem(new DbSelectItem(getTable(), "access_token"), accessToken, "="));

        return readAll(requests).stream().findFirst().orElse(null);
    }

    default JWTToken<ID> readByRefreshToken(String refreshToken) {
        Validator.isEmptyOrNull(refreshToken, "refreshToken").onThrow();
        DbRequests requests = new DbRequests();
        requests.addWhere(new DbWhereItem(new DbSelectItem(getTable(), "refresh_token"), refreshToken, "="));

        return readAll(requests).stream().findFirst().orElse(null);
    }

    default List<JWTToken<ID>> readByUserId(ID userId) {
        Validator.isNull(userId, "userId").onThrow();
        DbRequests requests = new DbRequests();
        requests.addWhere(new DbWhereItem(new DbSelectItem(getTable(), "userx_id"), userId, "="));

        return readAll(requests);
    }
}
