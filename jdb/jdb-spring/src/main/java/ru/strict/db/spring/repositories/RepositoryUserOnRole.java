package ru.strict.db.spring.repositories;

import ru.strict.db.core.common.GenerateIdType;

import ru.strict.db.core.common.SqlParameters;
import ru.strict.db.core.connections.CreateConnectionByDataSource;
import ru.strict.db.core.requests.DbTable;
import ru.strict.models.Roleuser;
import ru.strict.models.User;
import ru.strict.models.UserOnRole;
import ru.strict.db.core.entities.EntityUser;
import ru.strict.db.core.entities.EntityUserOnRole;
import ru.strict.db.core.mappers.dto.*;
import ru.strict.db.core.repositories.IRepository;
import ru.strict.db.core.repositories.interfaces.IRepositoryUserOnRole;
import ru.strict.db.spring.mappers.sql.MapperSqlUserOnRole;
import ru.strict.utils.UtilClass;

public class RepositoryUserOnRole<ID>
        extends RepositorySpringBase<ID, EntityUserOnRole<ID>, UserOnRole<ID>>
        implements IRepositoryUserOnRole<ID> {

    private static final String[] COLUMNS_NAME = new String[] {"userx_id", "roleuser_id"};

    public RepositoryUserOnRole(CreateConnectionByDataSource connectionSource, GenerateIdType generateIdType) {
        super(new DbTable("user_on_role", "ur"),
                COLUMNS_NAME,
                connectionSource,
                new MapperDtoFactory<ID>().instance(UtilClass.castClass(EntityUserOnRole.class), UtilClass.castClass(UserOnRole.class)),
                new MapperSqlUserOnRole<ID>(COLUMNS_NAME),
                generateIdType);
    }

    @Override
    protected SqlParameters getParameters(EntityUserOnRole<ID> entity){
        SqlParameters parameters = new SqlParameters();
        parameters.add(0, COLUMNS_NAME[0], entity.getUserId());
        parameters.add(1, COLUMNS_NAME[1], entity.getRoleId());
        return parameters;
    }

    @Override
    protected UserOnRole<ID> fill(UserOnRole<ID> dto){
        // Добавление пользователя
        IRepository<ID, User<ID>> repositoryUser = new RepositoryUser(getConnectionSource(),
                new MapperDtoFactory().instance(UtilClass.castClass(EntityUser.class), UtilClass.castClass(User.class)),
                GenerateIdType.NONE);
        dto.setUser(repositoryUser.read(dto.getUserId()));

        // Добавление роли пользователя
        IRepository<ID, Roleuser<ID>> repositoryRoleuser = new RepositoryRoleuser(getConnectionSource(), GenerateIdType.NONE);
        dto.setRole(repositoryRoleuser.read(dto.getRoleId()));
        return dto;
    }

    @Override
    protected Class getThisClass() {
        return this.getClass();
    }
}
