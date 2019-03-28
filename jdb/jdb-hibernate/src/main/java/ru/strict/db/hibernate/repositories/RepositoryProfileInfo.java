package ru.strict.db.hibernate.repositories;

import ru.strict.db.core.common.GenerateIdType;
import ru.strict.db.core.dto.DtoProfileInfo;
import ru.strict.db.core.repositories.interfaces.IRepositoryProfile;
import ru.strict.db.hibernate.connection.CreateConnectionHibernate;
import ru.strict.db.hibernate.entities.EntityProfileInfo;
import ru.strict.db.hibernate.mappers.dto.MapperDtoFactory;
import ru.strict.utils.UtilClass;

import java.io.Serializable;

/**
 * Репозиторий таблицы "profile" с расширенными данными.
 * Определяет столбцы: "name", "surname", "middlename", "userx_id", "datebirth", "phone", "city_id"
 */
public class RepositoryProfileInfo<ID extends Serializable>
        extends RepositoryHibernateBase<ID, EntityProfileInfo<ID>, DtoProfileInfo<ID>>
        implements IRepositoryProfile<ID, DtoProfileInfo<ID>> {

    private static final String[] COLUMNS_NAME = new String[] {"name", "surname", "middlename", "userx_id", "datebirth",
            "phone", "city_id"};

    public RepositoryProfileInfo(CreateConnectionHibernate connectionSource, GenerateIdType generateIdType) {
        super("profile",
                COLUMNS_NAME,
                connectionSource,
                new MapperDtoFactory<ID>().instance(UtilClass.castClass(EntityProfileInfo.class), UtilClass.castClass(DtoProfileInfo.class)),
                generateIdType);
    }

    @Override
    protected DtoProfileInfo<ID> fill(DtoProfileInfo<ID> dto) {
        return dto;
    }

    @Override
    protected Class<EntityProfileInfo<ID>> getEntityClass() {
        return UtilClass.castClass(EntityProfileInfo.class);
    }

    @Override
    protected Class getThisClass() {
        return this.getClass();
    }
}
