package ru.strict.db.core.requests;

import ru.strict.utils.UtilHashCode;

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
            return super.equals(object) && tableName.equals(object.getTableName());
        }else
            return false;
    }

    @Override
    public int hashCode(){
        return UtilHashCode.createHashCode(tableName);
    }
    //</editor-fold>
}
