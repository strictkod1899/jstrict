package ru.strict.components;

public class WrapperException extends Exception {

    private Exception wrapperObject;

    public WrapperException(Exception wrapperObject) {
        super(wrapperObject.getMessage(), wrapperObject.getCause());
        this.wrapperObject = wrapperObject;
    }

    public Exception getWrapperObject() {
        return wrapperObject;
    }
}
