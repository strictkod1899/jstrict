package ru.strict.db.mybatis.repositories;

import ru.strict.db.core.common.GenerateIdType;
import ru.strict.db.core.dto.*;
import ru.strict.db.core.entities.EntityUser;
import ru.strict.db.core.mappers.dto.MapperDtoBase;
import ru.strict.db.core.mappers.dto.MapperDtoFactory;
import ru.strict.db.core.repositories.interfaces.IRepositoryUser;
import ru.strict.db.core.requests.DbRequests;
import ru.strict.db.core.requests.DbWhereItem;
import ru.strict.db.mybatis.connection.CreateConnectionByMybatis;
import ru.strict.db.mybatis.mappers.sql.MapperSqlUser;
import ru.strict.utils.UtilClass;

public class RepositoryUser<ID, DTO extends DtoUserBase<ID>>
        extends RepositoryMybatisBase<ID, EntityUser<ID>, DTO, MapperSqlUser<ID>>
        implements IRepositoryUser<ID, DTO> {

    private static final String[] COLUMNS_NAME = new String[] {"username", "passwordencode", "email",
            "is_blocked", "is_deleted", "is_confirm_email"};

    /**
     * Для этого конструктуора используется DtoUser
     */
    public RepositoryUser(CreateConnectionByMybatis connectionSource, GenerateIdType generateIdType) {
        super("userx",
                COLUMNS_NAME,
                connectionSource,
                UtilClass.<MapperSqlUser<ID>>castClass(MapperSqlUser.class),
                new MapperDtoFactory<ID>().instance(UtilClass.castClass(EntityUser.class), UtilClass.castClass(DtoUser.class)),
                generateIdType);
    }

    public RepositoryUser(CreateConnectionByMybatis connectionSource,
                              MapperDtoBase<ID, EntityUser<ID>, DTO> dtoMapper,
                              GenerateIdType generateIdType) {
        super("userx",
                COLUMNS_NAME,
                connectionSource,
                UtilClass.castClass(MapperSqlUser.class),
                dtoMapper,
                generateIdType);
    }

    @Override
    public String getColumnWithName() {
        return COLUMNS_NAME[0];
    }

    @Override
    protected Class getThisClass() {
        return this.getClass();
    }
}
