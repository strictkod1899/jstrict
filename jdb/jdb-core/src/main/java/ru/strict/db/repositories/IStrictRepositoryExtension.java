package ru.strict.db.repositories;

import ru.strict.db.dto.StrictDtoBase;
import ru.strict.db.requests.StrictDbRequests;

import java.util.List;

/**
 * Расширенные возможности репозитория
 * @param <ID> Тип идентификатора
 * @param <DTO> Тип Dto-сущности базы данных
 */
public interface IStrictRepositoryExtension<ID, DTO extends StrictDtoBase> extends IStrictRepository<ID, DTO>{

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
    List<DTO> readAllFill(StrictDbRequests requests);

    /**
     * Создать или прочитать запись в таблице базы данных на основе dto-объекта, переданного в параметрах метода, подгрузив в качестве объектов внешние ссылки
     * Если в базе данных содержится запись с идентификатором переданного объекта, то метод вернет соответствующий объект, иначе пытаемся сохарнить запись
     *
     * @param dto Добавляемый объект
     * @return Созданный/прочитанный объект
     */
    DTO createOrReadFill(DTO dto);
}
