package ru.strict.db.core.requests;

import ru.strict.validate.ValidateBaseValue;

import java.util.Objects;

public class DbTable implements IDbRequest {

    private String tableName;
    private String alias;

    public DbTable(String tableName) {
        if(ValidateBaseValue.isEmptyOrNull(tableName)){
            throw new IllegalArgumentException("tableName is NULL");
        }
        this.tableName = tableName;
    }

    public DbTable(String tableName, String alias) {
        this(tableName);
        this.alias = alias;
    }

    public String getTableName() {
        return tableName;
    }

    public String getAlias() {
        return alias;
    }

    public String getRequiredName(){
        if(ValidateBaseValue.isEmptyOrNull(alias)){
            return tableName;
        }else{
            return alias;
        }
    }

    @Override
    public String getSql() {
        if(ValidateBaseValue.isEmptyOrNull(alias)){
            return tableName;
        } else {
            return String.format("%s as %s", getTableName(), alias);
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
        DbTable dbTable = (DbTable) o;
        return Objects.equals(tableName, dbTable.tableName) &&
                Objects.equals(alias, dbTable.alias);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tableName, alias);
    }
}
