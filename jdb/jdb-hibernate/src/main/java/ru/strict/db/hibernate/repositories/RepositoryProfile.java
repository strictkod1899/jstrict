package ru.strict.db.hibernate.repositories;

import ru.strict.db.core.common.GenerateIdType;
import ru.strict.db.core.common.MapperDtoType;
import ru.strict.db.core.dto.DtoProfile;
import ru.strict.db.hibernate.connection.CreateConnectionHibernate;
import ru.strict.db.hibernate.entities.EntityProfile;
import ru.strict.db.hibernate.mappers.dto.MapperDtoFactory;
import ru.strict.utils.UtilClass;

import java.io.Serializable;

/**
 * Репозиторий таблицы "profile". Определяет столбцы: "name", "surname", "middlename", "user_id"
 */
public class RepositoryProfile<ID extends Serializable> extends RepositoryHibernateBase<ID, EntityProfile<ID>, DtoProfile<ID>> {

    private static final String[] COLUMNS_NAME = new String[] {"name", "surname", "middlename", "userx_id"};

    public RepositoryProfile(CreateConnectionHibernate connectionSource, GenerateIdType generateIdType) {
        super("profile",
                COLUMNS_NAME,
                connectionSource,
                new MapperDtoFactory<ID>()
                        .instance(UtilClass.castClass(EntityProfile.class), UtilClass.castClass(DtoProfile.class)),
                generateIdType);
    }

    @Override
    protected DtoProfile<ID> fill(DtoProfile<ID> dto){
        return dto;
    }

    @Override
    protected Class<EntityProfile<ID>> getEntityClass() {
        return UtilClass.castClass(EntityProfile.class);
    }

    @Override
    protected Class getThisClass() {
        return this.getClass();
    }
}
