package ru.strict.db.jdbc.repositories;

import ru.strict.db.core.common.GenerateIdType;
import ru.strict.db.core.common.MapperDtoType;
import ru.strict.db.core.connections.ICreateConnection;
import ru.strict.db.core.dto.DtoCity;
import ru.strict.db.core.dto.DtoCountry;
import ru.strict.db.core.entities.EntityCity;
import ru.strict.db.core.entities.EntityCountry;
import ru.strict.db.core.mappers.dto.MapperDtoFactory;
import ru.strict.db.core.repositories.IRepository;
import ru.strict.db.jdbc.mappers.sql.MapperSqlCity;
import ru.strict.db.jdbc.mappers.sql.MapperSqlCountry;
import ru.strict.utils.UtilClass;

import java.sql.Connection;
import java.util.LinkedHashMap;
import java.util.Map;

public class RepositoryCity<ID>
        extends RepositoryNamedBase<ID, EntityCity<ID>, DtoCity<ID>> {

    private static final String[] COLUMNS_NAME = new String[] {"caption", "country_id"};

    public RepositoryCity(ICreateConnection<Connection> connectionSource, GenerateIdType generateIdType) {
        super("city", COLUMNS_NAME, connectionSource,
                new MapperDtoFactory<ID>().instance(UtilClass.castClass(EntityCity.class), UtilClass.castClass(DtoCity.class)),
                new MapperSqlCity<ID>(COLUMNS_NAME),
                generateIdType);
    }

    @Override
    protected Map<Integer, Object> getValueByColumn(EntityCity<ID> entity){
        Map<Integer, Object> valuesByColumn = new LinkedHashMap();
        valuesByColumn.put(0, entity.getCaption());
        valuesByColumn.put(1, entity.getCountryId());
        return valuesByColumn;
    }

    @Override
    protected DtoCity<ID> fill(DtoCity<ID> dto){
        IRepository<ID, DtoCountry<ID>> repositoryCountry = null;
        try {
            repositoryCountry = new RepositoryCountry(getConnectionSource(), GenerateIdType.NONE);
            dto.setCountry(repositoryCountry.read(dto.getCountryId()));
        }finally {
            if(repositoryCountry != null){
                repositoryCountry.close();
            }
        }
        return dto;
    }

    @Override
    protected String getColumnWithName() {
        return COLUMNS_NAME[0];
    }

    @Override
    protected Class getThisClass() {
        return this.getClass();
    }
}
