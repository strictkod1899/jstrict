package ru.strict.db.spring.repositories;

import ru.strict.db.core.common.GenerateIdType;
import ru.strict.db.core.common.MapperDtoType;
import ru.strict.db.core.connections.CreateConnectionByDataSource;
import ru.strict.db.core.dto.DtoUserOnRole;
import ru.strict.db.core.entities.EntityUserOnRole;
import ru.strict.db.core.mappers.dto.*;
import ru.strict.db.spring.mappers.sql.MapperSqlUserOnRole;

import java.util.LinkedHashMap;
import java.util.Map;

public class RepositoryUserOnRole<ID>
        extends RepositorySpringBase<ID, EntityUserOnRole, DtoUserOnRole> {

    private static final String[] COLUMNS_NAME = new String[] {"userx_id", "roleuser_id"};

    public RepositoryUserOnRole(CreateConnectionByDataSource connectionSource, GenerateIdType isGenerateId) {
        super("user_on_role", COLUMNS_NAME, connectionSource,
                new MapperDtoFactory().instance(MapperDtoType.USER_ON_ROLE),
                new MapperSqlUserOnRole(COLUMNS_NAME),
                isGenerateId);
    }

    @Override
    protected Map<Integer, Object> getValueByColumn(DtoUserOnRole dto) {
        Map<Integer, Object> valuesByColumn = new LinkedHashMap();
        valuesByColumn.put(0, dto.getUserId());
        valuesByColumn.put(1, dto.getRoleId());
        return valuesByColumn;
    }

    @Override
    protected DtoUserOnRole fill(DtoUserOnRole dto){
        return dto;
    }
}
