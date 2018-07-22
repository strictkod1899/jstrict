package ru.strict.db.jdbc.repositories;

import ru.strict.db.core.common.GenerateIdType;
import ru.strict.db.core.common.MapperDtoType;
import ru.strict.db.core.connections.ICreateConnection;
import ru.strict.db.core.dto.DtoJWTToken;
import ru.strict.db.core.entities.EntityJWTToken;
import ru.strict.db.core.mappers.dto.MapperDtoFactory;
import ru.strict.db.jdbc.mappers.sql.MapperSqlJWTToken;

import java.sql.Connection;
import java.util.LinkedHashMap;
import java.util.Map;

public class RepositoryJWTToken<ID>
        extends RepositoryJdbcBase<ID, EntityJWTToken, DtoJWTToken> {

    private static final String[] COLUMNS_NAME = new String[] {"accessToken", "refreshToken", "expireTimeAccess", "expireTimeRefresh",
            "issuedAt", "issuer", "subject", "notBefore", "audience", "secret", "algorithm", "type"};

    public RepositoryJWTToken(ICreateConnection<Connection> connectionSource, GenerateIdType isGenerateId) {
        super("token", COLUMNS_NAME, connectionSource,
                new MapperDtoFactory().instance(MapperDtoType.JWT_TOKEN),
                new MapperSqlJWTToken(COLUMNS_NAME),
                isGenerateId);
    }

    @Override
    protected Map<Integer, Object> getValueByColumn(EntityJWTToken entity) {
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
        return valuesByColumn;
    }

    @Override
    protected DtoJWTToken fill(DtoJWTToken dto){
        return dto;
    }
}
