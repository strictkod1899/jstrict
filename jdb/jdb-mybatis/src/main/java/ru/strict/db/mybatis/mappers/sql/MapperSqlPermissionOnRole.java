package ru.strict.db.mybatis.mappers.sql;

import org.apache.ibatis.annotations.Param;
import ru.strict.models.PermissionOnRole;

import java.util.List;

public interface MapperSqlPermissionOnRole<ID, PERMISSION> extends MapperSqlExtension<ID, PermissionOnRole<ID, PERMISSION>> {
    List<PermissionOnRole<ID, PERMISSION>> readByPermissionId(@Param("permissionId") Integer permissionId);
    List<PermissionOnRole<ID, PERMISSION>> readByRoleId(@Param("roleId") ID roleId);
}
