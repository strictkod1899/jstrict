package ru.strict.db.core.repositories.interfaces;

import ru.strict.db.core.dto.DtoJWTToken;
import ru.strict.db.core.repositories.IRepositoryExtension;
import ru.strict.db.core.requests.DbRequests;
import ru.strict.db.core.requests.DbWhereItem;
import ru.strict.validates.ValidateBaseValue;

public interface IRepositoryJWTToken<ID> extends IRepositoryExtension<ID, DtoJWTToken<ID>> {
    default DtoJWTToken<ID> readByAccessToken(String accessToken){
        if(ValidateBaseValue.isEmptyOrNull(accessToken)){
            throw new NullPointerException("accessToken for read is NULL");
        }
        DbRequests requests = new DbRequests();
        requests.addWhere(new DbWhereItem(getTableName(), "access_token", accessToken, "="));

        DtoJWTToken<ID> result = readAll(requests).stream().findFirst().orElse(null);
        return result;
    }

    default DtoJWTToken<ID> readByRefreshToken(String refreshToken){
        if(ValidateBaseValue.isEmptyOrNull(refreshToken)){
            throw new NullPointerException("refreshToken for read is NULL");
        }
        DbRequests requests = new DbRequests();
        requests.addWhere(new DbWhereItem(getTableName(), "refresh_token", refreshToken, "="));

        DtoJWTToken<ID> result = readAll(requests).stream().findFirst().orElse(null);
        return result;
    }
}
