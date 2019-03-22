package ru.strict.db.hibernate.repositories;

import org.hibernate.Session;
import ru.strict.db.core.common.GenerateIdType;
import ru.strict.db.core.common.MapperDtoType;
import ru.strict.db.core.dto.DtoJWTToken;
import ru.strict.db.core.repositories.interfaces.IRepositoryJWTToken;
import ru.strict.db.hibernate.connection.CreateConnectionHibernate;
import ru.strict.db.hibernate.entities.EntityJWTToken;
import ru.strict.db.hibernate.mappers.dto.MapperDtoFactory;
import ru.strict.utils.UtilClassOperations;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

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
                new MapperDtoFactory<ID, EntityJWTToken<ID>, DtoJWTToken<ID>>().instance(MapperDtoType.JWT_TOKEN),
                generateIdType);
    }

    @Override
    public DtoJWTToken<ID> readByAccessToken(String caption) {
        DtoJWTToken<ID> result = null;
        Session session = null;
        EntityManagerFactory entityManagerFactory = null;
        EntityManager entityManager = null;
        try{
            session = createConnection();
            session.beginTransaction();
            entityManagerFactory = session.getEntityManagerFactory();
            entityManager = entityManagerFactory.createEntityManager();
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<EntityJWTToken<ID>> criteriaEntity = criteriaBuilder.createQuery(getEntityClass());
            Root<EntityJWTToken<ID>> criteriaRoot = criteriaEntity.from(getEntityClass());
            criteriaEntity.select(criteriaRoot);
            criteriaEntity.where(criteriaBuilder.equal(criteriaRoot.get("access_token"), caption));
            TypedQuery<EntityJWTToken<ID>> typed =  entityManager.createQuery(criteriaEntity);
            List<EntityJWTToken<ID>> entities = typed.getResultList();
            EntityJWTToken<ID> entity = entities.isEmpty() ? null : entities.get(0);
            result = getDtoMapper().map(entity);

            session.getTransaction().commit();
        }catch(Exception ex){
            if(session != null) {
                session.getTransaction().rollback();
            }
            throw ex;
        }finally{
            if(entityManager != null) {
                entityManager.close();
            }

            if(entityManagerFactory != null){
                entityManagerFactory.close();
            }

            if(session != null) {
                session.close();
            }
        }
        return result;
    }

    @Override
    public DtoJWTToken<ID> readByRefreshToken(String caption) {
        DtoJWTToken<ID> result = null;
        Session session = null;
        EntityManagerFactory entityManagerFactory = null;
        EntityManager entityManager = null;
        try{
            session = createConnection();
            session.beginTransaction();
            entityManagerFactory = session.getEntityManagerFactory();
            entityManager = entityManagerFactory.createEntityManager();
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<EntityJWTToken<ID>> criteriaEntity = criteriaBuilder.createQuery(getEntityClass());
            Root<EntityJWTToken<ID>> criteriaRoot = criteriaEntity.from(getEntityClass());
            criteriaEntity.select(criteriaRoot);
            criteriaEntity.where(criteriaBuilder.equal(criteriaRoot.get("refresh_token"), caption));
            TypedQuery<EntityJWTToken<ID>> typed =  entityManager.createQuery(criteriaEntity);
            List<EntityJWTToken<ID>> entities = typed.getResultList();
            EntityJWTToken<ID> entity = entities.isEmpty() ? null : entities.get(0);
            result = getDtoMapper().map(entity);

            session.getTransaction().commit();
        }catch(Exception ex){
            if(session != null) {
                session.getTransaction().rollback();
            }
            throw ex;
        }finally{
            if(entityManager != null) {
                entityManager.close();
            }

            if(entityManagerFactory != null){
                entityManagerFactory.close();
            }

            if(session != null) {
                session.close();
            }
        }
        return result;
    }

    @Override
    protected DtoJWTToken<ID> fill(DtoJWTToken dto){
        return dto;
    }

    @Override
    protected Class<EntityJWTToken<ID>> getEntityClass() {
        return UtilClassOperations.<EntityJWTToken<ID>>castClass(EntityJWTToken.class);
    }

    @Override
    protected Class getThisClass() {
        return this.getClass();
    }
}
