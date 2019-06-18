package ru.strict.db.core.requests;

import java.util.Objects;

/**
 * Элемент LIMIT sql-запроса
 */
public class DbLimit implements IDbRequest {

    private int limit;

    public DbLimit(int limit) {
        if(limit < 0){
            throw new IllegalArgumentException("limit is less than 0");
        }
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DbLimit dbLimit = (DbLimit) o;
        return limit == dbLimit.limit;
    }

    @Override
    public int hashCode() {
        return Objects.hash(limit);
    }
}
