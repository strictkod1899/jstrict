package ru.strict.db.hibernate.repositories;

import ru.strict.db.core.common.GenerateIdType;

import ru.strict.models.UserOnRole;
import ru.strict.db.core.repositories.interfaces.IRepositoryUserOnRole;
import ru.strict.db.hibernate.connection.CreateConnectionHibernate;
import ru.strict.db.hibernate.entities.EntityUserOnRole;
import ru.strict.db.hibernate.mappers.dto.MapperDtoFactory;
import ru.strict.utils.UtilClass;

public class RepositoryUserOnRole
        extends RepositoryHibernateBase<Long, EntityUserOnRole, UserOnRole<Long>>
        implements IRepositoryUserOnRole<Long> {

    private static final String[] COLUMNS_NAME = new String[] {"userx_id", "roleuser_id"};

    public RepositoryUserOnRole(CreateConnectionHibernate connectionSource, GenerateIdType generateIdType) {
        super("user_on_role",
                COLUMNS_NAME,
                connectionSource,
                new MapperDtoFactory().instance(UtilClass.castClass(EntityUserOnRole.class), UtilClass.castClass(UserOnRole.class)),
                generateIdType);
    }

    @Override
    protected UserOnRole<Long> fill(UserOnRole<Long> dto){
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
