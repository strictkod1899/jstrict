package ru.strict.ioc.exception;

import java.lang.reflect.ParameterizedType;

public class ComponentNotFoundException extends IoCException {

    public ComponentNotFoundException(Object objectSearch) {
        super(getMessage(objectSearch));
    }

    public ComponentNotFoundException(String componentName) {
        super(String.format("Component by name [%s] not found", componentName));
    }

    private static String getMessage(Object objectSearch) {
        if (objectSearch instanceof Class) {
            return String.format("Component by class [%s] not found", objectSearch);
        } else if (objectSearch instanceof ParameterizedType) {
            return String.format("Component by parameterized-class [%s] not found", objectSearch);
        } else {
            return String.format("Component by object-search [%s] not found", objectSearch);
        }
    }
}
