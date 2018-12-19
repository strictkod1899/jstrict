package ru.strict.ioc.exceptions;

public class ManyMatchComponentsException extends RuntimeException {

    public ManyMatchComponentsException(Class component){
        super("Fail a get component because was found many component with class " + component.toString());
    }

    public ManyMatchComponentsException(String component){
        super("Fail a get component because was found many component with name " + component);
    }
}
