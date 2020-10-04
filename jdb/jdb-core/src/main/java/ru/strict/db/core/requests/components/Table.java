package ru.strict.db.core.requests.components;

import ru.strict.db.core.requests.IRequest;
import ru.strict.validate.CommonValidate;
import ru.strict.validate.Validator;

import java.util.Objects;

public class Table implements IRequest {
    private String tableName;
    private String alias;

    public Table(String tableName) {
        Validator.isEmptyOrNull(tableName, "tableName");

        this.tableName = tableName;
    }

    public Table(String tableName, String alias) {
        this(tableName);
        this.alias = alias;
    }

    public String getTableName() {
        return tableName;
    }

    public String getAlias() {
        return alias;
    }

    public String getRequiredName() {
        if (CommonValidate.isEmptyOrNull(alias)) {
            return tableName;
        } else {
            return alias;
        }
    }

    @Override
    public String getSql() {
        if (CommonValidate.isEmptyOrNull(alias)) {
            return tableName;
        } else {
            return String.format("%s AS %s", getTableName(), alias);
        }
    }

    @Override
    public String toString() {
        return getSql();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Table dbTable = (Table) o;
        return Objects.equals(tableName, dbTable.tableName) &&
                Objects.equals(alias, dbTable.alias);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tableName, alias);
    }
}
