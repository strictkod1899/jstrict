package ru.strict.ioc.annotations;

import ru.strict.utils.ReflectionUtil;

public class ConfigurationHandler {

    public static void invokeConfigurationMethods(Object instance) {
        ReflectionUtil.invokeVoidMethodsByAnnotation(instance, Configuration.class);
    }
}
