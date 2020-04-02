package ru.strict.db.mybatis.mappers.sql;

import ru.strict.models.Role;

import java.util.List;

public interface MapperSqlRole<ID> extends MapperSqlNamed<ID, Role<ID>> {
    List<Role<ID>> readByUserId(ID userId);
}
