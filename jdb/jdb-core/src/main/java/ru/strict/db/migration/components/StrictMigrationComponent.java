package ru.strict.db.migration.components;

public interface StrictMigrationComponent {
    /**
     * Сформировать sql-строку компонента
     * @return
     */
    String getSql();
}
