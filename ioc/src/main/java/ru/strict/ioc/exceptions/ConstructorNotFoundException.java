package ru.strict.ioc.exceptions;

import java.util.Arrays;

public class ConstructorNotFoundException extends RuntimeException {

    public ConstructorNotFoundException(Class<?> componentClass, Object... args) {
        super(String.format("Constructor for class [%s] with arguments [%s] not found",
                componentClass.getName(),
                args == null ? "" : Arrays.toString(args))
        );
    }
}
