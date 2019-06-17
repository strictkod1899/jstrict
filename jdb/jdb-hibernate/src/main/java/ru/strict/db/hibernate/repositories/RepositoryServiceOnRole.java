package ru.strict.db.hibernate.repositories;

import ru.strict.db.core.common.GenerateIdType;
import ru.strict.db.core.requests.DbTable;
import ru.strict.models.ServiceOnRole;
import ru.strict.db.core.mappers.dto.MapperDtoBase;
import ru.strict.db.core.repositories.interfaces.IRepositoryServiceOnRole;
import ru.strict.db.hibernate.connection.CreateConnectionHibernate;
import ru.strict.db.hibernate.entities.EntityServiceOnRole;
import ru.strict.utils.UtilClass;

public class RepositoryServiceOnRole<SERVICE>
        extends RepositoryHibernateBase<Long, EntityServiceOnRole<SERVICE>, ServiceOnRole<Long, SERVICE>>
        implements IRepositoryServiceOnRole<Long, SERVICE> {

    private static final String[] COLUMNS_NAME = new String[] {"service_id", "roleuser_id"};

    public RepositoryServiceOnRole(CreateConnectionHibernate connectionSource,
                                   MapperDtoBase<Long, EntityServiceOnRole<SERVICE>, ServiceOnRole<Long, SERVICE>> dtoMapper,
                                   GenerateIdType generateIdType) {
        super(new DbTable("service_on_role", "sr"),
                COLUMNS_NAME,
                connectionSource,
                dtoMapper,
                generateIdType);
    }

    @Override
    protected ServiceOnRole<Long, SERVICE> fill(ServiceOnRole<Long, SERVICE> dto){
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
