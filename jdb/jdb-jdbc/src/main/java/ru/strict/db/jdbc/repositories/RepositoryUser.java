package ru.strict.db.jdbc.repositories;

import ru.strict.db.core.common.GenerateIdType;
import ru.strict.db.core.connections.ICreateConnection;
import ru.strict.db.core.dto.*;
import ru.strict.db.core.dto.DtoUser;
import ru.strict.db.core.dto.DtoUserOnRole;
import ru.strict.db.core.entities.EntityJWTUserToken;
import ru.strict.db.core.entities.EntityProfileInfo;
import ru.strict.db.core.entities.EntityUser;
import ru.strict.db.core.entities.EntityUserOnRole;
import ru.strict.db.core.mappers.dto.MapperDtoBase;
import ru.strict.db.core.repositories.IRepository;
import ru.strict.db.core.repositories.RepositoryBase;
import ru.strict.db.core.requests.DbRequests;
import ru.strict.db.core.requests.DbWhere;
import ru.strict.db.jdbc.mappers.sql.MapperSqlUser;
import ru.strict.utils.UtilLogger;

import java.sql.Connection;
import java.util.*;

public class RepositoryUser<ID, DTO extends DtoUserBase>
        extends RepositoryNamedBase<ID, EntityUser, DTO> {

    private static final String[] COLUMNS_NAME = new String[] {"username", "passwordencode"};

    public RepositoryUser(ICreateConnection<Connection> connectionSource,
                          MapperDtoBase<EntityUser, DTO> dtoMapper,
                          GenerateIdType isGenerateId) {
        super("userx", COLUMNS_NAME, connectionSource, dtoMapper, new MapperSqlUser(COLUMNS_NAME), isGenerateId);
    }

    @Override
    protected Map<Integer, Object> getValueByColumn(EntityUser entity){
        Map<Integer, Object> valuesByColumn = new LinkedHashMap();
        valuesByColumn.put(0, entity.getUsername());
        valuesByColumn.put(1, entity.getPasswordEncode());
        return valuesByColumn;
    }

    @Override
    protected DTO fill(DTO dto){
        // Добавление ролей пользователей
        RepositoryJdbcBase<ID, EntityUserOnRole, DtoUserOnRole> repositoryUserOnRole =
                new RepositoryUserOnRole(getConnectionSource(), GenerateIdType.NONE);
        DbRequests requests = new DbRequests(repositoryUserOnRole.getTableName(), true);
        requests.add(new DbWhere(repositoryUserOnRole.getTableName(), "userx_id", dto.getId(), "="));
        List<DtoUserOnRole> userOnRoles = repositoryUserOnRole.readAll(requests);

        IRepository<ID, DtoRoleuser> repositoryRoleuser = new RepositoryRoleuser<>(getConnectionSource(), GenerateIdType.NONE);
        Collection<DtoRoleuser> roleusers = new LinkedList<>();
        for(DtoUserOnRole<ID> userOnRole : userOnRoles) {
            roleusers.add(repositoryRoleuser.read(userOnRole.getRoleId()));
        }
        dto.setRolesuser(roleusers);

        // Добавления профиля
        RepositoryJdbcBase<ID, EntityProfileInfo, DtoProfileInfo> repositoryProfile =
                new RepositoryProfileInfo<>(getConnectionSource(), GenerateIdType.NONE);
        requests = new DbRequests(repositoryProfile.getTableName(), true);
        requests.add(new DbWhere(repositoryProfile.getTableName(), "userx_id", dto.getId(), "="));
        dto.setProfile(repositoryProfile.readAll(requests).stream().findFirst().orElse(null));

        // Добавление токенов
        if(dto instanceof DtoUserToken) {
            RepositoryJdbcBase<ID, EntityJWTUserToken, DtoJWTUserToken> repositoryToken =
                    new RepositoryJWTUserToken<>(getConnectionSource(), GenerateIdType.NONE);
            requests = new DbRequests(repositoryToken.getTableName(), true);
            requests.add(new DbWhere(repositoryToken.getTableName(), "userx_id", dto.getId(), "="));

            List<DtoJWTUserToken> tokens = repositoryToken.readAll(requests);
            ((DtoUserToken)dto).setTokens(tokens);
        }
        return dto;
    }

    @Override
    protected String getColumnWithName() {
        return COLUMNS_NAME[0];
    }

    @Override
    protected Class getClassForLogReport() {
        return this.getClass();
    }
}
