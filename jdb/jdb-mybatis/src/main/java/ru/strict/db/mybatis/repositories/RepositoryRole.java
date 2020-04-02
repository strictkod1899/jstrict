package ru.strict.db.mybatis.repositories;

import ru.strict.db.core.common.GenerateIdType;
import ru.strict.db.core.repositories.DefaultColumns;
import ru.strict.db.core.repositories.DefaultTable;
import ru.strict.models.Role;
import ru.strict.db.core.repositories.IRepositoryNamed;
import ru.strict.db.mybatis.connection.CreateConnectionByMybatis;
import ru.strict.db.mybatis.mappers.sql.MapperSqlRole;
import ru.strict.utils.UtilClass;

public class RepositoryRole<ID>
        extends RepositoryMybatisNamed<ID, Role<ID>, MapperSqlRole<ID>>
        implements IRepositoryNamed<ID, Role<ID>>  {

    private static final String[] COLUMNS_NAME = DefaultColumns.ROLE.columns();

    public RepositoryRole(CreateConnectionByMybatis connectionSource, GenerateIdType generateIdType) {
        super(DefaultTable.ROLE.table(),
                COLUMNS_NAME,
                connectionSource,
                UtilClass.castClass(MapperSqlRole.class),
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
