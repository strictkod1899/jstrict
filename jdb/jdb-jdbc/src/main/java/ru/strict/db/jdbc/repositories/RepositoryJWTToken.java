package ru.strict.db.jdbc.repositories;

import ru.strict.db.core.common.GenerateIdType;
import ru.strict.db.core.common.MapperDtoType;
import ru.strict.db.core.connections.ICreateConnection;
import ru.strict.db.core.dto.DtoJWTToken;
import ru.strict.db.core.dto.DtoRoleuser;
import ru.strict.db.core.dto.DtoUserToken;
import ru.strict.db.core.entities.EntityJWTToken;
import ru.strict.db.core.mappers.dto.MapperDtoFactory;
import ru.strict.db.core.repositories.IRepository;
import ru.strict.db.core.repositories.interfaces.IRepositoryJWTToken;
import ru.strict.db.core.requests.DbRequests;
import ru.strict.db.core.requests.DbWhereItem;
import ru.strict.db.core.requests.WhereType;
import ru.strict.db.jdbc.mappers.sql.MapperSqlJWTToken;

import java.sql.Connection;
import java.util.LinkedHashMap;
import java.util.Map;

public class RepositoryJWTToken<ID>
        extends RepositoryJdbcBase<ID, EntityJWTToken<ID>, DtoJWTToken<ID>>
        implements IRepositoryJWTToken<ID> {

    private static final String[] COLUMNS_NAME = new String[] {"accessToken", "refreshToken", "expireTimeAccess",
            "expireTimeRefresh", "issuedAt", "issuer", "subject", "notBefore", "audience", "secret",
            "algorithm", "type", "userx_id", "roleuser_id"};

    public RepositoryJWTToken(ICreateConnection<Connection> connectionSource, GenerateIdType generateIdType) {
        super("token", COLUMNS_NAME, connectionSource,
                new MapperDtoFactory<ID, EntityJWTToken<ID>, DtoJWTToken<ID>>().instance(MapperDtoType.JWT_TOKEN),
                new MapperSqlJWTToken<ID>(COLUMNS_NAME),
                generateIdType);
    }

    @Override
    protected Map<Integer, Object> getValueByColumn(EntityJWTToken<ID> entity) {
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

    @Override
    public DtoJWTToken<ID> readByAccessToken(String caption){
        DbRequests requests = new DbRequests();
        requests.addWhere(new DbWhereItem(getTableName(), "accessToken", caption, "="));

        DtoJWTToken<ID> result = readAll(requests).stream().findFirst().orElse(null);
        return result;
    }

    @Override
    public DtoJWTToken<ID> readByRefreshToken(String caption){
        DbRequests requests = new DbRequests();
        requests.addWhere(new DbWhereItem(getTableName(), "refreshToken", caption, "="));

        DtoJWTToken<ID> result = readAll(requests).stream().findFirst().orElse(null);
        return result;
    }

    @Override
    protected DtoJWTToken<ID> fill(DtoJWTToken<ID> dto){
        // Добавление пользователя
        IRepository<ID, DtoUserToken<ID>> repositoryUser = null;
        IRepository<ID, DtoRoleuser<ID>> repositoryRoleuser = null;
        try {
            repositoryUser = new RepositoryUser(getConnectionSource(),
                    new MapperDtoFactory().instance(MapperDtoType.USER_TOKEN),
                    GenerateIdType.NONE);
            dto.setUser(repositoryUser.read(dto.getUserId()));

            // Добавление роли пользователя
            repositoryRoleuser = new RepositoryRoleuser(getConnectionSource(), GenerateIdType.NONE);
            dto.setRoleUser(repositoryRoleuser.read(dto.getRoleUserId()));
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
