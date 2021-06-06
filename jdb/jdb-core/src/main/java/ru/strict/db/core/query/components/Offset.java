package ru.strict.db.core.query.components;

import ru.strict.db.core.query.IQuery;

public class Offset implements IQuery {
    private static final String OFFSET_TEMPLATE = " OFFSET %s ";

    private int offset;

    public Offset(int offset) {
        this.offset = offset;
    }

    public int getOffset() {
        return offset;
    }

    @Override
    public String getSql() {
        return String.format(OFFSET_TEMPLATE, offset);
    }

    @Override
    public String toString() {
        return getSql();
    }
}
