package ru.strict.db.spring.repositories;

import ru.strict.db.core.common.GenerateIdType;
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
        return valuesByColumn;
    }

    @Override
    protected DtoJWTToken<ID> fill(DtoJWTToken<ID> dto){
        // Добавление пользователя
        IRepository<ID, DtoUserWithToken<ID>> repositoryUser = null;
        try {
            repositoryUser = new RepositoryUser(getConnectionSource(),
                    new MapperDtoFactory().instance(UtilClass.castClass(EntityUser.class), UtilClass.castClass(DtoUser.class)),
                    GenerateIdType.NONE);
            dto.setUser(repositoryUser.read(dto.getUserId()));
        }finally {
            if(repositoryUser != null){
                repositoryUser.close();
            }
        }
        return dto;
    }

    @Override
    protected Class getThisClass() {
        return this.getClass();
    }
}
