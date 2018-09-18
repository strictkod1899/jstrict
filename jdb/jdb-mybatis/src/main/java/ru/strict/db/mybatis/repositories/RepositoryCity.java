package ru.strict.db.mybatis.repositories;

import ru.strict.db.core.common.GenerateIdType;
import ru.strict.db.core.common.MapperDtoType;
import ru.strict.db.core.dto.DtoCity;
import ru.strict.db.core.entities.EntityCity;
import ru.strict.db.core.mappers.dto.MapperDtoFactory;
import ru.strict.db.core.repositories.IRepositoryNamed;
import ru.strict.db.mybatis.connection.CreateConnectionByMybatis;
import ru.strict.db.mybatis.mappers.sql.MapperSqlCity;
import ru.strict.utils.UtilClassOperations;

import java.util.List;

public class RepositoryCity<ID> extends RepositoryNamedBase<ID, EntityCity<ID>, DtoCity<ID>, MapperSqlCity<ID>> {

    private static final String[] COLUMNS_NAME = new String[] {"caption", "country_id"};

    public RepositoryCity(CreateConnectionByMybatis connectionSource, GenerateIdType generateIdType) {
        super("city",
                COLUMNS_NAME,
                connectionSource,
                UtilClassOperations.<MapperSqlCity<ID>>castClass(MapperSqlCity.class),
                new MapperDtoFactory<ID, EntityCity<ID>, DtoCity<ID>>().instance(MapperDtoType.CITY),
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
