package ru.strict.file.xml;

public class XmlReadException extends RuntimeException {

    public XmlReadException() {
    }

    public XmlReadException(String message) {
        super(message);
    }

    public XmlReadException(String message, Throwable cause) {
        super(message, cause);
    }

    public XmlReadException(Throwable cause) {
        super(cause);
    }

    public XmlReadException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
