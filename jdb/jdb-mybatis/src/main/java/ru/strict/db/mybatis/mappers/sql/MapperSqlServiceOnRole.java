package ru.strict.db.mybatis.mappers.sql;

import org.apache.ibatis.annotations.Param;
import ru.strict.db.core.entities.EntityServiceOnRole;

import java.util.List;

public interface MapperSqlServiceOnRole<ID, SERVICE> extends MapperSqlExtension<ID, EntityServiceOnRole<ID, SERVICE>> {
    List<EntityServiceOnRole<ID, SERVICE>> readByServiceId(@Param("serviceId") Integer serviceId);
    List<EntityServiceOnRole<ID, SERVICE>> readByRoleId(@Param("roleId") ID roleId);
}
