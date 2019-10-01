package ru.strict.db.jdbc.repositories;

import ru.strict.db.core.common.GenerateIdType;
import ru.strict.db.core.common.SqlParameters;
import ru.strict.db.core.connections.ICreateConnection;
import ru.strict.db.core.repositories.DefaultColumns;
import ru.strict.db.core.repositories.DefaultTable;
import ru.strict.db.core.requests.DbTable;
import ru.strict.models.*;
import ru.strict.db.core.repositories.IRepository;
import ru.strict.db.core.repositories.interfaces.IRepositoryUser;
import ru.strict.db.core.requests.DbRequests;
import ru.strict.db.core.requests.DbWhereItem;
import ru.strict.db.jdbc.mappers.sql.MapperSqlUser;
import java.sql.Connection;
import java.sql.SQLType;
import java.util.*;

public class RepositoryUser<ID>
        extends RepositoryJdbcNamed<ID, UserDetails<ID>>
        implements IRepositoryUser<ID, UserDetails<ID>> {

    private static final String[] COLUMNS_NAME = DefaultColumns.USER.columns();

    public RepositoryUser(ICreateConnection<Connection> connectionSource,
                          GenerateIdType generateIdType,
                          SQLType sqlIdType) {
        super(DefaultTable.USER.table(),
                COLUMNS_NAME,
                connectionSource,
                generateIdType,
                sqlIdType);
        setSqlMapper(new MapperSqlUser<>(COLUMNS_NAME, sqlIdType, getColumnIdName()));
    }

    @Override
    protected SqlParameters getParameters(UserDetails<ID> model){
        SqlParameters parameters = new SqlParameters();
        parameters.add(0, COLUMNS_NAME[0], model.getUsername());
        parameters.add(1, COLUMNS_NAME[1], model.getPasswordEncode());
        parameters.add(2, COLUMNS_NAME[2], model.getEmail());
        parameters.add(3, COLUMNS_NAME[3], model.isBlocked());
        parameters.add(4, COLUMNS_NAME[4], model.isDeleted());
        parameters.add(5, COLUMNS_NAME[5], model.isConfirmEmail());
        parameters.add(6, COLUMNS_NAME[6], model.getSalt());
        parameters.add(7, COLUMNS_NAME[7], model.getSecret());
        return parameters;
    }

    @Override
    protected UserDetails<ID> fill(UserDetails<ID> model){
        // Добавление ролей пользователей
        IRepository<ID, UserOnRole<ID>> repositoryUserOnRole = new RepositoryUserOnRole(getConnectionSource(), GenerateIdType.NONE, getSqlIdType());
        DbRequests requests = new DbRequests();
        requests.addWhere(new DbWhereItem(repositoryUserOnRole.getTable(), "userx_id", model.getId(), "="));
        List<UserOnRole<ID>> userOnRoles = repositoryUserOnRole.readAll(requests);

        IRepository<ID, Role<ID>> repositoryRole = new RepositoryRole<>(getConnectionSource(), GenerateIdType.NONE, getSqlIdType());
        List<Role<ID>> roles = new ArrayList<>();
        for (UserOnRole<ID> userOnRole : userOnRoles) {
            roles.add(repositoryRole.read(userOnRole.getRoleId()));
        }
        model.setRoles(roles);

        // Добавления профиля
        IRepository<ID, Profile<ID>> repositoryProfile = new RepositoryProfile<>(getConnectionSource(), GenerateIdType.NONE, getSqlIdType());
        requests = new DbRequests();
        requests.addWhere(new DbWhereItem(repositoryProfile.getTable(), "userx_id", model.getId(), "="));
        model.setProfiles(repositoryProfile.readAll(requests));

        // Добавление токенов
        IRepository<ID, JWTToken<ID>> repositoryToken = new RepositoryJWTToken<>(getConnectionSource(), GenerateIdType.NONE, getSqlIdType());
        requests = new DbRequests();
        requests.addWhere(new DbWhereItem(repositoryToken.getTable(), "userx_id", model.getId(), "="));
        model.setTokens(repositoryToken.readAll(requests));
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
