package ru.strict.db.core.requests;


import java.util.Objects;

/**
 * Базовое определние условия sql-запроса
 */
public abstract class DbRequestBase implements IDbRequest {

    /**
     * Наименование таблицы
     */
    private String tableName;

    public DbRequestBase(String tableName) {
        this.tableName = tableName;
    }

    public String getTableName() {
        return tableName;
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
            return Objects.equals(tableName, object.getTableName());
        }else {
            return false;
        }
    }

    @Override
    public int hashCode(){
        return Objects.hash(tableName);
    }
    //</editor-fold>
}
