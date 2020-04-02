package ru.strict.db.mybatis.mappers.sql;

import org.apache.ibatis.annotations.Param;
import ru.strict.models.JWTToken;

import java.util.List;

public interface MapperSqlJWTToken<ID> extends MapperSqlExtension<ID, JWTToken<ID>> {
    JWTToken<ID> readByAccessToken(@Param("accessToken") String accessToken);
    JWTToken<ID> readByRefreshToken(@Param("refreshToken") String refreshToken);
    List<JWTToken<ID>> readByUserId(@Param("userId") ID userId);
}
