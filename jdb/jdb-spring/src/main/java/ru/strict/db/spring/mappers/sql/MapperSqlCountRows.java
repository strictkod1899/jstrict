package ru.strict.db.spring.mappers.sql;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MapperSqlCountRows implements RowMapper<Integer> {

    public MapperSqlCountRows() {}

    @Override
    public Integer mapRow(ResultSet resultSet, int i) throws SQLException {
        resultSet.next();
        Integer result = resultSet.getInt(1);
        return result;
    }
}
