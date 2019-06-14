package ru.strict.db.spring.security;

import ru.strict.db.core.common.GenerateIdType;
import ru.strict.db.core.common.SqlParameters;
import ru.strict.db.core.connections.CreateConnectionByDataSource;
import ru.strict.models.Profile;
import ru.strict.models.Roleuser;
import ru.strict.models.UserOnRole;
import ru.strict.db.core.mappers.dto.MapperDtoProfile;
import ru.strict.db.core.mappers.dto.MapperDtoRoleuser;
import ru.strict.db.core.repositories.IRepository;
import ru.strict.db.core.repositories.IRepositoryNamed;
import ru.strict.db.core.requests.DbRequests;
import ru.strict.db.core.requests.DbWhereItem;
import ru.strict.db.spring.repositories.*;

import java.util.*;

public class RepositoryUserSecurity<ID>
        extends RepositorySpringBase<ID, EntityUserSecurity<ID>, UserSecurity<ID>>
        implements IRepositoryNamed<ID, UserSecurity<ID>> {

    private static final String[] COLUMNS_NAME = new String[] {"username", "passwordencode", "email",
            "is_blocked", "is_deleted", "is_confirm_email"};

    public RepositoryUserSecurity(CreateConnectionByDataSource connectionSource, GenerateIdType generateIdType) {
        super("userx", COLUMNS_NAME, connectionSource,
                new MapperDtoUserSecurity<ID>(new MapperDtoRoleuser(), new MapperDtoProfile()),
                new MapperSqlUserSecurity<ID>(COLUMNS_NAME),
                generateIdType);
    }

    @Override
    protected SqlParameters getParameters(EntityUserSecurity<ID> entity){
        SqlParameters parameters = new SqlParameters();
        parameters.add(0, COLUMNS_NAME[0], entity.getUsername());
        parameters.add(1, COLUMNS_NAME[1], entity.getPasswordEncode());
        parameters.add(2, COLUMNS_NAME[2], entity.getEmail());
        parameters.add(3, COLUMNS_NAME[3], entity.isBlocked());
        parameters.add(4, COLUMNS_NAME[4], entity.isDeleted());
        parameters.add(5, COLUMNS_NAME[5], entity.isConfirmEmail());
        return parameters;
    }

    @Override
    protected UserSecurity<ID> fill(UserSecurity<ID> dto){
        // Добавление ролей пользователей
        IRepository<ID, UserOnRole<ID>> repositoryUserOnRole = new RepositoryUserOnRole(getConnectionSource(), GenerateIdType.NONE);
        DbRequests requests = new DbRequests();
        requests.addWhere(new DbWhereItem(repositoryUserOnRole.getTableName(), "userx_id", dto.getId(), "="));
        List<UserOnRole<ID>> userOnRoles = repositoryUserOnRole.readAll(requests);

        IRepository<ID, Roleuser<ID>> repositoryRoleuser = new RepositoryRoleuser<>(getConnectionSource(), GenerateIdType.NONE);
        Collection<Roleuser<ID>> roleusers = new ArrayList<>();
        for(UserOnRole<ID> userOnRole : userOnRoles) {
            roleusers.add(repositoryRoleuser.read(userOnRole.getRoleId()));
        }
        dto.setRoles(roleusers);

        // Добавления профиля
        IRepository<ID, Profile<ID>> repositoryProfile = new RepositoryProfile<>(getConnectionSource(), GenerateIdType.NONE);
        requests = new DbRequests();
        requests.addWhere(new DbWhereItem(repositoryProfile.getTableName(), "userx_id", dto.getId(), "="));
        dto.setProfiles(repositoryProfile.readAll(requests));
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
