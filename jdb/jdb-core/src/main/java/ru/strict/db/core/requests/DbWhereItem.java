package ru.strict.db.core.requests;

import ru.strict.db.core.common.SqlParameters;
import ru.strict.patterns.composite.CompositeLeaf;
import ru.strict.validates.ValidateBaseValue;

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
 * Если, необходмо использовать IS NULL, тогда columnValue передается null и operator указывается 'IS NULL'.
 * При передаче columnValue равным null, это значение будет игнорироваться
 */
public class DbWhereItem extends DbWhereBase {

    private DbSelectItem whereItem;
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
    public DbWhereItem(DbTable table, String column, Object columnValue, String operator) {
        this.whereItem = new DbSelectItem(table, column);
        if(ValidateBaseValue.isEmptyOrNull(operator)){
            throw new IllegalArgumentException("operator is NULL");
        }
        this.columnValue = columnValue;
        this.operator = operator;
        templateSymbol = null;
    }

    public DbWhereItem(DbTable table, String column, Object columnValue, String operator, TemplateSymbol templateSymbol) {
        this.whereItem = new DbSelectItem(table, column);
        if(ValidateBaseValue.isEmptyOrNull(operator)){
            throw new IllegalArgumentException("operator is NULL");
        }
        this.columnValue = columnValue;
        this.operator = operator;
        this.templateSymbol = templateSymbol;
    }

    public DbWhereItem(DbSelectItem whereItem, Object columnValue, String operator) {
        if(whereItem == null){
            throw new IllegalArgumentException("whereItem is NULL");
        }
        if(ValidateBaseValue.isEmptyOrNull(operator)){
            throw new IllegalArgumentException("operator is NULL");
        }
        this.whereItem = whereItem;
        this.columnValue = columnValue;
        this.operator = operator;
        templateSymbol = null;
    }

    public DbWhereItem(DbSelectItem whereItem, Object columnValue, String operator, TemplateSymbol templateSymbol) {
        if(whereItem == null){
            throw new IllegalArgumentException("whereItem is NULL");
        }
        if(ValidateBaseValue.isEmptyOrNull(operator)){
            throw new IllegalArgumentException("operator is NULL");
        }
        this.whereItem = whereItem;
        this.columnValue = columnValue;
        this.operator = operator;
        this.templateSymbol = templateSymbol;
    }
    //</editor-fold>

    //<editor-fold defaultState="collapsed" desc="Get/Set">
    public DbTable getTable() {
        return whereItem.getTable();
    }

    public String getColumnName() {
        return whereItem.getColumnName();
    }

    public Object getColumnValue() {
        return columnValue;
    }

    public String getFormattedColumnValue() {
        return (templateSymbol != null
                    ? (templateSymbol.getPointTemplateSymbol()== PointTemplateSymbol.BEGIN
                            || templateSymbol.getPointTemplateSymbol()== PointTemplateSymbol.BOTH
                            ? templateSymbol.getTemplateSymbol():"")
                    : "")
                +
                columnValue
                +
                (templateSymbol != null
                    ? (templateSymbol.getPointTemplateSymbol()== PointTemplateSymbol.END
                            || templateSymbol.getPointTemplateSymbol()== PointTemplateSymbol.BOTH
                            ? templateSymbol.getTemplateSymbol():"")
                    : "");
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
        String result = null;

        if(columnValue instanceof String || columnValue instanceof UUID ){
            result = String.format("%s %s", whereItem.getSql(), operator) +
                    (columnValue == null ? "" : String.format(" '%s'", getFormattedColumnValue()));
        } else {
            result = String.format("%s %s", whereItem.getSql(), operator) + (columnValue == null ? "" : String.format(" %s", columnValue));
        }
        return result.trim();
    }

    @Override
    public String getParametrizedSql() {
        return String.format("%s %s", whereItem.getSql(), operator) + (columnValue == null ? "" : " ?");
    }

    @Override
    public SqlParameters getParameters() {
        SqlParameters sqlParameters = new SqlParameters();
        if(columnValue != null) {
            sqlParameters.add(0, "where", columnValue);
        }
        return sqlParameters;
    }

    //<editor-fold defaultState="collapsed" desc="Base override">
    @Override
    public String toString(){
        return getSql();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DbWhereItem that = (DbWhereItem) o;
        return Objects.equals(whereItem, that.whereItem) &&
                Objects.equals(columnValue, that.columnValue) &&
                Objects.equals(operator, that.operator) &&
                Objects.equals(templateSymbol, that.templateSymbol);
    }

    @Override
    public int hashCode() {
        return Objects.hash(whereItem, columnValue, operator, templateSymbol);
    }
    //</editor-fold>
}
