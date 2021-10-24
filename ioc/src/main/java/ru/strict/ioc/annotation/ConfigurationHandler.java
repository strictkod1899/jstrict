package ru.strict.ioc.annotation;

import lombok.experimental.UtilityClass;
import ru.strict.util.ReflectionUtil;

@UtilityClass
public class ConfigurationHandler {

    public static void invokeVoidConfigurationMethods(Object instance) {
        ReflectionUtil.invokeVoidMethodsByAnnotation(instance, Configuration.class);
    }
}