package ru.strict.db.core.repositories;

import ru.strict.db.core.common.SqlParameters;
import ru.strict.patterns.model.BaseModel;

import java.util.List;

public interface IConfigurableRepository<ID, MODEL extends BaseModel<ID>> {
    /**
     * Получить все объекты из базы данных по переданным условиям, выполнив посдтановку услвоия по queryName
     *
     * @param whereName Название запроса из файла с типом 'WHERE'
     * @param parameters Параметры для подставноки в запрос
     * @return Список объектов из базы данных
     */
    List<MODEL> readAll(String whereName, SqlParameters parameters);
}
