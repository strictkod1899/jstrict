package ru.strict.db.jdbc.repositories;

import ru.strict.db.core.common.GenerateIdType;
import ru.strict.db.core.common.MapperDtoType;
import ru.strict.db.core.connections.ICreateConnection;
import ru.strict.db.core.dto.DtoRoleuser;
import ru.strict.db.core.entities.EntityRoleuser;
import ru.strict.db.core.mappers.dto.MapperDtoFactory;
import ru.strict.db.jdbc.mappers.sql.MapperSqlRoleuser;

import java.util.LinkedHashMap;
import java.util.Map;

public class RepositoryRoleuser<ID, SOURCE extends ICreateConnection>
        extends RepositoryJdbcBase<ID, SOURCE, EntityRoleuser, DtoRoleuser> {

    private static final String[] COLUMNS_NAME = new String[] {"code", "description"};

    public RepositoryRoleuser(SOURCE connectionSource, GenerateIdType isGenerateId) {
        super("roleuser", COLUMNS_NAME, connectionSource,
                new MapperDtoFactory().instance(MapperDtoType.ROLE_USER),
                new MapperSqlRoleuser(COLUMNS_NAME),
                isGenerateId);
    }

    @Override
    protected Map<Integer, Object> getValueByColumn(DtoRoleuser dto){
        Map<Integer, Object> valuesByColumn = new LinkedHashMap();
        valuesByColumn.put(0, dto.getCode());
        valuesByColumn.put(1, dto.getDescription());
        return valuesByColumn;
    }

    @Override
    protected DtoRoleuser fill(DtoRoleuser dto){
        return dto;
    }
}
