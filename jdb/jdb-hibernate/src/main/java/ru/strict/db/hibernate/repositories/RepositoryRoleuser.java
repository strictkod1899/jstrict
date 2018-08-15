package ru.strict.db.hibernate.repositories;

import ru.strict.db.core.common.GenerateIdType;
import ru.strict.db.core.common.MapperDtoType;
import ru.strict.db.core.dto.DtoRoleuser;
import ru.strict.db.hibernate.connection.CreateConnectionHibernate;
import ru.strict.db.hibernate.entities.EntityRoleuser;
import ru.strict.db.hibernate.mappers.dto.MapperDtoFactory;

public class RepositoryRoleuser extends RepositoryNamedBase<EntityRoleuser, DtoRoleuser> {

    private static final String[] COLUMNS_NAME = new String[] {"code", "description"};

    public RepositoryRoleuser(CreateConnectionHibernate connectionSource, GenerateIdType isGenerateId) {
        super("roleuser",
                COLUMNS_NAME,
                connectionSource,
                new MapperDtoFactory().instance(MapperDtoType.ROLE_USER),
                isGenerateId);
    }

    @Override
    protected DtoRoleuser fill(DtoRoleuser dto){
        return dto;
    }

    @Override
    protected String getColumnWithName() {
        return COLUMNS_NAME[0];
    }

    @Override
    protected Class<EntityRoleuser> getEntityClass() {
        return EntityRoleuser.class;
    }

    @Override
    protected Class getThisClass() {
        return this.getClass();
    }
}
