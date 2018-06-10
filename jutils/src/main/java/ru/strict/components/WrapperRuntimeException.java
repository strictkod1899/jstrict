package ru.strict.components;

public class WrapperRuntimeException extends RuntimeException {

    private Exception wrapperObject;

    public WrapperRuntimeException(Exception wrapperObject) {
        super(wrapperObject.getMessage(), wrapperObject.getCause());
        this.wrapperObject = wrapperObject;
    }

    public Exception getWrapperObject() {
        return wrapperObject;
    }
}
