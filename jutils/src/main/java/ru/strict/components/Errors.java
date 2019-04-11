package ru.strict.components;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Errors implements Cloneable {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Errors object = (Errors) o;
        return Objects.equals(actionErrors, object.actionErrors) &&
                Objects.equals(fieldErrors, object.fieldErrors);
    }

    @Override
    public int hashCode() {
        return Objects.hash(actionErrors, fieldErrors);
    }

    @Override
    public Errors clone() {
        Errors clone = new Errors();
        for(Error error : actionErrors){
            clone.addActionError(error.clone());
        }
        for(FieldError error : fieldErrors){
            clone.addFieldError(error.clone());
        }
        return clone;
    }
}
