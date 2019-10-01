package ru.strict.db.jdbc.repositories;

import ru.strict.db.core.common.GenerateIdType;
import ru.strict.db.core.common.SqlParameters;
import ru.strict.db.core.connections.ICreateConnection;
import ru.strict.db.core.repositories.DefaultColumns;
import ru.strict.db.core.repositories.DefaultTable;
import ru.strict.db.core.requests.DbTable;
import ru.strict.models.Role;
import ru.strict.models.PermissionOnRole;
import ru.strict.db.core.repositories.IRepository;
import ru.strict.db.core.repositories.interfaces.IRepositoryPermissionOnRole;
import ru.strict.db.jdbc.mappers.sql.MapperSqlPermissionOnRole;

import java.sql.Connection;
import java.sql.SQLType;

public class RepositoryPermissionOnRole<ID, PERMISSION>
        extends RepositoryJdbcBase<ID, PermissionOnRole<ID, PERMISSION>>
        implements IRepositoryPermissionOnRole<ID, PERMISSION> {

    private static final String[] COLUMNS_NAME = DefaultColumns.PERMISSION_ON_ROLE.columns();

    public RepositoryPermissionOnRole(ICreateConnection<Connection> connectionSource,
                                      GenerateIdType generateIdType,
                                      SQLType sqlIdType) {
        super(DefaultTable.PERMISSION_ON_ROLE.table(),
                COLUMNS_NAME,
                connectionSource,
                generateIdType,
                sqlIdType);
        setSqlMapper(new MapperSqlPermissionOnRole<>(COLUMNS_NAME, sqlIdType, getColumnIdName()));
    }

    @Override
    protected SqlParameters getParameters(PermissionOnRole<ID, PERMISSION> model){
        SqlParameters parameters = new SqlParameters();
        parameters.add(0, COLUMNS_NAME[0], model.getPermissionId());
        parameters.add(1, COLUMNS_NAME[1], model.getRoleId());
        return parameters;
    }

    @Override
    protected PermissionOnRole<ID, PERMISSION> fill(PermissionOnRole<ID, PERMISSION> model){
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
