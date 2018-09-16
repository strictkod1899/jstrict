package ru.strict.db.spring.repositories;

import ru.strict.db.core.common.GenerateIdType;
import ru.strict.db.core.common.MapperDtoType;
import ru.strict.db.core.connections.CreateConnectionByDataSource;
import ru.strict.db.core.dto.DtoJWTToken;
import ru.strict.db.core.dto.DtoUserToken;
import ru.strict.db.core.entities.EntityJWTToken;
import ru.strict.db.core.entities.EntityUser;
import ru.strict.db.core.mappers.dto.MapperDtoFactory;
import ru.strict.db.core.requests.DbRequests;
import ru.strict.db.core.requests.DbWhere;

import java.util.List;
import java.util.Map;

public class RepositoryUserFillToken<ID>
        extends RepositoryUser<ID, DtoUserToken> {

    public RepositoryUserFillToken(CreateConnectionByDataSource connectionSource, GenerateIdType generateIdType) {
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
        RepositorySpringBase<ID, EntityJWTToken, DtoJWTToken> repositoryToken =
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
