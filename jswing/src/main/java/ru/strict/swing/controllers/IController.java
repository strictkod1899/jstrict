package ru.strict.swing.controllers;

import ru.strict.swing.models.ModelBase;

public interface IController<O extends Object, M extends ModelBase> {

    /**
     * Выполнить сборку объекта
     * @return
     */
    O build();

    /**
     * Получить связанный с контроллером объект
     * @return
     */
    O getObject();

    /**
     * Установить связанный с контроллером объект
     * @param object Связанный с контроллером объект
     */
    void setObject(O object);

    /**
     * Получить связанную с объектом модель
     * @return
     */
    M getModel();

    /**
     * Установить связанную с объектом модель
     * @param model Связанная с объектом модель
     */
    void setModel(M model);
}
