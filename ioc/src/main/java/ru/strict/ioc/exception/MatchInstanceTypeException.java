package ru.strict.ioc.exception;

import ru.strict.ioc.InstanceType;

public class MatchInstanceTypeException extends IoCException {

    private static final String MESSAGE_TEMPLATE = "An error occurred at matching IoC instance types. " +
            "Expected InstanceType = [%s], but obtained InstanceType = [%s]";

    public MatchInstanceTypeException(InstanceType expectedType, InstanceType factType) {
        super(String.format(MESSAGE_TEMPLATE, expectedType, factType));
    }
}
