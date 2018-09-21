package ru.strict.swing.views;

import ru.strict.swing.models.ModelFormBase;

/**
 * Базовая форма
 */
public interface IForm<M extends ModelFormBase> extends IView<M> {

    /**
     * Запуск формы
     */
    void launch();

    /**
     * Завершение работы формы
     */
    void destroy();

    /**
     * Обновление состояния формы
     */
    void updateView();

    /**
     * Установить высоту формы по содержимому
     */
    void packHeight();

    /**
     * Установить ширину формы по содержимому
     */
    void packWidth();
}
