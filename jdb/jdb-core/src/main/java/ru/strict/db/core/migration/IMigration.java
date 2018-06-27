package ru.strict.db.core.migration;

/**
 * Описание базовой функциональности миграции
 */
public interface IMigration {
    /**
     * Выполнить миграцию базы данных
     */
    void migration();
}
