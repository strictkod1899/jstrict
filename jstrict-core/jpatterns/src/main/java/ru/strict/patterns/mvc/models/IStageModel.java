package ru.strict.patterns.mvc.models;

import ru.strict.components.Error;

import java.util.Collection;
import java.util.List;

public interface IStageModel<T> {
    T getStage();

    void setStage(T stage);

    List<Error> getErrors();

    List<Error> popErrors();

    void addError(Error error);

    void addErrors(Collection<Error> errors);

    void cleanErrors();

    List<String> getWarnings();

    List<String> popWarnings();

    void addWarning(String warning);

    void addWarnings(Collection<String> warnings);

    void cleanWarnings();
}
