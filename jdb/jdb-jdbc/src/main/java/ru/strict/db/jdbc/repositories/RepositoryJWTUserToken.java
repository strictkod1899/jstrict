package ru.strict.db.jdbc.repositories;

import ru.strict.db.core.common.GenerateIdType;
import ru.strict.db.core.common.MapperDtoType;
import ru.strict.db.core.connections.ICreateConnection;
import ru.strict.db.core.dto.*;
import ru.strict.db.core.entities.EntityJWTToken;
import ru.strict.db.core.entities.EntityJWTUserToken;
import ru.strict.db.core.entities.EntityUserOnRole;
import ru.strict.db.core.mappers.dto.MapperDtoFactory;
import ru.strict.db.core.repositories.IRepository;
import ru.strict.db.core.requests.DbRequests;
import ru.strict.db.core.requests.DbWhere;
import ru.strict.db.jdbc.mappers.sql.MapperSqlJWTToken;
import ru.strict.db.jdbc.mappers.sql.MapperSqlJWTUserToken;
import ru.strict.db.jdbc.mappers.sql.MapperSqlUserOnRole;

import java.sql.Connection;
import java.util.LinkedHashMap;
import java.util.Map;

public class RepositoryJWTUserToken<ID>
        extends RepositoryJdbcBase<ID, EntityJWTUserToken, DtoJWTUserToken> {

    private static final String[] COLUMNS_NAME = new String[] {"accessToken", "refreshToken", "expireTimeAccess", "expireTimeRefresh",
            "issuedAt", "issuer", "subject", "notBefore", "audience", "secret", "algorithm", "type", "userx_id", "roleuser_id"};

    public RepositoryJWTUserToken(ICreateConnection<Connection> connectionSource, GenerateIdType isGenerateId) {
        super("token", COLUMNS_NAME, connectionSource,
                new MapperDtoFactory().instance(MapperDtoType.JWT_USER_TOKEN),
                new MapperSqlJWTUserToken(COLUMNS_NAME),
                isGenerateId);
    }

    @Override
    protected Map<Integer, Object> getValueByColumn(EntityJWTUserToken entity) {
        Map<Integer, Object> valuesByColumn = new LinkedHashMap();
        valuesByColumn.put(0, entity.getAccessToken());
        valuesByColumn.put(1, entity.getRefreshToken());
        valuesByColumn.put(2, entity.getExpireTimeAccess());
        valuesByColumn.put(3, entity.getExpireTimeRefresh());
        valuesByColumn.put(4, entity.getIssuedAt());
        valuesByColumn.put(5, entity.getIssuer());
        valuesByColumn.put(6, entity.getSubject());
        valuesByColumn.put(7, entity.getNotBefore());
        valuesByColumn.put(8, entity.getAudience());
        valuesByColumn.put(9, entity.getSecret());
        valuesByColumn.put(10, entity.getAlgorithm());
        valuesByColumn.put(11, entity.getType());
        valuesByColumn.put(12, entity.getUserId());
        valuesByColumn.put(13, entity.getRoleUserId());
        return valuesByColumn;
    }

    public DtoJWTUserToken readByAccessToken(String caption){
        DbRequests requests = new DbRequests(getTableName(), true);
        requests.add(new DbWhere(getTableName(), "accessToken", caption, "="));

        DtoJWTUserToken result = readAll(requests).stream().findFirst().orElse(null);
        return result;
    }

    public DtoJWTUserToken readByRefreshToken(String caption){
        DbRequests requests = new DbRequests(getTableName(), true);
        requests.add(new DbWhere(getTableName(), "refreshToken", caption, "="));

        DtoJWTUserToken result = readAll(requests).stream().findFirst().orElse(null);
        return result;
    }

    @Override
    protected DtoJWTUserToken fill(DtoJWTUserToken dto){
        // Добавление пользователя
        IRepository<ID, DtoUser> repositoryUser = new RepositoryUser(getConnectionSource(),
                new MapperDtoFactory().instance(MapperDtoType.USER),
                GenerateIdType.NONE);
        dto.setUser(repositoryUser.read((ID) dto.getUserId()));

        // Добавление роли пользователя
        IRepository<ID, DtoRoleuser> repositoryRoleuser = new RepositoryRoleuser(getConnectionSource(), GenerateIdType.NONE);
        dto.setRoleUser(repositoryRoleuser.read((ID) dto.getRoleUserId()));
        return dto;
    }
}
