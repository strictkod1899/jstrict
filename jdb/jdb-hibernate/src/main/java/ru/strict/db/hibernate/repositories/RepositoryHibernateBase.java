package ru.strict.db.hibernate.repositories;

import org.hibernate.Session;
import ru.strict.db.core.common.GenerateIdType;
import ru.strict.db.core.dto.DtoBase;
import ru.strict.db.hibernate.entities.EntityBase;
import ru.strict.db.core.mappers.dto.MapperDtoBase;
import ru.strict.db.core.repositories.RepositoryBase;
import ru.strict.db.core.requests.DbRequests;
import ru.strict.db.hibernate.connection.CreateConnectionHibernate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

public abstract class RepositoryHibernateBase
        <E extends EntityBase, DTO extends DtoBase>
        extends RepositoryBase<UUID, Session, CreateConnectionHibernate, E, DTO> {

    //<editor-fold defaultState="collapsed" desc="constructors">
    public RepositoryHibernateBase(String tableName,
                                   String[] columnsName,
                                   CreateConnectionHibernate connectionSource,
                                   MapperDtoBase<E, DTO> dtoMapper,
                                   GenerateIdType generateIdType) {
        super(tableName, columnsName, connectionSource, dtoMapper, generateIdType);
    }
    //</editor-fold>

    @Override
    public DTO create(DTO dto) {
        try(Session session = createConnection()){
            session.beginTransaction();
            E entity = getDtoMapper().map(dto);
            session.save(entity);
            session.getTransaction().commit();
        }
        return dto;
    }

    @Override
    public DTO read(UUID id) {
        DTO result = null;
        try(Session session = createConnection()){
            session.beginTransaction();
            E entity = session.get(getEntityClass(), id);
            result = getDtoMapper().map(entity);
            session.getTransaction().commit();
        }
        return result;
    }

    @Override
    public List<DTO> readAll(DbRequests requests) {
        List<DTO> result = new LinkedList<>();
        try(Session session = createConnection()){
            session.beginTransaction();
            EntityManagerFactory entityManagerFactory = session.getEntityManagerFactory();
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<E> criteriaEntity = criteriaBuilder.createQuery(getEntityClass());
            Root<E> criteriaRoot = criteriaEntity.from(getEntityClass());
            criteriaEntity.select(criteriaRoot);
            List<E> entities = entityManager.createQuery(criteriaEntity).getResultList();
            entities.stream().forEach(entity -> result.add(getDtoMapper().map(entity)));

            session.getTransaction().commit();
        }
        return result;
    }

    @Override
    public DTO update(DTO dto) {
        try(Session session = createConnection()){
            session.beginTransaction();
            E entity = getDtoMapper().map(dto);
            session.update(entity);
            session.getTransaction().commit();
        }
        return dto;
    }

    @Override
    public void delete(UUID id) {
        try(Session session = createConnection()){
            session.beginTransaction();
            E entity = getDtoMapper().map(read(id));
            session.delete(entity);
            session.getTransaction().commit();
        }
    }

    @Override
    public int readCount(DbRequests requests) {
        throw new UnsupportedOperationException("Method not implemented");
    }

    @Override
    public boolean IsRowExists(UUID id) {
        return read(id) != null;
    }

    protected abstract Class<E> getEntityClass();
}
