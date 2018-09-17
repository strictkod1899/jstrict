package ru.strict.db.hibernate.repositories;

import ru.strict.db.core.common.GenerateIdType;
import ru.strict.db.core.common.MapperDtoType;
import ru.strict.db.core.dto.DtoCity;
import ru.strict.db.hibernate.connection.CreateConnectionHibernate;
import ru.strict.db.hibernate.entities.EntityCity;
import ru.strict.db.hibernate.mappers.dto.MapperDtoFactory;
import ru.strict.utils.UtilClassOperations;

import java.io.Serializable;

public class RepositoryCity<ID extends Serializable> extends RepositoryNamedBase<ID, EntityCity<ID>, DtoCity<ID>> {

    private static final String[] COLUMNS_NAME = new String[] {"caption", "country_id"};

    public RepositoryCity(CreateConnectionHibernate connectionSource, GenerateIdType generateIdType) {
        super("city",
                COLUMNS_NAME,
                connectionSource,
                new MapperDtoFactory<ID, EntityCity<ID>, DtoCity<ID>>().instance(MapperDtoType.CITY),
                generateIdType);
    }

    @Override
    protected DtoCity fill(DtoCity dto){
        return dto;
    }

    @Override
    protected String getColumnWithName() {
        return COLUMNS_NAME[0];
    }

    @Override
    protected Class<EntityCity<ID>> getEntityClass() {
        return UtilClassOperations.<EntityCity<ID>>castClass(EntityCity.class);
    }

    @Override
    protected Class getThisClass() {
        return this.getClass();
    }
}
