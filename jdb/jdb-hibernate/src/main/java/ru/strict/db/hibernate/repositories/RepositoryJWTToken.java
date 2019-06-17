package ru.strict.db.hibernate.repositories;

import ru.strict.db.core.common.GenerateIdType;

import ru.strict.db.core.requests.DbTable;
import ru.strict.models.JWTToken;
import ru.strict.db.core.repositories.interfaces.IRepositoryJWTToken;
import ru.strict.db.hibernate.connection.CreateConnectionHibernate;
import ru.strict.db.hibernate.entities.EntityJWTToken;
import ru.strict.db.hibernate.mappers.dto.MapperDtoFactory;
import ru.strict.utils.UtilClass;

public class RepositoryJWTToken
        extends RepositoryHibernateBase<Long, EntityJWTToken, JWTToken<Long>>
        implements IRepositoryJWTToken<Long> {

    private static final String[] COLUMNS_NAME = new String[] {"access_token", "refresh_token",
            "expire_time_access", "expire_time_refresh", "issued_at", "issuer", "subject", "not_before",
            "audience", "secret", "algorithm", "type", "userx_id"};

    public RepositoryJWTToken(CreateConnectionHibernate connectionSource, GenerateIdType generateIdType) {
        super(new DbTable("token", "tkn"),
                COLUMNS_NAME,
                connectionSource,
                new MapperDtoFactory().instance(UtilClass.castClass(EntityJWTToken.class), UtilClass.castClass(JWTToken.class)),
                generateIdType);
    }

    @Override
    protected JWTToken<Long> fill(JWTToken dto){
        return dto;
    }

    @Override
    protected Class<EntityJWTToken> getEntityClass() {
        return UtilClass.castClass(EntityJWTToken.class);
    }

    @Override
    protected Class getThisClass() {
        return this.getClass();
    }
}
