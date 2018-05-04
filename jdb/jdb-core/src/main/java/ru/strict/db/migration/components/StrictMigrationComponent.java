package ru.strict.db.migration.components;

/**
 * Базовое описание компонента-миграции базы данных
 */
public interface StrictMigrationComponent {
    /**
     * Сформировать sql-строку компонента
     * @return
     */
    String getSql();
}
