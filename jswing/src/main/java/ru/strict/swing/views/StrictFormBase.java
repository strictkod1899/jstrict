package ru.strict.swing.views;

import ru.strict.swing.models.StrictModelFormBase;

/**
 * Базовая форма
 */
public interface StrictFormBase<M extends StrictModelFormBase> extends StrictViewBase<M> {

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

    @Override
    M getModel();

    @Override
    void setModel(M model);

}
