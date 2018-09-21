package ru.strict.swing.controllers;

import ru.strict.swing.models.ModelBase;

/**
 * Базовый контроллер
 */
public abstract class ControllerBase<O extends Object, M extends ModelBase> implements IController<O, M>{

    private O object;
    private M model;

    /**
     * Инициализация контроллера
     * @param object Объект управления
     * @param model Модель объекта управления
     */
    public ControllerBase(O object, M model) {
        this.object = object;
        this.model = model;
    }

    /**
     * Получить связанный с контроллером объект
     * @return
     */
    @Override
    public O getObject() {
        return object;
    }

    /**
     * Установить связанный с контроллером объект
     * @param object Связанный с контроллером объект
     */
    @Override
    public void setObject(O object) {
        this.object = object;
    }

    /**
     * Получить связанную с объектом модель
     * @return
     */
    @Override
    public M getModel() {
        return model;
    }

    /**
     * Установить связанную с объектом модель
     * @param model Связанная с объектом модель
     */
    @Override
    public void setModel(M model) {
        this.model = model;
    }
}
