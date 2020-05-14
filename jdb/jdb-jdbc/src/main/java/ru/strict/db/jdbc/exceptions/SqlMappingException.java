package ru.strict.db.jdbc.exceptions;

import ru.strict.patterns.MappingException;

public class SqlMappingException extends MappingException {

    public SqlMappingException(Throwable cause) {
        super("The exception occurred at SQL-result mapping", cause);
    }

    public SqlMappingException(String message, Throwable cause) {
        super(message, cause);
    }
}
