package ru.strict.db.spring.repositories;

import ru.strict.db.core.common.GenerateIdType;
import ru.strict.db.core.common.MapperDtoType;
import ru.strict.db.core.connections.CreateConnectionByDataSource;
import ru.strict.db.core.dto.DtoJWTUserToken;
import ru.strict.db.core.dto.DtoRoleuser;
import ru.strict.db.core.dto.DtoUser;
import ru.strict.db.core.entities.EntityJWTUserToken;
import ru.strict.db.core.mappers.dto.MapperDtoFactory;
import ru.strict.db.core.repositories.IRepository;
import ru.strict.db.spring.mappers.sql.MapperSqlJWTUserToken;

import java.util.LinkedHashMap;
import java.util.Map;

public class RepositoryJWTUserToken<ID>
        extends RepositorySpringBase<ID, EntityJWTUserToken, DtoJWTUserToken> {

    private static final String[] COLUMNS_NAME = new String[] {"accessToken", "refreshToken", "expireTimeAccess", "expireTimeRefresh",
            "issuedAt", "issuer", "subject", "notBefore", "audience", "secret", "algorithm", "type", "userx_id", "roleuser_id"};

    public RepositoryJWTUserToken(CreateConnectionByDataSource connectionSource, GenerateIdType isGenerateId) {
        super("token", COLUMNS_NAME, connectionSource,
                new MapperDtoFactory().instance(MapperDtoType.JWT_USER_TOKEN),
                new MapperSqlJWTUserToken(COLUMNS_NAME),
                isGenerateId);
    }

    @Override
    protected Map<Integer, Object> getValueByColumn(DtoJWTUserToken dto) {
        Map<Integer, Object> valuesByColumn = new LinkedHashMap();
        valuesByColumn.put(0, dto.getAccessToken());
        valuesByColumn.put(1, dto.getRefreshToken());
        valuesByColumn.put(2, dto.getExpireTimeAccess());
        valuesByColumn.put(3, dto.getExpireTimeRefresh());
        valuesByColumn.put(4, dto.getIssuedAt());
        valuesByColumn.put(5, dto.getIssuer());
        valuesByColumn.put(6, dto.getSubject());
        valuesByColumn.put(7, dto.getNotBefore());
        valuesByColumn.put(8, dto.getAudience());
        valuesByColumn.put(9, dto.getSecret());
        valuesByColumn.put(10, dto.getAlgorithm());
        valuesByColumn.put(11, dto.getType());
        valuesByColumn.put(12, dto.getUserId());
        valuesByColumn.put(13, dto.getRoleUserId());
        return valuesByColumn;
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
