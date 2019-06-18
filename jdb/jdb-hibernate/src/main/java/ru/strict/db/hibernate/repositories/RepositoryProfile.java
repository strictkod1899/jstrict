package ru.strict.db.hibernate.repositories;

import ru.strict.db.core.common.GenerateIdType;
import ru.strict.db.core.requests.DbTable;
import ru.strict.models.Profile;
import ru.strict.db.core.repositories.interfaces.IRepositoryProfile;
import ru.strict.db.hibernate.connection.CreateConnectionHibernate;
import ru.strict.db.hibernate.entities.EntityProfile;
import ru.strict.db.hibernate.mappers.dto.MapperDtoFactory;
import ru.strict.utils.UtilClass;

/**
 * Репозиторий таблицы "profile". Определяет столбцы: "name", "surname", "middlename", "userx_id"
 */
public class RepositoryProfile
        extends RepositoryHibernateBase<Long, EntityProfile, Profile<Long>>
        implements IRepositoryProfile<Long, Profile<Long>> {

    private static final String[] COLUMNS_NAME = new String[] {"name", "surname", "middlename", "userx_id"};

    public RepositoryProfile(CreateConnectionHibernate connectionSource, GenerateIdType generateIdType) {
        super(new DbTable("profile", "pr"),
                COLUMNS_NAME,
                connectionSource,
                new MapperDtoFactory().instance(UtilClass.castClass(EntityProfile.class), UtilClass.castClass(Profile.class)),
                generateIdType);
    }

    @Override
    protected Profile<Long> fill(Profile<Long> dto){
        return dto;
    }

    @Override
    protected Class<EntityProfile> getEntityClass() {
        return UtilClass.castClass(EntityProfile.class);
    }

    @Override
    protected Class getThisClass() {
        return this.getClass();
    }
}
