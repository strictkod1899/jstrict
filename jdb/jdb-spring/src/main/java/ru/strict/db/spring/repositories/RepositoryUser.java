package ru.strict.db.spring.repositories;

import ru.strict.db.core.connections.CreateConnectionByDataSource;
import ru.strict.db.core.dto.*;
import ru.strict.db.core.dto.DtoUserBase;
import ru.strict.db.core.dto.DtoUserOnRole;
import ru.strict.db.core.entities.EntityUser;
import ru.strict.db.core.entities.EntityUserOnRole;
import ru.strict.db.core.repositories.IRepository;
import ru.strict.db.core.requests.DbRequests;
import ru.strict.db.core.requests.DbWhere;
import ru.strict.db.core.mappers.dto.MapperDtoBase;
import ru.strict.db.spring.mappers.sql.MapperSqlUser;
import ru.strict.utils.StrictUtilLogger;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class RepositoryUser<ID, DTO extends DtoUserBase>
        extends RepositorySpringBase<ID, EntityUser, DTO> {

    private static final String[] COLUMNS_NAME = new String[] {"username", "passwordencode", "token"};

    public RepositoryUser(CreateConnectionByDataSource connectionSource,
                          MapperDtoBase<EntityUser, DTO> dtoMapper,
                          boolean isGenerateId) {
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
            StrictUtilLogger.error(RepositoryUser.class, "Expected a type dto-object is DtoUser");
            throw new IllegalArgumentException("Expected a type dto-object is DtoUser");
        }
    }

    @Override
    protected DTO fill(DTO dto){
        RepositorySpringBase<ID, EntityUserOnRole, DtoUserOnRole> rUserOnRole =
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
