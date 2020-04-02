package ru.strict.db.core.repositories;

import ru.strict.db.core.requests.DbTable;
import ru.strict.db.core.requests.DbRequests;
import ru.strict.db.core.requests.DbWhereItem;
import ru.strict.models.IModel;
import ru.strict.validate.Validator;

import java.util.List;

/**
 * Базовое описание репозитория
 * @param <ID> Тип идентификатора
 * @param <T> Модель сущности базы данных
 */
public interface IRepository<ID, T extends IModel<ID>> {

    /**
     * Добавить в базу данных новый объект, переданный в качестве параметра
     *
     * @param model Добавляемый объект
     * @return Сохраненный идентификатор
     */
    ID create(T model);

    /**
     * Получить объект из базы данных по переданному id
     *
     * @param id Идентификатор записи
     * @return Объект связанный с переданным id
     */
    T read(ID id);

    /**
     * Получить все объекты из базы данных по переданным условиям
     *
     * @param requests Условия выборки объектов. Если передать null, то будут считаны все объекты БД
     * @return Список объектов из базы данных
     */
    List<T> readAll(DbRequests requests);

    /**
     * Обновить объект в базе данных связанный с id переданного объекта
     *
     * @param model Обновляемый объект
     */
    void update(T model);

    /**
     * Удалить объект из базы данных
     *
     * @param id Идентификатор удаляемого объекта
     */
    void delete(ID id);

    /**
     * Создать или обновить запись в таблице базы данных на основе объекта, переданного в параметрах метода.
     * Если в базе данных содержится запись с идентификатором переданного объекта,
     * то будет произведена попытка обновления записи, иначе пытаемся сохранить запись
     *
     * @param model Добавляемый/обновляемый объект
     * @return Созданный/обновленный идентификатор
     */
    ID createOrUpdate(T model);

    /**
     * Создать или прочитать запись в таблице базы данных на основе объекта, переданного в параметрах метода.
     * Если в базе данных содержится запись с идентификатором переданного объекта,
     * то метод вернет соответствующий объект, иначе пытаемся сохарнить запись
     *
     * @param model Добавляемый объект
     * @return Созданный/прочитанный объект
     */
    T createOrRead(T model);

    /**
     * Получить количество записей из базы данных по переданным условиям
     *
     * @param requests Условия выборки объектов. Если передать null, то будут считаны все объекты БД
     * @return Еоличество записей из базы данных
     */
    long readCount(DbRequests requests);

    /**
     * Проверить существование записи в базе данных с переданным идентификатором
     */
    default boolean isRowExists(ID id){
        Validator.isNull(id, "id").onThrow();

        DbRequests requests = new DbRequests();
        requests.addWhere(new DbWhereItem(getTable(), getIdColumnName(), id, "="));

        long count = readCount(requests);
        return count > 0 ? true : false;
    }

    /**
     * Получить таблицу, с которой связан данный репозиторий
     * @return
     */
    DbTable getTable();

    /**
     * Получить наименование столбца, который представляет столбец с идентификатором
     * @return
     */
    default String getIdColumnName(){
        return "id";
    }
}
