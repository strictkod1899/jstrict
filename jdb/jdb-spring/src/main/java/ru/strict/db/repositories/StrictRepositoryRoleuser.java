package ru.strict.db.repositories;

import ru.strict.db.connections.StrictCreateConnectionByDataSource;
import ru.strict.db.dto.StrictDtoRoleuser;
import ru.strict.db.dto.StrictDtoUser;
import ru.strict.db.dto.StrictDtoUserOnRole;
import ru.strict.db.entities.StrictEntityRoleuser;
import ru.strict.db.entities.StrictEntityUserOnRole;
import ru.strict.db.mappers.dto.StrictMapperDtoFactory;
import ru.strict.db.mappers.dto.StrictMapperDtoRoleuser;
import ru.strict.db.mappers.dto.StrictMapperDtoUser;
import ru.strict.db.mappers.spring.StrictMapperSqlRoleuser;
import ru.strict.db.requests.StrictDbRequests;
import ru.strict.db.requests.StrictDbWhere;

import java.util.LinkedHashMap;
import java.util.List;
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
