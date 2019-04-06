package ru.strict.db.core.requests;

import ru.strict.db.core.common.SqlParameters;

import java.util.List;

/**
 * Элемент LIMIT sql-запроса
 */
public class DbLimit implements IDbRequest, IDbParametrizedRequest {

    private int limit;

    public DbLimit(int limit) {
        this.limit = limit;
    }

    public int getLimit() {
        return limit;
    }

    @Override
    public String getSql() {
        return "LIMIT " + limit;
    }

    @Override
    public String getParametrizedSql() {
        return "LIMIT ?";
    }

    @Override
    public SqlParameters getParameters() {
        SqlParameters sqlParameters = new SqlParameters();
        sqlParameters.add(0, "limit", limit);
        return sqlParameters;
    }

    @Override
    public String toString() {
        return getSql();
    }
}
