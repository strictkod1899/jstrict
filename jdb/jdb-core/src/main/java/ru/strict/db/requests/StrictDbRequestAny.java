package ru.strict.db.requests;

/**
 * Базовое определения условия sql-запроса
 */
public interface StrictDbRequestAny {
    /**
     * Сформировать sql-конструкцию условия
     * @return
     */
    String getSql();
}
