package ru.strict.db.hibernate.repositories;

import ru.strict.db.core.common.GenerateIdType;
import ru.strict.db.core.common.MapperDtoType;
import ru.strict.db.core.dto.DtoUserToken;
import ru.strict.db.core.mappers.dto.MapperDtoBase;
import ru.strict.db.hibernate.connection.CreateConnectionHibernate;
import ru.strict.db.hibernate.entities.EntityUser;
import ru.strict.db.hibernate.mappers.dto.MapperDtoFactory;

public class RepositoryUserFillToken<ID> extends RepositoryUser<DtoUserToken> {

    public RepositoryUserFillToken(CreateConnectionHibernate connectionSource,
                          MapperDtoBase<EntityUser, DtoUserToken> dtoMapper,
                          GenerateIdType isGenerateId) {
        super(connectionSource,
                new MapperDtoFactory().instance(MapperDtoType.USER_TOKEN),
                isGenerateId);
    }

    @Override
    protected Class getThisClass() {
        return this.getClass();
    }
}
