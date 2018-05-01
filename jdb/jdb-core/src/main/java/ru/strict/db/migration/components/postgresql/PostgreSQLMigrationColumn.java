package ru.strict.db.migration.components.postgresql;

import ru.strict.db.migration.components.StrictMigrationColumn;

public class PostgreSQLMigrationColumn extends StrictMigrationColumn {

    public PostgreSQLMigrationColumn(String name, String type, boolean isNotNull) {
        super(name, type, isNotNull);
    }

    public PostgreSQLMigrationColumn(String name, String type, boolean isNotNull, String defaultValue) {
        super(name, type, isNotNull, defaultValue);
    }
}
