package ru.strict.swing.views;

import ru.strict.patterns.mvc.views.IView;

/**
 * Базовая форма
 */
public interface IForm extends IView {
    /**
     * Установить высоту формы по содержимому
     */
    void packHeight();

    /**
     * Установить ширину формы по содержимому
     */
    void packWidth();

    void launch();
}
