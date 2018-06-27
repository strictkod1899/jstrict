package ru.strict.db.core.requests;

/**
 * Базовое определения условия sql-запроса
 */
public interface IDbRequest {
    /**
     * Сформировать sql-конструкцию условия
     * @return
     */
    String getSql();
}
