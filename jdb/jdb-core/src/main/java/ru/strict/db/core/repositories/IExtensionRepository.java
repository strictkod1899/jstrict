package ru.strict.db.core.repositories;

import ru.strict.db.core.requests.IParameterizedRequest;
import ru.strict.models.BaseModel;
import ru.strict.validate.Validator;

import java.util.List;

/**
 * Расширенные возможности репозитория
 *
 * @param <ID> Тип идентификатора
 * @param <T> Модель сущности базы данных
 */
public interface IExtensionRepository<ID, T extends BaseModel<ID>> extends IRepository<ID, T> {

    /**
     * Получить объект из базы данных по переданному id, подгрузив в качестве объектов внешние ссылки
     *
     * @param id Идентификатор записи
     * @return Объект связанный с переданным id
     */
    T readFill(ID id);

    /**
     * Получить все объекты из базы данных по переданным условиям, подгрузив в качестве объектов внешние ссылки
     *
     * @param requests Условия выборки объектов. Если передать null, то будут считаны все объекты БД
     * @return Список объектов из базы данных
     */
    List<T> readAllFill(IParameterizedRequest requests);

    /**
     * Создать или прочитать запись в таблице базы данных на основе объекта, переданного в параметрах метода,
     * подгрузив в качестве объектов внешние ссылки
     * Если в базе данных содержится запись с идентификатором переданного объекта, то метод вернет соответствующий
     * объект, иначе пытаемся сохранить запись
     *
     * @param model Добавляемый объект
     * @return Созданный/прочитанный объект
     */
    default T createOrReadFill(T model) {
        Validator.isNull(model, "model").onThrow();

        boolean rowExists = isRowExists(model.getId());
        if (rowExists) {
            return readFill(model.getId());
        } else {
            ID savedId = create(model);
            model.setId(savedId);
            return model;
        }
    }

    /**
     * Выполнить sql-запрос к базе данных
     */
    void executeSql(String sql);
}
