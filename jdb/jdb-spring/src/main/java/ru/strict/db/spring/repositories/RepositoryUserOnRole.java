package ru.strict.db.spring.repositories;

import ru.strict.db.core.common.GenerateIdType;

import ru.strict.db.core.common.SqlParameters;
import ru.strict.db.core.connections.CreateConnectionByDataSource;
import ru.strict.db.core.dto.DtoRoleuser;
import ru.strict.db.core.dto.DtoUser;
import ru.strict.db.core.dto.DtoUserOnRole;
import ru.strict.db.core.entities.EntityUser;
import ru.strict.db.core.entities.EntityUserOnRole;
import ru.strict.db.core.mappers.dto.*;
import ru.strict.db.core.repositories.IRepository;
import ru.strict.db.core.repositories.interfaces.IRepositoryUserOnRole;
import ru.strict.db.spring.mappers.sql.MapperSqlUserOnRole;
import ru.strict.utils.UtilClass;

import java.util.LinkedHashMap;
import java.util.Map;

public class RepositoryUserOnRole<ID>
        extends RepositorySpringBase<ID, EntityUserOnRole<ID>, DtoUserOnRole<ID>>
        implements IRepositoryUserOnRole<ID> {

    private static final String[] COLUMNS_NAME = new String[] {"userx_id", "roleuser_id"};

    public RepositoryUserOnRole(CreateConnectionByDataSource connectionSource, GenerateIdType generateIdType) {
        super("user_on_role", COLUMNS_NAME, connectionSource,
                new MapperDtoFactory<ID>().instance(UtilClass.castClass(EntityUserOnRole.class), UtilClass.castClass(DtoUserOnRole.class)),
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
    protected DtoUserOnRole<ID> fill(DtoUserOnRole<ID> dto){
        IRepository<ID, DtoUser<ID>> repositoryUser = null;
        IRepository<ID, DtoRoleuser<ID>> repositoryRoleuser = null;
        try {
            // Добавление пользователя
            repositoryUser = new RepositoryUser(getConnectionSource(),
                    new MapperDtoFactory().instance(UtilClass.castClass(EntityUser.class), UtilClass.castClass(DtoUser.class)),
                    GenerateIdType.NONE);
            dto.setUser(repositoryUser.read(dto.getUserId()));

            // Добавление роли пользователя
            repositoryRoleuser = new RepositoryRoleuser(getConnectionSource(), GenerateIdType.NONE);
            dto.setRole(repositoryRoleuser.read(dto.getRoleId()));
        }finally {
            if(repositoryUser != null){
                repositoryUser.close();
            }
            if(repositoryRoleuser != null){
                repositoryRoleuser.close();
            }
        }
        return dto;
    }

    @Override
    protected Class getThisClass() {
        return this.getClass();
    }
}
