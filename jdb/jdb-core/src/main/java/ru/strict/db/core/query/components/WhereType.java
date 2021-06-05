package ru.strict.db.core.query.components;

import ru.strict.db.core.query.IQuery;

public enum WhereType implements IQuery {
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
