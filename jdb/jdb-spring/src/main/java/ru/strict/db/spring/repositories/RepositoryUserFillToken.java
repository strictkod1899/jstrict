package ru.strict.db.spring.repositories;

import ru.strict.db.core.common.GenerateIdType;
import ru.strict.db.core.common.MapperDtoType;
import ru.strict.db.core.connections.CreateConnectionByDataSource;
import ru.strict.db.core.dto.DtoJWTToken;
import ru.strict.db.core.dto.DtoUserToken;
import ru.strict.db.core.entities.EntityUser;
import ru.strict.db.core.mappers.dto.MapperDtoFactory;
import ru.strict.db.core.repositories.IRepository;
import ru.strict.db.core.requests.DbRequests;
import ru.strict.db.core.requests.DbWhere;
import ru.strict.db.core.requests.DbWhereItem;
import ru.strict.utils.UtilClass;

import java.util.List;
import java.util.Map;

public class RepositoryUserFillToken<ID>
        extends RepositoryUser<ID, DtoUserToken<ID>> {

    public RepositoryUserFillToken(CreateConnectionByDataSource connectionSource, GenerateIdType generateIdType) {
        super(connectionSource,
                new MapperDtoFactory<ID>().instance(UtilClass.castClass(EntityUser.class), UtilClass.castClass(DtoUserToken.class)),
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
            repositoryToken = new RepositoryJWTToken<>(getConnectionSource(), GenerateIdType.NONE);
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
