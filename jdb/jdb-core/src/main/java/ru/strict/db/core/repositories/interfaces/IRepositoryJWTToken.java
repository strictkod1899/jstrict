package ru.strict.db.core.repositories.interfaces;

import ru.strict.db.core.dto.DtoJWTToken;
import ru.strict.db.core.repositories.IRepositoryExtension;

public interface IRepositoryJWTToken<ID> extends IRepositoryExtension<ID, DtoJWTToken<ID>> {
    DtoJWTToken<ID> readByAccessToken(String caption);
    DtoJWTToken<ID> readByRefreshToken(String caption);
}
