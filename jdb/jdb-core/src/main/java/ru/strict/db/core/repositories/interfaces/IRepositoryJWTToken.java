package ru.strict.db.core.repositories.interfaces;

import ru.strict.db.core.requests.DbSelectItem;
import ru.strict.models.JWTToken;
import ru.strict.db.core.repositories.IRepositoryExtension;
import ru.strict.db.core.requests.DbRequests;
import ru.strict.db.core.requests.DbWhereItem;
import ru.strict.validates.ValidateBaseValue;

import java.util.List;

public interface IRepositoryJWTToken<ID> extends IRepositoryExtension<ID, JWTToken<ID>> {
    default JWTToken<ID> readByAccessToken(String accessToken){
        if(ValidateBaseValue.isEmptyOrNull(accessToken)){
            throw new IllegalArgumentException("accessToken for read is NULL");
        }
        DbRequests requests = new DbRequests();
        requests.addWhere(new DbWhereItem(new DbSelectItem(getTable(), "access_token"), accessToken, "="));

        JWTToken<ID> result = readAll(requests).stream().findFirst().orElse(null);
        return result;
    }

    default JWTToken<ID> readByRefreshToken(String refreshToken){
        if(ValidateBaseValue.isEmptyOrNull(refreshToken)){
            throw new IllegalArgumentException("refreshToken for read is NULL");
        }
        DbRequests requests = new DbRequests();
        requests.addWhere(new DbWhereItem(new DbSelectItem(getTable(), "refresh_token"), refreshToken, "="));

        JWTToken<ID> result = readAll(requests).stream().findFirst().orElse(null);
        return result;
    }

    default List<JWTToken<ID>> readByUserId(ID userId){
        if(userId == null){
            throw new IllegalArgumentException("userId for read is NULL");
        }
        DbRequests requests = new DbRequests();
        requests.addWhere(new DbWhereItem(new DbSelectItem(getTable(), "userx_id"), userId, "="));

        List<JWTToken<ID>> result = readAll(requests);
        return result;
    }
}
