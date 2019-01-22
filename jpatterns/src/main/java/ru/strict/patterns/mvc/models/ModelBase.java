package ru.strict.patterns.mvc.models;

import ru.strict.components.Error;

import java.util.ArrayList;
import java.util.List;

public class ModelBase<STAGE> implements IStageModel<STAGE>, IResetModel{

    private STAGE currentStage;
    private List<Error> errors;
    private List<String> warnings;

    public ModelBase() {
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
    public List<Error> getErrors() {
        return errors;
    }

    @Override
    public void addError(Error error) {
        if(error != null){
            errors.add(error);
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
    public void addWarning(String warning) {
        if(warning != null){
            warnings.add(warning);
        }
    }

    @Override
    public void cleanWarnings() {
        warnings.clear();
    }
}
