package ru.strict.components;

public class StrictWrapperRuntimeException extends RuntimeException {

    private Exception wrapperObject;

    public StrictWrapperRuntimeException(Exception wrapperObject) {
        super(wrapperObject.getMessage(), wrapperObject.getCause());
        this.wrapperObject = wrapperObject;
    }

    public Exception getWrapperObject() {
        return wrapperObject;
    }
}
