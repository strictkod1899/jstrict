package ru.strict.ioc.exception;

public class ManyMatchComponentAnnotationException extends IoCException {

    private static String MESSAGE_TEMPLATE = "An error occurred at component creation because was found many " +
            "constructors with @Component-annotation. ComponentCLass = %s";

    public ManyMatchComponentAnnotationException(Class<?> component) {
        super(String.format(MESSAGE_TEMPLATE, component));
    }
}
