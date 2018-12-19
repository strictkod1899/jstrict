package ru.strict.ioc.exceptions;

import ru.strict.ioc.InstanceType;

public class MatchInstanceTypeException extends Exception {

    public MatchInstanceTypeException(InstanceType expectedType, InstanceType factType){
        super(String.format("IoC exception. Expected InstanceType = [%s], but obtained InstanceType = [%s]",
                expectedType, factType));
    }
}
