package ru.strict.db.mybatis.repositories;

import ru.strict.db.core.common.GenerateIdType;
import ru.strict.db.core.requests.DbTable;
import ru.strict.models.Roleuser;
import ru.strict.db.core.entities.EntityRoleuser;
import ru.strict.db.core.mappers.dto.MapperDtoFactory;
import ru.strict.db.core.repositories.IRepositoryNamed;
import ru.strict.db.mybatis.connection.CreateConnectionByMybatis;
import ru.strict.db.mybatis.mappers.sql.MapperSqlRoleuser;
import ru.strict.utils.UtilClass;

public class RepositoryRoleuser<ID>
        extends RepositoryNamedBase<ID, EntityRoleuser<ID>, Roleuser<ID>, MapperSqlRoleuser<ID>>
        implements IRepositoryNamed<ID, Roleuser<ID>>  {

    private static final String[] COLUMNS_NAME = new String[] {"code", "description"};

    public RepositoryRoleuser(CreateConnectionByMybatis connectionSource, GenerateIdType generateIdType) {
        super(new DbTable("roleuser", "role"),
                COLUMNS_NAME,
                connectionSource,
                UtilClass.<MapperSqlRoleuser<ID>>castClass(MapperSqlRoleuser.class),
                new MapperDtoFactory<ID>().instance(UtilClass.castClass(EntityRoleuser.class), UtilClass.castClass(Roleuser.class)),
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
