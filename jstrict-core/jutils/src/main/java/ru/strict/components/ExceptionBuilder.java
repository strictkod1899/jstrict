package ru.strict.components;

import ru.strict.exceptions.AlertException;

import static ru.strict.validate.Validator.*;

public class ExceptionBuilder implements IExceptionBuilder {

    private final IMessageProvider messageProvider;

    public ExceptionBuilder(IMessageProvider messageProvider) {
        this.messageProvider = messageProvider;
    }

    /**
     * @throws AlertException
     */
    @Override
    public void onThrow(String messageCode, Object...args) {
        String message = messageProvider.getMessage(messageCode);
        isNull(message, "message");

        throw new AlertException(message, args);
    }
}
