package ru.strict.db.jdbc.repositories;

import ru.strict.db.core.connections.ICreateConnection;
import ru.strict.db.core.dto.*;
import ru.strict.db.core.dto.DtoUser;
import ru.strict.db.core.dto.DtoUserOnRole;
import ru.strict.db.core.entities.EntityUser;
import ru.strict.db.core.entities.EntityUserOnRole;
import ru.strict.db.core.mappers.dto.MapperDtoBase;
import ru.strict.db.core.repositories.IRepository;
import ru.strict.db.core.requests.DbRequests;
import ru.strict.db.core.requests.DbWhere;
import ru.strict.db.jdbc.mappers.sql.MapperSqlUser;
import ru.strict.utils.UtilLogger;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class RepositoryUser<ID, SOURCE extends ICreateConnection, DTO extends DtoUserBase>
        extends RepositoryJdbcBase<ID, SOURCE, EntityUser, DTO> {

    private static final String[] COLUMNS_NAME = new String[] {"username", "passwordencode", "token"};

    public RepositoryUser(SOURCE connectionSource,
                          MapperDtoBase<EntityUser, DTO> dtoMapper,
                          Boolean isGenerateId) {
        super("userx", COLUMNS_NAME, connectionSource, dtoMapper, new MapperSqlUser(COLUMNS_NAME), isGenerateId);
    }

    @Override
    protected Map<Integer, Object> getValueByColumn(DTO dto){
        if(dto instanceof DtoUser) {
            DtoUser dtoUser = (DtoUser) dto;
            Map<Integer, Object> valuesByColumn = new LinkedHashMap();
            valuesByColumn.put(0, dtoUser.getUsername());
            valuesByColumn.put(1, dtoUser.getPasswordEncode());
            valuesByColumn.put(2, dtoUser.getToken());
            return valuesByColumn;
        }else {
            UtilLogger.error(RepositoryUser.class, "Expected a type dto-object is DtoUser");
            throw new IllegalArgumentException("Expected a type dto-object is DtoUser");
        }
    }

    @Override
    protected DTO fill(DTO dto){
        RepositoryJdbcBase<ID, SOURCE, EntityUserOnRole, DtoUserOnRole> rUserOnRole =
                new RepositoryUserOnRole(getConnectionSource(), false);
        DbRequests requests = new DbRequests(rUserOnRole.getTableName(), true);
        requests.add(new DbWhere(getTableName(), "id", dto.getId(), "="));
        List<DtoUserOnRole> userOnRoles = rUserOnRole.readAll(requests);

        IRepository<ID, DtoRoleuser> rRoleuser = new RepositoryRoleuser<>(getConnectionSource(), false);
        for(DtoUserOnRole<ID> userOnRole : userOnRoles)
            dto.addRoleuser(rRoleuser.read(userOnRole.getRoleId()));
        return dto;
    }
}
