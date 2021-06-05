package ru.strict.db.core.query;

/**
 * Базовое определения sql-запроса
 */
public interface IQuery {
    /**
     * Сформировать sql-конструкцию
     */
    String getSql();
}
