package ru.strict.db.core.migration.components.postgresql;

import ru.strict.db.core.migration.components.MigrationColumn;

/**
 * Столбец таблицы для миграции в базу данных PostgreSQL
 */
public class PostgreSQLMigrationColumn extends MigrationColumn {

    public PostgreSQLMigrationColumn(String name, String type, boolean isNotNull) {
        super(name, type, isNotNull);
    }

    public PostgreSQLMigrationColumn(String name, String type, boolean isNotNull, String defaultValue) {
        super(name, type, isNotNull, defaultValue);
    }
}
