package ru.strict.db.spring.repositories;

import ru.strict.db.core.common.GenerateIdType;

import ru.strict.db.core.common.SqlParameters;
import ru.strict.db.core.connections.CreateConnectionByDataSource;
import ru.strict.db.core.dto.DtoRoleuser;
import ru.strict.db.core.dto.DtoUser;
import ru.strict.db.core.dto.DtoUserBase;
import ru.strict.db.core.dto.DtoUserOnRole;
import ru.strict.db.core.entities.EntityRoleuser;
import ru.strict.db.core.entities.EntityUser;
import ru.strict.db.core.mappers.dto.MapperDtoFactory;
import ru.strict.db.core.repositories.IRepository;
import ru.strict.db.core.repositories.IRepositoryNamed;
import ru.strict.db.core.requests.DbRequests;
import ru.strict.db.core.requests.DbWhere;
import ru.strict.db.core.requests.DbWhereItem;
import ru.strict.db.spring.mappers.sql.MapperSqlRoleuser;
import ru.strict.utils.UtilClass;

import java.util.*;

public class RepositoryRoleuser<ID>
        extends RepositorySpringBase<ID, EntityRoleuser<ID>, DtoRoleuser<ID>>
        implements IRepositoryNamed<ID, DtoRoleuser<ID>> {

    private static final String[] COLUMNS_NAME = new String[] {"code", "description"};

    public RepositoryRoleuser(CreateConnectionByDataSource connectionSource, GenerateIdType generateIdType) {
        super("roleuser", COLUMNS_NAME, connectionSource,
                new MapperDtoFactory<ID>().instance(UtilClass.castClass(EntityRoleuser.class), UtilClass.castClass(DtoRoleuser.class)),
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
    protected DtoRoleuser<ID> fill(DtoRoleuser<ID> dto){
        IRepository<ID, DtoUserOnRole<ID>> repositoryUserOnRole = null;
        IRepository<ID, DtoUserBase<ID>> repositoryUser = null;
        try {
            // Добавление пользователей
            repositoryUserOnRole = new RepositoryUserOnRole(getConnectionSource(), GenerateIdType.NONE);
            DbRequests requests = new DbRequests();
            requests.addWhere(new DbWhereItem(repositoryUserOnRole.getTableName(), "roleuser_id", dto.getId(), "="));
            List<DtoUserOnRole<ID>> userOnRoles = repositoryUserOnRole.readAll(requests);

            repositoryUser = new RepositoryUser<>(getConnectionSource(),
                    new MapperDtoFactory().instance(UtilClass.castClass(EntityUser.class), UtilClass.castClass(DtoUser.class)),
                    GenerateIdType.NONE);
            Collection<DtoUserBase<ID>> users = new ArrayList<>();
            for (DtoUserOnRole<ID> userOnRole : userOnRoles) {
                users.add(repositoryUser.read(userOnRole.getUserId()));
            }
            dto.setUsers(users);
        }finally {
            if(repositoryUserOnRole != null){
                repositoryUserOnRole.close();
            }

            if(repositoryUser != null){
                repositoryUser.close();
            }
        }
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
