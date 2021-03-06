package ru.strict.db.hibernate.repositories;

import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import ru.strict.db.core.common.GenerateIdType;
import ru.strict.db.core.repositories.RepositoryBase;
import ru.strict.db.core.requests.*;
import ru.strict.db.hibernate.connection.CreateConnectionHibernate;
import ru.strict.models.ModelBase;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public abstract class RepositoryHibernateBase
        <ID extends Serializable, T extends ModelBase<ID>>
        extends RepositoryBase<ID, Session, CreateConnectionHibernate, T> {

    //<editor-fold defaultState="collapsed" desc="constructors">
    public RepositoryHibernateBase(DbTable table,
                                   String[] columnsName,
                                   CreateConnectionHibernate connectionSource,
                                   GenerateIdType generateIdType) {
        super(table, columnsName, connectionSource, generateIdType);
    }
    //</editor-fold>

    @Override
    public ID create(T model) {
        Session session = null;
        ID generatedId = null;
        try{
            session = createConnection();
            session.beginTransaction();
            session.save(model);
            session.getTransaction().commit();
            generatedId = model.getId();
            model.setId(null);
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
        return generatedId;
    }

    @Override
    public T read(ID id) {
        Session session = null;
        try{
            session = createConnection();
            return session.get(getEntityClass(), id);
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
    public List<T> readAll(DbRequests requests) {
        List<T> result = new ArrayList<>();
        Session session = null;
        EntityManagerFactory entityManagerFactory = null;
        EntityManager entityManager = null;
        try{
            session = createConnection();
            entityManagerFactory = session.getEntityManagerFactory();
            entityManager = entityManagerFactory.createEntityManager();
            CriteriaQuery<T> criteriaEntity = createCriteriaEntityByRequests(entityManager, requests);
            Query query = entityManager.createQuery(criteriaEntity);

            if(requests.getLimit() != null){
                query.setMaxResults(requests.getLimit().getLimit());
            }
            if(requests.getOffset() != null){
                query.setFirstResult(requests.getOffset().getOffset());
            }

            return query.getResultList();
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
    }

    @Override
    public void update(T model) {
        Session session = null;
        try{
            session = createConnection();
            session.beginTransaction();
            session.update(model);
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
    public void delete(ID id) {
        // TODO: добавитьп рвоерку на null
        Session session = null;
        try{
            session = createConnection();
            session.beginTransaction();
            T model = read(id);
            if(model == null){
                throw new NullPointerException(String.format("Entity by id [%s] not found", id));
            }
            session.delete(model);
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

        DbSelect select = createSqlCount();
        select.setRequests(requests);

        Session session = null;
        try{
            session = createConnection();
            NativeQuery resultQuery = session.createNativeQuery(select.getSql());
            return (Integer) resultQuery.list().get(0);
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
    public void executeSql(String sql) {
        Session session = null;
        try{
            session = createConnection();
            NativeQuery query = session.createNativeQuery(sql);
            query.executeUpdate();
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

    //<editor-fold defaultState="collapsed" desc="convert requests into predicate[]">
    /**
     * Преобразовать объект DbRequests в Predicate[] для Hibernate
     * @param entityManager объект EntityManager, который обеспечивает функциональность для создания и заполнения CriteriaQuery<E>
     * @param requests объект DbRequests, который требуется преобразовать
     * @return
     */
    protected CriteriaQuery<T> createCriteriaEntityByRequests(EntityManager entityManager, DbRequests requests){
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> criteriaEntity = criteriaBuilder.createQuery(getEntityClass());
        Root<T> criteriaRoot = criteriaEntity.from(getEntityClass());
        criteriaEntity.select(criteriaRoot);

        if(requests != null) {
            if(requests.getWhere() != null && requests.getWhere().size() > 0) {
                criteriaEntity.where(convertWhereToPredicate(criteriaBuilder, requests.getWhere(), criteriaRoot));
            }
            if(requests.getSort() != null){
                DbSort sort = requests.getSort();
                switch (sort.getSortType()) {
                    case ASC:
                        criteriaEntity.orderBy(criteriaBuilder.asc(criteriaRoot.get(sort.getColumnName())));
                        break;
                    case DESC:
                        criteriaEntity.orderBy(criteriaBuilder.desc(criteriaRoot.get(sort.getColumnName())));
                        break;
                }
            }

            if(requests.getJoinRequests() != null){
                List<DbJoin> joins = requests.getJoinRequests();
                for(DbJoin join : joins){
                    //criteriaRoot.join(join.getTableName());
                }
            }
        }

        return criteriaEntity;
    }

    /**
     * Преобразовать объект DbWhere в Predicate
     */
    private Predicate convertWhereToPredicate(CriteriaBuilder criteriaBuilder, DbWhere where, Root<T> criteriaRoot){
        List<DbWhereBase> whereItems = where.getChilds();
        Predicate predicate = null;

        if(whereItems.size() == 1) {
            DbWhereBase whereItemBase = whereItems.get(0);
            if(whereItemBase instanceof DbWhereItem) {
                DbWhereItem whereItem = (DbWhereItem) whereItemBase;
                predicate = createPredicateByWhereItem(criteriaBuilder, whereItem, criteriaRoot);
            } else if (whereItemBase instanceof DbWhere){
                predicate = convertWhereToPredicate(criteriaBuilder, (DbWhere)whereItemBase, criteriaRoot);
            }
        } else if(whereItems.size() > 1){
            switch (where.getType()){
                case AND:
                    predicate = criteriaBuilder.and(convertWhereItemsToPredicates(criteriaBuilder, whereItems, criteriaRoot));
                    break;
                case OR:
                    predicate = criteriaBuilder.or(convertWhereItemsToPredicates(criteriaBuilder, whereItems, criteriaRoot));
                    break;
            }
        }

        return predicate;
    }

    /**
     * Преобразовать список условий List<DbWhereBase> в Predicate[]
     */
    private Predicate[] convertWhereItemsToPredicates(CriteriaBuilder criteriaBuilder, List<DbWhereBase> whereItems, Root<E> criteriaRoot){
        Predicate[] predicates = new Predicate[whereItems.size()];
        for(int i = 0; i < whereItems.size(); i++){
            if(whereItems.get(i) instanceof DbWhereItem){
                DbWhereItem whereItem = (DbWhereItem) whereItems.get(i);
                predicates[i] = createPredicateByWhereItem(criteriaBuilder, whereItem, criteriaRoot);
            }else if(whereItems.get(i) instanceof DbWhere){
                DbWhere where = (DbWhere) whereItems.get(i);
                predicates[i] = convertWhereToPredicate(criteriaBuilder, where, criteriaRoot);
            }
        }

        return predicates;
    }

    /**
     * Преобразовать объект DbWhereItem в Predicate
     * @return
     */
    private Predicate createPredicateByWhereItem(CriteriaBuilder criteriaBuilder, DbWhereItem whereItem, Root<T> criteriaRoot){
        Predicate predicate = null;
        if(whereItem.getOperator().equalsIgnoreCase("LIKE")) {
            predicate = criteriaBuilder.like(criteriaRoot.get(whereItem.getColumnName()), whereItem.getFormattedColumnValue());
        } else {
            predicate = criteriaBuilder.equal(criteriaRoot.get(whereItem.getColumnName()), whereItem.getColumnValue());
        }

        return predicate;
    }
    //</editor-fold>

    protected abstract Class<T> getEntityClass();
}
