package ru.strict.patterns.view.console;

/**
 * Базовый интерфейс представления MVC
 */
public interface IView {
    /**
     * Обновить состояние представления
     */
    void refresh();

    /**
     * Запустить (отобразить) представление
     */
    void launch();

    void destroy();
}
