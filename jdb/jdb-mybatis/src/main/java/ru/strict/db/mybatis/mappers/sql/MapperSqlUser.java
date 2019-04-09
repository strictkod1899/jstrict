package ru.strict.db.mybatis.mappers.sql;

import org.apache.ibatis.annotations.Param;
import ru.strict.db.core.entities.EntityUser;

import java.util.List;

public interface MapperSqlUser<ID> extends MapperSqlNamed<ID, EntityUser<ID>> {
    EntityUser<ID> readByEmail(@Param("email") String email);
    boolean isDeleted(@Param("id") ID id);
    boolean isBlocked(@Param("id") ID id);
    boolean isConfirmEmail(@Param("id") ID id);
}
