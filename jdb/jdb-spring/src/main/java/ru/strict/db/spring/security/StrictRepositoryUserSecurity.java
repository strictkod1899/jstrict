package ru.strict.db.spring.security;

import ru.strict.db.core.connections.StrictCreateConnectionByDataSource;
import ru.strict.db.core.dto.StrictDtoRoleuser;
import ru.strict.db.core.dto.StrictDtoUserOnRole;
import ru.strict.db.core.entities.StrictEntityUserOnRole;
import ru.strict.db.core.mappers.dto.StrictMapperDtoRoleuser;
import ru.strict.db.core.repositories.IStrictRepository;
import ru.strict.db.core.requests.StrictDbRequests;
import ru.strict.db.core.requests.StrictDbWhere;
import ru.strict.db.spring.repositories.StrictRepositoryRoleuser;
import ru.strict.db.spring.repositories.StrictRepositorySpringBase;
import ru.strict.db.spring.repositories.StrictRepositoryUserOnRole;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class StrictRepositoryUserSecurity<ID>
        extends StrictRepositorySpringBase<ID, StrictEntityUserSecurity, StrictDtoUserSecurity> {

    private static final String[] COLUMNS_NAME = new String[] {"username", "passwordencode", "token"};

    public StrictRepositoryUserSecurity(StrictCreateConnectionByDataSource connectionSource, boolean isGenerateId) {
        super("userx", COLUMNS_NAME, connectionSource,
                new StrictMapperDtoUserSecurity(new StrictMapperDtoRoleuser()),
                new StrictMapperSqlUserSecurity(COLUMNS_NAME),
                isGenerateId);
    }

    @Override
    protected Map<Integer, Object> getValueByColumn(StrictDtoUserSecurity dto){
        StrictDtoUserSecurity dtoUser = new StrictDtoUserSecurity();
        Map<Integer, Object> valuesByColumn = new LinkedHashMap();
        valuesByColumn.put(0, dtoUser.getUsername());
        valuesByColumn.put(1, dtoUser.getPasswordEncode());
        valuesByColumn.put(2, dtoUser.getToken());
        return valuesByColumn;
    }

    @Override
    protected StrictDtoUserSecurity fill(StrictDtoUserSecurity dto){
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
