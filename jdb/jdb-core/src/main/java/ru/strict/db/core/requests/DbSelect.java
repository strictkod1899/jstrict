package ru.strict.db.core.requests;

import ru.strict.db.core.common.SqlParameters;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DbSelect extends DbRequestBase implements IDbParametrizedRequest {

    private List<DbSelectItem> selectItems;
    private DbRequests requests;

    public DbSelect(DbTable table) {
        super(table);
        selectItems = new ArrayList<>();
        requests = new DbRequests();
    }

    public DbSelect(DbTable table, DbSelectItem selectItem) {
        this(table);
        addSelectItem(selectItem);
    }

    public void addSelectItem(DbSelectItem item){
        if(item == null){
            throw new IllegalArgumentException("selectItem is NULL");
        }

        selectItems.add(item);
    }

    public void addSelectItem(DbTable table, String column){
        selectItems.add(new DbSelectItem(table, column));
    }

    public List<DbSelectItem> getSelectItems() {
        return selectItems;
    }

    public DbRequests getRequests() {
        return requests;
    }

    public void setRequests(DbRequests requests) {
        if(requests == null){
            throw new IllegalArgumentException("request is NULL");
        }
        this.requests = requests;
    }

    private String getSelectItemAsString(){
        if(selectItems.isEmpty()){
            return "*";
        } else {
            return selectItems.stream()
                    .map(i -> i.getSql())
                    .reduce((i1, i2) -> String.format("%s, %s", i1, i2))
                    .get();
        }
    }

    @Override
    public String getSql() {
        return String.format("SELECT %s FROM %s %s", getSelectItemAsString(), getTable().getSql(), requests.getSql()).trim();
    }

    @Override
    public String getParametrizedSql() {
        return String.format("SELECT %s FROM %s %s", getSelectItemAsString(), getTable().getSql(), requests.getParametrizedSql()).trim();
    }

    @Override
    public SqlParameters getParameters() {
        return requests.getParameters();
    }

    @Override
    public String toString(){
        return getSql();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        DbSelect dbSelect = (DbSelect) o;
        return Objects.equals(selectItems, dbSelect.selectItems) &&
                Objects.equals(requests, dbSelect.requests);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), selectItems, requests);
    }
}
