package ru.strict.db.hibernate.repositories;

import org.hibernate.Session;
import ru.strict.db.core.common.GenerateIdType;
import ru.strict.db.core.dto.DtoUserBase;
import ru.strict.db.core.mappers.dto.MapperDtoBase;
import ru.strict.db.core.repositories.interfaces.IRepositoryUser;
import ru.strict.db.hibernate.connection.CreateConnectionHibernate;
import ru.strict.db.hibernate.entities.EntityUser;
import ru.strict.utils.UtilClassOperations;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.UUID;

public class RepositoryUser<ID extends Serializable, DTO extends DtoUserBase<ID>>
        extends RepositoryNamedBase<ID, EntityUser<ID>, DTO>
        implements IRepositoryUser<ID, DTO> {

    private static final String[] COLUMNS_NAME = new String[] {"username", "passwordencode", "email",
            "isBlocked", "isDeleted", "isConfirmEmail"};

    public RepositoryUser(CreateConnectionHibernate connectionSource,
                          MapperDtoBase<ID, EntityUser<ID>, DTO> dtoMapper,
                          GenerateIdType generateIdType) {
        super("userx",
                COLUMNS_NAME,
                connectionSource,
                dtoMapper,
                generateIdType);
    }

    @Override
    protected DTO fill(DTO dto){
        return dto;
    }

    @Override
    protected String getColumnWithName() {
        return COLUMNS_NAME[0];
    }

    @Override
    public DTO readByEmail(String email) {
        DTO result = null;
        Session session = null;
        EntityManagerFactory entityManagerFactory = null;
        EntityManager entityManager = null;
        try{
            session = createConnection();
            session.beginTransaction();
            entityManagerFactory = session.getEntityManagerFactory();
            entityManager = entityManagerFactory.createEntityManager();
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<EntityUser<ID>> criteriaEntity = criteriaBuilder.createQuery(getEntityClass());
            Root<EntityUser<ID>> criteriaRoot = criteriaEntity.from(getEntityClass());
            criteriaEntity.select(criteriaRoot);
            criteriaEntity.where(criteriaBuilder.equal(criteriaRoot.get("email"), email));
            EntityUser<ID> entity = entityManager.createQuery(criteriaEntity)
                    .getResultStream()
                    .findFirst()
                    .orElse(null);
            result = getDtoMapper().map(entity);

            session.getTransaction().commit();
        }catch(Exception ex){
            LOGGER.error(ex.getClass().toString(), ex.getMessage());
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
    protected Class<EntityUser<ID>> getEntityClass() {
        return UtilClassOperations.<EntityUser<ID>>castClass(EntityUser.class);
    }

    @Override
    protected Class getThisClass() {
        return this.getClass();
    }
}
