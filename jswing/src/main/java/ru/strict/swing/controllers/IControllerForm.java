package ru.strict.swing.controllers;

import ru.strict.swing.models.ModelFormBase;
import ru.strict.swing.views.IForm;

public interface IControllerForm<O extends IForm, M extends ModelFormBase> extends IController<O, M> {
    /**
     * Запуск формы
     */
    void launch();
    /**
     * Завершение работы формы
     */
    void destroy();

    /**
     * Обновить состояние формы
     */
    void updateForm();
}
