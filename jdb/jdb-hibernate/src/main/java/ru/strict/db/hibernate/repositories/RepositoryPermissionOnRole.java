package ru.strict.db.hibernate.repositories;

import ru.strict.db.core.common.GenerateIdType;
import ru.strict.db.core.requests.DbTable;
import ru.strict.models.PermissionOnRole;
import ru.strict.db.core.mappers.dto.MapperDtoBase;
import ru.strict.db.core.repositories.interfaces.IRepositoryPermissionOnRole;
import ru.strict.db.hibernate.connection.CreateConnectionHibernate;
import ru.strict.db.hibernate.entities.EntityServiceOnRole;
import ru.strict.utils.UtilClass;

public class RepositoryPermissionOnRole<SERVICE>
        extends RepositoryHibernateBase<Long, EntityServiceOnRole<SERVICE>, PermissionOnRole<Long, SERVICE>>
        implements IRepositoryPermissionOnRole<Long, SERVICE> {

    private static final String[] COLUMNS_NAME = new String[] {"service_id", "roleuser_id"};

    public RepositoryPermissionOnRole(CreateConnectionHibernate connectionSource,
                                      MapperDtoBase<Long, EntityServiceOnRole<SERVICE>, PermissionOnRole<Long, SERVICE>> dtoMapper,
                                      GenerateIdType generateIdType) {
        super(new DbTable("service_on_role", "sr"),
                COLUMNS_NAME,
                connectionSource,
                dtoMapper,
                generateIdType);
    }

    @Override
    protected PermissionOnRole<Long, SERVICE> fill(PermissionOnRole<Long, SERVICE> dto){
        return dto;
    }

    @Override
    protected Class<EntityServiceOnRole<SERVICE>> getEntityClass() {
        return UtilClass.castClass(EntityServiceOnRole.class);
    }

    @Override
    protected Class getThisClass() {
        return this.getClass();
    }
}
