package ru.strict.db.spring.mappers.sql;

import org.springframework.jdbc.core.RowMapper;
import ru.strict.db.spring.security.UserSecurity;

import java.sql.ResultSet;

public class MapperSpringUserSecurity<ID> implements RowMapper<UserSecurity<ID>> {

    private MapperSpringUser<ID> userMapper;

    public MapperSpringUserSecurity(MapperSpringUser<ID> userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public UserSecurity<ID> mapRow(ResultSet resultSet, int i) {
        return new UserSecurity<>(userMapper.mapRow(resultSet, i));
    }
}
