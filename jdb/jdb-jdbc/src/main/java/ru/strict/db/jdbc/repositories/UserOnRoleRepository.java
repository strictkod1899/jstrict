package ru.strict.db.jdbc.repositories;

import ru.strict.db.core.common.GenerateIdType;
import ru.strict.db.core.common.SqlParameters;
import ru.strict.db.core.connections.IConnectionCreator;
import ru.strict.db.core.repositories.DefaultColumns;
import ru.strict.db.core.repositories.DefaultTable;
import ru.strict.db.core.repositories.interfaces.IUserOnRoleRepository;
import ru.strict.models.Role;
import ru.strict.models.User;
import ru.strict.models.UserOnRole;
import ru.strict.db.core.repositories.IRepository;
import ru.strict.db.jdbc.mappers.sql.UserOnRoleSqlMapper;

import java.sql.Connection;
import java.sql.SQLType;

public class UserOnRoleRepository<ID>
        extends JdbcRepository<ID, UserOnRole<ID>>
        implements IUserOnRoleRepository<ID> {

    private static final String[] COLUMNS_NAME = DefaultColumns.USER_ON_ROLE.columns();

    public UserOnRoleRepository(IConnectionCreator<Connection> connectionSource,
            GenerateIdType generateIdType,
            SQLType sqlIdType) {
        super(DefaultTable.USER_ON_ROLE.table(),
                COLUMNS_NAME,
                connectionSource,
                generateIdType,
                sqlIdType);
        setSqlMapper(new UserOnRoleSqlMapper<>(COLUMNS_NAME, sqlIdType, getIdColumnName()));
    }

    @Override
    protected SqlParameters getParameters(UserOnRole<ID> model) {
        SqlParameters parameters = new SqlParameters();
        parameters.add(0, COLUMNS_NAME[0], model.getUserId());
        parameters.add(1, COLUMNS_NAME[1], model.getRoleId());
        return parameters;
    }

    @Override
    protected UserOnRole<ID> fill(UserOnRole<ID> model) {
        // Добавление пользователя
        IRepository<ID, User<ID>> repositoryUser =
                new UserRepository(getConnectionSource(), GenerateIdType.NONE, getSqlIdType());
        model.setUser(repositoryUser.read(model.getUserId()));

        // Добавление роли пользователя
        IRepository<ID, Role<ID>> repositoryRole =
                new RoleRepository(getConnectionSource(), GenerateIdType.NONE, getSqlIdType());
        model.setRole(repositoryRole.read(model.getRoleId()));
        return model;
    }

    @Override
    protected Class getThisClass() {
        return this.getClass();
    }
}
