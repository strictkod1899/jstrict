package ru.strict.db.repositories;

import ru.strict.db.connections.StrictCreateConnectionByDataSource;
import ru.strict.db.dto.StrictDtoUser;
import ru.strict.db.dto.StrictDtoUserBase;
import ru.strict.db.entities.StrictEntityUser;
import ru.strict.db.mappers.dto.StrictMapperDtoUser;
import ru.strict.db.mappers.spring.StrictMapperSqlUser;
import ru.strict.utils.StrictUtilLogger;

import java.util.LinkedHashMap;
import java.util.Map;

public class StrictRepositoryUser<ID, DTO extends StrictDtoUserBase>
        extends StrictRepositorySpringBase<ID, StrictEntityUser, DTO> {

    private static final String[] COLUMNS_NAME = new String[] {"username", "passwordEncode", "roleuser", "token"};

    public StrictRepositoryUser(StrictCreateConnectionByDataSource connectionSource,
                                boolean isGenerateId,
                                StrictMapperDtoUser dtoMapper) {
        super("user", COLUMNS_NAME, connectionSource,  dtoMapper, isGenerateId, new StrictMapperSqlUser(COLUMNS_NAME));
    }

    @Override
    protected Map<Integer, Object> getValueByColumn(DTO dto){
        if(dto instanceof StrictDtoUser) {
            StrictDtoUser dtoUser = (StrictDtoUser) dto;
            Map<Integer, Object> valuesByColumn = new LinkedHashMap();
            valuesByColumn.put(0, dtoUser.getUsername());
            valuesByColumn.put(1, dtoUser.getPasswordEncode());
            valuesByColumn.put(2, dtoUser.getRoleuserId());
            valuesByColumn.put(3, dtoUser.getToken());
            return valuesByColumn;
        }else {
            StrictUtilLogger.error(StrictRepositoryUser.class, "Expected a type dto-object is StrictDtoUser");
            throw new IllegalArgumentException("Expected a type dto-object is StrictDtoUser");
        }
    }
}
