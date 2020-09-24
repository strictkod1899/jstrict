package ru.strict.ioc.exceptions;

import java.util.Arrays;

public class ConstructorNotFoundException extends RuntimeException {

    public ConstructorNotFoundException(Class clazz, Object... args) {
        super(String.format("Constructor for clazz [%s] with arguments [%s] not found",
                clazz.getName(),
                args == null ? "" : Arrays.toString(args))
        );
    }
}
