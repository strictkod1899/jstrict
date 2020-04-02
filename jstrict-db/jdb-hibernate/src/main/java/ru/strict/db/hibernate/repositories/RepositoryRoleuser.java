package ru.strict.db.hibernate.repositories;

import ru.strict.db.core.common.GenerateIdType;
import ru.strict.db.core.requests.DbTable;
import ru.strict.models.Role;
import ru.strict.db.core.repositories.IRepositoryNamed;
import ru.strict.db.hibernate.connection.CreateConnectionHibernate;
import ru.strict.db.hibernate.entities.EntityRoleuser;
import ru.strict.db.hibernate.mappers.dto.MapperDtoFactory;
import ru.strict.utils.UtilClass;

public class RepositoryRoleuser
        extends RepositoryHibernateBase<Long, EntityRoleuser, Role<Long>>
        implements IRepositoryNamed<Long, Role<Long>> {

    private static final String[] COLUMNS_NAME = new String[] {"code", "description"};

    public RepositoryRoleuser(CreateConnectionHibernate connectionSource, GenerateIdType generateIdType) {
        super(new DbTable("roleuser", "role"),
                COLUMNS_NAME,
                connectionSource,
                new MapperDtoFactory().instance(UtilClass.castClass(EntityRoleuser.class), UtilClass.castClass(Role.class)),
                generateIdType);
    }

    @Override
    protected Role<Long> fill(Role<Long> dto){
        return dto;
    }

    @Override
    public String getColumnWithName() {
        return COLUMNS_NAME[0];
    }

    @Override
    protected Class<EntityRoleuser> getEntityClass() {
        return UtilClass.castClass(EntityRoleuser.class);
    }

    @Override
    protected Class getThisClass() {
        return this.getClass();
    }
}
