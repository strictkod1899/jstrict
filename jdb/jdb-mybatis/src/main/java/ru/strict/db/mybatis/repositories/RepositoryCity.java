package ru.strict.db.mybatis.repositories;

import ru.strict.db.core.common.GenerateIdType;
import ru.strict.db.core.dto.DtoCity;
import ru.strict.db.core.entities.EntityCity;
import ru.strict.db.core.mappers.dto.MapperDtoFactory;
import ru.strict.db.core.repositories.interfaces.IRepositoryCity;
import ru.strict.db.mybatis.connection.CreateConnectionByMybatis;
import ru.strict.db.mybatis.mappers.sql.MapperSqlCity;
import ru.strict.utils.UtilClass;

import java.util.List;

public class RepositoryCity<ID>
        extends RepositoryNamedBase<ID, EntityCity<ID>, DtoCity<ID>, MapperSqlCity<ID>>
        implements IRepositoryCity<ID> {

    private static final String[] COLUMNS_NAME = new String[] {"caption", "country_id"};

    public RepositoryCity(CreateConnectionByMybatis connectionSource, GenerateIdType generateIdType) {
        super("city",
                COLUMNS_NAME,
                connectionSource,
                UtilClass.<MapperSqlCity<ID>>castClass(MapperSqlCity.class),
                new MapperDtoFactory<ID>().instance(UtilClass.castClass(EntityCity.class), UtilClass.castClass(DtoCity.class)),
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
