package ru.strict.db.requests;

import ru.strict.db.enums.StrictEnumTemplateSymbol;

/**
 * Условие Where для добавления к sql-запросу
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

    public StrictDbWhere(String tableName, String columnName, Object columnValue, String operator) {
        super(tableName);
        this.columnName = columnName;
        this.columnValue = columnValue;
        this.operator = operator;
    }

    public StrictDbWhere(String tableName, String columnName, Object columnValue, String operator, StrictTemplateSymbol templateSymbol) {
        super(tableName);
        this.columnName = columnName;
        this.columnValue = columnValue;
        this.operator = operator;
        this.templateSymbol = templateSymbol;
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

    public StrictTemplateSymbol getTemplateSymbol() {
        return templateSymbol;
    }

    @Override
    public String getSql(){
        String result;

        if(columnValue instanceof String)
            result = getTableName() + "." + columnName + " "
                    + operator + " " + "'"
                    + (templateSymbol.getEnumTemplateSymbol()== StrictEnumTemplateSymbol.BEGIN
                    || templateSymbol.getEnumTemplateSymbol()== StrictEnumTemplateSymbol.BETWEEN
                    ?templateSymbol.getTemplateSymbol():"")
                    + columnValue
                    + (templateSymbol.getEnumTemplateSymbol()== StrictEnumTemplateSymbol.END
                    || templateSymbol.getEnumTemplateSymbol()== StrictEnumTemplateSymbol.BETWEEN
                    ?templateSymbol.getTemplateSymbol():"") + "'";
        else
            result = getTableName() + "." + columnName + " " + operator + " " + columnValue;
        return result;
    }
}
