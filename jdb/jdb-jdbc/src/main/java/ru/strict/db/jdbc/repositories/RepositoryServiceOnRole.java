package ru.strict.db.jdbc.repositories;

import ru.strict.db.core.common.GenerateIdType;
import ru.strict.db.core.connections.ICreateConnection;
import ru.strict.db.core.dto.DtoRoleuser;
import ru.strict.db.core.dto.DtoServiceOnRole;
import ru.strict.db.core.entities.EntityServiceOnRole;
import ru.strict.db.core.mappers.dto.MapperDtoBase;
import ru.strict.db.core.repositories.IRepository;
import ru.strict.db.core.repositories.interfaces.IRepositoryServiceOnRole;
import ru.strict.db.jdbc.mappers.sql.MapperSqlServiceOnRole;

import java.sql.Connection;
import java.util.LinkedHashMap;
import java.util.Map;

public class RepositoryServiceOnRole<ID, SERVICE>
        extends RepositoryJdbcBase<ID, EntityServiceOnRole<ID, SERVICE>, DtoServiceOnRole<ID, SERVICE>>
        implements IRepositoryServiceOnRole<ID, SERVICE> {

    private static final String[] COLUMNS_NAME = new String[] {"service_id", "roleuser_id"};

    public RepositoryServiceOnRole(ICreateConnection<Connection> connectionSource,
                                   MapperDtoBase<ID, EntityServiceOnRole<ID, SERVICE>, DtoServiceOnRole<ID, SERVICE>> dtoMapper,
                                   GenerateIdType generateIdType) {
        super("service_on_role", COLUMNS_NAME, connectionSource,
                dtoMapper,
                new MapperSqlServiceOnRole<ID, SERVICE>(COLUMNS_NAME),
                generateIdType);
    }

    @Override
    protected Map<Integer, Object> getValueByColumn(EntityServiceOnRole<ID, SERVICE> entity) {
        Map<Integer, Object> valuesByColumn = new LinkedHashMap();
        valuesByColumn.put(0, entity.getServiceId());
        valuesByColumn.put(1, entity.getRoleId());
        return valuesByColumn;
    }

    @Override
    protected DtoServiceOnRole<ID, SERVICE> fill(DtoServiceOnRole<ID, SERVICE> dto){
        IRepository<ID, DtoRoleuser<ID>> repositoryRoleuser = null;
        try {
            // Добавление роли пользователя
            repositoryRoleuser = new RepositoryRoleuser(getConnectionSource(), GenerateIdType.NONE);
            dto.setRole(repositoryRoleuser.read(dto.getRoleId()));
        }finally {
            if(repositoryRoleuser != null){
                repositoryRoleuser.close();
            }
        }
        return dto;
    }

    @Override
    protected Class getThisClass() {
        return this.getClass();
    }
}
