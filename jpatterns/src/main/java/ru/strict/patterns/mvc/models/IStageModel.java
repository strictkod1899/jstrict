package ru.strict.patterns.mvc.models;

import ru.strict.components.Error;

import java.util.List;

public interface IStageModel<T> {
    T getStage();
    void setStage(T stage);
    List<Error> getErrors();
    void addError(Error error);
    void cleanErrors();
}
