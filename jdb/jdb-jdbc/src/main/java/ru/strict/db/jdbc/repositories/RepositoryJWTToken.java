package ru.strict.db.jdbc.repositories;

import ru.strict.db.core.common.GenerateIdType;
import ru.strict.db.core.common.SqlParameters;
import ru.strict.db.core.connections.ICreateConnection;
import ru.strict.models.JWTToken;
import ru.strict.models.User;
import ru.strict.models.UserWithToken;
import ru.strict.db.core.entities.EntityJWTToken;
import ru.strict.db.core.entities.EntityUser;
import ru.strict.db.core.mappers.dto.MapperDtoFactory;
import ru.strict.db.core.repositories.IRepository;
import ru.strict.db.core.repositories.interfaces.IRepositoryJWTToken;
import ru.strict.db.jdbc.mappers.sql.MapperSqlJWTToken;
import ru.strict.utils.UtilClass;

import java.sql.Connection;

public class RepositoryJWTToken<ID>
        extends RepositoryJdbcBase<ID, EntityJWTToken<ID>, JWTToken<ID>>
        implements IRepositoryJWTToken<ID> {

    private static final String[] COLUMNS_NAME = new String[] {"access_token", "refresh_token", "expire_time_access",
            "expire_time_refresh", "issued_at", "issuer", "subject", "not_before", "audience", "secret",
            "algorithm", "type", "userx_id"};

    public RepositoryJWTToken(ICreateConnection<Connection> connectionSource, GenerateIdType generateIdType) {
        super("token", COLUMNS_NAME, connectionSource,
                new MapperDtoFactory<ID>().instance(UtilClass.castClass(EntityJWTToken.class), UtilClass.castClass(JWTToken.class)),
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
    protected JWTToken<ID> fill(JWTToken<ID> dto){
        // Добавление пользователя
        IRepository<ID, UserWithToken<ID>> repositoryUser = new RepositoryUser(getConnectionSource(),
                new MapperDtoFactory().instance(UtilClass.castClass(EntityUser.class), UtilClass.castClass(User.class)),
                GenerateIdType.NONE);
        dto.setUser(repositoryUser.read(dto.getUserId()));
        return dto;
    }

    @Override
    protected Class getThisClass() {
        return this.getClass();
    }
}
