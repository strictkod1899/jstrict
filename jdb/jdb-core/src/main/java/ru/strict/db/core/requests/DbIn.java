package ru.strict.db.core.requests;

import ru.strict.db.core.common.SqlParameters;

import java.util.Collection;
import java.util.Objects;
import java.util.UUID;

public class DbIn implements IDbRequest, IDbParametrizedRequest {

    private DbSelect innerSelect;
    private Collection<Object> items;

    public DbIn(Collection<Object> items) {
        if(items == null || items.isEmpty()){
            throw new IllegalArgumentException("items is NULL or < 0");
        }
        this.items = items;
    }

    public DbIn(DbSelect innerSelect) {
        if(innerSelect == null){
            throw new IllegalArgumentException("innerSelect is NULL");
        }
        this.innerSelect = innerSelect;
    }

    public DbSelect getInnerSelect() {
        return innerSelect;
    }

    public Collection<Object> getItems() {
        return items;
    }

    @Override
    public String getSql() {
        if (items != null) {
            String itemsString = items.stream()
                    .map(o -> {
                        if (o instanceof String || o instanceof UUID) {
                            return o == null ? null : String.format("'%s'", o);
                        } else {
                            return o == null ? null : o.toString();
                        }
                    })
                    .filter(i -> i != null)
                    .reduce((i1, i2) -> String.format("%s, %s", i1, i2))
                    .orElseThrow(NullPointerException::new);
            return String.format("(%s)", itemsString);
        } else {
            return String.format("(%s)", innerSelect.getSql());
        }
    }

    @Override
    public String getParametrizedSql() {
        if (items != null) {
            String itemsString = items.stream()
                    .map(o -> o == null ? null : "?")
                    .filter(i -> i != null)
                    .reduce((i1, i2) -> String.format("%s, %s", i1, i2))
                    .orElseThrow(NullPointerException::new);
            return String.format("(%s)", itemsString);
        } else {
            return String.format("(%s)", innerSelect.getParametrizedSql());
        }
    }

    @Override
    public SqlParameters getParameters() {
        if (items != null) {
            SqlParameters sqlParameters = new SqlParameters();
            int index = 0;
            for (Object i : items) {
                if (i != null) {
                    sqlParameters.add(index, "in" + index, i);
                    index++;
                }
            }
            return sqlParameters;
        } else {
            return innerSelect.getParameters();
        }
    }

    @Override
    public String toString() {
        return getSql();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DbIn dbIn = (DbIn) o;
        return Objects.equals(items, dbIn.items);
    }

    @Override
    public int hashCode() {
        return Objects.hash(items);
    }
}
