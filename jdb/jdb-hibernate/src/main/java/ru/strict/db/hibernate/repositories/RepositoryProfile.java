package ru.strict.db.hibernate.repositories;

import ru.strict.db.core.common.GenerateIdType;
import ru.strict.db.core.common.MapperDtoType;
import ru.strict.db.core.dto.DtoProfile;
import ru.strict.db.hibernate.connection.CreateConnectionHibernate;
import ru.strict.db.hibernate.entities.EntityProfile;
import ru.strict.db.hibernate.mappers.dto.MapperDtoFactory;

/**
 * Репозиторий таблицы "profile". Определяет столбцы: "name", "surname", "middlename", "user_id"
 */
public class RepositoryProfile extends RepositoryHibernateBase<EntityProfile, DtoProfile> {

    private static final String[] COLUMNS_NAME = new String[] {"name", "surname", "middlename", "userx_id"};

    public RepositoryProfile(CreateConnectionHibernate connectionSource, GenerateIdType isGenerateId) {
        super("profile",
                COLUMNS_NAME,
                connectionSource,
                new MapperDtoFactory().instance(MapperDtoType.PROFILE),
                isGenerateId);
    }

    @Override
    protected DtoProfile fill(DtoProfile dto){
        return dto;
    }

    @Override
    protected Class<EntityProfile> getEntityClass() {
        return EntityProfile.class;
    }

}
