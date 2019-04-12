package ru.strict.db.hibernate.repositories;

import ru.strict.db.core.common.GenerateIdType;
import ru.strict.db.core.dto.DtoCity;
import ru.strict.db.core.repositories.interfaces.IRepositoryCity;
import ru.strict.db.hibernate.connection.CreateConnectionHibernate;
import ru.strict.db.hibernate.entities.EntityCity;
import ru.strict.db.hibernate.mappers.dto.MapperDtoFactory;
import ru.strict.utils.UtilClass;

import java.io.Serializable;

public class RepositoryCity
        extends RepositoryHibernateBase<Long, EntityCity, DtoCity<Long>>
        implements IRepositoryCity<Long> {

    private static final String[] COLUMNS_NAME = new String[] {"caption", "country_id"};

    public RepositoryCity(CreateConnectionHibernate connectionSource, GenerateIdType generateIdType) {
        super("city",
                COLUMNS_NAME,
                connectionSource,
                new MapperDtoFactory().instance(UtilClass.castClass(EntityCity.class), UtilClass.castClass(DtoCity.class)),
                generateIdType);
    }

    @Override
    protected DtoCity fill(DtoCity dto){
        return dto;
    }

    @Override
    public String getColumnWithName() {
        return COLUMNS_NAME[0];
    }

    @Override
    protected Class<EntityCity> getEntityClass() {
        return UtilClass.castClass(EntityCity.class);
    }

    @Override
    protected Class getThisClass() {
        return this.getClass();
    }
}
