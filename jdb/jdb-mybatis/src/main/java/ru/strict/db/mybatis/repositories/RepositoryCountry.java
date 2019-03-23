package ru.strict.db.mybatis.repositories;

import ru.strict.db.core.common.GenerateIdType;
import ru.strict.db.core.dto.DtoCountry;
import ru.strict.db.core.entities.EntityCountry;
import ru.strict.db.core.mappers.dto.MapperDtoFactory;
import ru.strict.db.mybatis.connection.CreateConnectionByMybatis;
import ru.strict.db.mybatis.mappers.sql.MapperSqlCountry;
import ru.strict.utils.UtilClass;

public class RepositoryCountry<ID> extends RepositoryNamedBase<ID, EntityCountry<ID>, DtoCountry<ID>, MapperSqlCountry<ID>> {

    private static final String[] COLUMNS_NAME = new String[] {"caption"};

    public RepositoryCountry(CreateConnectionByMybatis connectionSource, GenerateIdType generateIdType) {
        super("country",
                COLUMNS_NAME,
                connectionSource,
                UtilClass.<MapperSqlCountry<ID>>castClass(MapperSqlCountry.class),
                new MapperDtoFactory<ID>().instance(UtilClass.castClass(EntityCountry.class), UtilClass.castClass(DtoCountry.class)),
                generateIdType);
    }

    @Override
    protected Class getThisClass() {
        return this.getClass();
    }

    @Override
    protected String getColumnWithName() {
        return COLUMNS_NAME[0];
    }
}
