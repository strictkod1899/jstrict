package ru.strict.db.hibernate.repositories;

import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import ru.strict.db.core.common.GenerateIdType;
import ru.strict.db.core.dto.DtoBase;
import ru.strict.db.core.requests.DbWhere;
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
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public abstract class RepositoryHibernateBase
        <ID extends Serializable, E extends EntityBase<ID>, DTO extends DtoBase<ID>>
        extends RepositoryBase<ID, Session, CreateConnectionHibernate, E, DTO> {

    //<editor-fold defaultState="collapsed" desc="constructors">
    public RepositoryHibernateBase(String tableName,
                                   String[] columnsName,
                                   CreateConnectionHibernate connectionSource,
                                   MapperDtoBase<ID, E, DTO> dtoMapper,
                                   GenerateIdType generateIdType) {
        super(tableName, columnsName, connectionSource, dtoMapper, generateIdType);
    }
    //</editor-fold>

    @Override
    public DTO create(DTO dto) {
        Session session = null;
        try{
            session = createConnection();
            session.beginTransaction();
            E entity = getDtoMapper().map(dto);
            session.save(entity);
            session.getTransaction().commit();
        }catch(Exception ex){
            if(session != null) {
                session.getTransaction().rollback();
            }
            throw ex;
        }finally{
            if(session != null) {
                session.close();
            }
        }
        return dto;
    }

    @Override
    public DTO read(ID id) {
        DTO result = null;
        Session session = null;
        try{
            session = createConnection();
            session.beginTransaction();
            E entity = session.get(getEntityClass(), id);
            result = getDtoMapper().map(entity);
            session.getTransaction().commit();
        }catch(Exception ex){
            if(session != null) {
                session.getTransaction().rollback();
            }
            throw ex;
        }finally{
            if(session != null) {
                session.close();
            }
        }
        return result;
    }

    @Override
    public List<DTO> readAll(DbRequests requests) {
        List<DTO> result = new LinkedList<>();
        Session session = null;
        EntityManagerFactory entityManagerFactory = null;
        EntityManager entityManager = null;
        try{
            session = createConnection();
            session.beginTransaction();
            entityManagerFactory = session.getEntityManagerFactory();
            entityManager = entityManagerFactory.createEntityManager();
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<E> criteriaEntity = criteriaBuilder.createQuery(getEntityClass());
            Root<E> criteriaRoot = criteriaEntity.from(getEntityClass());
            criteriaEntity.select(criteriaRoot);
            List<E> entities = entityManager.createQuery(criteriaEntity).getResultList();
            entities.stream().forEach(entity -> result.add(getDtoMapper().map(entity)));

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
    public DTO update(DTO dto) {
        Session session = null;
        try{
            session = createConnection();
            session.beginTransaction();
            E entity = getDtoMapper().map(dto);
            session.update(entity);
            session.getTransaction().commit();
        }catch(Exception ex){
            if(session != null) {
                session.getTransaction().rollback();
            }
            throw ex;
        }finally{
            if(session != null) {
                session.close();
            }
        }
        return dto;
    }

    @Override
    public void delete(ID id) {
        Session session = null;
        try{
            session = createConnection();
            session.beginTransaction();
            E entity = getDtoMapper().map(read(id));
            session.delete(entity);
            session.getTransaction().commit();
        }catch(Exception ex){
            if(session != null) {
                session.getTransaction().rollback();
            }
            throw ex;
        }finally{
            if(session != null) {
                session.close();
            }
        }
    }

    @Override
    public int readCount(DbRequests requests) {
        int result = -1;
        String sql = createSqlCount() + (requests==null ? "" : requests.getSql());
        Session session = null;
        try{
            session = createConnection();
            NativeQuery resultQuery = session.createNativeQuery(sql);
            result = (Integer) resultQuery.list().get(0);
        }catch(Exception ex){
            if(session != null) {
                session.getTransaction().rollback();
            }
            throw ex;
        }finally{
            if(session != null) {
                session.close();
            }
        }

        return result;
    }

    @Override
    public boolean isRowExists(ID id) {
        DbRequests requests = new DbRequests(getTableName(), true);
        requests.add(new DbWhere(getTableName(), getColumnIdName(), id, "="));
        return readCount(requests) > 0;
    }

    protected abstract Class<E> getEntityClass();
}
