package ru.strict.db.mybatis.repositories;

import ru.strict.db.core.common.GenerateIdType;
import ru.strict.db.core.dto.DtoProfileInfo;
import ru.strict.db.core.entities.EntityProfileInfo;
import ru.strict.db.core.mappers.dto.MapperDtoFactory;
import ru.strict.db.core.repositories.interfaces.IRepositoryProfile;
import ru.strict.db.mybatis.connection.CreateConnectionByMybatis;
import ru.strict.db.mybatis.mappers.sql.MapperSqlProfileInfo;
import ru.strict.utils.UtilClass;

public class RepositoryProfileInfo<ID>
        extends RepositoryMybatisBase<ID, EntityProfileInfo<ID>, DtoProfileInfo<ID>, MapperSqlProfileInfo<ID>>
        implements IRepositoryProfile<ID, DtoProfileInfo<ID>> {

    private static final String[] COLUMNS_NAME = new String[] {"name", "surname", "middlename", "userx_id", "datebirth",
            "phone", "city_id"};

    public RepositoryProfileInfo(CreateConnectionByMybatis connectionSource, GenerateIdType generateIdType) {
        super("profile",
                COLUMNS_NAME,
                connectionSource,
                UtilClass.castClass(MapperSqlProfileInfo.class),
                new MapperDtoFactory<ID>().instance(UtilClass.castClass(EntityProfileInfo.class), UtilClass.castClass(DtoProfileInfo.class)),
                generateIdType);
    }

    @Override
    protected Class getThisClass() {
        return this.getClass();
    }
}
