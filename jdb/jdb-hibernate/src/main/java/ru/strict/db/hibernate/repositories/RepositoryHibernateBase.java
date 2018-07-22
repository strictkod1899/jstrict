package ru.strict.db.hibernate.repositories;

import org.hibernate.Session;
import ru.strict.db.core.common.GenerateIdType;
import ru.strict.db.core.dto.DtoBase;
import ru.strict.db.core.entities.EntityBase;
import ru.strict.db.core.mappers.dto.MapperDtoBase;
import ru.strict.db.core.repositories.RepositoryBase;
import ru.strict.db.hibernate.connection.CreateConnectionHibernate;

public abstract class RepositoryHibernateBase
        <ID, E extends EntityBase, DTO extends DtoBase>
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
}
