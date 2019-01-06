package ru.strict.db.core.requests;

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

    public DbSort(String tableName, String columnName, SortType sortType) {
        super(tableName);
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
        return String.format("ORDER BY %s.%s %s", getTableName(), columnName, sortType.getCaption());
    }

    @Override
    public String toString() {
        return getSql();
    }
}
