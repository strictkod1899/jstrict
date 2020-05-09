package ru.strict.db.core.requests.components;

import ru.strict.db.core.requests.IRequest;
import ru.strict.validate.Validator;

public class Limit implements IRequest {
    private static final String LIMIT_TEMPLATE = " LIMIT %s ";

    private int limit;

    public Limit(int limit) {
        Validator.isLess(limit, "limit", 0).onThrow();

        this.limit = limit;
    }

    public int getLimit() {
        return limit;
    }

    @Override
    public String getSql() {
        return String.format(LIMIT_TEMPLATE, limit);
    }

    @Override
    public String toString() {
        return getSql();
    }
}
