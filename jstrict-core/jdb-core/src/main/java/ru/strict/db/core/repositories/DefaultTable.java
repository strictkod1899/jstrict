package ru.strict.db.core.repositories;

import ru.strict.db.core.requests.DbTable;

/**
 * Стандартное название таблицы
 */
public enum DefaultTable {
    CITY(new DbTable("city", "ci")),
    COUNTRY(new DbTable("country", "co")),
    FILE_STORAGE(new DbTable("file_storage", "fs")),
    JWT_TOKEN(new DbTable("token", "t")),
    PERMISSION_ON_ROLE(new DbTable("permission_on_role", "pr")),
    PROFILE(new DbTable("profile", "p")),
    ROLE(new DbTable("role", "r")),
    USER(new DbTable("userx", "u")),
    USER_ON_ROLE(new DbTable("user_on_role", "ur"));

    private DbTable table;

    DefaultTable(DbTable table) {
        this.table = table;
    }

    public DbTable table(){
        return table;
    }
}
