package ru.strict.db.migration;

/**
 * Описание базовой функциональности миграции
 */
public interface IMigration {
    /**
     * Выполнить миграцию базы данных
     */
    void migration();
}
