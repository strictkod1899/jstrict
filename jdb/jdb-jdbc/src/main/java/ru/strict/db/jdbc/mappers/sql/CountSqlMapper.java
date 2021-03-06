package ru.strict.db.jdbc.mappers.sql;

import java.sql.ResultSet;

public class CountSqlMapper extends BaseSqlMapper<Long> {

    @Override
    protected Long implementMap(ResultSet resultSet) throws Exception {
        return resultSet.getLong(1);
    }
}
