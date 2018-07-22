package ru.strict.db.hibernate.repositories;

import org.hibernate.Session;
import ru.strict.db.core.common.GenerateIdType;
import ru.strict.db.core.dto.DtoBase;
import ru.strict.db.core.entities.EntityBase;
import ru.strict.db.core.mappers.dto.MapperDtoBase;
import ru.strict.db.core.repositories.RepositoryBase;
import ru.strict.db.core.requests.DbRequests;
import ru.strict.db.hibernate.connection.CreateConnectionHibernate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public abstract class RepositoryHibernateBase
        <ID extends Serializable, E extends EntityBase, DTO extends DtoBase>
        extends RepositoryBase<ID, Session, CreateConnectionHibernate, E, DTO> {

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
    public DTO read(ID id) {
        DTO result = null;
        try(Session session = createConnection()){
            session.beginTransaction();
            E entity = (E) session.get(getEmptyEntity().getClass(), id);
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
            CriteriaQuery<E> criteriaEntity =
                    (CriteriaQuery<E>) criteriaBuilder.createQuery(getEmptyEntity().getClass());
            Root<E> criteriaRoot = (Root<E>) criteriaEntity.from(getEmptyEntity().getClass());
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
    public void delete(ID id) {
        try(Session session = createConnection()){
            session.beginTransaction();
            E entity = getDtoMapper().map(read(id));
            session.delete(entity);
            session.getTransaction().commit();
        }
    }

    @Override
    public boolean IsRowExists(ID id) {
        return read(id) != null;
    }

    protected abstract E getEmptyEntity();
}
