package ru.strict.db.hibernate.repositories;

import ru.strict.db.core.common.GenerateIdType;
import ru.strict.db.core.dto.DtoCountry;
import ru.strict.db.hibernate.entities.EntityCountry;
import ru.strict.db.hibernate.connection.CreateConnectionHibernate;
import ru.strict.db.hibernate.mappers.dto.MapperDtoCountry;

import java.io.Serializable;

public class RepositoryCountry<ID extends Serializable>
        extends RepositoryNamedBase<ID, EntityCountry, DtoCountry> {

    private static final String[] COLUMNS_NAME = new String[] {"caption"};

    public RepositoryCountry(CreateConnectionHibernate connectionSource, GenerateIdType isGenerateId) {
        super("country",
                COLUMNS_NAME,
                connectionSource,
                new MapperDtoCountry(),
                isGenerateId);
    }

    @Override
    protected DtoCountry fill(DtoCountry dto){
        return dto;
    }

    @Override
    protected String getColumnWithName() {
        return COLUMNS_NAME[0];
    }

    @Override
    protected EntityCountry getEmptyEntity() {
        return new EntityCountry();
    }
}
