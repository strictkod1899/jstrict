package ru.strict.db.hibernate.repositories;

import ru.strict.db.core.common.GenerateIdType;
import ru.strict.db.core.common.MapperDtoType;
import ru.strict.db.core.dto.DtoUserOnRole;
import ru.strict.db.hibernate.connection.CreateConnectionHibernate;
import ru.strict.db.hibernate.entities.EntityUserOnRole;
import ru.strict.db.hibernate.mappers.dto.MapperDtoFactory;
import ru.strict.utils.UtilClassOperations;

import java.io.Serializable;

public class RepositoryUserOnRole<ID extends Serializable>
        extends RepositoryHibernateBase<ID, EntityUserOnRole<ID>, DtoUserOnRole<ID>> {

    private static final String[] COLUMNS_NAME = new String[] {"userx_id", "roleuser_id"};

    public RepositoryUserOnRole(CreateConnectionHibernate connectionSource, GenerateIdType generateIdType) {
        super("user_on_role",
                COLUMNS_NAME,
                connectionSource,
                new MapperDtoFactory<ID, EntityUserOnRole<ID>, DtoUserOnRole<ID>>().instance(MapperDtoType.USER_ON_ROLE),
                generateIdType);
    }

    @Override
    protected DtoUserOnRole<ID> fill(DtoUserOnRole<ID> dto){
        return dto;
    }

    @Override
    protected Class<EntityUserOnRole<ID>> getEntityClass() {
        return UtilClassOperations.<EntityUserOnRole<ID>>castClass(EntityUserOnRole.class);
    }

    @Override
    protected Class getThisClass() {
        return this.getClass();
    }
}
