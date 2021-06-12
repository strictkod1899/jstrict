package ru.strict.db.core.dao;

import ru.strict.db.core.common.SqlParameters;
import ru.strict.template.mapper.IMapper;
import ru.strict.template.model.BaseModel;

import java.sql.ResultSet;
import java.util.List;

public interface IConfigurableDao<ID, MODEL extends BaseModel<ID>> {
    /**
     * Получить все объекты из базы данных по переданным условиям, выполнив посдтановку услвоия по queryName
     *
     * @param whereName Название запроса из файла с типом 'WHERE'
     * @param parameters Параметры для подставноки в запрос
     * @return Список объектов из базы данных
     */
    List<MODEL> readAll(String whereName, SqlParameters parameters);
    long readCount(String whereName, SqlParameters parameters);

    <T> List<T> readByQuery(String queryName, SqlParameters parameters, IMapper<ResultSet, T> sqlMapper);

    void executeQuery(String queryName, SqlParameters parameters);

}
