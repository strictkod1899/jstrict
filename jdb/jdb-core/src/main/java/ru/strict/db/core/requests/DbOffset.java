package ru.strict.db.core.requests;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DbOffset dbOffset = (DbOffset) o;
        return offset == dbOffset.offset;
    }

    @Override
    public int hashCode() {
        return Objects.hash(offset);
    }
}
