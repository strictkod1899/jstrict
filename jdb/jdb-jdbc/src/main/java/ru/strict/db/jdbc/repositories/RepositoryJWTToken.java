package ru.strict.db.jdbc.repositories;

import ru.strict.db.core.common.GenerateIdType;
import ru.strict.db.core.common.MapperDtoType;
import ru.strict.db.core.connections.ICreateConnection;
import ru.strict.db.core.dto.DtoJWTToken;
import ru.strict.db.core.entities.EntityJWTToken;
import ru.strict.db.core.mappers.dto.MapperDtoFactory;
import ru.strict.db.jdbc.mappers.sql.MapperSqlJWTToken;

import java.util.LinkedHashMap;
import java.util.Map;

public class RepositoryJWTToken<ID, SOURCE extends ICreateConnection>
        extends RepositoryJdbcBase<ID, SOURCE, EntityJWTToken, DtoJWTToken> {

    private static final String[] COLUMNS_NAME = new String[] {"accessToken", "refreshToken", "expireTimeAccess", "expireTimeRefresh",
            "issuedAt", "issuer", "subject", "notBefore", "audience", "secret", "algorithm", "type"};

    public RepositoryJWTToken(SOURCE connectionSource, GenerateIdType isGenerateId) {
        super("token", COLUMNS_NAME, connectionSource,
                new MapperDtoFactory().instance(MapperDtoType.JWT_TOKEN),
                new MapperSqlJWTToken(COLUMNS_NAME),
                isGenerateId);
    }

    @Override
    protected Map<Integer, Object> getValueByColumn(DtoJWTToken dto) {
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
        return valuesByColumn;
    }

    @Override
    protected DtoJWTToken fill(DtoJWTToken dto){
        return dto;
    }
}
