package ru.strict.patterns.mvc.views;

/**
 * Базовый интерфейс представления MVC
 */
public interface IView {
    /**
     * Обновить состояние представления
     */
    void refresh();

    void launch();

    void destroy();
}
