package ru.strict.db.jdbc.repositories;

import ru.strict.db.core.common.GenerateIdType;
import ru.strict.db.core.common.MapperDtoType;
import ru.strict.db.core.connections.ICreateConnection;
import ru.strict.db.core.dto.*;
import ru.strict.db.core.entities.EntityJWTToken;
import ru.strict.db.core.entities.EntityUser;
import ru.strict.db.core.mappers.dto.MapperDtoFactory;
import ru.strict.db.core.requests.DbRequests;
import ru.strict.db.core.requests.DbWhere;

import java.sql.Connection;
import java.util.*;

public class RepositoryUserFillToken<ID>
        extends RepositoryUser<ID, DtoUserToken> {

    public RepositoryUserFillToken(ICreateConnection<Connection> connectionSource, GenerateIdType generateIdType) {
        super(connectionSource, new MapperDtoFactory().instance(MapperDtoType.USER_TOKEN), generateIdType);
    }

    @Override
    protected Map<Integer, Object> getValueByColumn(EntityUser entity){
        return super.getValueByColumn(entity);
    }

    @Override
    protected DtoUserToken fill(DtoUserToken dto){
        dto = super.fill(dto);

        // Добавление токенов
        RepositoryJdbcBase<ID, EntityJWTToken, DtoJWTToken> repositoryToken =
                new RepositoryJWTToken<>(getConnectionSource(), GenerateIdType.NONE);
        DbRequests requests = new DbRequests(repositoryToken.getTableName(), true);
        requests.add(new DbWhere(repositoryToken.getTableName(), "userx_id", dto.getId(), "="));

        List<DtoJWTToken> tokens = repositoryToken.readAll(requests);
        dto.setTokens(tokens);
        return dto;
    }

    @Override
    protected Class getThisClass() {
        return this.getClass();
    }
}
