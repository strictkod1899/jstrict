package ru.strict.ioc.annotations;

import ru.strict.logging.LoggerBase;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Внедрить указанный логгер.
 * <p><b>Пример использования:</b></p>
 * <code><pre style="background-color: white; font-family: consolas">
 * {@code
 *     @Logger
 *     private ILogger logger;
 * }
 * </pre></code>
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD })
public @interface Logger {
    /**
     * Класс логирования. Первый аргумент в конструкторе логгера, должен быть класс логируемого объекта
     */
    Class<? extends LoggerBase> value() default LoggerBase.class;
}
