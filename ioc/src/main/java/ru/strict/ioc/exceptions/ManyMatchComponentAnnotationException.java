package ru.strict.ioc.exceptions;

public class ManyMatchComponentAnnotationException extends RuntimeException {

    public ManyMatchComponentAnnotationException(Class component){
        super("Fail a create component because was found many constructors with @Component-annotation. Error class = " + component.toString());
    }
}
