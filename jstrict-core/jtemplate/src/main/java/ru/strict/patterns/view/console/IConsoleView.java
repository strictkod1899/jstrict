package ru.strict.patterns.view.console;

import java.util.Collection;

/**
 * Интерфейс представления для взаимодействия с пользователем посредством последовательных комманд
 */
public interface IConsoleView extends IView {
    void showMessage(String message);
    void showWarning(String message);
    void showError(String message);

    String getCurrentMessage();
    Integer inputInteger(String message);
    String inputString(String message);
    Integer inputCommand(String message, int minValue, int maxValues);
    <RESULT> RESULT inputCommand(String message, Class<RESULT> inputType, Collection<RESULT> correctValues);
    <RESULT> RESULT inputCommand(String message,
            Class<RESULT> inputType,
            Collection<RESULT> correctValues,
            String defaultCancelValue);
}
