package ru.strict.ioc.annotations;

import lombok.experimental.UtilityClass;
import ru.strict.utils.ReflectionUtil;

@UtilityClass
public class ConfigurationHandler {

    public static void invokeVoidConfigurationMethods(Object instance) {
        ReflectionUtil.invokeVoidMethodsByAnnotation(instance, Configuration.class);
    }
}