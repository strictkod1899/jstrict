package ru.strict.db.hibernate.repositories;

import ru.strict.db.core.common.GenerateIdType;
import ru.strict.db.core.common.MapperDtoType;
import ru.strict.db.core.dto.DtoUserToken;
import ru.strict.db.hibernate.connection.CreateConnectionHibernate;
import ru.strict.db.hibernate.entities.EntityUser;
import ru.strict.db.hibernate.mappers.dto.MapperDtoFactory;

import java.io.Serializable;

public class RepositoryUserFillToken<ID extends Serializable> extends RepositoryUser<ID, DtoUserToken<ID>> {

    public RepositoryUserFillToken(CreateConnectionHibernate connectionSource,
                          GenerateIdType generateIdType) {
        super(connectionSource,
                new MapperDtoFactory<ID, EntityUser<ID>, DtoUserToken<ID>>().instance(MapperDtoType.USER_TOKEN),
                generateIdType);
    }

    @Override
    protected Class getThisClass() {
        return this.getClass();
    }
}
