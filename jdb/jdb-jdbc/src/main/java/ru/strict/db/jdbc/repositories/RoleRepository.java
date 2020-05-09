package ru.strict.db.jdbc.repositories;

import ru.strict.db.core.common.GenerateIdType;
import ru.strict.db.core.common.SqlParameters;
import ru.strict.db.core.connections.IConnectionCreator;
import ru.strict.db.core.repositories.DefaultColumns;
import ru.strict.db.core.repositories.DefaultTable;
import ru.strict.db.core.repositories.INamedRepository;
import ru.strict.db.core.repositories.interfaces.IUserOnRoleRepository;
import ru.strict.models.Role;
import ru.strict.models.User;
import ru.strict.models.DetailsUser;
import ru.strict.models.UserOnRole;
import ru.strict.db.core.repositories.IRepository;
import ru.strict.db.jdbc.mappers.sql.RoleSqlMapper;

import java.sql.Connection;
import java.sql.SQLType;
import java.util.*;

public class RoleRepository<ID>
        extends NamedJdbcRepository<ID, Role<ID>>
        implements INamedRepository<ID, Role<ID>> {

    private static final String[] COLUMNS_NAME = DefaultColumns.ROLE.columns();

    public RoleRepository(IConnectionCreator<Connection> connectionSource,
            GenerateIdType generateIdType,
            SQLType sqlIdType) {
        super(DefaultTable.ROLE.table(),
                COLUMNS_NAME,
                connectionSource,
                generateIdType,
                sqlIdType);
        setSqlMapper(new RoleSqlMapper<>(COLUMNS_NAME, sqlIdType, getIdColumnName()));
    }

    @Override
    protected SqlParameters getParameters(Role<ID> model) {
        SqlParameters parameters = new SqlParameters();
        parameters.set(0, COLUMNS_NAME[0], model.getCode());
        parameters.set(1, COLUMNS_NAME[1], model.getDescription());
        return parameters;
    }

    @Override
    protected Role<ID> fill(Role<ID> model) {
        // Добавление пользователей
        IUserOnRoleRepository<ID> userOnRoleRepository =
                new UserOnRoleRepository<>(getConnectionSource(), GenerateIdType.NONE, getSqlIdType());
        List<UserOnRole<ID>> userOnRoles = userOnRoleRepository.readByRoleId(model.getId());

        IRepository<ID, DetailsUser<ID>> userRepository =
                new UserRepository<>(getConnectionSource(), GenerateIdType.NONE, getSqlIdType());
        List<User<ID>> users = new ArrayList<>();
        for (UserOnRole<ID> userOnRole : userOnRoles) {
            users.add(userRepository.read(userOnRole.getUserId()));
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
