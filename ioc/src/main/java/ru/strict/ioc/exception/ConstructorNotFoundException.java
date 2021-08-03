package ru.strict.ioc.exception;

import java.util.Arrays;

public class ConstructorNotFoundException extends IoCException {

    public ConstructorNotFoundException(Class<?> componentClass, Object... args) {
        super(String.format("Constructor for class [%s] with arguments [%s] not found",
                componentClass.getName(),
                args == null ? "" : Arrays.toString(args))
        );
    }
}
