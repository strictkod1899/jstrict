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

import java.util.LinkedHashMap;
import java.util.Map;

public class RepositoryCity<ID, SOURCE extends ICreateConnection>
        extends RepositoryNamedBase<ID, SOURCE, EntityCity, DtoCity> {

    private static final String[] COLUMNS_NAME = new String[] {"caption", "country_id"};

    public RepositoryCity(SOURCE connectionSource, GenerateIdType isGenerateId) {
        super("city", COLUMNS_NAME, connectionSource,
                new MapperDtoFactory().instance(MapperDtoType.CITY),
                new MapperSqlCity(COLUMNS_NAME),
                isGenerateId);
    }

    @Override
    protected Map<Integer, Object> getValueByColumn(DtoCity dto){
        Map<Integer, Object> valuesByColumn = new LinkedHashMap();
        valuesByColumn.put(0, dto.getCaption());
        valuesByColumn.put(1, dto.getCountryId());
        return valuesByColumn;
    }

    @Override
    protected DtoCity fill(DtoCity dto){
        IRepository<ID, DtoCountry> repositoryCountry =
                new RepositoryCountry(getConnectionSource(), GenerateIdType.NONE);
        dto.setCountry(repositoryCountry.read((ID) dto.getCountryId()));
        return dto;
    }
}
