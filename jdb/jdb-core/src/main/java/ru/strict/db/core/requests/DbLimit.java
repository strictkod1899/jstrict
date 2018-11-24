package ru.strict.db.core.requests;

/**
 * Элемент LIMIT sql-запроса
 */
public class DbLimit implements IDbRequest {

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
    public String toString() {
        return getSql();
    }
}
