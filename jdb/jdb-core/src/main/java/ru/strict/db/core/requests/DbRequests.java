package ru.strict.db.core.requests;

import ru.strict.db.core.common.SqlParameter;
import ru.strict.db.core.common.SqlParameters;
import ru.strict.validate.ValidateBaseValue;

import java.util.*;

/**
 * Набор условий для добавления к sql-запросу
 */
public class DbRequests implements IDbRequest, IDbParametrizedRequest {

    private List<DbJoin> joinRequests;
    private DbWhere whereRequests;
    private DbGroup group;
    private DbSort sortRequest;
    private DbLimit limitRequest;
    private DbOffset offsetRequest;

    //<editor-fold defaultState="collapsed" desc="constructors">
    public DbRequests() {
        joinRequests = new ArrayList<>();
        whereRequests = new DbWhere(WhereType.AND);
    }

    public DbRequests(WhereType whereType) {
        joinRequests = new ArrayList<>();
        whereRequests = new DbWhere(whereType);
    }
    //</editor-fold>

    @Override
    public String getSql(){
        String result = "";

        result = fillJoin(result);
        result = fillWhere(result);
        result = fillGroup(result);
        result = fillOrderBy(result);
        result = fillLimitOffset(result);

        return result.trim();
    }

    @Override
    public String getParametrizedSql() {
        String result = "";

        result = fillJoin(result);
        result = fillParameterizedWhere(result);
        result = fillParameterizedGroup(result);
        result = fillOrderBy(result);
        result = fillLimitOffset(result);

        return result.trim();
    }

    @Override
    public SqlParameters getParameters() {
        SqlParameters parameters = new SqlParameters();
        parameters.addAll(whereRequests.getParameters());
        if(group != null) {
            SqlParameters<?> groupParameters = group.getParameters();
            int i = parameters.size();
            for(SqlParameter parameter : groupParameters.getParameters()){
                i++;
                parameter.setIndex(i);
            }
            parameters.addAll(groupParameters);
        }
        return parameters;
    }

    //<editor-fold defaultState="collapsed" desc="Fill methods">
    private String fillJoin(String result){
        for(DbJoin join : joinRequests){
            result += " " + join.getSql();
        }

        return result;
    }

    private String fillWhere(String result){
        String sql = whereRequests.getSql();
        if(!ValidateBaseValue.isEmptySpaceOrNull(sql)) {
            result += " WHERE " + sql;
        }
        return result;
    }

    private String fillParameterizedWhere(String result){
        String sql = whereRequests.getParametrizedSql();
        if(!ValidateBaseValue.isEmptySpaceOrNull(sql)) {
            result += " WHERE " + sql;
        }
        return result;
    }

    private String fillGroup(String result){
        if(group != null){
            result += " " + group.getSql();
        }

        return result;
    }

    private String fillParameterizedGroup(String result){
        if(group != null){
            result += " " + group.getParametrizedSql();
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

    private String fillOrderBy(String result){
        if(sortRequest != null){
            result += " " + sortRequest.getSql();
        }

        return result;
    }
    //</editor-fold>

    //<editor-fold defaultState="collapsed" desc="Get/Set">
    public void addJoin(DbJoin request){
        if(request != null){
            joinRequests.add(request);
        }else{
            throw new IllegalArgumentException("join-request is NULL");
        }
    }

    public List<DbJoin> getJoinRequests() {
        return joinRequests;
    }

    public void addWhere(DbWhereBase request){
        if(request != null){
            whereRequests.add(request);
        }else{
            throw new IllegalArgumentException("where-request is NULL");
        }
    }

    public DbWhere getWhere() {
        return whereRequests;
    }

    public DbGroup getGroup() {
        return group;
    }

    public void setGroup(DbGroup group) {
        this.group = group;
    }

    public DbSort getSort() {
        return sortRequest;
    }

    public void setSort(DbSort sortRequest) {
        this.sortRequest = sortRequest;
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
    //</editor-fold>

    //<editor-fold defaultState="collapsed" desc="Base override">
    @Override
    public String toString(){
        return getSql();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DbRequests requests = (DbRequests) o;
        return Objects.equals(joinRequests, requests.joinRequests) &&
                Objects.equals(whereRequests, requests.whereRequests) &&
                Objects.equals(group, requests.group) &&
                Objects.equals(limitRequest, requests.limitRequest) &&
                Objects.equals(offsetRequest, requests.offsetRequest) &&
                Objects.equals(sortRequest, requests.sortRequest);
    }

    @Override
    public int hashCode() {
        return Objects.hash(joinRequests, whereRequests, group, limitRequest, offsetRequest, sortRequest);
    }
    //</editor-fold>
}
