package ru.strict.db.requests;

import ru.strict.db.enums.StrictEnumTemplateSymbol;

/**
 * Условие Where для добавления к sql-запросу
 */
public class PostgreSQLDbWhere extends StrictDbWhere{

    /**
     * Игнорирование регистра при сравнении
     */
    private boolean ignoreCase;

    public PostgreSQLDbWhere(String tableName, String columnName, Object columnValue, String operator, boolean ignoreCase) {
        super(tableName, columnName, columnValue, operator);
        this.ignoreCase = ignoreCase;
    }

    public PostgreSQLDbWhere(String tableName, String columnName, Object columnValue, String operator
            , StrictTemplateSymbol templateSymbol, boolean ignoreCase) {
        super(tableName, columnName, columnValue, operator, templateSymbol);
        this.ignoreCase = ignoreCase;
    }

    public boolean isIgnoreCase() {
        return ignoreCase;
    }

    @Override
    public String getSql(){
        String result;

        if(getColumnValue() instanceof String)
            result = (ignoreCase?"lower(":"")
                    + getTableName() + "." + getColumnName() + (ignoreCase?")":"") + " "
                    + getOperator() + " " + (ignoreCase?"lower(":"") + "'"
                    + (getTemplateSymbol().getEnumTemplateSymbol()== StrictEnumTemplateSymbol.BEGIN
                    || getTemplateSymbol().getEnumTemplateSymbol()== StrictEnumTemplateSymbol.BETWEEN
                    ?getTemplateSymbol().getTemplateSymbol():"")
                    + getColumnValue()
                    + (getTemplateSymbol().getEnumTemplateSymbol()== StrictEnumTemplateSymbol.END
                    || getTemplateSymbol().getEnumTemplateSymbol()== StrictEnumTemplateSymbol.BETWEEN
                    ?getTemplateSymbol().getTemplateSymbol():"") + "'" + (ignoreCase?")":"");
        else
            result = getTableName() + "." + getColumnName() + " " + getOperator() + " " + getColumnValue();
        return result;
    }
}
