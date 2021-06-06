package ru.strict.db.core.query;

import ru.strict.db.core.common.SqlParameters;

/**
 * Базовое определения параметризованного sql-запроса
 */
public interface IParameterizedQuery {
    /**
     * Сформировать sql-конструкцию со вставками параметров (параметр обозначется знаком вопроса '?')
     */
    String getParameterizedSql();

    /**
     * Получить параметры, которые необходимо вставить в запрос
     */
    SqlParameters getParameters();
}
