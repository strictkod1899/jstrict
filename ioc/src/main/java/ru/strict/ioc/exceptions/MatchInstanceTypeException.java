package ru.strict.ioc.exceptions;

import ru.strict.ioc.InstanceType;

public class MatchInstanceTypeException extends RuntimeException {

    private static final String MESSAGE_TEMPLATE = "An error occurred at matching IoC instance types. " +
            "Expected InstanceType = [%s], but obtained InstanceType = [%s]";

    public MatchInstanceTypeException(InstanceType expectedType, InstanceType factType) {
        super(String.format(MESSAGE_TEMPLATE, expectedType, factType));
    }
}
