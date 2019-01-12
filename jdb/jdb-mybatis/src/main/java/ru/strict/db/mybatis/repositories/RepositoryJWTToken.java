package ru.strict.db.mybatis.repositories;

import ru.strict.db.core.common.GenerateIdType;
import ru.strict.db.core.common.MapperDtoType;
import ru.strict.db.core.connections.ICreateConnection;
import ru.strict.db.core.dto.DtoJWTToken;
import ru.strict.db.core.dto.DtoRoleuser;
import ru.strict.db.core.dto.DtoUser;
import ru.strict.db.core.entities.EntityJWTToken;
import ru.strict.db.core.mappers.dto.MapperDtoFactory;
import ru.strict.db.core.repositories.IRepository;
import ru.strict.db.core.repositories.interfaces.IRepositoryJWTToken;
import ru.strict.db.core.requests.DbRequests;
import ru.strict.db.core.requests.DbWhere;
import ru.strict.db.core.requests.DbWhereItem;
import ru.strict.db.mybatis.connection.CreateConnectionByMybatis;
import ru.strict.db.mybatis.mappers.sql.MapperSqlJWTToken;
import ru.strict.utils.UtilClassOperations;

import java.sql.Connection;
import java.util.LinkedHashMap;
import java.util.Map;

public class RepositoryJWTToken<ID>
        extends RepositoryNamedBase<ID, EntityJWTToken<ID>, DtoJWTToken<ID>, MapperSqlJWTToken<ID>>
        implements IRepositoryJWTToken<ID> {

    private static final String[] COLUMNS_NAME = new String[] {"accessToken", "refreshToken", "expireTimeAccess",
            "expireTimeRefresh", "issuedAt", "issuer", "subject", "notBefore", "audience", "secret",
            "algorithm", "type", "userx_id", "roleuser_id"};

    public RepositoryJWTToken(CreateConnectionByMybatis connectionSource, GenerateIdType generateIdType) {
        super("token",
                COLUMNS_NAME,
                connectionSource,
                UtilClassOperations.<MapperSqlJWTToken<ID>>castClass(MapperSqlJWTToken.class),
                new MapperDtoFactory<ID, EntityJWTToken<ID>, DtoJWTToken<ID>>().instance(MapperDtoType.JWT_TOKEN),
                generateIdType);
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
    protected Class getThisClass() {
        return this.getClass();
    }

    @Override
    protected String getColumnWithName() {
        return COLUMNS_NAME[0];
    }
}
