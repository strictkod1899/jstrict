package ru.strict.db.core.exceptions;

public class ConfigurationQueryNotFoundException extends RuntimeException {

    private static final String MESSAGE_TEMPLATE = "Query from configuration not found [group = %s, query = %s]";

    public ConfigurationQueryNotFoundException(String group, String query) {
        super(String.format(MESSAGE_TEMPLATE, group, query));
    }
}
