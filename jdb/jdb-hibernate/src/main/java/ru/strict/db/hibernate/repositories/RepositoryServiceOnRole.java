package ru.strict.db.hibernate.repositories;

import ru.strict.db.core.common.GenerateIdType;
import ru.strict.db.core.dto.DtoServiceOnRole;
import ru.strict.db.core.mappers.dto.MapperDtoBase;
import ru.strict.db.core.repositories.interfaces.IRepositoryServiceOnRole;
import ru.strict.db.hibernate.connection.CreateConnectionHibernate;
import ru.strict.db.hibernate.entities.EntityServiceOnRole;
import ru.strict.utils.UtilClass;

import java.io.Serializable;

public class RepositoryServiceOnRole<ID extends Serializable, SERVICE>
        extends RepositoryHibernateBase<ID, EntityServiceOnRole<ID, SERVICE>, DtoServiceOnRole<ID, SERVICE>>
        implements IRepositoryServiceOnRole<ID, SERVICE> {

    private static final String[] COLUMNS_NAME = new String[] {"service_id", "roleuser_id"};

    public RepositoryServiceOnRole(CreateConnectionHibernate connectionSource,
                                   MapperDtoBase<ID, EntityServiceOnRole<ID, SERVICE>, DtoServiceOnRole<ID, SERVICE>> dtoMapper,
                                   GenerateIdType generateIdType) {
        super("service_on_role",
                COLUMNS_NAME,
                connectionSource,
                dtoMapper,
                generateIdType);
    }

    @Override
    protected DtoServiceOnRole<ID, SERVICE> fill(DtoServiceOnRole<ID, SERVICE> dto){
        return dto;
    }

    @Override
    protected Class<EntityServiceOnRole<ID, SERVICE>> getEntityClass() {
        return UtilClass.castClass(EntityServiceOnRole.class);
    }

    @Override
    protected Class getThisClass() {
        return this.getClass();
    }
}
