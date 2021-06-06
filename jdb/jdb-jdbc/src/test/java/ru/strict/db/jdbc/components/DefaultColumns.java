package ru.strict.db.jdbc.components;

/**
 * Стандартные наборы столбцов различных таблиц
 */
public enum DefaultColumns {
    CITY(new String[]{ "caption", "country_id" }),
    COUNTRY(new String[]{ "caption" });

    private String[] columns;

    DefaultColumns(String[] columns) {
        this.columns = columns;
    }

    public String[] columns() {
        return columns;
    }

    public String get(int index) {
        return columns[index];
    }
}
