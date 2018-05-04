package ru.strict.db.repositories;

import ru.strict.db.connections.StrictCreateConnectionByDataSource;
import ru.strict.db.dto.StrictDtoUser;
import ru.strict.db.dto.StrictDtoUserBase;
import ru.strict.db.dto.StrictDtoUserOnRole;
import ru.strict.db.entities.StrictEntityUser;
import ru.strict.db.entities.StrictEntityUserOnRole;
import ru.strict.db.mappers.dto.*;
import ru.strict.db.mappers.spring.StrictMapperSqlUser;
import ru.strict.db.mappers.spring.StrictMapperSqlUserOnRole;
import ru.strict.utils.StrictUtilLogger;

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
