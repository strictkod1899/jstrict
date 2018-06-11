package ru.strict.db.jdbc.repositories;

import ru.strict.db.core.common.MapperDtoType;
import ru.strict.db.core.connections.ICreateConnection;
import ru.strict.db.core.dto.DtoUserOnRole;
import ru.strict.db.core.entities.EntityUserOnRole;
import ru.strict.db.core.mappers.dto.StrictMapperDtoFactory;
import ru.strict.db.jdbc.mappers.sql.MapperSqlUserOnRole;

import java.util.LinkedHashMap;
import java.util.Map;

public class RepositoryUserOnRole<ID, SOURCE extends ICreateConnection>
        extends RepositoryJdbcBase<ID, SOURCE, EntityUserOnRole, DtoUserOnRole> {

    private static final String[] COLUMNS_NAME = new String[] {"user_id", "roleuser_id"};

    public RepositoryUserOnRole(SOURCE connectionSource, boolean isGenerateId) {
        super("userOnRole", COLUMNS_NAME, connectionSource,
                new StrictMapperDtoFactory().instance(MapperDtoType.USER_ON_ROLE),
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
