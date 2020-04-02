package ru.strict.db.core.requests;

/**
 * Базовое определения sql-запроса
 */
public interface IDbRequest {
    /**
     * Сформировать sql-конструкцию
     * @return
     */
    String getSql();
}
