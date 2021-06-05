package ru.strict.db.core.query.components;

import ru.strict.db.core.query.IQuery;

import java.util.Objects;

public class Sort extends TableRequest implements IQuery {
    private static final String ORDER_BY_TEMPLATE = " ORDER BY %s.%s %s ";

    private String columnName;
    private SortType sortType;

    public Sort(Table table, String columnName, SortType sortType) {
        super(table);
        this.columnName = columnName;
        this.sortType = sortType;
    }

    public String getColumnName() {
        return columnName;
    }

    public SortType getSortType() {
        return sortType;
    }

    @Override
    public String getSql() {
        return String.format(ORDER_BY_TEMPLATE, getTable().getRequiredName(), columnName, sortType.getCaption());
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
        if (!super.equals(o)) {
            return false;
        }
        Sort sort = (Sort) o;
        return Objects.equals(columnName, sort.columnName) &&
                sortType == sort.sortType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), columnName, sortType);
    }
}