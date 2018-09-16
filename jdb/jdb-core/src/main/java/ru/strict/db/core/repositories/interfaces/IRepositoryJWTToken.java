package ru.strict.db.core.repositories.interfaces;

import ru.strict.db.core.dto.DtoJWTToken;
import ru.strict.db.core.repositories.IRepositoryExtension;

public interface IRepositoryJWTToken<ID> extends IRepositoryExtension<ID, DtoJWTToken> {
    DtoJWTToken readByAccessToken(String caption);
    DtoJWTToken readByRefreshToken(String caption);
}
