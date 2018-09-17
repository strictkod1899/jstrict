package ru.strict.db.hibernate.repositories;

import ru.strict.db.core.common.GenerateIdType;
import ru.strict.db.core.common.MapperDtoType;
import ru.strict.db.core.dto.DtoCountry;
import ru.strict.db.hibernate.entities.EntityCountry;
import ru.strict.db.hibernate.connection.CreateConnectionHibernate;
import ru.strict.db.hibernate.mappers.dto.MapperDtoFactory;
import ru.strict.utils.UtilClassOperations;

import java.io.Serializable;

public class RepositoryCountry<ID extends Serializable> extends RepositoryNamedBase<ID, EntityCountry<ID>, DtoCountry<ID>> {

    private static final String[] COLUMNS_NAME = new String[] {"caption"};

    public RepositoryCountry(CreateConnectionHibernate connectionSource, GenerateIdType generateIdType) {
        super("country",
                COLUMNS_NAME,
                connectionSource,
                new MapperDtoFactory<ID, EntityCountry<ID>, DtoCountry<ID>>().instance(MapperDtoType.COUNTRY),
                generateIdType);
    }

    @Override
    protected DtoCountry<ID> fill(DtoCountry<ID> dto){
        return dto;
    }

    @Override
    protected String getColumnWithName() {
        return COLUMNS_NAME[0];
    }

    @Override
    protected Class<EntityCountry<ID>> getEntityClass() {
        return UtilClassOperations.<EntityCountry<ID>>castClass(EntityCountry.class);
    }

    @Override
    protected Class getThisClass() {
        return this.getClass();
    }
}
