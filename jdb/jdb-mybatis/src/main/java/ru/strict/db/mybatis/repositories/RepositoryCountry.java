package ru.strict.db.mybatis.repositories;

import ru.strict.db.core.common.GenerateIdType;
import ru.strict.db.core.repositories.DefaultColumns;
import ru.strict.db.core.repositories.DefaultTable;
import ru.strict.models.Country;
import ru.strict.db.core.repositories.IRepositoryNamed;
import ru.strict.db.mybatis.connection.CreateConnectionByMybatis;
import ru.strict.db.mybatis.mappers.sql.MapperSqlCountry;
import ru.strict.utils.UtilClass;

public class RepositoryCountry<ID>
        extends RepositoryMybatisNamed<ID, Country<ID>, MapperSqlCountry<ID>>
        implements IRepositoryNamed<ID, Country<ID>> {

    private static final String[] COLUMNS_NAME = DefaultColumns.COUNTRY.columns();

    public RepositoryCountry(CreateConnectionByMybatis connectionSource, GenerateIdType generateIdType) {
        super(DefaultTable.COUNTRY.table(),
                COLUMNS_NAME,
                connectionSource,
                UtilClass.castClass(MapperSqlCountry.class),
                generateIdType);
    }

    @Override
    protected Class getThisClass() {
        return this.getClass();
    }

    @Override
    public String getColumnWithName() {
        return COLUMNS_NAME[0];
    }
}
