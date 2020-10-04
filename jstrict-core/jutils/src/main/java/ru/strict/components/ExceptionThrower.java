package ru.strict.components;

import ru.strict.exceptions.AlertException;

import static ru.strict.validate.Validator.*;

public class ExceptionThrower implements IExceptionProvider {

    private final IMessageProvider messageProvider;

    public ExceptionThrower(IMessageProvider messageProvider) {
        this.messageProvider = messageProvider;
    }

    /**
     * @throws AlertException
     */
    @Override
    public void onThrow(IMessageCode messageCode, Object[] args) {
        onThrow(messageCode.getCode(), args);
    }

    /**
     * @throws AlertException
     */
    @Override
    public void onThrow(String messageCode, Object... args) {
        String message = messageProvider.getMessage(messageCode);
        isNull(message, "message");

        throw new AlertException(message, args);
    }
}
