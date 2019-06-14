package ru.strict.db.spring.repositories;

import ru.strict.db.core.common.GenerateIdType;
import ru.strict.db.core.common.SqlParameters;
import ru.strict.db.core.connections.CreateConnectionByDataSource;
import ru.strict.db.core.dto.DtoCity;
import ru.strict.db.core.dto.DtoCountry;
import ru.strict.db.core.entities.EntityCountry;
import ru.strict.db.core.mappers.dto.MapperDtoFactory;
import ru.strict.db.core.repositories.IRepository;
import ru.strict.db.core.repositories.IRepositoryNamed;
import ru.strict.db.core.requests.DbRequests;
import ru.strict.db.core.requests.DbWhereItem;
import ru.strict.db.spring.mappers.sql.MapperSqlCountry;
import ru.strict.utils.UtilClass;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class RepositoryCountry<ID>
        extends RepositorySpringBase<ID, EntityCountry<ID>, DtoCountry<ID>>
        implements IRepositoryNamed<ID, DtoCountry<ID>> {

    private static final String[] COLUMNS_NAME = new String[] {"caption"};

    public RepositoryCountry(CreateConnectionByDataSource connectionSource, GenerateIdType generateIdType) {
        super("country", COLUMNS_NAME, connectionSource,
                new MapperDtoFactory<ID>().instance(UtilClass.castClass(EntityCountry.class), UtilClass.castClass(DtoCountry.class)),
                new MapperSqlCountry<ID>(COLUMNS_NAME),
                generateIdType);
    }

    @Override
    protected SqlParameters getParameters(EntityCountry<ID> entity){
        SqlParameters parameters = new SqlParameters();
        parameters.add(0, COLUMNS_NAME[0], entity.getCaption());
        return parameters;
    }

    @Override
    protected DtoCountry<ID> fill(DtoCountry<ID> dto){
        IRepository<ID, DtoCity<ID>> repositoryCity = new RepositoryCity(getConnectionSource(), GenerateIdType.NONE);
        DbRequests requests = new DbRequests();
        requests.addWhere(new DbWhereItem(repositoryCity.getTableName(), "country_id", dto.getId(), "="));

        List<DtoCity<ID>> cities = repositoryCity.readAll(requests);
        dto.setCities(cities);

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
