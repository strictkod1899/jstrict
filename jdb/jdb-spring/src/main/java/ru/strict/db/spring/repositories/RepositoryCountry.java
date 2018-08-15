package ru.strict.db.spring.repositories;

import ru.strict.db.core.common.GenerateIdType;
import ru.strict.db.core.common.MapperDtoType;
import ru.strict.db.core.connections.CreateConnectionByDataSource;
import ru.strict.db.core.connections.ICreateConnection;
import ru.strict.db.core.dto.DtoCity;
import ru.strict.db.core.dto.DtoCountry;
import ru.strict.db.core.entities.EntityCity;
import ru.strict.db.core.entities.EntityCountry;
import ru.strict.db.core.mappers.dto.MapperDtoFactory;
import ru.strict.db.core.requests.DbRequests;
import ru.strict.db.core.requests.DbWhere;
import ru.strict.db.spring.mappers.sql.MapperSqlCountry;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class RepositoryCountry<ID>
        extends RepositoryNamedBase<ID, EntityCountry, DtoCountry> {

    private static final String[] COLUMNS_NAME = new String[] {"caption"};

    public RepositoryCountry(CreateConnectionByDataSource connectionSource, GenerateIdType isGenerateId) {
        super("country", COLUMNS_NAME, connectionSource,
                new MapperDtoFactory().instance(MapperDtoType.COUNTRY),
                new MapperSqlCountry(COLUMNS_NAME),
                isGenerateId);
    }

    @Override
    protected Map<Integer, Object> getValueByColumn(EntityCountry entity){
        Map<Integer, Object> valuesByColumn = new LinkedHashMap();
        valuesByColumn.put(0, entity.getCaption());
        return valuesByColumn;
    }

    @Override
    protected DtoCountry fill(DtoCountry dto){
        RepositorySpringBase<ID, EntityCity, DtoCity> repositoryCity =
                new RepositoryCity(getConnectionSource(), GenerateIdType.NONE);
        DbRequests requests = new DbRequests(repositoryCity.getTableName(), true);
        requests.add(new DbWhere(repositoryCity.getTableName(), "country_id", dto.getId(), "="));

        List<DtoCity> cities = repositoryCity.readAll(requests);
        dto.setCities(cities);

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
