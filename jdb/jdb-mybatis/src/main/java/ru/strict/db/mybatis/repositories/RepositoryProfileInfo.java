package ru.strict.db.mybatis.repositories;

import ru.strict.db.core.common.GenerateIdType;
import ru.strict.db.core.common.MapperDtoType;
import ru.strict.db.core.dto.DtoProfileInfo;
import ru.strict.db.core.entities.EntityProfileInfo;
import ru.strict.db.core.mappers.dto.MapperDtoFactory;
import ru.strict.db.mybatis.connection.CreateConnectionByMybatis;
import ru.strict.db.mybatis.mappers.sql.MapperSqlProfileInfo;
import ru.strict.utils.UtilClassOperations;

public class RepositoryProfileInfo<ID>
        extends RepositoryMybatisBase<ID, EntityProfileInfo<ID>, DtoProfileInfo<ID>, MapperSqlProfileInfo<ID>> {

    private static final String[] COLUMNS_NAME = new String[] {"name", "surname", "middlename", "userx_id", "datebirth",
            "phone", "city_id"};

    public RepositoryProfileInfo(CreateConnectionByMybatis connectionSource, GenerateIdType generateIdType) {
        super("profile",
                COLUMNS_NAME,
                connectionSource,
                UtilClassOperations.<MapperSqlProfileInfo<ID>>castClass(MapperSqlProfileInfo.class),
                new MapperDtoFactory<ID, EntityProfileInfo<ID>, DtoProfileInfo<ID>>().instance(MapperDtoType.PROFILE_INFO),
                generateIdType);
    }

    @Override
    protected Class getThisClass() {
        return this.getClass();
    }
}
