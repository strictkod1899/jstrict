package ru.strict.db.hibernate.repositories;

import ru.strict.db.core.common.GenerateIdType;

import ru.strict.db.core.dto.DtoUserOnRole;
import ru.strict.db.core.repositories.interfaces.IRepositoryUserOnRole;
import ru.strict.db.hibernate.connection.CreateConnectionHibernate;
import ru.strict.db.hibernate.entities.EntityUserOnRole;
import ru.strict.db.hibernate.mappers.dto.MapperDtoFactory;
import ru.strict.utils.UtilClass;

import java.io.Serializable;

public class RepositoryUserOnRole
        extends RepositoryHibernateBase<Long, EntityUserOnRole, DtoUserOnRole<Long>>
        implements IRepositoryUserOnRole<Long> {

    private static final String[] COLUMNS_NAME = new String[] {"userx_id", "roleuser_id"};

    public RepositoryUserOnRole(CreateConnectionHibernate connectionSource, GenerateIdType generateIdType) {
        super("user_on_role",
                COLUMNS_NAME,
                connectionSource,
                new MapperDtoFactory().instance(UtilClass.castClass(EntityUserOnRole.class), UtilClass.castClass(DtoUserOnRole.class)),
                generateIdType);
    }

    @Override
    protected DtoUserOnRole<Long> fill(DtoUserOnRole<Long> dto){
        return dto;
    }

    @Override
    protected Class<EntityUserOnRole> getEntityClass() {
        return UtilClass.castClass(EntityUserOnRole.class);
    }

    @Override
    protected Class getThisClass() {
        return this.getClass();
    }
}
