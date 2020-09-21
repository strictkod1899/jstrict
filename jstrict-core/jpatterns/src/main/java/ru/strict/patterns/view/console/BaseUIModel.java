package ru.strict.patterns.view.console;

import ru.strict.components.Message;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Модель представления (паттерн MVC)
 *
 * @param <STAGE> Элемент (обычно enum) текущего состояния модели
 */
public class BaseUIModel<STAGE> implements IConsoleModel<STAGE>{

    private STAGE currentStage;
    private List<Message> errors;
    private List<String> warnings;

    public BaseUIModel() {
        errors = new ArrayList<>();
        warnings = new ArrayList<>();
    }

    @Override
    public void reset() {
        cleanErrors();
    }

    @Override
    public STAGE getStage() {
        return currentStage;
    }

    @Override
    public void setStage(STAGE stage) {
        currentStage = stage;
    }

    @Override
    public List<Message> getErrors() {
        return errors;
    }

    @Override
    public List<Message> popErrors() {
        List<Message> result = new ArrayList<>(errors);
        cleanErrors();
        return result;
    }

    @Override
    public void addError(Message alert) {
        if(alert != null){
            errors.add(alert);
        }
    }

    @Override
    public void addErrors(Collection<Message> alerts) {
        if(alerts != null){
            this.errors.addAll(alerts);
        }
    }

    @Override
    public void cleanErrors() {
        errors.clear();
    }

    @Override
    public List<String> getWarnings() {
        return warnings;
    }

    @Override
    public List<String> popWarnings() {
        List<String> result = new ArrayList<>(warnings);
        cleanWarnings();
        return result;
    }

    @Override
    public void addWarning(String warning) {
        if(warning != null){
            warnings.add(warning);
        }
    }

    @Override
    public void addWarnings(Collection<String> warnings) {
        if(warnings != null){
            this.warnings.addAll(warnings);
        }
    }

    @Override
    public void cleanWarnings() {
        warnings.clear();
    }
}
