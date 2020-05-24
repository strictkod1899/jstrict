package ru.strict.db.jdbc.repositories;

import ru.strict.db.core.common.GenerateIdType;
import ru.strict.db.core.common.SqlParameters;
import ru.strict.db.core.connections.IConnectionCreator;
import ru.strict.db.core.repositories.DefaultColumns;
import ru.strict.db.core.repositories.DefaultTable;
import ru.strict.db.core.repositories.interfaces.IPermissionOnRoleRepository;
import ru.strict.patterns.IModel;
import ru.strict.models.IModelProvider;
import ru.strict.models.Role;
import ru.strict.models.PermissionOnRole;
import ru.strict.db.core.repositories.IRepository;
import ru.strict.db.jdbc.mappers.sql.PermissionOnRoleSqlMapper;

import java.sql.Connection;
import java.sql.SQLType;

public class PermissionOnRoleRepository<ID, PERMISSION extends IModel<Integer>>
        extends JdbcRepository<ID, PermissionOnRole<ID, PERMISSION>>
        implements IPermissionOnRoleRepository<ID, PERMISSION> {

    private static final String[] COLUMNS_NAME = DefaultColumns.PERMISSION_ON_ROLE.columns();

    private final IModelProvider<PERMISSION> permissionProvider;

    public PermissionOnRoleRepository(IConnectionCreator<Connection> connectionSource,
            GenerateIdType generateIdType,
            SQLType sqlIdType) {
        this(connectionSource, generateIdType, sqlIdType, null);
    }

    public PermissionOnRoleRepository(IConnectionCreator<Connection> connectionSource,
            GenerateIdType generateIdType,
            SQLType sqlIdType,
            IModelProvider<PERMISSION> permissionProvider) {
        super(DefaultTable.PERMISSION_ON_ROLE.table(),
                COLUMNS_NAME,
                connectionSource,
                generateIdType,
                sqlIdType);
        setSqlMapper(new PermissionOnRoleSqlMapper<>(COLUMNS_NAME, sqlIdType, getIdColumnName()));

        this.permissionProvider = permissionProvider;
    }

    @Override
    protected SqlParameters getParameters(PermissionOnRole<ID, PERMISSION> model) {
        SqlParameters parameters = new SqlParameters();
        parameters.set(0, COLUMNS_NAME[0], model.getPermissionId());
        parameters.set(1, COLUMNS_NAME[1], model.getRoleId());
        return parameters;
    }

    @Override
    protected PermissionOnRole<ID, PERMISSION> postRead(PermissionOnRole<ID, PERMISSION> model) {
        if (model == null) {
            return null;
        }
        model.setPermissionProvider(permissionProvider);
        return model;
    }

    @Override
    protected PermissionOnRole<ID, PERMISSION> fill(PermissionOnRole<ID, PERMISSION> model) {
        // Добавление роли пользователя
        IRepository<ID, Role<ID>> roleRepository =
                new RoleRepository<>(getConnectionSource(), GenerateIdType.NONE, getSqlIdType());
        model.setRole(roleRepository.read(model.getRoleId()));
        return model;
    }

    @Override
    protected Class getThisClass() {
        return this.getClass();
    }
}
