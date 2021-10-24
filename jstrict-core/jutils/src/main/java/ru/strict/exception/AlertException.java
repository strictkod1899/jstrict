package ru.strict.exception;

import lombok.Getter;
import ru.strict.model.Codeable;

/**
 * Ошибка, которая содержит сообщения для отображения пользователю.
 * Такую ошибку стоит выкидывать, когда ее нужно перехватить и отобразить внутреннее сообщение
 */
@Getter
public class AlertException extends RuntimeException {
    private Codeable<String> messageCode;
    private Object[] messageArgs;
    private String lang;

    public AlertException(String logMessage, Object... args) {
        super(String.format(logMessage, args));
    }

    private AlertException(Codeable messageCode, Object[] messageArgs, String lang) {
        this.messageCode = messageCode;
        this.messageArgs = messageArgs;
        this.lang = lang;
    }

    private AlertException(Codeable messageCode, Object[] messageArgs, String lang, String logMessage) {
        super(logMessage);
        this.messageCode = messageCode;
        this.messageArgs = messageArgs;
        this.lang = lang;
    }

    public static AlertException create(Codeable messageCode, Object... messageArgs) {
        return new AlertException(messageCode, messageArgs, null);
    }

    public static AlertException create(Codeable messageCode, String logMessage, Object... messageArgs) {
        return new AlertException(messageCode, messageArgs, null, logMessage);
    }

    public static AlertException createByLang(Codeable messageCode, String lang, Object... messageArgs) {
        return new AlertException(messageCode, messageArgs, lang);
    }

    public static AlertException createByLang(Codeable messageCode,
            String lang,
            String logMessage,
            Object... messageArgs) {
        return new AlertException(messageCode, messageArgs, lang, logMessage);
    }
}
