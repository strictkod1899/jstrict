package ru.strict.db.spring.repositories;

import ru.strict.db.core.common.GenerateIdType;
import ru.strict.db.core.common.MapperDtoType;
import ru.strict.db.core.connections.CreateConnectionByDataSource;
import ru.strict.db.core.dto.DtoRoleuser;
import ru.strict.db.core.dto.DtoUser;
import ru.strict.db.core.dto.DtoUserBase;
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
        extends RepositoryNamedBase<ID, EntityRoleuser<ID>, DtoRoleuser<ID>> {

    private static final String[] COLUMNS_NAME = new String[] {"code", "description"};

    public RepositoryRoleuser(CreateConnectionByDataSource connectionSource, GenerateIdType generateIdType) {
        super("roleuser", COLUMNS_NAME, connectionSource,
                new MapperDtoFactory<ID, EntityRoleuser<ID>, DtoRoleuser<ID>>().instance(MapperDtoType.ROLE_USER),
                new MapperSqlRoleuser<ID>(COLUMNS_NAME),
                generateIdType);
    }

    @Override
    protected Map<Integer, Object> getValueByColumn(EntityRoleuser<ID> entity){
        Map<Integer, Object> valuesByColumn = new LinkedHashMap();
        valuesByColumn.put(0, entity.getCode());
        valuesByColumn.put(1, entity.getDescription());
        return valuesByColumn;
    }

    @Override
    protected DtoRoleuser<ID> fill(DtoRoleuser<ID> dto){
        // Добавление пользователей
        IRepository<ID, DtoUserOnRole<ID>> repositoryUserOnRole =
                new RepositoryUserOnRole(getConnectionSource(), GenerateIdType.NONE);
        DbRequests requests = new DbRequests(repositoryUserOnRole.getTableName(), true);
        requests.add(new DbWhere(repositoryUserOnRole.getTableName(), "roleuser_id", dto.getId(), "="));
        List<DtoUserOnRole<ID>> userOnRoles = repositoryUserOnRole.readAll(requests);

        IRepository<ID, DtoUserBase<ID>> repositoryUser = new RepositoryUser<>(getConnectionSource(),
                new MapperDtoFactory().instance(MapperDtoType.USER),
                GenerateIdType.NONE);
        Collection<DtoUserBase<ID>> users = new LinkedList<>();
        for(DtoUserOnRole<ID> userOnRole : userOnRoles) {
            users.add(repositoryUser.read(userOnRole.getUserId()));
        }
        dto.setUsers(users);
        return dto;
    }

    @Override
    protected String getColumnWithName() {
        return COLUMNS_NAME[0];
    }

    @Override
    protected Class getThisClass() {
        return this.getClass();
    }
}
