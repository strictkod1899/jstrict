package ru.strict.repositories;

import ru.strict.requests.StrictDbWheres;
import ru.strict.entities.StrictEntityBase;

import java.util.List;

/**
 * Интерфейс определяет общие методы для репозиториев
 */
public interface StrictRepositoryAny<E extends StrictEntityBase> {

    /**
     * Метод добавляет в базу данных новый объект, переданный в качестве параметра
     *
     * @param e, добавляемый объект
     * @return при успешном добавлении объекта в базу данных возвращается true, иначе false
     */
    public boolean create(E e);

    /**
     * Метод считывает все сущности из базы данных
     *
     * @return список объектов базы данных
     */
    public List<E> readList(StrictDbWheres wheres);

    /**
     * Метод считывает сузность из базы данных по переданноум id
     * @param id id сущности
     * @return объект содержащий в себе переданный id
     */

    public E read(Number id);

    /**
     * Метод удаляет объект из базы данных
     *
     * @param e удаляемый объект
     */
    public boolean delete(E e);

    /**
     * Метод ищет сущность в базе данных по id переданного объекта и у этой сущности обновляет все данные на данные переданного объекта
     * @param e, обновляемый объект
     * @return при успешном обновлении объекта в базе данных возвращается true, иначе false
     */
    public boolean update(E e);
}
