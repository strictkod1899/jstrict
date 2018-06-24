package ru.strict.db.core.common;

/**
 * Тип генерируемого значения для id записи в базе данных.
 * Используется в классах производных от RepositoryBase
 */
public enum GenerateIdType {
    /**
     * Генерация числового значения
     */
    NUMBER,
    /**
     * Генерация значений UUID
     */
    UUID,
    /**
     * Не генерировать значеник
     */
    NONE
}
