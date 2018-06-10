package ru.strict.swing.views;

import ru.strict.swing.models.ModelFormBase;

/**
 * Базовая форма
 */
public interface FormBase<M extends ModelFormBase> extends ViewBase<M> {

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
