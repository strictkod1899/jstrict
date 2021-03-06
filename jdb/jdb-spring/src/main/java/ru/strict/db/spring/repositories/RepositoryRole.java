package ru.strict.db.spring.repositories;

import ru.strict.db.core.common.GenerateIdType;

import ru.strict.db.core.common.SqlParameters;
import ru.strict.db.core.connections.CreateConnectionByDataSource;
import ru.strict.db.core.repositories.DefaultColumns;
import ru.strict.db.core.repositories.DefaultTable;
import ru.strict.db.core.requests.DbTable;
import ru.strict.models.Role;
import ru.strict.models.User;
import ru.strict.models.DetailsUser;
import ru.strict.models.UserOnRole;
import ru.strict.db.core.repositories.IRepository;
import ru.strict.db.core.requests.DbRequests;
import ru.strict.db.core.requests.DbWhereItem;
import ru.strict.db.spring.mappers.sql.MapperSpringRole;

import java.sql.SQLType;
import java.util.*;

public class RepositoryRole<ID> extends RepositorySpringNamed<ID, Role<ID>> {

    private static final String[] COLUMNS_NAME = DefaultColumns.ROLE.columns();

    public RepositoryRole(CreateConnectionByDataSource connectionSource,
                          GenerateIdType generateIdType,
                          SQLType sqlIdType) {
        super(DefaultTable.ROLE.table(),
                COLUMNS_NAME,
                connectionSource,
                generateIdType,
                sqlIdType);
        setSpringMapper(new MapperSpringRole<>(COLUMNS_NAME, sqlIdType, getColumnIdName()));
    }

    @Override
    protected SqlParameters getParameters(Role<ID> model){
        SqlParameters parameters = new SqlParameters();
        parameters.add(0, COLUMNS_NAME[0], model.getCode());
        parameters.add(1, COLUMNS_NAME[1], model.getDescription());
        return parameters;
    }

    @Override
    protected Role<ID> fill(Role<ID> model){
        // Добавление пользователей
        IRepository<ID, UserOnRole<ID>> repositoryUserOnRole = new RepositoryUserOnRole(getConnectionSource(), GenerateIdType.NONE, getSqlIdType());
        DbRequests requests = new DbRequests();
        requests.addWhere(new DbWhereItem(repositoryUserOnRole.getTable(), "role_id", model.getId(), "="));
        List<UserOnRole<ID>> userOnRoles = repositoryUserOnRole.readAll(requests);
        IRepository<ID, DetailsUser<ID>> repositoryUser = new RepositoryUser<>(getConnectionSource(), GenerateIdType.NONE, getSqlIdType());
        List<User<ID>> users = new ArrayList<>();
        for (UserOnRole<ID> userOnRole : userOnRoles) {
            users.add(repositoryUser.read(userOnRole.getUserId()));
        }
        model.setUsers(users);
        return model;
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
