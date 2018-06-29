package ru.strict.db.spring.repositories;

import ru.strict.db.core.common.GenerateIdType;
import ru.strict.db.core.common.MapperDtoType;
import ru.strict.db.core.connections.CreateConnectionByDataSource;
import ru.strict.db.core.dto.DtoRoleuser;
import ru.strict.db.core.dto.DtoUser;
import ru.strict.db.core.dto.DtoUserOnRole;
import ru.strict.db.core.entities.EntityRoleuser;
import ru.strict.db.core.entities.EntityUserOnRole;
import ru.strict.db.core.mappers.dto.MapperDtoFactory;
import ru.strict.db.core.repositories.IRepository;
import ru.strict.db.core.requests.DbRequests;
import ru.strict.db.core.requests.DbWhere;
import ru.strict.db.spring.mappers.sql.MapperSqlRoleuser;

import java.util.*;

public class RepositoryRoleuser<ID>
        extends RepositorySpringBase<ID, EntityRoleuser, DtoRoleuser> {

    private static final String[] COLUMNS_NAME = new String[] {"code", "description"};

    public RepositoryRoleuser(CreateConnectionByDataSource connectionSource, GenerateIdType isGenerateId) {
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
        // Добавление пользователей
        RepositorySpringBase<ID, EntityUserOnRole, DtoUserOnRole> repositoryUserOnRole =
                new RepositoryUserOnRole(getConnectionSource(), GenerateIdType.NONE);
        DbRequests requests = new DbRequests(repositoryUserOnRole.getTableName(), true);
        requests.add(new DbWhere(repositoryUserOnRole.getTableName(), "roleuser_id", dto.getId(), "="));
        List<DtoUserOnRole> userOnRoles = repositoryUserOnRole.readAll(requests);

        IRepository<ID, DtoUser> repositoryUser = new RepositoryUser<>(getConnectionSource(),
                new MapperDtoFactory().instance(MapperDtoType.USER),
                GenerateIdType.NONE);
        Collection<DtoUser> users = new LinkedList<>();
        for(DtoUserOnRole<ID> userOnRole : userOnRoles) {
            users.add(repositoryUser.read(userOnRole.getUserId()));
        }
        dto.setUsers(users);
        return dto;
    }
}