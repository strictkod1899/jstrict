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
        try {
            Errors clone = (Errors) super.clone();

            List<Error> actionErrors = new ArrayList<>();
            List<FieldError> fieldErrors = new ArrayList<>();
            for(Error error : this.actionErrors){
                actionErrors.add(error.clone());
            }
            for(FieldError error : this.fieldErrors){
                fieldErrors.add(error.clone());
            }

            clone.actionErrors = actionErrors;
            clone.fieldErrors = fieldErrors;
            return clone;
        } catch (CloneNotSupportedException ex) {
            throw new RuntimeException(ex);
        }
    }
}
