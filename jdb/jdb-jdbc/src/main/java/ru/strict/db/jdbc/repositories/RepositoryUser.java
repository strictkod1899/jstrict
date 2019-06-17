package ru.strict.db.jdbc.repositories;

import ru.strict.db.core.common.GenerateIdType;
import ru.strict.db.core.common.SqlParameters;
import ru.strict.db.core.connections.ICreateConnection;
import ru.strict.db.core.requests.DbTable;
import ru.strict.models.*;
import ru.strict.db.core.entities.EntityUser;
import ru.strict.db.core.mappers.dto.MapperDtoBase;
import ru.strict.db.core.mappers.dto.MapperDtoFactory;
import ru.strict.db.core.repositories.IRepository;
import ru.strict.db.core.repositories.interfaces.IRepositoryUser;
import ru.strict.db.core.requests.DbRequests;
import ru.strict.db.core.requests.DbWhereItem;
import ru.strict.db.jdbc.mappers.sql.MapperSqlUser;
import ru.strict.utils.UtilClass;

import java.sql.Connection;
import java.util.*;

public class RepositoryUser<ID, DTO extends UserBase<ID>>
        extends RepositoryJdbcBase<ID, EntityUser<ID>, DTO>
        implements IRepositoryUser<ID, DTO> {

    private static final String[] COLUMNS_NAME = new String[] {"username", "passwordencode", "email",
            "is_blocked", "is_deleted", "is_confirm_email"};

    /**
     * Для этого конструктуора используется DtoUser
     */
    public RepositoryUser(ICreateConnection<Connection> connectionSource, GenerateIdType generateIdType) {
        super(new DbTable("userx", "usr"),
                COLUMNS_NAME,
                connectionSource,
                new MapperDtoFactory<ID>().instance(UtilClass.castClass(EntityUser.class), UtilClass.castClass(User.class)),
                new MapperSqlUser<ID>(COLUMNS_NAME),
                generateIdType);
    }

    public RepositoryUser(ICreateConnection<Connection> connectionSource,
                          MapperDtoBase<ID, EntityUser<ID>, DTO> dtoMapper,
                          GenerateIdType generateIdType) {
        super(new DbTable("userx", "usr"),
                COLUMNS_NAME,
                connectionSource,
                dtoMapper,
                new MapperSqlUser<ID>(COLUMNS_NAME),
                generateIdType);
    }

    @Override
    protected SqlParameters getParameters(EntityUser<ID> entity){
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
    protected DTO fill(DTO dto){
        // Добавление ролей пользователей
        IRepository<ID, UserOnRole<ID>> repositoryUserOnRole = new RepositoryUserOnRole(getConnectionSource(), GenerateIdType.NONE);
        DbRequests requests = new DbRequests();
        requests.addWhere(new DbWhereItem(repositoryUserOnRole.getTable(), "userx_id", dto.getId(), "="));
        List<UserOnRole<ID>> userOnRoles = repositoryUserOnRole.readAll(requests);

        IRepository<ID, Roleuser<ID>> repositoryRoleuser = new RepositoryRoleuser<>(getConnectionSource(), GenerateIdType.NONE);
        Collection<Roleuser<ID>> roleusers = new ArrayList<>();
        for (UserOnRole<ID> userOnRole : userOnRoles) {
            roleusers.add(repositoryRoleuser.read(userOnRole.getRoleId()));
        }
        dto.setRoles(roleusers);

        // Добавления профиля
        IRepository<ID, Profile<ID>> repositoryProfile = new RepositoryProfile<>(getConnectionSource(), GenerateIdType.NONE);
        requests = new DbRequests();
        requests.addWhere(new DbWhereItem(repositoryProfile.getTable(), "userx_id", dto.getId(), "="));
        dto.setProfiles(repositoryProfile.readAll(requests));

        // Добавление токенов
        if(dto instanceof UserWithToken){
            IRepository<ID, JWTToken<ID>> repositoryToken = new RepositoryJWTToken<>(getConnectionSource(), GenerateIdType.NONE);
            requests = new DbRequests();
            requests.addWhere(new DbWhereItem(repositoryToken.getTable(), "userx_id", dto.getId(), "="));

            List<JWTToken<ID>> tokens = repositoryToken.readAll(requests);
            ((UserWithToken)dto).setTokens(tokens);
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
