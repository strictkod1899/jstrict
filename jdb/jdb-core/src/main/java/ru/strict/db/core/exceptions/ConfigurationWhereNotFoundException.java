package ru.strict.db.core.exceptions;

public class ConfigurationWhereNotFoundException extends RuntimeException {

    private static final String MESSAGE_TEMPLATE = "Where-query from configuration not found [group = %s, query = %s]";

    public ConfigurationWhereNotFoundException(String group, String query) {
        super(String.format(MESSAGE_TEMPLATE, group, query));
    }
}
