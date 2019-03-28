package ru.strict.db.hibernate.repositories;

import ru.strict.db.core.common.GenerateIdType;

import ru.strict.db.core.dto.DtoUserOnRole;
import ru.strict.db.core.repositories.interfaces.IRepositoryUserOnRole;
import ru.strict.db.hibernate.connection.CreateConnectionHibernate;
import ru.strict.db.hibernate.entities.EntityUserOnRole;
import ru.strict.db.hibernate.mappers.dto.MapperDtoFactory;
import ru.strict.utils.UtilClass;

import java.io.Serializable;

public class RepositoryUserOnRole<ID extends Serializable>
        extends RepositoryHibernateBase<ID, EntityUserOnRole<ID>, DtoUserOnRole<ID>>
        implements IRepositoryUserOnRole<ID> {

    private static final String[] COLUMNS_NAME = new String[] {"userx_id", "roleuser_id"};

    public RepositoryUserOnRole(CreateConnectionHibernate connectionSource, GenerateIdType generateIdType) {
        super("user_on_role",
                COLUMNS_NAME,
                connectionSource,
                new MapperDtoFactory<ID>().instance(UtilClass.castClass(EntityUserOnRole.class), UtilClass.castClass(DtoUserOnRole.class)),
                generateIdType);
    }

    @Override
    protected DtoUserOnRole<ID> fill(DtoUserOnRole<ID> dto){
        return dto;
    }

    @Override
    protected Class<EntityUserOnRole<ID>> getEntityClass() {
        return UtilClass.castClass(EntityUserOnRole.class);
    }

    @Override
    protected Class getThisClass() {
        return this.getClass();
    }
}
