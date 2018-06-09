package ru.strict.db.core.migration;

/**
 * Описание базовой функциональности миграции
 */
public interface StrictMigrationAny {
    /**
     * Выполнить миграцию базы данных
     */
    void migration();
}
