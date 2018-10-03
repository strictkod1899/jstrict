package ru.strict.patterns.mvc.controllers;

public interface IController {
    /**
     * Запуск формы
     */
    void launch();

    /**
     * Завершение работы формы
     */
    void destroy();
}
