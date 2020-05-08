package ru.strict.db.spring.repositories;

import ru.strict.db.core.common.GenerateIdType;

import ru.strict.db.core.common.SqlParameters;
import ru.strict.db.core.connections.CreateConnectionByDataSource;
import ru.strict.db.core.repositories.DefaultColumns;
import ru.strict.db.core.repositories.DefaultTable;
import ru.strict.db.core.requests.DbTable;
import ru.strict.models.Role;
import ru.strict.models.DetailsUser;
import ru.strict.models.UserOnRole;
import ru.strict.db.core.repositories.IRepository;
import ru.strict.db.core.repositories.interfaces.IRepositoryUserOnRole;
import ru.strict.db.spring.mappers.sql.MapperSpringUserOnRole;

import java.sql.SQLType;

public class RepositoryUserOnRole<ID>
        extends RepositorySpringBase<ID, UserOnRole<ID>>
        implements IRepositoryUserOnRole<ID> {

    private static final String[] COLUMNS_NAME = DefaultColumns.USER_ON_ROLE.columns();

    public RepositoryUserOnRole(CreateConnectionByDataSource connectionSource,
                                GenerateIdType generateIdType,
                                SQLType sqlIdType) {
        super(DefaultTable.USER_ON_ROLE.table(),
                COLUMNS_NAME,
                connectionSource,
                generateIdType,
                sqlIdType);
        setSpringMapper(new MapperSpringUserOnRole<>(COLUMNS_NAME, sqlIdType, getColumnIdName()));
    }

    @Override
    protected SqlParameters getParameters(UserOnRole<ID> model){
        SqlParameters parameters = new SqlParameters();
        parameters.add(0, COLUMNS_NAME[0], model.getUserId());
        parameters.add(1, COLUMNS_NAME[1], model.getRoleId());
        return parameters;
    }

    @Override
    protected UserOnRole<ID> fill(UserOnRole<ID> model){
        // Добавление пользователя
        IRepository<ID, DetailsUser<ID>> repositoryUser = new RepositoryUser(getConnectionSource(), GenerateIdType.NONE, getSqlIdType());
        model.setUser(repositoryUser.read(model.getUserId()));

        // Добавление роли пользователя
        IRepository<ID, Role<ID>> repositoryRole = new RepositoryRole(getConnectionSource(), GenerateIdType.NONE, getSqlIdType());
        model.setRole(repositoryRole.read(model.getRoleId()));
        return model;
    }

    @Override
    protected Class getThisClass() {
        return this.getClass();
    }
}
