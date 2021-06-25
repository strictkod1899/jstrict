package ru.strict.ioc.exceptions;

public class ComponentNotFoundException extends RuntimeException {

    public ComponentNotFoundException(Class<?> componentClass) {
        super(String.format("Component by class [%s] not found", componentClass));
    }

    public ComponentNotFoundException(String componentName) {
        super(String.format("Component by name [%s] not found", componentName));
    }
}
