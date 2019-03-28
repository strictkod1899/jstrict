package ru.strict.db.mybatis.repositories;

import ru.strict.db.core.common.GenerateIdType;
import ru.strict.db.core.dto.DtoServiceOnRole;
import ru.strict.db.core.entities.EntityServiceOnRole;
import ru.strict.db.core.mappers.dto.MapperDtoBase;
import ru.strict.db.core.repositories.interfaces.IRepositoryServiceOnRole;
import ru.strict.db.mybatis.connection.CreateConnectionByMybatis;
import ru.strict.db.mybatis.mappers.sql.MapperSqlServiceOnRole;
import ru.strict.utils.UtilClass;

public class RepositoryServiceOnRole<ID, SERVICE>
        extends RepositoryMybatisBase<ID, EntityServiceOnRole<ID, SERVICE>, DtoServiceOnRole<ID, SERVICE>, MapperSqlServiceOnRole<ID, SERVICE>>
        implements IRepositoryServiceOnRole<ID, SERVICE> {

    private static final String[] COLUMNS_NAME = new String[] {"service_id", "roleuser_id"};

    public RepositoryServiceOnRole(CreateConnectionByMybatis connectionSource,
                                   MapperDtoBase<ID, EntityServiceOnRole<ID, SERVICE>, DtoServiceOnRole<ID, SERVICE>> dtoMapper,
                                   GenerateIdType generateIdType) {
        super("service_on_role",
                COLUMNS_NAME,
                connectionSource,
                UtilClass.<MapperSqlServiceOnRole<ID, SERVICE>>castClass(MapperSqlServiceOnRole.class),
                dtoMapper,
                generateIdType);
    }

    @Override
    protected Class getThisClass() {
        return this.getClass();
    }
}
