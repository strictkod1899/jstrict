package ru.strict.ioc.exceptions;

public class ManyMatchConstructorsException extends RuntimeException {

    public ManyMatchConstructorsException(Class component) {
        super("Fail a create component because was found many constructors. Error class = " + component.toString());
    }
}
