package ru.strict.db.jdbc.repositories;

import ru.strict.db.core.common.GenerateIdType;
import ru.strict.db.core.common.MapperDtoType;
import ru.strict.db.core.connections.ICreateConnection;
import ru.strict.db.core.dto.DtoCity;
import ru.strict.db.core.dto.DtoCountry;
import ru.strict.db.core.dto.DtoRoleuser;
import ru.strict.db.core.entities.EntityCity;
import ru.strict.db.core.entities.EntityCountry;
import ru.strict.db.core.entities.EntityRoleuser;
import ru.strict.db.core.mappers.dto.MapperDtoFactory;
import ru.strict.db.core.repositories.IRepository;
import ru.strict.db.core.requests.DbRequests;
import ru.strict.db.core.requests.DbWhere;
import ru.strict.db.jdbc.mappers.sql.MapperSqlCountry;
import ru.strict.db.jdbc.mappers.sql.MapperSqlRoleuser;

import java.sql.Connection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class RepositoryCountry<ID>
        extends RepositoryNamedBase<ID, EntityCountry<ID>, DtoCountry<ID>> {

    private static final String[] COLUMNS_NAME = new String[] {"caption"};

    public RepositoryCountry(ICreateConnection<Connection> connectionSource, GenerateIdType generateIdType) {
        super("country", COLUMNS_NAME, connectionSource,
                new MapperDtoFactory<ID, EntityCountry<ID>, DtoCountry<ID>>().instance(MapperDtoType.COUNTRY),
                new MapperSqlCountry<ID>(COLUMNS_NAME),
                generateIdType);
    }

    @Override
    protected Map<Integer, Object> getValueByColumn(EntityCountry<ID> entity){
        Map<Integer, Object> valuesByColumn = new LinkedHashMap();
        valuesByColumn.put(0, entity.getCaption());
        return valuesByColumn;
    }

    @Override
    protected DtoCountry<ID> fill(DtoCountry<ID> dto){
        IRepository<ID, DtoCity<ID>> repositoryCity = null;
        try {
            repositoryCity = new RepositoryCity(getConnectionSource(), GenerateIdType.NONE);
            DbRequests requests = new DbRequests(repositoryCity.getTableName(), true);
            requests.addWhere(new DbWhere(repositoryCity.getTableName(), "country_id", dto.getId(), "="));

            List<DtoCity<ID>> cities = repositoryCity.readAll(requests);
            dto.setCities(cities);
        } finally {
            if(repositoryCity != null){
                repositoryCity.close();
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
