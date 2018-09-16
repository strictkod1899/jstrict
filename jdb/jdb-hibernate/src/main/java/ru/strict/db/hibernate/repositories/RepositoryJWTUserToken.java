package ru.strict.db.hibernate.repositories;

import org.hibernate.Session;
import ru.strict.db.core.common.GenerateIdType;
import ru.strict.db.core.common.MapperDtoType;
import ru.strict.db.core.dto.DtoJWTUserToken;
import ru.strict.db.core.repositories.interfaces.IRepositoryJWTToken;
import ru.strict.db.hibernate.connection.CreateConnectionHibernate;
import ru.strict.db.hibernate.entities.EntityJWTUserToken;
import ru.strict.db.hibernate.mappers.dto.MapperDtoFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.UUID;

public class RepositoryJWTUserToken
        extends RepositoryHibernateBase<EntityJWTUserToken, DtoJWTUserToken>
        implements IRepositoryJWTToken<UUID> {

    private static final String[] COLUMNS_NAME = new String[] {"accessToken", "refreshToken", "expireTimeAccess", "expireTimeRefresh",
            "issuedAt", "issuer", "subject", "notBefore", "audience", "secret", "algorithm", "type", "userx_id", "roleuser_id"};

    public RepositoryJWTUserToken(CreateConnectionHibernate connectionSource, GenerateIdType generateIdType) {
        super("token",
                COLUMNS_NAME,
                connectionSource,
                new MapperDtoFactory().instance(MapperDtoType.JWT_USER_TOKEN),
                generateIdType);
    }

    @Override
    protected DtoJWTUserToken fill(DtoJWTUserToken dto){
        return dto;
    }

    @Override
    protected Class<EntityJWTUserToken> getEntityClass() {
        return EntityJWTUserToken.class;
    }

    @Override
    protected Class getThisClass() {
        return this.getClass();
    }

    @Override
    public DtoJWTUserToken readByAccessToken(String caption) {
        DtoJWTUserToken result = null;
        try(Session session = createConnection()){
            session.beginTransaction();
            EntityManagerFactory entityManagerFactory = session.getEntityManagerFactory();
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<EntityJWTUserToken> criteriaEntity = criteriaBuilder.createQuery(getEntityClass());
            Root<EntityJWTUserToken> criteriaRoot = criteriaEntity.from(getEntityClass());
            criteriaEntity.select(criteriaRoot);
            criteriaEntity.where(criteriaBuilder.equal(criteriaRoot.get("accessToken"), caption));
            EntityJWTUserToken entity = entityManager.createQuery(criteriaEntity)
                    .getResultStream()
                    .findFirst()
                    .orElse(null);
            result = getDtoMapper().map(entity);

            session.getTransaction().commit();
        }
        return result;
    }

    @Override
    public DtoJWTUserToken readByRefreshToken(String caption) {
        DtoJWTUserToken result = null;
        try(Session session = createConnection()){
            session.beginTransaction();
            EntityManagerFactory entityManagerFactory = session.getEntityManagerFactory();
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<EntityJWTUserToken> criteriaEntity = criteriaBuilder.createQuery(getEntityClass());
            Root<EntityJWTUserToken> criteriaRoot = criteriaEntity.from(getEntityClass());
            criteriaEntity.select(criteriaRoot);
            criteriaEntity.where(criteriaBuilder.equal(criteriaRoot.get("refreshToken"), caption));
            EntityJWTUserToken entity = entityManager.createQuery(criteriaEntity)
                    .getResultStream()
                    .findFirst()
                    .orElse(null);
            result = getDtoMapper().map(entity);

            session.getTransaction().commit();
        }
        return result;
    }
}
