package ru.strict.db.core.requests;

import java.util.Collection;

public class DbWhereIn extends DbWhereItem {

    public DbWhereIn(DbTable table, String column, DbIn in) {
        super(new DbSelectItem(table, column), in, "IN");
        if(in == null){
            throw new IllegalArgumentException("in for where is NULL");
        }
    }

    public DbWhereIn(DbSelectItem whereItem, DbIn in) {
        super(whereItem, in, "IN");
        if(in == null){
            throw new IllegalArgumentException("in for where is NULL");
        }
    }
}
