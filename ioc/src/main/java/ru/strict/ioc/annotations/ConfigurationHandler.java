package ru.strict.ioc.annotations;

import lombok.experimental.UtilityClass;
import ru.strict.ioc.IoC;
import ru.strict.validate.CommonValidate;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class ConfigurationHandler {

    public static void invokeVoidConfigurationMethods(Object instance) {
        List<Method> voidConfigurationMethods = getVoidConfigurationMethods(instance.getClass());
        voidConfigurationMethods.forEach(method -> {
            try {
                method.invoke(instance);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });
    }

    public static void fillIoCByConfiguration(Class<?> configurationClass, IoC ioc) {
        var methods = getReturnedConfigurationMethods(configurationClass);

        for (Method method : methods) {
            Class<?> returnClass = method.getReturnType();

            String componentName;
            Component componentAnnotation = method.getAnnotation(Component.class);
            if (componentAnnotation != null && !CommonValidate.isEmptyOrNull(componentAnnotation.value())) {
                componentName = componentAnnotation.value();
            } else {
                componentName = method.getName();
            }

            ioc.addSingleton(componentName, returnClass, () -> {
                Object configurationInstance = ioc.getComponent(configurationClass);
                try {
                    return method.invoke(configurationInstance);
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            });
        }
    }

    private static List<Method> getVoidConfigurationMethods(Class<?> configurationClass) {
        var configurationMethods = getConfigurationMethods(configurationClass);
        return configurationMethods.stream()
                .filter(m -> m.getReturnType() == null || m.getReturnType().getName().equals("void"))
                .collect(Collectors.toList());
    }

    private static List<Method> getReturnedConfigurationMethods(Class<?> configurationClass) {
        var configurationMethods = getConfigurationMethods(configurationClass);
        return configurationMethods.stream()
                .filter(m -> m.getReturnType() != null && !m.getReturnType().getName().equals("void"))
                .collect(Collectors.toList());
    }

    private static List<Method> getConfigurationMethods(Class<?> configurationClass) {
        var methods = configurationClass.getDeclaredMethods();
        List<Method> configurationMethods = new ArrayList<>(methods.length);

        for (Method method : methods) {
            if (method.isAnnotationPresent(Configuration.class)) {
                configurationMethods.add(method);
            }
        }

        return configurationMethods;
    }
}