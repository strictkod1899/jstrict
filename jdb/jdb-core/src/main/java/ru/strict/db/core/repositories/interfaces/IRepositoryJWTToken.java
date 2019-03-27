package ru.strict.db.core.repositories.interfaces;

import ru.strict.db.core.dto.DtoJWTToken;
import ru.strict.db.core.repositories.IRepositoryExtension;
import ru.strict.db.core.requests.DbRequests;
import ru.strict.db.core.requests.DbWhereItem;

public interface IRepositoryJWTToken<ID> extends IRepositoryExtension<ID, DtoJWTToken<ID>> {
    default DtoJWTToken<ID> readByAccessToken(String caption){
        DbRequests requests = new DbRequests();
        requests.addWhere(new DbWhereItem(getTableName(), "access_token", caption, "="));

        DtoJWTToken<ID> result = readAll(requests).stream().findFirst().orElse(null);
        return result;
    }

    default DtoJWTToken<ID> readByRefreshToken(String caption){
        DbRequests requests = new DbRequests();
        requests.addWhere(new DbWhereItem(getTableName(), "refresh_token", caption, "="));

        DtoJWTToken<ID> result = readAll(requests).stream().findFirst().orElse(null);
        return result;
    }
}
