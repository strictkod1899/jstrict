package ru.strict.db.mybatis.mappers.sql;

import org.apache.ibatis.annotations.Param;
import ru.strict.models.DetailsUser;

public interface MapperSqlUser<ID> extends MapperSqlNamed<ID, DetailsUser<ID>> {
    DetailsUser<ID> readByEmail(@Param("email") String email);
    boolean isDeleted(@Param("id") ID id);
    boolean isBlocked(@Param("id") ID id);
    boolean isConfirmEmail(@Param("id") ID id);
}
