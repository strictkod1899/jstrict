package ru.strict.db.mybatis.mappers.sql;

import ru.strict.db.core.entities.EntityCity;

import java.util.List;

public interface MapperSqlCity<ID> extends MapperSqlExtension<ID, EntityCity<ID>> {
    List<EntityCity<ID>> readByCountryId(ID countryId);
}
