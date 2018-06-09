package ru.strict.db.core.requests;

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
