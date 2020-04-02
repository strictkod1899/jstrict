package ru.strict.db.hibernate.repositories;

import ru.strict.db.core.common.GenerateIdType;
import ru.strict.db.core.requests.DbTable;
import ru.strict.models.Country;
import ru.strict.db.core.repositories.IRepositoryNamed;
import ru.strict.db.hibernate.entities.EntityCountry;
import ru.strict.db.hibernate.connection.CreateConnectionHibernate;
import ru.strict.db.hibernate.mappers.dto.MapperDtoFactory;
import ru.strict.utils.UtilClass;

public class RepositoryCountry
        extends RepositoryHibernateBase<Long, EntityCountry, Country<Long>>
        implements IRepositoryNamed<Long, Country<Long>> {

    private static final String[] COLUMNS_NAME = new String[] {"caption"};

    public RepositoryCountry(CreateConnectionHibernate connectionSource, GenerateIdType generateIdType) {
        super(new DbTable("country", "co"),
                COLUMNS_NAME,
                connectionSource,
                new MapperDtoFactory().instance(UtilClass.castClass(EntityCountry.class), UtilClass.castClass(Country.class)),
                generateIdType);
    }

    @Override
    protected Country<Long> fill(Country<Long> dto){
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
