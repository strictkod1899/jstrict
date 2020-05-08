package ru.strict.ioc.exceptions;

import java.util.Arrays;

public class ConstructorNotFound extends RuntimeException {

    public ConstructorNotFound(Class clazz, Object... args) {
        super(String.format("Constructor for clazz [%s] with arguments [%s] not found",
                clazz.getName(),
                args == null ? "" : Arrays.toString(args))
        );
    }
}
