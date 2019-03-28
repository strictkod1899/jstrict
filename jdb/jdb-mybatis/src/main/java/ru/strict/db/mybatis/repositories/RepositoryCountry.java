package ru.strict.db.mybatis.repositories;

import ru.strict.db.core.common.GenerateIdType;
import ru.strict.db.core.dto.DtoCountry;
import ru.strict.db.core.entities.EntityCountry;
import ru.strict.db.core.mappers.dto.MapperDtoFactory;
import ru.strict.db.core.repositories.IRepositoryNamed;
import ru.strict.db.mybatis.connection.CreateConnectionByMybatis;
import ru.strict.db.mybatis.mappers.sql.MapperSqlCountry;
import ru.strict.utils.UtilClass;

public class RepositoryCountry<ID>
        extends RepositoryMybatisBase<ID, EntityCountry<ID>, DtoCountry<ID>, MapperSqlCountry<ID>>
        implements IRepositoryNamed<ID, DtoCountry<ID>> {

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
    public String getColumnWithName() {
        return COLUMNS_NAME[0];
    }
}
