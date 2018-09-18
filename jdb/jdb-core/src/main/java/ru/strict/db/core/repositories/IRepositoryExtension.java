package ru.strict.db.core.repositories;

import ru.strict.db.core.dto.DtoBase;
import ru.strict.db.core.requests.DbRequests;

import java.util.List;

/**
 * Расширенные возможности репозитория
 * @param <ID> Тип идентификатора
 * @param <DTO> Тип Dto-сущности базы данных
 */
public interface IRepositoryExtension<ID, DTO extends DtoBase<ID>> extends IRepository<ID, DTO> {

    /**
     * Получить объект из базы данных по переданному id, подгрузив в качестве объектов внешние ссылки
     *
     * @param id Идентификатор записи
     * @return Объект связанный с переданным id
     */
    DTO readFill(ID id);

    /**
     * Получить все объекты из базы данных по переданным условиям, подгрузив в качестве объектов внешние ссылки
     *
     * @param requests Условия выборки объектов. Если передать null, то будут считаны все объекты БД
     * @return Список объектов из базы данных
     */
    List<DTO> readAllFill(DbRequests requests);

    /**
     * Создать или прочитать запись в таблице базы данных на основе dto-объекта, переданного в параметрах метода, подгрузив в качестве объектов внешние ссылки
     * Если в базе данных содержится запись с идентификатором переданного объекта, то метод вернет соответствующий объект, иначе пытаемся сохарнить запись
     *
     * @param dto Добавляемый объект
     * @return Созданный/прочитанный объект
     */
    DTO createOrReadFill(DTO dto);

    /**
     * Получить количество записей из базы данных по переданным условиям
     *
     * @param requests Условия выборки объектов. Если передать null, то будут считаны все объекты БД
     * @return Еоличество записей из базы данных
     */
    int readCount(DbRequests requests);

    /**
     * Проверить существование записи в базе данных с переданным идентификатором
     * @param id
     * @return
     */
    boolean isRowExists(ID id);
}
