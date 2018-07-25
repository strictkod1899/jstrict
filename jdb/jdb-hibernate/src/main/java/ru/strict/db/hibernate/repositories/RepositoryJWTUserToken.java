package ru.strict.db.hibernate.repositories;

import ru.strict.db.core.common.GenerateIdType;
import ru.strict.db.core.common.MapperDtoType;
import ru.strict.db.core.dto.DtoJWTUserToken;
import ru.strict.db.hibernate.connection.CreateConnectionHibernate;
import ru.strict.db.hibernate.entities.EntityJWTUserToken;
import ru.strict.db.hibernate.mappers.dto.MapperDtoFactory;

public class RepositoryJWTUserToken extends RepositoryHibernateBase<EntityJWTUserToken, DtoJWTUserToken> {

    private static final String[] COLUMNS_NAME = new String[] {"accessToken", "refreshToken", "expireTimeAccess", "expireTimeRefresh",
            "issuedAt", "issuer", "subject", "notBefore", "audience", "secret", "algorithm", "type", "userx_id", "roleuser_id"};

    public RepositoryJWTUserToken(CreateConnectionHibernate connectionSource, GenerateIdType isGenerateId) {
        super("token",
                COLUMNS_NAME,
                connectionSource,
                new MapperDtoFactory().instance(MapperDtoType.JWT_USER_TOKEN),
                isGenerateId);
    }

    @Override
    protected DtoJWTUserToken fill(DtoJWTUserToken dto){
        return dto;
    }

    @Override
    protected Class<EntityJWTUserToken> getEntityClass() {
        return EntityJWTUserToken.class;
    }
}
