package ru.strict.db.requests;

import ru.strict.db.enums.StrictEnumTemplateSymbol;

/**
 * Условие WHERE
 * @param <V> Тип сравниваемого значения
 */
public class StrictDbRequest<V> {

    /**
     * Наименование столбца
     */
    private String columnName;
    /**
     * Значение столбца
     */
    private V columnValue;
    /**
     * Оператор
     */
    private String operator;
    /**
     * Шаблонный символ сравнения строк
     */
    private StrictTemplateSymbol templateSymbol;

    /**
     * Игнорирование регистра при сравнении
     */
    private boolean ignoreCase;

    public StrictDbRequest(String columnName, V columnValue, String operator, StrictTemplateSymbol templateSymbol, boolean ignoreCase) {
        this.columnName = columnName;
        this.columnValue = columnValue;
        this.operator = operator;
        this.templateSymbol = templateSymbol;
        this.ignoreCase = ignoreCase;
    }

    public String getColumnName() {
        return columnName;
    }

    public V getColumnValue() {
        return columnValue;
    }

    public String getOperator() {
        return operator;
    }

    public StrictTemplateSymbol getTemplateSymbol() {
        return templateSymbol;
    }

    public boolean isIgnoreCase() {
        return ignoreCase;
    }

    @Override
    public String toString(){
        String result;

        if(columnValue instanceof String)
            result = (ignoreCase?"lower(":"")
                    + columnName + (ignoreCase?")":"") + " "
                    + operator + " " + (ignoreCase?"lower(":"") + "'"
                    + (templateSymbol.getEnumTemplateSymbol()== StrictEnumTemplateSymbol.BEGIN
                    || templateSymbol.getEnumTemplateSymbol()== StrictEnumTemplateSymbol.BETWEEN
                    ?templateSymbol.getTemplateSymbol():"")
                    + columnValue
                    + (templateSymbol.getEnumTemplateSymbol()== StrictEnumTemplateSymbol.END
                    || templateSymbol.getEnumTemplateSymbol()== StrictEnumTemplateSymbol.BETWEEN
                    ?templateSymbol.getTemplateSymbol():"") + "'" + (ignoreCase?")":"");
        else
            result = columnName + " " + operator + " " + columnValue;
        return result;
    }
}
