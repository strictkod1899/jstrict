package ru.strict.db.requests;

import ru.strict.utils.StrictUtilHashCode;

/**
 * Условие Where для добавления к sql-запросу
 */
public class PostgreSQLDbWhere extends StrictDbWhere{

    /**
     * Игнорирование регистра при сравнении
     */
    private boolean ignoreCase;

    //<editor-fold defaultState="collapsed" desc="constructors">
    public PostgreSQLDbWhere(String tableName, String columnName, Object columnValue, String operator, boolean ignoreCase) {
        super(tableName, columnName, columnValue, operator);
        this.ignoreCase = ignoreCase;
    }

    public PostgreSQLDbWhere(String tableName, String columnName, Object columnValue, String operator
            , StrictTemplateSymbol templateSymbol, boolean ignoreCase) {
        super(tableName, columnName, columnValue, operator, templateSymbol);
        this.ignoreCase = ignoreCase;
    }
    //</editor-fold>

    //<editor-fold defaultState="collapsed" desc="Get/Set">
    public boolean isIgnoreCase() {
        return ignoreCase;
    }
    //</editor-fold>

    @Override
    public String getSql(){
        String result;

        if(getColumnValue() instanceof String)
            result = (ignoreCase?"lower(":"")
                    + getTableName() + "." + getColumnName() + (ignoreCase?")":"") + " "
                    + getOperator() + " " + (ignoreCase?"lower(":"") + "'"
                    + (getTemplateSymbol().getPointTemplateSymbol()== StrictPointTemplateSymbol.BEGIN
                    || getTemplateSymbol().getPointTemplateSymbol()== StrictPointTemplateSymbol.BOTH
                    ?getTemplateSymbol().getTemplateSymbol():"")
                    + getColumnValue()
                    + (getTemplateSymbol().getPointTemplateSymbol()== StrictPointTemplateSymbol.END
                    || getTemplateSymbol().getPointTemplateSymbol()== StrictPointTemplateSymbol.BOTH
                    ?getTemplateSymbol().getTemplateSymbol():"") + "'" + (ignoreCase?")":"");
        else
            result = getTableName() + "." + getColumnName() + " " + getOperator() + " " + getColumnValue();
        return result;
    }

    //<editor-fold defaultState="collapsed" desc="Base override">
    @Override
    public boolean equals(Object obj){
        if(obj!=null && obj instanceof PostgreSQLDbWhere) {
            PostgreSQLDbWhere object = (PostgreSQLDbWhere) obj;
            return super.equals(object) && ignoreCase == object.isIgnoreCase();
        }else
            return false;
    }

    @Override
    public int hashCode(){
        int superHashCode = super.hashCode();
        return StrictUtilHashCode.createSubHashCode(superHashCode, ignoreCase);
    }
    //</editor-fold>
}
