package ru.strict.db.mybatis.repositories;

import ru.strict.db.core.common.GenerateIdType;
import ru.strict.db.core.common.MapperDtoType;
import ru.strict.db.core.dto.DtoProfile;
import ru.strict.db.core.entities.EntityProfile;
import ru.strict.db.core.mappers.dto.MapperDtoFactory;
import ru.strict.db.mybatis.connection.CreateConnectionByMybatis;
import ru.strict.db.mybatis.mappers.sql.MapperSqlProfile;
import ru.strict.utils.UtilClassOperations;

public class RepositoryProfile<ID> extends RepositoryMybatisBase<ID, EntityProfile<ID>, DtoProfile<ID>, MapperSqlProfile<ID>> {

    private static final String[] COLUMNS_NAME = new String[] {"name", "surname", "middlename", "userx_id"};

    public RepositoryProfile(CreateConnectionByMybatis connectionSource, GenerateIdType generateIdType) {
        super("profile",
                COLUMNS_NAME,
                connectionSource,
                UtilClassOperations.<MapperSqlProfile<ID>>castClass(MapperSqlProfile.class),
                new MapperDtoFactory<ID, EntityProfile<ID>, DtoProfile<ID>>().instance(MapperDtoType.PROFILE),
                generateIdType);
    }

    @Override
    protected Class getThisClass() {
        return this.getClass();
    }
}
