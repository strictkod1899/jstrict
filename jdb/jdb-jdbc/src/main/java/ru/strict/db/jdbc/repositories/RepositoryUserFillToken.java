package ru.strict.db.jdbc.repositories;

import ru.strict.db.core.common.GenerateIdType;
import ru.strict.db.core.common.MapperDtoType;
import ru.strict.db.core.connections.ICreateConnection;
import ru.strict.db.core.dto.*;
import ru.strict.db.core.entities.EntityUser;
import ru.strict.db.core.mappers.dto.MapperDtoFactory;
import ru.strict.db.core.repositories.IRepository;
import ru.strict.db.core.requests.DbRequests;
import ru.strict.db.core.requests.DbWhereItem;
import ru.strict.db.core.requests.WhereType;

import java.sql.Connection;
import java.util.*;

public class RepositoryUserFillToken<ID> extends RepositoryUser<ID, DtoUserToken<ID>> {

    public RepositoryUserFillToken(ICreateConnection<Connection> connectionSource, GenerateIdType generateIdType) {
        super(connectionSource,
                new MapperDtoFactory<ID, EntityUser<ID>, DtoUserToken<ID>>().instance(MapperDtoType.USER_TOKEN),
                generateIdType);
    }

    @Override
    protected Map<Integer, Object> getValueByColumn(EntityUser<ID> entity){
        return super.getValueByColumn(entity);
    }

    @Override
    protected DtoUserToken<ID> fill(DtoUserToken<ID> dto){
        dto = super.fill(dto);

        IRepository<ID, DtoJWTToken<ID>> repositoryToken = null;

        try {
            // Добавление токенов
            repositoryToken =
                    new RepositoryJWTToken<>(getConnectionSource(), GenerateIdType.NONE);
            DbRequests requests = new DbRequests();
            requests.addWhere(new DbWhereItem(repositoryToken.getTableName(), "userx_id", dto.getId(), "="));

            List<DtoJWTToken<ID>> tokens = repositoryToken.readAll(requests);
            dto.setTokens(tokens);
        }finally {
            if(repositoryToken != null){
                repositoryToken.close();
            }
        }
        return dto;
    }

    @Override
    protected Class getThisClass() {
        return this.getClass();
    }
}
