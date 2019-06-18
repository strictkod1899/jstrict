package ru.strict.db.core.requests;

public class DbWhereIsNull extends DbWhereItem {

    public DbWhereIsNull(DbTable table, String column) {
        super(new DbSelectItem(table, column), null, "IS NULL");
    }

    public DbWhereIsNull(DbSelectItem whereItem) {
        super(whereItem, null, "IS NULL");
    }
}
