package ru.strict.db.core.requests;

import java.util.*;

/**
 * Набор условий для добавления к sql-запросу
 */
public class DbRequests implements IDbRequest {

    /**
     * Общие ограничения.
     * Используются как унирвесальные ограничения и добавляются в конец запроса, за исключением limit/offset.
     * Предпочтительно использовать специализированные ограничения
     */
    private List<IDbRequest> commonRequests;

    private List<DbWhere> whereRequests;

    private DbLimit limitRequest;

    private DbOffset offsetRequest;

    /**
     * Наименование таблицы из основной конструкции select, чтобы предотвратить повторного его доабвления в блок SELECT
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
        whereRequests = new ArrayList<>();
        commonRequests = new ArrayList<>();
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

        result = fillWhere(result);
        result = fillCommonRequests(result);
        result = fillLimitOffset(result);

        return result;
    }

    public void addWhere(DbWhere request){
        if(request != null){
            whereRequests.add(request);
        }else{
            throw new NullPointerException("where-request is NULL");
        }
    }

    public void addRequest(IDbRequest request){
        if(request != null){
            commonRequests.add(request);
        }else{
            throw new NullPointerException("common-request is NULL");
        }
    }

    public List<DbWhere> getWhereRequests() {
        return whereRequests;
    }

    public List<IDbRequest> getCommonRequests() {
        return commonRequests;
    }

    public DbLimit getLimitRequest() {
        return limitRequest;
    }

    public void setLimitRequest(DbLimit limitRequest) {
        this.limitRequest = limitRequest;
    }

    public DbOffset getOffsetRequest() {
        return offsetRequest;
    }

    public void setOffsetRequest(DbOffset offsetRequest) {
        this.offsetRequest = offsetRequest;
    }

    private String fillWhere(String result){
        if(whereRequests.isEmpty()) {
            return result;
        }

        Collection<String> tableNames = new LinkedList<>();

        // Добавляем наименования используемых таблиц в SELECT
        for(DbWhere request : whereRequests){
            if(!tableNames.contains(request.getTableName()) && !request.getTableName().equals(selectTableName)) {
                tableNames.add(request.getTableName());
            }
        }

        for(String tableName : tableNames) {
            result += ", " + tableName;
        }

        result += " WHERE ";

        String symbol;
        if(isAnd) {
            symbol = "AND";
        }else {
            symbol = "OR";
        }

        result += whereRequests.get(0).getSql() + " ";

        for(int i = 1; i< whereRequests.size(); i++) {
            result += symbol + " " + whereRequests.get(i).getSql() + " ";
        }

        return result;
    }

    private String fillCommonRequests(String result){
        for(IDbRequest request : commonRequests) {
            result += " " + request.getSql();
        }

        return result;
    }

    private String fillLimitOffset(String result){
        if(limitRequest != null){
            result += " " + limitRequest.getSql();
        }
        if(offsetRequest != null){
            result += " " + offsetRequest.getSql();
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
        if(obj!=null && obj instanceof DbRequests) {
            DbRequests object = (DbRequests) obj;
            return Objects.equals(selectTableName, object.getSelectTableName())
                    && Objects.equals(isAnd, object.isAnd())
                    && Objects.equals(whereRequests, object.getWhereRequests());
        }else {
            return false;
        }
    }

    @Override
    public int hashCode(){
        return Objects.hash(selectTableName, isAnd, whereRequests);
    }
    //</editor-fold>
}
