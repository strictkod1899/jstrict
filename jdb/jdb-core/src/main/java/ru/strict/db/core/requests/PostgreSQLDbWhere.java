package ru.strict.db.core.requests;

import ru.strict.utils.UtilHashCode;

/**
 * Условие Where для добавления к sql-запросу при использовании базы данных PostgreSql
 */
public class PostgreSQLDbWhere extends DbWhere {

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
            , TemplateSymbol templateSymbol, boolean ignoreCase) {
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
                    + (getTemplateSymbol().getPointTemplateSymbol()== PointTemplateSymbol.BEGIN
                    || getTemplateSymbol().getPointTemplateSymbol()== PointTemplateSymbol.BOTH
                    ?getTemplateSymbol().getTemplateSymbol():"")
                    + getColumnValue()
                    + (getTemplateSymbol().getPointTemplateSymbol()== PointTemplateSymbol.END
                    || getTemplateSymbol().getPointTemplateSymbol()== PointTemplateSymbol.BOTH
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
        return UtilHashCode.createSubHashCode(superHashCode, ignoreCase);
    }
    //</editor-fold>
}
