package ru.strict.db.hibernate.repositories;

import ru.strict.db.core.common.GenerateIdType;
import ru.strict.db.core.requests.DbTable;
import ru.strict.models.DetailsProfile;
import ru.strict.db.core.repositories.interfaces.IRepositoryProfile;
import ru.strict.db.hibernate.connection.CreateConnectionHibernate;
import ru.strict.db.hibernate.entities.EntityProfileInfo;
import ru.strict.db.hibernate.mappers.dto.MapperDtoFactory;
import ru.strict.utils.UtilClass;

/**
 * Репозиторий таблицы "profile" с расширенными данными.
 * Определяет столбцы: "name", "surname", "middlename", "userx_id", "datebirth", "phone", "city_id"
 */
public class RepositoryProfileInfo
        extends RepositoryHibernateBase<Long, EntityProfileInfo, DetailsProfile<Long>>
        implements IRepositoryProfile<Long, DetailsProfile<Long>> {

    private static final String[] COLUMNS_NAME = new String[] {"name", "surname", "middlename", "userx_id", "datebirth",
            "phone", "city_id"};

    public RepositoryProfileInfo(CreateConnectionHibernate connectionSource, GenerateIdType generateIdType) {
        super(new DbTable("profile", "pr"),
                COLUMNS_NAME,
                connectionSource,
                new MapperDtoFactory().instance(UtilClass.castClass(EntityProfileInfo.class), UtilClass.castClass(DetailsProfile.class)),
                generateIdType);
    }

    @Override
    protected DetailsProfile<Long> fill(DetailsProfile<Long> dto) {
        return dto;
    }

    @Override
    protected Class<EntityProfileInfo> getEntityClass() {
        return UtilClass.castClass(EntityProfileInfo.class);
    }

    @Override
    protected Class getThisClass() {
        return this.getClass();
    }
}
