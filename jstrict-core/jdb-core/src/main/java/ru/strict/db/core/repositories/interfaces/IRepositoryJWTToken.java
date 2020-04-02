package ru.strict.db.core.repositories.interfaces;

import ru.strict.db.core.requests.DbSelectItem;
import ru.strict.models.JWTToken;
import ru.strict.db.core.repositories.IRepositoryExtension;
import ru.strict.db.core.requests.DbRequests;
import ru.strict.db.core.requests.DbWhereItem;
import ru.strict.validate.ValidateBaseValue;

import java.util.List;

public interface IRepositoryJWTToken<ID> extends IRepositoryExtension<ID, JWTToken<ID>> {
    default JWTToken<ID> readByAccessToken(String accessToken){
        if(ValidateBaseValue.isEmptyOrNull(accessToken)){
            throw new IllegalArgumentException("accessToken is NULL");
        }
        DbRequests requests = new DbRequests();
        requests.addWhere(new DbWhereItem(new DbSelectItem(getTable(), "access_token"), accessToken, "="));

        return readAll(requests).stream().findFirst().orElse(null);
    }

    default JWTToken<ID> readByRefreshToken(String refreshToken){
        if(ValidateBaseValue.isEmptyOrNull(refreshToken)){
            throw new IllegalArgumentException("refreshToken is NULL");
        }
        DbRequests requests = new DbRequests();
        requests.addWhere(new DbWhereItem(new DbSelectItem(getTable(), "refresh_token"), refreshToken, "="));

        return readAll(requests).stream().findFirst().orElse(null);
    }

    default List<JWTToken<ID>> readByUserId(ID userId){
        if(userId == null){
            throw new IllegalArgumentException("userId is NULL");
        }
        DbRequests requests = new DbRequests();
        requests.addWhere(new DbWhereItem(new DbSelectItem(getTable(), "userx_id"), userId, "="));

        return readAll(requests);
    }
}
