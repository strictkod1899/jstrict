package ru.strict.installer;

public class AcceptLicenseException extends Exception {
    public AcceptLicenseException() {
        super();
    }

    public AcceptLicenseException(String message) {
        super(message);
    }
}
