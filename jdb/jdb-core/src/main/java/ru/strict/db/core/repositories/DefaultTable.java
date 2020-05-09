package ru.strict.db.core.repositories;

import ru.strict.db.core.requests.components.Table;

/**
 * Стандартное название таблицы
 */
public enum DefaultTable {
    CITY(new Table("city", "ci")),
    COUNTRY(new Table("country", "co")),
    FILE_STORAGE(new Table("file_storage", "fs")),
    JWT_TOKEN(new Table("token", "t")),
    PERMISSION_ON_ROLE(new Table("permission_on_role", "pr")),
    PROFILE(new Table("profile", "p")),
    ROLE(new Table("role", "r")),
    USER(new Table("userx", "u")),
    USER_ON_ROLE(new Table("user_on_role", "ur"));

    private Table table;

    DefaultTable(Table table) {
        this.table = table;
    }

    public Table table() {
        return table;
    }
}
