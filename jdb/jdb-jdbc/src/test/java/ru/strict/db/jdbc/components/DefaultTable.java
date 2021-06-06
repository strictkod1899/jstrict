package ru.strict.db.jdbc.components;

import ru.strict.db.core.query.components.Table;

/**
 * Стандартное название таблицы
 */
public enum DefaultTable {
    CITY(new Table("city", "ci")),
    COUNTRY(new Table("country", "co"));

    private Table table;

    DefaultTable(Table table) {
        this.table = table;
    }

    public Table table() {
        return table;
    }
}
