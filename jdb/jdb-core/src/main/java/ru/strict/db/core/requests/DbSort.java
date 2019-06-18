package ru.strict.db.core.requests;

import java.util.Objects;

/**
 * Элемент ORDER BY sql-запроса
 */
public class DbSort extends DbRequestBase {

    /**
     * Наименование столбца
     */
    private String columnName;
    /**
     * Тип сортировки
     */
    private SortType sortType;

    public DbSort(DbTable table, String columnName, SortType sortType) {
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
        return String.format("ORDER BY %s.%s %s", getTable().getRequiredName(), columnName, sortType.getCaption());
    }

    @Override
    public String toString() {
        return getSql();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        DbSort dbSort = (DbSort) o;
        return Objects.equals(columnName, dbSort.columnName) &&
                sortType == dbSort.sortType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), columnName, sortType);
    }
}
