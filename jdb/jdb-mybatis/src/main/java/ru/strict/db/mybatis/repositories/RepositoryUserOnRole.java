package ru.strict.db.mybatis.repositories;

import ru.strict.db.core.common.GenerateIdType;
import ru.strict.db.core.dto.DtoUserOnRole;
import ru.strict.db.core.entities.EntityUserOnRole;
import ru.strict.db.core.mappers.dto.MapperDtoFactory;
import ru.strict.db.mybatis.connection.CreateConnectionByMybatis;
import ru.strict.db.mybatis.mappers.sql.MapperSqlUserOnRole;
import ru.strict.utils.UtilClass;

public class RepositoryUserOnRole<ID>
        extends RepositoryMybatisBase<ID, EntityUserOnRole<ID>, DtoUserOnRole<ID>, MapperSqlUserOnRole<ID>> {

    private static final String[] COLUMNS_NAME = new String[] {"userx_id", "roleuser_id"};

    public RepositoryUserOnRole(CreateConnectionByMybatis connectionSource, GenerateIdType generateIdType) {
        super("user_on_role",
                COLUMNS_NAME,
                connectionSource,
                UtilClass.castClass(MapperSqlUserOnRole.class),
                new MapperDtoFactory<ID>().instance(UtilClass.castClass(EntityUserOnRole.class), UtilClass.castClass(DtoUserOnRole.class)),
                generateIdType);
    }

    @Override
    protected Class getThisClass() {
        return this.getClass();
    }
}
