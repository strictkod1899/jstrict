package ru.strict.db.core.requests;

/**
 * Элемент LIMIT sql-запроса
 */
public class DbOffset implements IDbRequest {

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
    public String toString() {
        return getSql();
    }
}
