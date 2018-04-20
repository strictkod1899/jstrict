package ru.strict.db.repositories;

import ru.strict.db.dto.StrictDtoBase;
import ru.strict.db.requests.StrictDbRequests;
import ru.strict.db.entities.StrictEntityBase;

import java.util.List;

/**
 * Базовое описание репозитория
 */
public interface StrictRepositoryAny<ID, E extends StrictEntityBase, DTO extends StrictDtoBase> {

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
    List<DTO> readAll(StrictDbRequests requests);

    /**
     * Обновить объект в базе данных связанный с id переданного объекта
     * @param dto Обновляемый объект
     * @return Обновленный объект
     */
    DTO update(DTO dto);

    /**
     * Удалить объект из базы данных
     *
     * @param dto Удаляемый объект
     * @return При успешном удалении возвращает true, иначе false
     */
    boolean delete(DTO dto);

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
}
