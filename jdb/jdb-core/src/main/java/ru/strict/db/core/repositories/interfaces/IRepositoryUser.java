package ru.strict.db.core.repositories.interfaces;

import ru.strict.db.core.dto.DtoUserBase;
import ru.strict.db.core.repositories.IRepositoryNamed;

public interface IRepositoryUser<ID, DTO extends DtoUserBase<ID>> extends IRepositoryNamed<ID, DTO> {
    DTO readByEmail(String email);

    default boolean isDeleted(ID id){
        DTO user = read(id);
        return user.isDeleted();
    }

    default boolean isBlocked(ID id){
        DTO user = read(id);
        return user.isDeleted();
    }

    default boolean isConfirmEmail(ID id){
        DTO user = read(id);
        return user.isDeleted();
    }
}
