package ru.strict.db.core.requests;

import ru.strict.db.core.common.SqlParameters;

import java.util.Objects;

public class DbGroup implements IDbRequest, IDbParametrizedRequest{

    private DbSelectItem item;
    private DbWhere having;

    public DbGroup(DbTable table, String columnName) {
        this.item = new DbSelectItem(table, columnName);
    }

    public DbGroup(DbTable table, String columnName, DbWhereItem havingItem) {
        this(table, columnName);
        DbWhere having = new DbWhere(WhereType.AND);
        having.setParameterPrefix("having");
        having.add(havingItem);
        this.having = having;
    }

    public DbGroup(DbTable table, String columnName, DbWhere having) {
        this(table, columnName);
        having.setParameterPrefix("having");
        this.having = having;
    }

    @Override
    public String getSql() {
        String result = String.format("GROUP BY %s", item.getSql());
        if(having != null){
            result += String.format(" HAVING %s", having.getSql());
        }
        return result;
    }

    @Override
    public String getParametrizedSql() {
        String result = String.format("GROUP BY %s", item.getSql());
        if(having != null){
            result += String.format(" HAVING %s", having.getParametrizedSql());
        }
        return result;
    }

    @Override
    public SqlParameters getParameters() {
        if(having != null) {
            return having.getParameters();
        }else{
            return new SqlParameters();
        }
    }

    @Override
    public String toString(){
        return getSql();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DbGroup dbGroup = (DbGroup) o;
        return Objects.equals(item, dbGroup.item) &&
                Objects.equals(having, dbGroup.having);
    }

    @Override
    public int hashCode() {
        return Objects.hash(item, having);
    }
}
