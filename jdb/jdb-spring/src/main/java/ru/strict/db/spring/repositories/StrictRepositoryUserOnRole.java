package ru.strict.db.spring.repositories;

import ru.strict.db.core.connections.StrictCreateConnectionByDataSource;
import ru.strict.db.core.dto.StrictDtoUserOnRole;
import ru.strict.db.core.entities.StrictEntityUserOnRole;
import ru.strict.db.core.mappers.dto.*;
import ru.strict.db.spring.mappers.sql.StrictMapperSqlUserOnRole;

import java.util.LinkedHashMap;
import java.util.Map;

public class StrictRepositoryUserOnRole<ID>
        extends StrictRepositorySpringBase<ID, StrictEntityUserOnRole, StrictDtoUserOnRole> {

    private static final String[] COLUMNS_NAME = new String[] {"user_id", "roleuser_id"};

    public StrictRepositoryUserOnRole(StrictCreateConnectionByDataSource connectionSource,
                                      boolean isGenerateId) {
        super("userOnRole", COLUMNS_NAME, connectionSource
                , StrictMapperDtoFactory.createMapperUserOnRole()
                , isGenerateId, new StrictMapperSqlUserOnRole(COLUMNS_NAME));
    }

    @Override
    protected Map<Integer, Object> getValueByColumn(StrictDtoUserOnRole dto) {
        Map<Integer, Object> valuesByColumn = new LinkedHashMap();
        valuesByColumn.put(0, dto.getUserId());
        valuesByColumn.put(1, dto.getRoleId());
        return valuesByColumn;
    }

    @Override
    protected StrictDtoUserOnRole fill(StrictDtoUserOnRole dto){
        return dto;
    }
}
