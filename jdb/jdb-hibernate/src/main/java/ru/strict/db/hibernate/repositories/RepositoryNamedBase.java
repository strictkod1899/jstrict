package ru.strict.db.hibernate.repositories;

import org.hibernate.Session;
import ru.strict.db.core.common.GenerateIdType;
import ru.strict.db.core.dto.DtoBase;
import ru.strict.db.core.entities.EntityBase;
import ru.strict.db.core.mappers.dto.MapperDtoBase;
import ru.strict.db.core.repositories.IRepositoryNamed;
import ru.strict.db.hibernate.connection.CreateConnectionHibernate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/**
 * Базовый класс репозитория с использованием Jdbc для таблиц со столбцом наименования (caption)
 * @param <ID> Тип идентификатора
 * @param <E> Тип сущности базы данных (entity)
 * @param <DTO> Тип Dto-сущности базы данных
 */
public abstract class RepositoryNamedBase
        <ID extends Serializable, E extends EntityBase, DTO extends DtoBase>
        extends RepositoryHibernateBase<ID, E, DTO>
        implements IRepositoryNamed<ID, DTO> {

    public RepositoryNamedBase(String tableName,
                               String[] columnsName,
                               CreateConnectionHibernate connectionSource,
                               MapperDtoBase<E, DTO> dtoMapper,
                               GenerateIdType generateIdType) {
        super(tableName, columnsName, connectionSource, dtoMapper, generateIdType);
    }

    @Override
    public DTO readByName(String caption){
        DTO result = null;
        try(Session session = createConnection()){
            session.beginTransaction();
            EntityManagerFactory entityManagerFactory = session.getEntityManagerFactory();
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<DTO> criteriaEntity =
                    (CriteriaQuery<DTO>) criteriaBuilder.createQuery(getEmptyDto().getClass());
            Root<DTO> criteriaRoot = (Root<DTO>) criteriaEntity.from(getEmptyDto().getClass());
            criteriaEntity.select(criteriaRoot);
            criteriaEntity.where(criteriaBuilder.equal(criteriaRoot.get(getColumnWithName()), caption));
            result = entityManager.createQuery(criteriaEntity)
                    .getResultStream()
                    .findFirst()
                    .orElse(null);

            session.getTransaction().commit();
        }
        return result;
    }

    @Override
    public List<DTO> readAllByName(String caption){
        List<DTO> result = new LinkedList<>();
        try(Session session = createConnection()){
            session.beginTransaction();
            EntityManagerFactory entityManagerFactory = session.getEntityManagerFactory();
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<DTO> criteriaEntity =
                    (CriteriaQuery<DTO>) criteriaBuilder.createQuery(getEmptyDto().getClass());
            Root<DTO> criteriaRoot = (Root<DTO>) criteriaEntity.from(getEmptyDto().getClass());
            criteriaEntity.select(criteriaRoot);
            criteriaEntity.where(criteriaBuilder.equal(criteriaRoot.get(getColumnWithName()), caption));
            result = entityManager.createQuery(criteriaEntity).getResultList();

            session.getTransaction().commit();
        }
        return result;
    }

    /**
     * Получить наименование столбца, который выполняет роль наименования записи
     * @return
     */
    protected abstract String getColumnWithName();
}