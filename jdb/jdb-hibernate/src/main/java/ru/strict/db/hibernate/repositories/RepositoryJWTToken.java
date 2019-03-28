package ru.strict.db.hibernate.repositories;

import org.hibernate.Session;
import ru.strict.db.core.common.GenerateIdType;

import ru.strict.db.core.dto.DtoJWTToken;
import ru.strict.db.core.repositories.interfaces.IRepositoryJWTToken;
import ru.strict.db.core.requests.DbRequests;
import ru.strict.db.core.requests.DbWhereItem;
import ru.strict.db.hibernate.connection.CreateConnectionHibernate;
import ru.strict.db.hibernate.entities.EntityJWTToken;
import ru.strict.db.hibernate.mappers.dto.MapperDtoFactory;
import ru.strict.utils.UtilClass;
import ru.strict.validates.ValidateBaseValue;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.List;

public class RepositoryJWTToken<ID extends Serializable>
        extends RepositoryHibernateBase<ID, EntityJWTToken<ID>, DtoJWTToken<ID>>
        implements IRepositoryJWTToken<ID> {

    private static final String[] COLUMNS_NAME = new String[] {"access_token", "refresh_token",
            "expire_time_access", "expire_time_refresh", "issued_at", "issuer", "subject", "not_before",
            "audience", "secret", "algorithm", "type", "userx_id"};

    public RepositoryJWTToken(CreateConnectionHibernate connectionSource, GenerateIdType generateIdType) {
        super("token",
                COLUMNS_NAME,
                connectionSource,
                new MapperDtoFactory<ID>().instance(UtilClass.castClass(EntityJWTToken.class), UtilClass.castClass(DtoJWTToken.class)),
                generateIdType);
    }

    @Override
    protected DtoJWTToken<ID> fill(DtoJWTToken dto){
        return dto;
    }

    @Override
    protected Class<EntityJWTToken<ID>> getEntityClass() {
        return UtilClass.castClass(EntityJWTToken.class);
    }

    @Override
    protected Class getThisClass() {
        return this.getClass();
    }
}
