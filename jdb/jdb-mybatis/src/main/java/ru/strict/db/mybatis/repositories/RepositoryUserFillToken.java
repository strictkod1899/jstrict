package ru.strict.db.mybatis.repositories;

import ru.strict.db.core.common.GenerateIdType;
import ru.strict.db.core.common.MapperDtoType;
import ru.strict.db.core.dto.DtoUserToken;
import ru.strict.db.core.entities.EntityUser;
import ru.strict.db.core.mappers.dto.MapperDtoFactory;
import ru.strict.db.mybatis.connection.CreateConnectionByMybatis;

public class RepositoryUserFillToken<ID> extends RepositoryUser<ID, DtoUserToken<ID>> {

    public RepositoryUserFillToken(CreateConnectionByMybatis connectionSource, GenerateIdType generateIdType) {
        super(connectionSource,
                new MapperDtoFactory<ID, EntityUser<ID>, DtoUserToken<ID>>().instance(MapperDtoType.USER_TOKEN),
                generateIdType);
    }

    @Override
    protected Class getThisClass() {
        return this.getClass();
    }
}
