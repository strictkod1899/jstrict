package ru.strict.db.mybatis.mappers.sql;

import org.apache.ibatis.annotations.Param;
import ru.strict.db.core.entities.EntityJWTToken;

import java.util.List;

public interface MapperSqlJWTToken<ID> extends MapperSqlExtension<ID, EntityJWTToken<ID>> {
    EntityJWTToken<ID> readByAccessToken(@Param("accessToken") String accessToken);
    EntityJWTToken<ID> readByRefreshToken(@Param("refreshToken") String refreshToken);
    List<EntityJWTToken<ID>> readByUserId(@Param("userId") ID userId);
}
