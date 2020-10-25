package ru.strict.exceptions;

import ru.strict.components.IMessageCode;

/**
 * Ошибка, которая содержит сообщения для отображения пользователю.
 * Такую ошибку стоит выкидывать, когда ее нужно перехватить и отобразить внутреннее сообщение
 */
public class AlertException extends RuntimeException {
    private IMessageCode messageCode;
    private Object[] messageArgs;
    private String lang;

    public AlertException(String message, Object... args) {
        super(String.format(message, args));
    }

    private AlertException(IMessageCode messageCode, Object[] messageArgs, String lang) {
        this.messageCode = messageCode;
        this.messageArgs = messageArgs;
        this.lang = lang;
    }

    private AlertException(IMessageCode messageCode, Object[] messageArgs, String lang, String message) {
        super(message);
        this.messageCode = messageCode;
        this.messageArgs = messageArgs;
        this.lang = lang;
    }

    public static AlertException create(IMessageCode messageCode, Object... messageArgs) {
        return new AlertException(messageCode, messageArgs, null);
    }

    public static AlertException createByLang(IMessageCode messageCode, String lang, Object... messageArgs) {
        return new AlertException(messageCode, messageArgs, lang);
    }

    public static AlertException create(IMessageCode messageCode, String message, Object... messageArgs) {
        return new AlertException(messageCode, messageArgs, null, message);
    }

    public static AlertException createByLang(IMessageCode messageCode,
            String lang,
            String message,
            Object... messageArgs) {
        return new AlertException(messageCode, messageArgs, lang, message);
    }
}
