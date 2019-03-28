package ru.strict.db.hibernate.repositories;

import ru.strict.db.core.common.GenerateIdType;
import ru.strict.db.core.dto.DtoCountry;
import ru.strict.db.core.repositories.IRepositoryNamed;
import ru.strict.db.hibernate.entities.EntityCountry;
import ru.strict.db.hibernate.connection.CreateConnectionHibernate;
import ru.strict.db.hibernate.mappers.dto.MapperDtoFactory;
import ru.strict.utils.UtilClass;

import java.io.Serializable;

public class RepositoryCountry<ID extends Serializable>
        extends RepositoryHibernateBase<ID, EntityCountry<ID>, DtoCountry<ID>>
        implements IRepositoryNamed<ID, DtoCountry<ID>> {

    private static final String[] COLUMNS_NAME = new String[] {"caption"};

    public RepositoryCountry(CreateConnectionHibernate connectionSource, GenerateIdType generateIdType) {
        super("country",
                COLUMNS_NAME,
                connectionSource,
                new MapperDtoFactory<ID>().instance(UtilClass.castClass(EntityCountry.class), UtilClass.castClass(DtoCountry.class)),
                generateIdType);
    }

    @Override
    protected DtoCountry<ID> fill(DtoCountry<ID> dto){
        return dto;
    }

    @Override
    public String getColumnWithName() {
        return COLUMNS_NAME[0];
    }

    @Override
    protected Class<EntityCountry<ID>> getEntityClass() {
        return UtilClass.castClass(EntityCountry.class);
    }

    @Override
    protected Class getThisClass() {
        return this.getClass();
    }
}
