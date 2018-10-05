package ru.strict.db.core.requests;

import java.util.*;


/**
 * Набор условий для добавления к sql-запросу
 */
public class DbRequests implements IDbRequest {

    private List<DbRequestBase> requests;
    /**
     * Наименование таблицы из основной конструкции select
     */
    private String selectTableName;
    /**
     * Добавлять AND между условиями
     */
    private boolean isAnd;

    //<editor-fold defaultState="collapsed" desc="constructors">
    public DbRequests(String selectTableName, boolean isAnd) {
        this.selectTableName = selectTableName;
        this.isAnd = isAnd;
        requests = new ArrayList<>();
    }
    //</editor-fold>

    //<editor-fold defaultState="collapsed" desc="Get/Set">
    public String getSelectTableName() {
        return selectTableName;
    }

    public boolean isAnd() {
        return isAnd;
    }
    //</editor-fold>

    @Override
    public String getSql(){
        String result = "";

        Collection<String> tableNames = new LinkedList<>();

        for(DbRequestBase request : requests){
            if(!tableNames.contains(request.getTableName()) && !request.getTableName().equals(selectTableName))
                tableNames.add(request.getTableName());
        }

        for(String tableName : tableNames)
            result+=", " + tableName;

        if(!requests.isEmpty())
            result+=" WHERE ";
        else
            return "";

        String symbol;
        if(isAnd)
            symbol = "AND";
        else
            symbol = "OR";

        result += requests.get(0).getSql() + " ";

        for(int i=1; i<requests.size(); i++)
            result += symbol + " " + requests.get(i).getSql() + " ";

        return result;
    }

    public void add(DbRequestBase request){
        if(request != null){
            requests.add(request);
        }else{
            throw new IllegalArgumentException("request is NULL");
        }
    }

    public List<DbRequestBase> getRequests() {
        return requests;
    }

    //<editor-fold defaultState="collapsed" desc="Base override">
    @Override
    public String toString(){
        return getSql();
    }

    @Override
    public boolean equals(Object obj){
        if(obj!=null && obj instanceof DbRequests) {
            DbRequests object = (DbRequests) obj;
            return Objects.equals(selectTableName, object.getSelectTableName())
                    && Objects.equals(isAnd, object.isAnd())
                    && Objects.equals(requests, object.getRequests());
        }else {
            return false;
        }
    }

    @Override
    public int hashCode(){
        return Objects.hash(selectTableName, isAnd, requests);
    }
    //</editor-fold>
}
