package ru.strict.db.jdbc.repositories;

import ru.strict.db.core.common.GenerateIdType;

import ru.strict.db.core.common.SqlParameters;
import ru.strict.db.core.connections.ICreateConnection;
import ru.strict.db.core.requests.DbTable;
import ru.strict.models.Roleuser;
import ru.strict.models.User;
import ru.strict.models.UserBase;
import ru.strict.models.UserOnRole;
import ru.strict.db.core.entities.EntityRoleuser;
import ru.strict.db.core.entities.EntityUser;
import ru.strict.db.core.mappers.dto.MapperDtoFactory;
import ru.strict.db.core.repositories.IRepository;
import ru.strict.db.core.repositories.IRepositoryNamed;
import ru.strict.db.core.requests.DbRequests;
import ru.strict.db.core.requests.DbWhereItem;
import ru.strict.db.jdbc.mappers.sql.MapperSqlRoleuser;
import ru.strict.utils.UtilClass;

import java.sql.Connection;
import java.util.*;

public class RepositoryRoleuser<ID>
        extends RepositoryJdbcBase<ID, EntityRoleuser<ID>, Roleuser<ID>>
        implements IRepositoryNamed<ID, Roleuser<ID>> {

    private static final String[] COLUMNS_NAME = new String[] {"code", "description"};

    public RepositoryRoleuser(ICreateConnection<Connection> connectionSource, GenerateIdType generateIdType) {
        super(new DbTable("roleuser", "role"),
                COLUMNS_NAME,
                connectionSource,
                new MapperDtoFactory<ID>().instance(UtilClass.castClass(EntityRoleuser.class), UtilClass.castClass(Roleuser.class)),
                new MapperSqlRoleuser<ID>(COLUMNS_NAME),
                generateIdType);
    }

    @Override
    protected SqlParameters getParameters(EntityRoleuser<ID> entity){
        SqlParameters parameters = new SqlParameters();
        parameters.add(0, COLUMNS_NAME[0], entity.getCode());
        parameters.add(1, COLUMNS_NAME[1], entity.getDescription());
        return parameters;
    }

    @Override
    protected Roleuser<ID> fill(Roleuser<ID> dto){
        // Добавление пользователей
        IRepository<ID, UserOnRole<ID>> repositoryUserOnRole = new RepositoryUserOnRole(getConnectionSource(), GenerateIdType.NONE);
        DbRequests requests = new DbRequests();
        requests.addWhere(new DbWhereItem(repositoryUserOnRole.getTable(), "roleuser_id", dto.getId(), "="));
        List<UserOnRole<ID>> userOnRoles = repositoryUserOnRole.readAll(requests);

        IRepository<ID, UserBase<ID>> repositoryUser = new RepositoryUser<>(getConnectionSource(),
                new MapperDtoFactory().instance(UtilClass.castClass(EntityUser.class), UtilClass.castClass(User.class)),
                GenerateIdType.NONE);
        Collection<UserBase<ID>> users = new ArrayList<>();
        for (UserOnRole<ID> userOnRole : userOnRoles) {
            users.add(repositoryUser.read(userOnRole.getUserId()));
        }
        dto.setUsers(users);
        return dto;
    }

    @Override
    public String getColumnWithName() {
        return COLUMNS_NAME[0];
    }

    @Override
    protected Class getThisClass() {
        return this.getClass();
    }
}
