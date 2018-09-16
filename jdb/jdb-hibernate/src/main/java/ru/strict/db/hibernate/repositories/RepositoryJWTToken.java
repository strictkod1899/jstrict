package ru.strict.db.hibernate.repositories;

import ru.strict.db.core.common.GenerateIdType;
import ru.strict.db.core.common.MapperDtoType;
import ru.strict.db.core.dto.DtoJWTToken;
import ru.strict.db.hibernate.connection.CreateConnectionHibernate;
import ru.strict.db.hibernate.entities.EntityJWTToken;
import ru.strict.db.hibernate.mappers.dto.MapperDtoFactory;

public class RepositoryJWTToken extends RepositoryHibernateBase<EntityJWTToken, DtoJWTToken> {

    private static final String[] COLUMNS_NAME = new String[] {"accessToken", "refreshToken", "expireTimeAccess", "expireTimeRefresh",
            "issuedAt", "issuer", "subject", "notBefore", "audience", "secret", "algorithm", "type"};

    public RepositoryJWTToken(CreateConnectionHibernate connectionSource, GenerateIdType generateIdType) {
        super("token",
                COLUMNS_NAME,
                connectionSource,
                new MapperDtoFactory().instance(MapperDtoType.JWT_TOKEN),
                generateIdType);
    }

    @Override
    protected DtoJWTToken fill(DtoJWTToken dto){
        return dto;
    }

    @Override
    protected Class<EntityJWTToken> getEntityClass() {
        return EntityJWTToken.class;
    }

    @Override
    protected Class getThisClass() {
        return this.getClass();
    }
}
