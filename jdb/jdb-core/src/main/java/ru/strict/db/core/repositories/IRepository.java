package ru.strict.db.core.repositories;

import ru.strict.db.core.dto.DtoBase;
import ru.strict.db.core.requests.DbRequests;
import java.util.List;

/**
 * Базовое описание репозитория
 * @param <ID> Тип идентификатора
 * @param <DTO> Тип Dto-сущности базы данных
 */
public interface IRepository<ID, DTO extends DtoBase<ID>> extends AutoCloseable {

    /**
     * Добавить в базу данных новый объект, переданный в качестве параметра
     *
     * @param dto Добавляемый объект
     * @return Созданный объект
     */
    DTO create(DTO dto);

    /**
     * Получить объект из базы данных по переданному id
     *
     * @param id Идентификатор записи
     * @return Объект связанный с переданным id
     */
    DTO read(ID id);

    /**
     * Получить все объекты из базы данных по переданным условиям
     *
     * @param requests Условия выборки объектов. Если передать null, то будут считаны все объекты БД
     * @return Список объектов из базы данных
     */
    List<DTO> readAll(DbRequests requests);

    /**
     * Обновить объект в базе данных связанный с id переданного объекта
     *
     * @param dto Обновляемый объект
     * @return Обновленный объект
     */
    DTO update(DTO dto);

    /**
     * Удалить объект из базы данных
     *
     * @param id Идентификатор удаляемого объекта
     */
    void delete(ID id);

    /**
     * Cоздать или обновить запись в таблице базы данных на основе dto-объекта, переданного в параметрах метода.
     * Если в базе данных содержится запись с идентификатором переданного объекта, то будет произведена попытка обновления записи, иначе пытаемся сохранить запись
     *
     * @param dto Добавляемый/обновляемый объект
     * @return Созданный/обновленный объект
     */
    DTO createOrUpdate(DTO dto);

    /**
     * Создать или прочитать запись в таблице базы данных на основе dto-объекта, переданного в параметрах метода.
     * Если в базе данных содержится запись с идентификатором переданного объекта, то метод вернет соответствующий объект, иначе пытаемся сохарнить запись
     *
     * @param dto Добавляемый объект
     * @return Созданный/прочитанный объект
     */
    DTO createOrRead(DTO dto);

    /**
     * Получить наименование таблицы, с которой связан данный репозиторий
     * @return
     */
    String getTableName();

    /**
     * Получить наименование столбца, который представляет столбец с идентификатором
     * @return
     */
    default String getColumnIdName(){
        return "id";
    }

    void close();
}
