package ru.strict.db.core.requests;

import java.util.Objects;

/**
 * Базовое определние условия sql-запроса
 */
public abstract class DbRequestBase implements IDbRequest {

    /**
     * Наименование таблицы
     */
    private DbTable table;

    public DbRequestBase(DbTable table) {
        if(table == null){
            throw new IllegalArgumentException("table is NULL");
        }
        this.table = table;
    }

    public DbTable getTable() {
        return table;
    }
    //<editor-fold defaultState="collapsed" desc="Base override">
    @Override
    public String toString(){
        return getSql();
    }

    @Override
    public boolean equals(Object obj){
        if(obj!=null && obj instanceof DbRequestBase) {
            DbRequestBase object = (DbRequestBase) obj;
            return Objects.equals(table, object.table);
        }else {
            return false;
        }
    }

    @Override
    public int hashCode(){
        return Objects.hash(table);
    }
    //</editor-fold>
}
