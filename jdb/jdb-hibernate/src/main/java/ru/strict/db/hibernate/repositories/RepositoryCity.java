package ru.strict.db.hibernate.repositories;

import ru.strict.db.core.common.GenerateIdType;
import ru.strict.db.core.dto.DtoCity;
import ru.strict.db.hibernate.connection.CreateConnectionHibernate;
import ru.strict.db.hibernate.entities.EntityCity;
import ru.strict.db.hibernate.mappers.dto.MapperDtoCity;
import ru.strict.db.hibernate.mappers.dto.MapperDtoCountry;

import java.io.Serializable;

public class RepositoryCity extends RepositoryNamedBase<EntityCity, DtoCity> {

    private static final String[] COLUMNS_NAME = new String[] {"caption", "country_id"};

    public RepositoryCity(CreateConnectionHibernate connectionSource, GenerateIdType isGenerateId) {
        super("city",
                COLUMNS_NAME,
                connectionSource,
                new MapperDtoCity(new MapperDtoCountry()),
                isGenerateId);
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
    protected Class<EntityCity> getEntityClass() {
        return EntityCity.class;
    }
}
