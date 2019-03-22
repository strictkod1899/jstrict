package ru.strict.components;

import java.util.ArrayList;
import java.util.List;

public class Errors {
    private List<Error> actionErrors;
    private List<FieldError> fieldErrors;

    public Errors() {
        actionErrors = new ArrayList<>();
        fieldErrors = new ArrayList<>();
    }

    public void addActionError(Error error){
        actionErrors.add(error);
    }

    public void addFieldError(FieldError error){
        fieldErrors.add(error);
    }

    public List<Error> getActionErrors() {
        return actionErrors;
    }

    public List<FieldError> getFieldErrors() {
        return fieldErrors;
    }
}
