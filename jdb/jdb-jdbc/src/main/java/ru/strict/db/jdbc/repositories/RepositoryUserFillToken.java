package ru.strict.db.jdbc.repositories;

import ru.strict.db.core.common.GenerateIdType;
import ru.strict.db.core.common.MapperDtoType;
import ru.strict.db.core.connections.ICreateConnection;
import ru.strict.db.core.dto.*;
import ru.strict.db.core.entities.EntityJWTUserToken;
import ru.strict.db.core.entities.EntityProfileInfo;
import ru.strict.db.core.entities.EntityUser;
import ru.strict.db.core.entities.EntityUserOnRole;
import ru.strict.db.core.mappers.dto.MapperDtoBase;
import ru.strict.db.core.mappers.dto.MapperDtoFactory;
import ru.strict.db.core.repositories.IRepository;
import ru.strict.db.core.repositories.RepositoryBase;
import ru.strict.db.core.requests.DbRequests;
import ru.strict.db.core.requests.DbWhere;
import ru.strict.db.jdbc.mappers.sql.MapperSqlUser;
import ru.strict.utils.UtilLogger;

import java.util.*;

public class RepositoryUserFillToken<ID, SOURCE extends ICreateConnection>
        extends RepositoryUser<ID, SOURCE, DtoUserToken> {

    public RepositoryUserFillToken(SOURCE connectionSource, GenerateIdType isGenerateId) {
        super(connectionSource, new MapperDtoFactory().instance(MapperDtoType.USER_TOKEN), isGenerateId);
    }

    @Override
    protected Map<Integer, Object> getValueByColumn(EntityUser entity){
        return super.getValueByColumn(entity);
    }

    @Override
    protected DtoUserToken fill(DtoUserToken dto){
        dto = super.fill(dto);

        // Добавление токенов
        RepositoryJdbcBase<ID, SOURCE, EntityJWTUserToken, DtoJWTUserToken> repositoryToken =
                new RepositoryJWTUserToken<>(getConnectionSource(), GenerateIdType.NONE);
        DbRequests requests = new DbRequests(repositoryToken.getTableName(), true);
        requests.add(new DbWhere(repositoryToken.getTableName(), "userx_id", dto.getId(), "="));

        List<DtoJWTUserToken> tokens = repositoryToken.readAll(requests);
        dto.setTokens(tokens);
        return dto;
    }
}
