package ru.strict.db.repositories;

import ru.strict.db.connections.StrictCreateConnectionByDataSource;
import ru.strict.db.dto.StrictDtoRoleuser;
import ru.strict.db.dto.StrictDtoUser;
import ru.strict.db.dto.StrictDtoUserBase;
import ru.strict.db.dto.StrictDtoUserOnRole;
import ru.strict.db.entities.StrictEntityUser;
import ru.strict.db.entities.StrictEntityUserOnRole;
import ru.strict.db.mappers.dto.StrictMapperDtoBase;
import ru.strict.db.mappers.spring.StrictMapperSqlUser;
import ru.strict.db.requests.StrictDbWhere;
import ru.strict.db.requests.StrictDbRequests;
import ru.strict.utils.StrictUtilLogger;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class StrictRepositoryUser<ID, DTO extends StrictDtoUserBase>
        extends StrictRepositorySpringBase<ID, StrictEntityUser, DTO> {

    private static final String[] COLUMNS_NAME = new String[] {"username", "passwordencode", "token"};

    public StrictRepositoryUser(StrictCreateConnectionByDataSource connectionSource,
                                StrictMapperDtoBase<StrictEntityUser, DTO> dtoMapper,
                                boolean isGenerateId) {
        super("userx", COLUMNS_NAME, connectionSource,  dtoMapper, isGenerateId, new StrictMapperSqlUser(COLUMNS_NAME));
    }

    @Override
    protected Map<Integer, Object> getValueByColumn(DTO dto){
        if(dto instanceof StrictDtoUser) {
            StrictDtoUser dtoUser = (StrictDtoUser) dto;
            Map<Integer, Object> valuesByColumn = new LinkedHashMap();
            valuesByColumn.put(0, dtoUser.getUsername());
            valuesByColumn.put(1, dtoUser.getPasswordEncode());
            valuesByColumn.put(2, dtoUser.getToken());
            return valuesByColumn;
        }else {
            StrictUtilLogger.error(StrictRepositoryUser.class, "Expected a type dto-object is StrictDtoUser");
            throw new IllegalArgumentException("Expected a type dto-object is StrictDtoUser");
        }
    }

    @Override
    protected DTO fill(DTO dto){
        StrictRepositorySpringBase<ID, StrictEntityUserOnRole, StrictDtoUserOnRole> rUserOnRole =
                new StrictRepositoryUserOnRole(getConnectionSource(), false);
        StrictDbRequests requests = new StrictDbRequests(rUserOnRole.getTableName(), true);
        requests.add(new StrictDbWhere(getTableName(), "id", dto.getId(), "="));
        List<StrictDtoUserOnRole> userOnRoles = rUserOnRole.readAll(requests);

        IStrictRepository<ID, StrictDtoRoleuser> rRoleuser = new StrictRepositoryRoleuser<>(getConnectionSource(), false);
        for(StrictDtoUserOnRole<ID> userOnRole : userOnRoles)
            dto.addRoleuser(rRoleuser.read(userOnRole.getRoleId()));
        return dto;
    }
}
