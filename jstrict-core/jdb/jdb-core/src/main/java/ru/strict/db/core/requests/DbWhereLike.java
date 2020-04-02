package ru.strict.db.core.requests;

public class DbWhereLike extends DbWhereItem {

    public DbWhereLike(DbTable table, String column, String columnValue) {
        super(new DbSelectItem(table, column), columnValue, "LIKE");
    }

    public DbWhereLike(DbSelectItem whereItem, String columnValue) {
        super(whereItem, columnValue, "LIKE");
    }
}
