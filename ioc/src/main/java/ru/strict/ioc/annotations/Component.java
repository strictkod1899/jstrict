package ru.strict.ioc.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD, ElementType.PARAMETER, ElementType.CONSTRUCTOR })
public @interface Component {
    String value() default "";

    /**
     * Применяется для связи поля класса с полем конструктора
     * (полезно, например, когда конструктор генерируертся через lombok)
     * @return порядковый номер в конструкторе (начинается с 0)
     */
    int constructorParam() default -1;
}
