package ru.strict.db.core.requests;

import ru.strict.db.core.common.SqlParameters;

import java.util.List;

/**
 * Базовое определения параметризованного sql-запроса
 */
public interface IDbParametrizedRequest {
    /**
     * Сформировать sql-конструкцию со вставками параметров (параметр обозначется знаком вопроса '?')
     * @return
     */
    String getParametrizedSql();

    /**
     * Получить параметры, которые необходимо вставить в запрос
     */
    SqlParameters getParameters();
}
