package ru.strict.ioc.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Выполнить логирование вызываемых методов у класса или у помеченных методов
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE, ElementType.METHOD })
public @interface Logging {
    /**
     * Классы логирования. Первый аргумент в конструкторе логгера, должен быть класс логируемого объекта
     */
    //Class<? extends LoggerBase>[] value() default {};

    /**
     * Использовать для логирования указанного метода только логгеры текущей аннотации
     */
    //boolean onlyThis() default false;

    /**
     * Весь лог будет записан в указанном уровне
     */
    //LogLevel logLevel() default LogLevel.DEBUG;
}
