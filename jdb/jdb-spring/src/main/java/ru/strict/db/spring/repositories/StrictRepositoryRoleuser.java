package ru.strict.db.spring.repositories;

import ru.strict.db.core.connections.StrictCreateConnectionByDataSource;
import ru.strict.db.core.dto.StrictDtoRoleuser;
import ru.strict.db.core.entities.StrictEntityRoleuser;
import ru.strict.db.core.mappers.dto.StrictMapperDtoFactory;
import ru.strict.db.spring.mappers.sql.StrictMapperSqlRoleuser;

import java.util.LinkedHashMap;
import java.util.Map;

public class StrictRepositoryRoleuser<ID>
        extends StrictRepositorySpringBase<ID, StrictEntityRoleuser, StrictDtoRoleuser> {

    private static final String[] COLUMNS_NAME = new String[] {"symbols", "description"};

    public StrictRepositoryRoleuser(StrictCreateConnectionByDataSource connectionSource, Boolean isGenerateId) {
        super("roleuser", COLUMNS_NAME, connectionSource, StrictMapperDtoFactory.createMapperRoleuser(), isGenerateId,
                new StrictMapperSqlRoleuser(COLUMNS_NAME));
    }

    @Override
    protected Map<Integer, Object> getValueByColumn(StrictDtoRoleuser dto){
        Map<Integer, Object> valuesByColumn = new LinkedHashMap();
        valuesByColumn.put(0, dto.getSymbols());
        valuesByColumn.put(1, dto.getDescription());
        return valuesByColumn;
    }

    @Override
    protected StrictDtoRoleuser fill(StrictDtoRoleuser dto){
        return dto;
    }
}
