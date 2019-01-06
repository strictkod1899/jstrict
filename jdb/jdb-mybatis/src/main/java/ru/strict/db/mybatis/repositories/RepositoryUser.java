package ru.strict.db.mybatis.repositories;

import ru.strict.db.core.common.GenerateIdType;
import ru.strict.db.core.dto.*;
import ru.strict.db.core.entities.EntityUser;
import ru.strict.db.core.mappers.dto.MapperDtoBase;
import ru.strict.db.core.repositories.interfaces.IRepositoryUser;
import ru.strict.db.core.requests.DbRequests;
import ru.strict.db.core.requests.DbWhere;
import ru.strict.db.core.requests.DbWhereItem;
import ru.strict.db.mybatis.connection.CreateConnectionByMybatis;
import ru.strict.db.mybatis.mappers.sql.MapperSqlUser;
import ru.strict.utils.UtilClassOperations;

import java.sql.Connection;
import java.util.*;

public class RepositoryUser<ID, DTO extends DtoUserBase<ID>>
        extends RepositoryNamedBase<ID, EntityUser<ID>, DTO, MapperSqlUser<ID>>
        implements IRepositoryUser<ID, DTO> {

    private static final String[] COLUMNS_NAME = new String[] {"username", "passwordencode", "email",
            "isBlocked", "isDeleted", "isConfirmEmail"};

    public RepositoryUser(CreateConnectionByMybatis connectionSource,
                              MapperDtoBase<ID, EntityUser<ID>, DTO> dtoMapper,
                              GenerateIdType generateIdType) {
        super("userx",
                COLUMNS_NAME,
                connectionSource,
                UtilClassOperations.<MapperSqlUser<ID>>castClass(MapperSqlUser.class),
                dtoMapper,
                generateIdType);
    }

    @Override
    public DTO readByEmail(String email) {
        DbRequests requests = new DbRequests(getTableName());
        requests.addWhere(new DbWhereItem(getTableName(), "email", email, "="));

        DTO result = readAll(requests).stream().findFirst().orElse(null);
        return result;
    }

    @Override
    protected String getColumnWithName() {
        return COLUMNS_NAME[0];
    }

    @Override
    protected Class getThisClass() {
        return this.getClass();
    }
}
