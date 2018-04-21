package ru.strict.db.mappers.sql;

import ru.strict.db.mappers.MapSource;
import ru.strict.db.mappers.MapTarget;

import java.sql.ResultSet;

/**
 * Класс используется в StrictMapperSqlBase для преобразования sql-выборки в entity-класс
 */
public class StrictResultSetMap implements MapSource, MapTarget {

    private ResultSet resultSet;

    public StrictResultSetMap(ResultSet resultSet) {
        this.resultSet = resultSet;
    }

    public ResultSet getResultSet() {
        return resultSet;
    }
}
