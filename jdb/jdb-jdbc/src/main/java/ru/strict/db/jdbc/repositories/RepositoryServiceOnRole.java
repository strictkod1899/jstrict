package ru.strict.db.jdbc.repositories;

import ru.strict.db.core.common.GenerateIdType;
import ru.strict.db.core.common.SqlParameters;
import ru.strict.db.core.connections.ICreateConnection;
import ru.strict.db.core.requests.DbTable;
import ru.strict.models.Roleuser;
import ru.strict.models.ServiceOnRole;
import ru.strict.db.core.entities.EntityServiceOnRole;
import ru.strict.db.core.mappers.dto.MapperDtoBase;
import ru.strict.db.core.repositories.IRepository;
import ru.strict.db.core.repositories.interfaces.IRepositoryServiceOnRole;
import ru.strict.db.jdbc.mappers.sql.MapperSqlServiceOnRole;

import java.sql.Connection;

public class RepositoryServiceOnRole<ID, SERVICE>
        extends RepositoryJdbcBase<ID, EntityServiceOnRole<ID, SERVICE>, ServiceOnRole<ID, SERVICE>>
        implements IRepositoryServiceOnRole<ID, SERVICE> {

    private static final String[] COLUMNS_NAME = new String[] {"service_id", "roleuser_id"};

    public RepositoryServiceOnRole(ICreateConnection<Connection> connectionSource,
                                   MapperDtoBase<ID, EntityServiceOnRole<ID, SERVICE>, ServiceOnRole<ID, SERVICE>> dtoMapper,
                                   GenerateIdType generateIdType) {
        super(new DbTable("service_on_role", "sr"),
                COLUMNS_NAME,
                connectionSource,
                dtoMapper,
                new MapperSqlServiceOnRole<ID, SERVICE>(COLUMNS_NAME),
                generateIdType);
    }

    @Override
    protected SqlParameters getParameters(EntityServiceOnRole<ID, SERVICE> entity){
        SqlParameters parameters = new SqlParameters();
        parameters.add(0, COLUMNS_NAME[0], entity.getServiceId());
        parameters.add(1, COLUMNS_NAME[1], entity.getRoleId());
        return parameters;
    }

    @Override
    protected ServiceOnRole<ID, SERVICE> fill(ServiceOnRole<ID, SERVICE> dto){
        // Добавление роли пользователя
        IRepository<ID, Roleuser<ID>> repositoryRoleuser = new RepositoryRoleuser(getConnectionSource(), GenerateIdType.NONE);
        dto.setRole(repositoryRoleuser.read(dto.getRoleId()));
        return dto;
    }

    @Override
    protected Class getThisClass() {
        return this.getClass();
    }
}
