package ru.strict.db.spring.security;

import ru.strict.db.core.common.GenerateIdType;
import ru.strict.db.core.connections.CreateConnectionByDataSource;
import ru.strict.db.core.dto.DtoRoleuser;
import ru.strict.db.core.dto.DtoUserOnRole;
import ru.strict.db.core.entities.EntityUserOnRole;
import ru.strict.db.core.mappers.dto.MapperDtoRoleuser;
import ru.strict.db.core.repositories.IRepository;
import ru.strict.db.core.requests.DbRequests;
import ru.strict.db.core.requests.DbWhere;
import ru.strict.db.spring.repositories.RepositoryRoleuser;
import ru.strict.db.spring.repositories.RepositorySpringBase;
import ru.strict.db.spring.repositories.RepositoryUserOnRole;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class RepositoryUserSecurity<ID>
        extends RepositorySpringBase<ID, EntityUserSecurity, DtoUserSecurity> {

    private static final String[] COLUMNS_NAME = new String[] {"username", "passwordencode", "token"};

    public RepositoryUserSecurity(CreateConnectionByDataSource connectionSource, GenerateIdType generateIdType) {
        super("userx", COLUMNS_NAME, connectionSource,
                new MapperDtoUserSecurity(new MapperDtoRoleuser()),
                new MapperSqlUserSecurity(COLUMNS_NAME),
                generateIdType);
    }

    @Override
    protected Map<Integer, Object> getValueByColumn(EntityUserSecurity entity){
        DtoUserSecurity dtoUser = new DtoUserSecurity();
        Map<Integer, Object> valuesByColumn = new LinkedHashMap();
        valuesByColumn.put(0, dtoUser.getUsername());
        valuesByColumn.put(1, dtoUser.getPasswordEncode());
        return valuesByColumn;
    }

    @Override
    protected DtoUserSecurity fill(DtoUserSecurity dto){
        RepositorySpringBase<ID, EntityUserOnRole, DtoUserOnRole> rUserOnRole =
                new RepositoryUserOnRole(getConnectionSource(), GenerateIdType.NONE);
        DbRequests requests = new DbRequests(rUserOnRole.getTableName(), true);
        requests.add(new DbWhere(getTableName(), "id", dto.getId(), "="));
        List<DtoUserOnRole> userOnRoles = rUserOnRole.readAll(requests);

        IRepository<ID, DtoRoleuser> rRoleuser = new RepositoryRoleuser<>(getConnectionSource(), GenerateIdType.NONE);
        for(DtoUserOnRole<ID> userOnRole : userOnRoles)
            dto.addRoleuser(rRoleuser.read(userOnRole.getRoleId()));
        return dto;
    }

    @Override
    protected Class getThisClass() {
        return this.getClass();
    }
}
