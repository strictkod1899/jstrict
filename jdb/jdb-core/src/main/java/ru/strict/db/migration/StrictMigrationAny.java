package ru.strict.db.migration;

/**
 * Описание базовой функциональности миграции
 */
public interface StrictMigrationAny {
    /**
     * Выполнить миграцию базы данных
     */
    void migration();
}
