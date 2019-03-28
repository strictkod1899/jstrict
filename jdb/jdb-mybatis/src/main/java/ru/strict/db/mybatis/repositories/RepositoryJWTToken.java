package ru.strict.db.mybatis.repositories;

import ru.strict.db.core.common.GenerateIdType;
import ru.strict.db.core.dto.DtoJWTToken;
import ru.strict.db.core.entities.EntityJWTToken;
import ru.strict.db.core.mappers.dto.MapperDtoFactory;
import ru.strict.db.core.repositories.interfaces.IRepositoryJWTToken;
import ru.strict.db.mybatis.connection.CreateConnectionByMybatis;
import ru.strict.db.mybatis.mappers.sql.MapperSqlJWTToken;
import ru.strict.utils.UtilClass;

public class RepositoryJWTToken<ID>
        extends RepositoryMybatisBase<ID, EntityJWTToken<ID>, DtoJWTToken<ID>, MapperSqlJWTToken<ID>>
        implements IRepositoryJWTToken<ID> {

    private static final String[] COLUMNS_NAME = new String[] {"access_token", "refresh_token", "expire_time_access",
            "expire_time_refresh", "issued_at", "issuer", "subject", "not_before", "audience", "secret",
            "algorithm", "type", "userx_id"};

    public RepositoryJWTToken(CreateConnectionByMybatis connectionSource, GenerateIdType generateIdType) {
        super("token",
                COLUMNS_NAME,
                connectionSource,
                UtilClass.<MapperSqlJWTToken<ID>>castClass(MapperSqlJWTToken.class),
                new MapperDtoFactory<ID>().instance(UtilClass.castClass(EntityJWTToken.class), UtilClass.castClass(DtoJWTToken.class)),
                generateIdType);
    }

    @Override
    protected Class getThisClass() {
        return this.getClass();
    }
}
