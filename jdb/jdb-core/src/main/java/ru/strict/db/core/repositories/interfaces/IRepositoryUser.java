package ru.strict.db.core.repositories.interfaces;

import ru.strict.db.core.dto.DtoUserBase;
import ru.strict.db.core.repositories.IRepositoryNamed;

public interface IRepositoryUser<ID, DTO extends DtoUserBase> extends IRepositoryNamed<ID, DTO> {
    DTO readByEmail(String email);
}
