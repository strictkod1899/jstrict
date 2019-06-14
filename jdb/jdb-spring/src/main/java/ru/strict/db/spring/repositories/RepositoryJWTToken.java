package ru.strict.db.spring.repositories;

import ru.strict.db.core.common.GenerateIdType;
import ru.strict.db.core.common.SqlParameters;
import ru.strict.db.core.connections.CreateConnectionByDataSource;
import ru.strict.db.core.dto.DtoJWTToken;
import ru.strict.db.core.dto.DtoUser;
import ru.strict.db.core.dto.DtoUserWithToken;
import ru.strict.db.core.entities.EntityJWTToken;
import ru.strict.db.core.entities.EntityUser;
import ru.strict.db.core.mappers.dto.MapperDtoFactory;
import ru.strict.db.core.repositories.IRepository;
import ru.strict.db.core.repositories.interfaces.IRepositoryJWTToken;
import ru.strict.db.core.requests.DbRequests;
import ru.strict.db.core.requests.DbWhereItem;
import ru.strict.db.spring.mappers.sql.MapperSqlJWTToken;
import ru.strict.utils.UtilClass;

import java.util.LinkedHashMap;
import java.util.Map;

public class RepositoryJWTToken<ID>
        extends RepositorySpringBase<ID, EntityJWTToken<ID>, DtoJWTToken<ID>>
        implements IRepositoryJWTToken<ID> {

    private static final String[] COLUMNS_NAME = new String[] {"access_token", "refresh_token",
            "expire_time_access", "expire_time_refresh", "issued_at", "issuer", "subject", "not_before",
            "audience", "secret", "algorithm", "type", "userx_id"};

    public RepositoryJWTToken(CreateConnectionByDataSource connectionSource, GenerateIdType generateIdType) {
        super("token", COLUMNS_NAME, connectionSource,
                new MapperDtoFactory<ID>().instance(UtilClass.castClass(EntityJWTToken.class), UtilClass.castClass(DtoJWTToken.class)),
                new MapperSqlJWTToken<ID>(COLUMNS_NAME),
                generateIdType);
    }

    @Override
    protected SqlParameters getParameters(EntityJWTToken<ID> entity){
        SqlParameters parameters = new SqlParameters();
        parameters.add(0, COLUMNS_NAME[0], entity.getAccessToken());
        parameters.add(1, COLUMNS_NAME[1], entity.getRefreshToken());
        parameters.add(2, COLUMNS_NAME[2], entity.getExpireTimeAccess());
        parameters.add(3, COLUMNS_NAME[3], entity.getExpireTimeRefresh());
        parameters.add(4, COLUMNS_NAME[4], entity.getIssuedAt());
        parameters.add(5, COLUMNS_NAME[5], entity.getIssuer());
        parameters.add(6, COLUMNS_NAME[6], entity.getSubject());
        parameters.add(7, COLUMNS_NAME[7], entity.getNotBefore());
        parameters.add(8, COLUMNS_NAME[8], entity.getAudience());
        parameters.add(9, COLUMNS_NAME[9], entity.getSecret());
        parameters.add(10, COLUMNS_NAME[10], entity.getAlgorithm());
        parameters.add(11, COLUMNS_NAME[11], entity.getType());
        parameters.add(12, COLUMNS_NAME[12], entity.getUserId());
        return parameters;
    }

    @Override
    protected DtoJWTToken<ID> fill(DtoJWTToken<ID> dto){
        // Добавление пользователя
        IRepository<ID, DtoUserWithToken<ID>> repositoryUser = new RepositoryUser(getConnectionSource(),
                new MapperDtoFactory().instance(UtilClass.castClass(EntityUser.class), UtilClass.castClass(DtoUser.class)),
                GenerateIdType.NONE);
        dto.setUser(repositoryUser.read(dto.getUserId()));
        return dto;
    }

    @Override
    protected Class getThisClass() {
        return this.getClass();
    }
}
