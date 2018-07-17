package ru.strict.db.core.requests;

import ru.strict.utils.UtilHashCode;

import java.util.UUID;

/**
 * Условие Where sql-запроса
 * <p><b>Пример использования:</b></p>
 * <p>Значение столбца 'id' в таблице 'userx' равно '1'</p>
 * <code><pre style="background-color: white; font-family: consolas">
 *      new DbWhere("userx", "id", 1, "=");
 *      new DbWhere("userx", "name", "Aleks", "LIKE", new TemplateSymbol("%", PointTemplateSymbol.END));
 * </pre></code>
 * В результате получится следующее условие:
 * <code><pre style="background-color: white; font-family: consolas">
 *     WHERE userx.id = 1
 *     WHERE userx.name LIKE 'Aleks%'
 * </pre></code>
 */
public class DbWhere extends DbRequestBase {

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
    private TemplateSymbol templateSymbol;

    //<editor-fold defaultState="collapsed" desc="constructors">
    public DbWhere(String tableName, String columnName, Object columnValue, String operator) {
        super(tableName);
        this.columnName = columnName;
        this.columnValue = columnValue;
        this.operator = operator;
        templateSymbol = null;
    }

    public DbWhere(String tableName, String columnName, Object columnValue, String operator
            , TemplateSymbol templateSymbol) {
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

    public TemplateSymbol getTemplateSymbol() {
        return templateSymbol;
    }
    //</editor-fold>

    @Override
    public String getSql(){
        String result;

        if(columnValue instanceof String || columnValue instanceof UUID)
            result = getTableName() + "." + columnName + " "
                    + operator + " " + "'"
                    + (templateSymbol != null ? (templateSymbol.getPointTemplateSymbol()== PointTemplateSymbol.BEGIN
                    || templateSymbol.getPointTemplateSymbol()== PointTemplateSymbol.BOTH
                    ?templateSymbol.getTemplateSymbol():"") : "")
                    + columnValue
                    + (templateSymbol != null ? (templateSymbol.getPointTemplateSymbol()== PointTemplateSymbol.END
                    || templateSymbol.getPointTemplateSymbol()== PointTemplateSymbol.BOTH
                    ?templateSymbol.getTemplateSymbol():"") : "") + "'";
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
        if(obj!=null && obj instanceof DbWhere) {
            DbWhere object = (DbWhere) obj;
            return super.equals(object) && columnName.equals(object.getColumnName())
                    && columnValue.equals(object.getColumnValue()) && operator.equals(object.getOperator())
                    && templateSymbol.equals(object.getTemplateSymbol());
        }else
            return false;
    }

    @Override
    public int hashCode(){
        int superHashCode = super.hashCode();
        return UtilHashCode.createSubHashCode(superHashCode, columnName, columnValue, operator, templateSymbol);
    }
    //</editor-fold>
}
