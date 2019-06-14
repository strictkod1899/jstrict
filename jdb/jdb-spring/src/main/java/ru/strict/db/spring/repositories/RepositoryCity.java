package ru.strict.db.spring.repositories;

import ru.strict.db.core.common.GenerateIdType;

import ru.strict.db.core.common.SqlParameters;
import ru.strict.db.core.connections.CreateConnectionByDataSource;
import ru.strict.db.core.dto.DtoCity;
import ru.strict.db.core.dto.DtoCountry;
import ru.strict.db.core.entities.EntityCity;
import ru.strict.db.core.mappers.dto.MapperDtoFactory;
import ru.strict.db.core.repositories.IRepository;
import ru.strict.db.core.repositories.interfaces.IRepositoryCity;
import ru.strict.db.spring.mappers.sql.MapperSqlCity;
import ru.strict.utils.UtilClass;

import java.util.LinkedHashMap;
import java.util.Map;

public class RepositoryCity<ID>
        extends RepositorySpringBase<ID, EntityCity<ID>, DtoCity<ID>>
        implements IRepositoryCity<ID> {

    private static final String[] COLUMNS_NAME = new String[] {"caption", "country_id"};

    public RepositoryCity(CreateConnectionByDataSource connectionSource, GenerateIdType generateIdType) {
        super("city", COLUMNS_NAME, connectionSource,
                new MapperDtoFactory<ID>().instance(UtilClass.castClass(EntityCity.class), UtilClass.castClass(DtoCity.class)),
                new MapperSqlCity<ID>(COLUMNS_NAME),
                generateIdType);
    }

    @Override
    protected SqlParameters getParameters(EntityCity<ID> entity){
        SqlParameters parameters = new SqlParameters();
        parameters.add(0, COLUMNS_NAME[0], entity.getCaption());
        parameters.add(1, COLUMNS_NAME[1], entity.getCountryId());
        return parameters;
    }

    @Override
    protected DtoCity<ID> fill(DtoCity<ID> dto){
        IRepository<ID, DtoCountry<ID>> repositoryCountry = new RepositoryCountry(getConnectionSource(), GenerateIdType.NONE);
        dto.setCountry(repositoryCountry.read(dto.getCountryId()));
        return dto;
    }

    @Override
    public String getColumnWithName() {
        return COLUMNS_NAME[0];
    }

    @Override
    protected Class getThisClass() {
        return this.getClass();
    }
}
