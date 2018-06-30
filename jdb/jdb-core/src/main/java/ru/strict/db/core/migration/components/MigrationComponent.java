package ru.strict.db.core.migration.components;

/**
 * Базовое описание компонента-миграции базы данных
 */
public interface MigrationComponent {
    /**
     * Сформировать sql-строку компонента
     * @return
     */
    String getSql();
}
