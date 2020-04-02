package ru.strict.patterns.mvc.views;

import java.util.Collection;

/**
 * Интерфейс представления для взаимодействия с пользователем посредством последовательных комманд
 */
public interface IInteractiveView extends IMessageView {
    String getCurrentMessage();
    Integer inputInteger(String message);
    String inputString(String message);
    Integer inputCommand(String message, int minValue, int maxValues);
    <RESULT> RESULT inputCommand(String message, Class<RESULT> inputType, Collection<RESULT> correctValues);
    <RESULT> RESULT inputCommand(String message, Class<RESULT> inputType, Collection<RESULT> correctValues, String defaultCancelValue);
}
