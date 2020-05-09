package ru.strict.db.core.requests.components;

import ru.strict.db.core.requests.IRequest;

public enum WhereType implements IRequest {
    AND("AND"),
    OR("OR");

    private String sql;

    WhereType(String sql) {
        this.sql = sql;
    }

    @Override
    public String getSql() {
        return sql;
    }
}
