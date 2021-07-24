package ru.strict.ioc.exceptions;

public class ManyMatchComponentsException extends IoCException {

    private static final String MESSAGE_TEMPLATE_BY_COMPONENT =
            "An error occurred at get component because was found many component with class = %s";
    private static final String MESSAGE_TEMPLATE_BY_NAME =
            "An error occurred at get component because was found many component with name = %s";

    public ManyMatchComponentsException(Class<?> componentClass) {
        super(String.format(MESSAGE_TEMPLATE_BY_COMPONENT, componentClass));
    }

    public ManyMatchComponentsException(String componentName) {
        super(String.format(MESSAGE_TEMPLATE_BY_NAME, componentName));
    }
}
