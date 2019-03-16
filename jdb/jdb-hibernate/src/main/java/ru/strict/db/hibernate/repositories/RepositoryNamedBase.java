package ru.strict.db.hibernate.repositories;

import org.hibernate.Session;
import ru.strict.db.core.common.GenerateIdType;
import ru.strict.db.core.dto.DtoBase;
import ru.strict.db.hibernate.entities.EntityBase;
import ru.strict.db.core.mappers.dto.MapperDtoBase;
import ru.strict.db.core.repositories.IRepositoryNamed;
import ru.strict.db.hibernate.connection.CreateConnectionHibernate;
import ru.strict.validates.ValidateBaseValue;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * Базовый класс репозитория с использованием Jdbc для таблиц со столбцом наименования (caption)
 * @param <E> Тип сущности базы данных (entity)
 * @param <DTO> Тип Dto-сущности базы данных
 */
public abstract class RepositoryNamedBase
        <ID extends Serializable, E extends EntityBase<ID>, DTO extends DtoBase<ID>>
        extends RepositoryHibernateBase<ID, E, DTO>
        implements IRepositoryNamed<ID, DTO> {

    public RepositoryNamedBase(String tableName,
                               String[] columnsName,
                               CreateConnectionHibernate connectionSource,
                               MapperDtoBase<ID, E, DTO> dtoMapper,
                               GenerateIdType generateIdType) {
        super(tableName, columnsName, connectionSource, dtoMapper, generateIdType);
    }

    @Override
    public DTO readByName(String caption){
        if(ValidateBaseValue.isEmptyOrNull(caption)){
            throw new NullPointerException("caption for read by name is NULL");
        }
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
            CriteriaQuery<E> criteriaEntity = criteriaBuilder.createQuery(getEntityClass());
            Root<E> criteriaRoot = criteriaEntity.from(getEntityClass());
            criteriaEntity.select(criteriaRoot);
            criteriaEntity.where(criteriaBuilder.equal(criteriaRoot.get(getColumnWithName()), caption));
            TypedQuery<E> typed =  entityManager.createQuery(criteriaEntity);
            List<E> entities = typed.getResultList();
            E entity = entities.isEmpty() ? null : entities.get(0);
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
    public List<DTO> readAllByName(String caption){
        if(ValidateBaseValue.isEmptyOrNull(caption)){
            throw new NullPointerException("caption for read by name is NULL");
        }
        List<DTO> result = new ArrayList<>();
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
            criteriaEntity.where(criteriaBuilder.equal(criteriaRoot.get(getColumnWithName()), caption));
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

    /**
     * Получить наименование столбца, который выполняет роль наименования записи
     * @return
     */
    protected abstract String getColumnWithName();
}
