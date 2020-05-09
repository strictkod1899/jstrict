package ru.strict.db.core.requests;

/**
 * Базовое определения sql-запроса
 */
public interface IRequest {
    /**
     * Сформировать sql-конструкцию
     */
    String getSql();
}
