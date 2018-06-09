package ru.strict.components;

public class StrictWrapperException extends Exception {

    private Exception wrapperObject;

    public StrictWrapperException(Exception wrapperObject) {
        super(wrapperObject.getMessage(), wrapperObject.getCause());
        this.wrapperObject = wrapperObject;
    }

    public Exception getWrapperObject() {
        return wrapperObject;
    }
}
