package ru.strict.patterns.view.console;

import ru.strict.components.Message;

import java.util.Collection;
import java.util.List;

public interface IConsoleModel<T> {
    T getStage();
    void setStage(T stage);
    List<Message> getErrors();
    List<Message> popErrors();
    void addError(Message alert);
    void addErrors(Collection<Message> alerts);
    void clearErrors();
    List<String> getWarnings();
    List<String> popWarnings();
    void addWarning(String warning);
    void addWarnings(Collection<String> warnings);
    void clearWarnings();
    /**
     * Сбросить состояние модели
     */
    void reset();
}
