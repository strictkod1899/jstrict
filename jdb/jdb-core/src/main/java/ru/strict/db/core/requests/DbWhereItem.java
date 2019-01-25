package ru.strict.db.core.requests;

import ru.strict.patterns.composite.CompositeLeaf;

import java.util.Objects;
import java.util.UUID;

/**
 * Условие Where sql-запроса
 * <p><b>Пример использования:</b></p>
 * <p>Значение столбца 'id' в таблице 'userx' равно '1'</p>
 * <code><pre style="background-color: white; font-family: consolas">
 *      new DbWhereItem("userx", "id", 1, "=");
 *      new DbWhereItem("userx", "name", "Aleks", "LIKE", new TemplateSymbol("%", PointTemplateSymbol.END));
 * </pre></code>
 * В результате получится следующее условие:
 * <code><pre style="background-color: white; font-family: consolas">
 *     WHERE userx.id = 1
 *     WHERE userx.name LIKE 'Aleks%'
 * </pre></code>
 */
public class DbWhereItem extends DbWhereBase {
    /**
     * Наименование таблицы
     */
    private String tableName;
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
    public DbWhereItem(String tableName, String columnName, Object columnValue, String operator) {
        this.tableName = tableName;
        this.columnName = columnName;
        this.columnValue = columnValue;
        this.operator = operator;
        templateSymbol = null;
    }

    public DbWhereItem(String tableName, String columnName, Object columnValue, String operator, TemplateSymbol templateSymbol) {
        this.tableName = tableName;
        this.columnName = columnName;
        this.columnValue = columnValue;
        this.operator = operator;
        this.templateSymbol = templateSymbol;
    }
    //</editor-fold>

    //<editor-fold defaultState="collapsed" desc="Get/Set">
    public String getTableName() {
        return tableName;
    }

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

        if(columnValue instanceof String || columnValue instanceof UUID ){
            result = getTableName() + "." + columnName + " "
                    + operator + " " + "'"
                    + (templateSymbol != null ? (templateSymbol.getPointTemplateSymbol()== PointTemplateSymbol.BEGIN
                    || templateSymbol.getPointTemplateSymbol()== PointTemplateSymbol.BOTH
                    ?templateSymbol.getTemplateSymbol():"") : "")
                    + columnValue
                    + (templateSymbol != null ? (templateSymbol.getPointTemplateSymbol()== PointTemplateSymbol.END
                    || templateSymbol.getPointTemplateSymbol()== PointTemplateSymbol.BOTH
                    ?templateSymbol.getTemplateSymbol():"") : "") + "'";
        } else {
            result = getTableName() + "." + columnName + " " + operator + " "
                    + (columnValue == null ? "" : columnValue);
        }
        return result;
    }

    //<editor-fold defaultState="collapsed" desc="Base override">
    @Override
    public String toString(){
        return getSql();
    }

    @Override
    public boolean equals(Object obj){
        if(obj!=null && obj instanceof DbWhereItem) {
            DbWhereItem object = (DbWhereItem) obj;
            return super.equals(obj) && Objects.equals(columnName, object.getColumnName())
                    && Objects.equals(columnValue, object.getColumnValue())
                    && Objects.equals(operator, object.getOperator())
                    && Objects.equals(templateSymbol, object.getTemplateSymbol());
        } else {
            return false;
        }
    }

    @Override
    public int hashCode(){
        return Objects.hash(getTableName(), columnName, columnValue, operator, templateSymbol);
    }
    //</editor-fold>
}
