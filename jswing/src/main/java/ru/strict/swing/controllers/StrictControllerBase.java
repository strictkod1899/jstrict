package ru.strict.swing.controllers;

import ru.strict.swing.models.StrictModelBase;

/**
 * Базовый контроллер
 */
public abstract class StrictControllerBase<O extends Object, M extends StrictModelBase> {

    private O object;
    private M model;

    /**
     * Инициализация контроллера
     * @param object Объект управления
     * @param model Модель объекта управления
     */
    public StrictControllerBase(O object, M model) {
        this.object = object;
        this.model = model;
    }

    /**
     * Создание объекта
     * @return Созданный объект
     */
    public abstract O build();

    /**
     * Получить связанный с контроллером объект
     * @return
     */
    public O getObject() {
        return object;
    }

    /**
     * Установить связанный с контроллером объект
     * @param object Связанный с контроллером объект
     */
    public void setObject(O object) {
        this.object = object;
    }

    /**
     * Получить связанную с объектом модель
     * @return
     */
    public M getModel() {
        return model;
    }

    /**
     * Установить связанную с объектом модель
     * @param model Связанная с объектом модель
     */
    public void setModel(M model) {
        this.model = model;
    }
}
