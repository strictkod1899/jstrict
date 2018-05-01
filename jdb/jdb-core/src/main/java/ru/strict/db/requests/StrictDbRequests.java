package ru.strict.db.requests;

import java.util.*;

/**
 * Набор условий для добавления к запросу
 */
public class StrictDbRequests extends LinkedList<StrictDbRequestBase> {

    /**
     * Добавлять AND между условиями
     */
    private boolean isAnd;

    /**
     * Наименование таблицы из основной конструкции select
     */
    private String selectTableName;

    public StrictDbRequests(String selectTableName, boolean isAnd) {
        this.selectTableName = selectTableName;
        this.isAnd = isAnd;
    }

    public String getSql(){
        String result = "";

        Collection<String> tableNames = new LinkedList<>();

        for(StrictDbRequestBase request : this){
            if(!tableNames.contains(request.getTableName()) && !request.getTableName().equals(selectTableName))
                tableNames.add(request.getTableName());
        }

        for(String tableName : tableNames)
            result+=", " + tableName;

        if(!isEmpty())
            result+=" WHERE ";
        else
            return "";

        String symbol;
        if(isAnd)
            symbol = "AND";
        else
            symbol = "OR";

        result += get(0).getSql() + " ";

        for(int i=1; i<size(); i++)
            result += symbol + " " + get(i).getSql() + " ";

        return result;
    }

    public String getSelectTableName() {
        return selectTableName;
    }

    public boolean isAnd() {
        return isAnd;
    }

    @Override
    public String toString(){
        return getSql();
    }
}
