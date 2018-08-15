package ru.strict.db.hibernate.repositories;

import ru.strict.db.core.common.GenerateIdType;
import ru.strict.db.core.common.MapperDtoType;
import ru.strict.db.core.dto.DtoUserOnRole;
import ru.strict.db.hibernate.connection.CreateConnectionHibernate;
import ru.strict.db.hibernate.entities.EntityUserOnRole;
import ru.strict.db.hibernate.mappers.dto.MapperDtoFactory;

public class RepositoryUserOnRole extends RepositoryHibernateBase<EntityUserOnRole, DtoUserOnRole> {

    private static final String[] COLUMNS_NAME = new String[] {"userx_id", "roleuser_id"};

    public RepositoryUserOnRole(CreateConnectionHibernate connectionSource, GenerateIdType isGenerateId) {
        super("user_on_role",
                COLUMNS_NAME,
                connectionSource,
                new MapperDtoFactory().instance(MapperDtoType.USER_ON_ROLE),
                isGenerateId);
    }

    @Override
    protected DtoUserOnRole fill(DtoUserOnRole dto){
        return dto;
    }

    @Override
    protected Class<EntityUserOnRole> getEntityClass() {
        return EntityUserOnRole.class;
    }

    @Override
    protected Class getThisClass() {
        return this.getClass();
    }
}
