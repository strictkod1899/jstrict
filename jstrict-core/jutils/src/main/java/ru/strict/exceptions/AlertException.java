package ru.strict.exceptions;

/**
 * Ошибка, которая содержит сообщения для отображения пользователю.
 * Такую ошибку стоит выкидывать, когда ее нужно перехватить и отобразить внутреннее сообщение
 */
public class AlertException extends RuntimeException {

    public AlertException(String message, Object... args) {
        super(String.format(message, args));
    }
}
