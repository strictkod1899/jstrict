package ru.strict.db.spring.repositories;

import ru.strict.db.core.connections.CreateConnectionByDataSource;
import ru.strict.db.core.dto.DtoRoleuser;
import ru.strict.db.core.entities.EntityRoleuser;
import ru.strict.db.core.mappers.dto.StrictMapperDtoFactory;
import ru.strict.db.spring.mappers.sql.MapperSqlRoleuser;

import java.util.LinkedHashMap;
import java.util.Map;

public class RepositoryRoleuser<ID>
        extends RepositorySpringBase<ID, EntityRoleuser, DtoRoleuser> {

    private static final String[] COLUMNS_NAME = new String[] {"symbols", "description"};

    public RepositoryRoleuser(CreateConnectionByDataSource connectionSource, Boolean isGenerateId) {
        super("roleuser", COLUMNS_NAME, connectionSource,
                StrictMapperDtoFactory.createMapperRoleuser(),
                new MapperSqlRoleuser(COLUMNS_NAME),
                isGenerateId);
    }

    @Override
    protected Map<Integer, Object> getValueByColumn(DtoRoleuser dto){
        Map<Integer, Object> valuesByColumn = new LinkedHashMap();
        valuesByColumn.put(0, dto.getSymbols());
        valuesByColumn.put(1, dto.getDescription());
        return valuesByColumn;
    }

    @Override
    protected DtoRoleuser fill(DtoRoleuser dto){
        return dto;
    }
}
