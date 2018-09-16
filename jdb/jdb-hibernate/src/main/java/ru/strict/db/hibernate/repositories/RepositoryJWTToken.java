package ru.strict.db.hibernate.repositories;

import org.hibernate.Session;
import ru.strict.db.core.common.GenerateIdType;
import ru.strict.db.core.common.MapperDtoType;
import ru.strict.db.core.dto.DtoJWTToken;
import ru.strict.db.core.repositories.interfaces.IRepositoryJWTToken;
import ru.strict.db.hibernate.connection.CreateConnectionHibernate;
import ru.strict.db.hibernate.entities.EntityJWTToken;
import ru.strict.db.hibernate.mappers.dto.MapperDtoFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.UUID;

public class RepositoryJWTToken extends RepositoryHibernateBase<EntityJWTToken, DtoJWTToken>
        implements IRepositoryJWTToken<UUID> {

    private static final String[] COLUMNS_NAME = new String[] {"accessToken", "refreshToken",
            "expireTimeAccess", "expireTimeRefresh", "issuedAt", "issuer", "subject", "notBefore",
            "audience", "secret", "algorithm", "type", "userx_id", "roleuser_id"};

    public RepositoryJWTToken(CreateConnectionHibernate connectionSource, GenerateIdType generateIdType) {
        super("token",
                COLUMNS_NAME,
                connectionSource,
                new MapperDtoFactory().instance(MapperDtoType.JWT_TOKEN),
                generateIdType);
    }

    @Override
    public DtoJWTToken readByAccessToken(String caption) {
        DtoJWTToken result = null;
        try(Session session = createConnection()){
            session.beginTransaction();
            EntityManagerFactory entityManagerFactory = session.getEntityManagerFactory();
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<EntityJWTToken> criteriaEntity = criteriaBuilder.createQuery(getEntityClass());
            Root<EntityJWTToken> criteriaRoot = criteriaEntity.from(getEntityClass());
            criteriaEntity.select(criteriaRoot);
            criteriaEntity.where(criteriaBuilder.equal(criteriaRoot.get("accessToken"), caption));
            EntityJWTToken entity = entityManager.createQuery(criteriaEntity)
                    .getResultStream()
                    .findFirst()
                    .orElse(null);
            result = getDtoMapper().map(entity);

            session.getTransaction().commit();
        }
        return result;
    }

    @Override
    public DtoJWTToken readByRefreshToken(String caption) {
        DtoJWTToken result = null;
        try(Session session = createConnection()){
            session.beginTransaction();
            EntityManagerFactory entityManagerFactory = session.getEntityManagerFactory();
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<EntityJWTToken> criteriaEntity = criteriaBuilder.createQuery(getEntityClass());
            Root<EntityJWTToken> criteriaRoot = criteriaEntity.from(getEntityClass());
            criteriaEntity.select(criteriaRoot);
            criteriaEntity.where(criteriaBuilder.equal(criteriaRoot.get("refreshToken"), caption));
            EntityJWTToken entity = entityManager.createQuery(criteriaEntity)
                    .getResultStream()
                    .findFirst()
                    .orElse(null);
            result = getDtoMapper().map(entity);

            session.getTransaction().commit();
        }
        return result;
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
