package ru.strict.db.core.exceptions;

public class UncorrectedQueryFormatException extends RuntimeException {

    private static final String MESSAGE_TEMPLATE = "Uncorrected format for query [%s]. %s";

    public UncorrectedQueryFormatException(String queryName, String description) {
        super(String.format(MESSAGE_TEMPLATE, queryName, description));
    }
}
