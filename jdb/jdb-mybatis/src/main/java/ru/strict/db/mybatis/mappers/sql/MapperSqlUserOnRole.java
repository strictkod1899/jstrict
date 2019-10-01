package ru.strict.db.mybatis.mappers.sql;

import org.apache.ibatis.annotations.Param;
import ru.strict.models.UserOnRole;

import java.util.List;

public interface MapperSqlUserOnRole<ID> extends MapperSqlExtension<ID, UserOnRole<ID>> {
    List<UserOnRole<ID>> readByUserId(@Param("userId") ID userId);
    List<UserOnRole<ID>> readByRoleId(@Param("roleId") ID roleId);
}
