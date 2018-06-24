package ru.strict.db.jdbc.repositories;

import ru.strict.db.core.common.GenerateIdType;
import ru.strict.db.core.common.MapperDtoType;
import ru.strict.db.core.connections.ICreateConnection;
import ru.strict.db.core.dto.DtoCountry;
import ru.strict.db.core.dto.DtoRoleuser;
import ru.strict.db.core.entities.EntityCountry;
import ru.strict.db.core.entities.EntityRoleuser;
import ru.strict.db.core.mappers.dto.MapperDtoFactory;
import ru.strict.db.jdbc.mappers.sql.MapperSqlCountry;
import ru.strict.db.jdbc.mappers.sql.MapperSqlRoleuser;

import java.util.LinkedHashMap;
import java.util.Map;

public class RepositoryCountry<ID, SOURCE extends ICreateConnection>
        extends RepositoryJdbcBase<ID, SOURCE, EntityCountry, DtoCountry> {

    private static final String[] COLUMNS_NAME = new String[] {"caption"};

    public RepositoryCountry(SOURCE connectionSource, GenerateIdType isGenerateId) {
        super("country", COLUMNS_NAME, connectionSource,
                new MapperDtoFactory().instance(MapperDtoType.COUNTRY),
                new MapperSqlCountry(COLUMNS_NAME),
                isGenerateId);
    }

    @Override
    protected Map<Integer, Object> getValueByColumn(DtoCountry dto){
        Map<Integer, Object> valuesByColumn = new LinkedHashMap();
        valuesByColumn.put(0, dto.getCaption());
        return valuesByColumn;
    }

    @Override
    protected DtoCountry fill(DtoCountry dto){
        return dto;
    }
}
