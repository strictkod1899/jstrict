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
            session.save(dto);
            session.getTransaction().commit();
        }
        return dto;
    }

    @Override
    public DTO read(ID id) {
        DTO result = null;
        try(Session session = createConnection()){
            session.beginTransaction();
            result = (DTO) session.get(getEmptyDto().getClass(), id);
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
            CriteriaQuery<DTO> criteriaEntity =
                    (CriteriaQuery<DTO>) criteriaBuilder.createQuery(getEmptyDto().getClass());
            Root<DTO> criteriaRoot = (Root<DTO>) criteriaEntity.from(getEmptyDto().getClass());
            criteriaEntity.select(criteriaRoot);
            result = entityManager.createQuery(criteriaEntity).getResultList();

            session.getTransaction().commit();
        }
        return result;
    }

    @Override
    public DTO update(DTO dto) {
        try(Session session = createConnection()){
            session.beginTransaction();
            session.update(dto);
            session.getTransaction().commit();
        }
        return dto;
    }

    @Override
    public void delete(ID id) {
        try(Session session = createConnection()){
            session.beginTransaction();
            session.delete(read(id));
            session.getTransaction().commit();
        }
    }

    @Override
    public boolean IsRowExists(ID id) {
        return read(id) != null;
    }

    protected abstract DTO getEmptyDto();
}
