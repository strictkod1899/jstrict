package ru.strict.db.hibernate.repositories;

import ru.strict.db.core.common.GenerateIdType;
import ru.strict.db.core.dto.DtoCountry;
import ru.strict.db.core.repositories.IRepositoryNamed;
import ru.strict.db.hibernate.entities.EntityCountry;
import ru.strict.db.hibernate.connection.CreateConnectionHibernate;
import ru.strict.db.hibernate.mappers.dto.MapperDtoFactory;
import ru.strict.utils.UtilClass;

import java.io.Serializable;

public class RepositoryCountry
        extends RepositoryHibernateBase<Long, EntityCountry, DtoCountry<Long>>
        implements IRepositoryNamed<Long, DtoCountry<Long>> {

    private static final String[] COLUMNS_NAME = new String[] {"caption"};

    public RepositoryCountry(CreateConnectionHibernate connectionSource, GenerateIdType generateIdType) {
        super("country",
                COLUMNS_NAME,
                connectionSource,
                new MapperDtoFactory().instance(UtilClass.castClass(EntityCountry.class), UtilClass.castClass(DtoCountry.class)),
                generateIdType);
    }

    @Override
    protected DtoCountry<Long> fill(DtoCountry<Long> dto){
        return dto;
    }

    @Override
    public String getColumnWithName() {
        return COLUMNS_NAME[0];
    }

    @Override
    protected Class<EntityCountry> getEntityClass() {
        return UtilClass.castClass(EntityCountry.class);
    }

    @Override
    protected Class getThisClass() {
        return this.getClass();
    }
}
