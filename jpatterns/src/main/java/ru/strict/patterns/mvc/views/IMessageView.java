package ru.strict.patterns.mvc.views;

/**
 * Интерфейс представления для вывода сообщений
 */
public interface IMessageView extends IView {
    void showMessage(String message);
    void showError(String message);
}
