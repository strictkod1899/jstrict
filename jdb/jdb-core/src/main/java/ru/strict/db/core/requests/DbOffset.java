package ru.strict.db.core.requests;

import ru.strict.db.core.common.SqlParameters;

/**
 * Элемент LIMIT sql-запроса
 */
public class DbOffset implements IDbRequest, IDbParametrizedRequest {

    private int offset;

    public DbOffset(int offset) {
        this.offset = offset;
    }

    public int getOffset() {
        return offset;
    }

    @Override
    public String getSql() {
        return "OFFSET " + offset;
    }

    @Override
    public String getParametrizedSql() {
        return "OFFSET ?";
    }

    @Override
    public SqlParameters getParameters() {
        SqlParameters sqlParameters = new SqlParameters();
        sqlParameters.add(0, "offset", offset);
        return sqlParameters;
    }

    @Override
    public String toString() {
        return getSql();
    }
}
