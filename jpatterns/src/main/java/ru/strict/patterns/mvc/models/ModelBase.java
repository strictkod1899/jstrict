package ru.strict.patterns.mvc.models;

import ru.strict.components.Error;

import java.util.ArrayList;
import java.util.Collection;
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
    public List<Error> popErrors() {
        List<Error> result = new ArrayList<>(errors);
        cleanErrors();
        return result;
    }

    @Override
    public void addError(Error error) {
        if(error != null){
            errors.add(error);
        }
    }

    @Override
    public void addErrors(Collection<Error> errors) {
        if(errors != null){
            this.errors.addAll(errors);
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
