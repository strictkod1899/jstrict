package ru.strict.db.core.requests;

import java.util.*;

/**
 * Набор условий для добавления к sql-запросу
 */
public class DbRequests implements IDbRequest {

    private List<DbJoin> joinRequests;

    private DbWhere whereRequests;

    private DbLimit limitRequest;

    private DbOffset offsetRequest;

    private DbSort sortRequest;

    /**
     * Наименование таблицы из конструкции select [SELECT * FROM <tablename>]
     */
    private String selectTableName;

    //<editor-fold defaultState="collapsed" desc="constructors">
    public DbRequests(String selectTableName) {
        this.selectTableName = selectTableName;
        joinRequests = new ArrayList<>();
        whereRequests = new DbWhere(WhereType.AND);
    }

    public DbRequests(String selectTableName, WhereType whereType) {
        this.selectTableName = selectTableName;
        joinRequests = new ArrayList<>();
        whereRequests = new DbWhere(whereType);
    }
    //</editor-fold>

    //<editor-fold defaultState="collapsed" desc="Get/Set">
    public String getSelectTableName() {
        return selectTableName;
    }
    //</editor-fold>

    @Override
    public String getSql(){
        String result = "";

        result = fillJoin(result);
        result = fillWhere(result);
        result = fillOrderBy(result);
        result = fillLimitOffset(result);

        return result;
    }

    public void addJoin(DbJoin request){
        if(request != null){
            joinRequests.add(request);
        }else{
            throw new NullPointerException("join-request is NULL");
        }
    }

    public List<DbJoin> getJoinRequests() {
        return joinRequests;
    }

    public void addWhere(DbWhereBase request){
        if(request != null){
            whereRequests.add(request);
        }else{
            throw new NullPointerException("where-request is NULL");
        }
    }

    public DbWhere getWhere() {
        return whereRequests;
    }

    public DbLimit getLimit() {
        return limitRequest;
    }

    public void setLimit(DbLimit limitRequest) {
        this.limitRequest = limitRequest;
    }

    public DbOffset getOffset() {
        return offsetRequest;
    }

    public void setOffset(DbOffset offsetRequest) {
        this.offsetRequest = offsetRequest;
    }

    public DbSort getSort() {
        return sortRequest;
    }

    public void setSort(DbSort sortRequest) {
        this.sortRequest = sortRequest;
    }

    private String fillJoin(String result){
        for(DbJoin join : joinRequests){
            result += " " + join.getSql();
        }

        return result;
    }

    private String fillWhere(String result){
        result += " WHERE " + whereRequests.getSql();
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

    private String fillOrderBy(String result){
        if(sortRequest != null){
            result += " " + sortRequest.getSql();
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
                    && Objects.equals(whereRequests, object.getWhere());
        }else {
            return false;
        }
    }

    @Override
    public int hashCode(){
        return Objects.hash(selectTableName, whereRequests);
    }
    //</editor-fold>
}
