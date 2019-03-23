package ru.strict.db.hibernate.repositories;

import ru.strict.db.core.common.GenerateIdType;
import ru.strict.db.core.dto.DtoRoleuser;
import ru.strict.db.hibernate.connection.CreateConnectionHibernate;
import ru.strict.db.hibernate.entities.EntityRoleuser;
import ru.strict.db.hibernate.mappers.dto.MapperDtoFactory;
import ru.strict.utils.UtilClass;

import java.io.Serializable;

public class RepositoryRoleuser<ID extends Serializable>
        extends RepositoryNamedBase<ID, EntityRoleuser<ID>, DtoRoleuser<ID>> {

    private static final String[] COLUMNS_NAME = new String[] {"code", "description"};

    public RepositoryRoleuser(CreateConnectionHibernate connectionSource, GenerateIdType generateIdType) {
        super("roleuser",
                COLUMNS_NAME,
                connectionSource,
                new MapperDtoFactory<ID>().instance(UtilClass.castClass(EntityRoleuser.class), UtilClass.castClass(DtoRoleuser.class)),
                generateIdType);
    }

    @Override
    protected DtoRoleuser<ID> fill(DtoRoleuser<ID> dto){
        return dto;
    }

    @Override
    protected String getColumnWithName() {
        return COLUMNS_NAME[0];
    }

    @Override
    protected Class<EntityRoleuser<ID>> getEntityClass() {
        return UtilClass.castClass(EntityRoleuser.class);
    }

    @Override
    protected Class getThisClass() {
        return this.getClass();
    }
}
