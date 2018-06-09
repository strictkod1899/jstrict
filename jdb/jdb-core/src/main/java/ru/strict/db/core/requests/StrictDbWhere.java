package ru.strict.db.core.requests;

import ru.strict.utils.StrictUtilHashCode;

/**
 * Условие Where sql-запроса
 * <p><b>Пример использования:</b></p>
 * <p>Значение столбца 'id' в таблице 'userx' равно '1'</p>
 * <code><pre style="background-color: white; font-family: consolas">
 *      new StrictDbWhere("userx", "id", 1, "=");
 * </pre></code>
 */
public class StrictDbWhere extends StrictDbRequestBase {

    /**
     * Наименование столбца
     */
    private String columnName;
    /**
     * Значение столбца
     */
    private Object columnValue;
    /**
     * Оператор
     */
    private String operator;
    /**
     * Шаблонный символ сравнения строк
     */
    private StrictTemplateSymbol templateSymbol;

    //<editor-fold defaultState="collapsed" desc="constructors">
    public StrictDbWhere(String tableName, String columnName, Object columnValue, String operator) {
        super(tableName);
        this.columnName = columnName;
        this.columnValue = columnValue;
        this.operator = operator;
        templateSymbol = null;
    }

    public StrictDbWhere(String tableName, String columnName, Object columnValue, String operator
            , StrictTemplateSymbol templateSymbol) {
        super(tableName);
        this.columnName = columnName;
        this.columnValue = columnValue;
        this.operator = operator;
        this.templateSymbol = templateSymbol;
    }
    //</editor-fold>

    //<editor-fold defaultState="collapsed" desc="Get/Set">
    public String getColumnName() {
        return columnName;
    }

    public Object getColumnValue() {
        return columnValue;
    }

    public String getOperator() {
        return operator;
    }

    public StrictTemplateSymbol getTemplateSymbol() {
        return templateSymbol;
    }
    //</editor-fold>

    @Override
    public String getSql(){
        String result;

        if(columnValue instanceof String)
            result = getTableName() + "." + columnName + " "
                    + operator + " " + "'"
                    + (templateSymbol.getPointTemplateSymbol()== StrictPointTemplateSymbol.BEGIN
                    || templateSymbol.getPointTemplateSymbol()== StrictPointTemplateSymbol.BOTH
                    ?templateSymbol.getTemplateSymbol():"")
                    + columnValue
                    + (templateSymbol.getPointTemplateSymbol()== StrictPointTemplateSymbol.END
                    || templateSymbol.getPointTemplateSymbol()== StrictPointTemplateSymbol.BOTH
                    ?templateSymbol.getTemplateSymbol():"") + "'";
        else
            result = getTableName() + "." + columnName + " " + operator + " " + columnValue;
        return result;
    }

    //<editor-fold defaultState="collapsed" desc="Base override">
    @Override
    public String toString(){
        return getSql();
    }

    @Override
    public boolean equals(Object obj){
        if(obj!=null && obj instanceof StrictDbWhere) {
            StrictDbWhere object = (StrictDbWhere) obj;
            return super.equals(object) && columnName.equals(object.getColumnName())
                    && columnValue.equals(object.getColumnValue()) && operator.equals(object.getOperator())
                    && templateSymbol.equals(object.getTemplateSymbol());
        }else
            return false;
    }

    @Override
    public int hashCode(){
        int superHashCode = super.hashCode();
        return StrictUtilHashCode.createSubHashCode(superHashCode, columnName, columnValue, operator, templateSymbol);
    }
    //</editor-fold>
}
