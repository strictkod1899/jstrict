package ru.strict.db.requests;

/**
 * Базовое определние условия sql-запроса
 */
public abstract class StrictDbRequestBase implements StrictDbRequestAny{

    /**
     * Наименование таблицы
     */
    private String tableName;

    public StrictDbRequestBase(String tableName) {
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
        if(obj instanceof StrictDbRequestBase) {
            StrictDbRequestBase object = (StrictDbRequestBase) obj;
            return super.equals(object) && tableName.equals(object.getTableName());
        }else
            return false;
    }
    //</editor-fold>
}
