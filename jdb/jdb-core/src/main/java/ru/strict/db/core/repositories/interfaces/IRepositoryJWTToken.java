package ru.strict.db.core.repositories.interfaces;

import ru.strict.db.core.dto.DtoJWTToken;
import ru.strict.db.core.dto.DtoJWTUserToken;
import ru.strict.db.core.repositories.IRepositoryExtension;

public interface IRepositoryJWTToken<ID> extends IRepositoryExtension<ID, DtoJWTUserToken> {
    DtoJWTUserToken readByAccessToken(String caption);
    DtoJWTUserToken readByRefreshToken(String caption);
}
