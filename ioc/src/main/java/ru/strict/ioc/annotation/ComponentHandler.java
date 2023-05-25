package ru.strict.ioc.annotation;

import lombok.experimental.UtilityClass;
import ru.strict.ioc.IoC;
import ru.strict.ioc.exception.ManyMatchComponentAnnotationException;
import ru.strict.ioc.exception.ManyMatchConstructorFieldsException;
import ru.strict.util.ReflectionUtil;
import ru.strict.validate.CommonValidator;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class ComponentHandler {

    public static Object[] getConstructorArguments(Constructor<?> constructor, Class<?> instanceClass) {
        Object[] constructorArguments = new Object[constructor.getParameterCount()];
        Parameter[] parameters = constructor.getParameters();
        for (int i = 0; i < parameters.length; i++) {
            Parameter constructorParameter = parameters[i];
            Component componentAnnotation = findComponentAnnotation(constructorParameter, i, instanceClass);

            if (componentAnnotation != null) {
                constructorArguments[i] = componentAnnotation.value();
            } else {
                constructorArguments[i] = constructorParameter.getParameterizedType();
            }
        }
        return constructorArguments;
    }

    public static Constructor<?> getMainConstructor(Constructor<?>[] constructors) {
        Constructor<?> mainConstructor = null;

        for (Constructor<?> constructor : constructors) {
            if (constructor.isAnnotationPresent(Component.class)) {
                if (mainConstructor != null) {
                    throw new ManyMatchComponentAnnotationException(constructor.getDeclaringClass());
                }
                mainConstructor = constructor;
            }
        }

        return mainConstructor;
    }

    public static void fillIoCFromConfiguration(Class<?> configurationClass, IoC ioc) {
        var methods = getReturnedComponentMethods(configurationClass);

        for (var method : methods) {
            var returnClass = method.getReturnType();

            String componentName;
            var componentAnnotation = method.getAnnotation(Component.class);
            if (CommonValidator.isNullOrEmpty(componentAnnotation.value())) {
                componentName = method.getName();
            } else {
                componentName = componentAnnotation.value();
            }

            ioc.addSingleton(componentName,
                    returnClass,
                    () -> createComponentFromMethod(configurationClass, ioc, method));
        }
    }

    private static Object createComponentFromMethod(Class<?> configurationClass, IoC ioc, Method method) {
        Object configurationInstance = ioc.getComponent(configurationClass);
        try {
            var parameters = method.getParameters();
            var args = new Object[parameters.length];

            for (int i = 0; i < parameters.length; i++) {
                var parameter = parameters[i];
                if (parameter.isAnnotationPresent(Component.class)) {
                    var componentAnnotation = parameter.getAnnotation(Component.class);
                    args[i] = ioc.getComponent(componentAnnotation.value());
                } else {
                    args[i] = ioc.getComponent(parameter.getType());
                }
            }

            Object instance = method.invoke(configurationInstance, args);
            fillInstanceFromProperty(instance, method.getAnnotation(FromProperties.class));

            return instance;
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    private static void fillInstanceFromProperty(Object instance, FromProperties fromProperties) {
        if (fromProperties == null) {
            return;
        }

        FromPropertyHandler.fillFromProperties(instance, fromProperties);
    }

    private static List<Method> getReturnedComponentMethods(Class<?> configurationClass) {
        var configurationMethods = getComponentMethods(configurationClass);
        return configurationMethods.stream()
                .filter(m -> m.getReturnType() != null && !m.getReturnType().getName().equals("void"))
                .collect(Collectors.toList());
    }

    private static List<Method> getComponentMethods(Class<?> configurationClass) {
        var methods = configurationClass.getDeclaredMethods();
        List<Method> componentMethods = new ArrayList<>(methods.length);

        for (Method method : methods) {
            if (method.isAnnotationPresent(Component.class)) {
                componentMethods.add(method);
            }
        }

        return componentMethods;
    }

    private static Component findComponentAnnotation(Parameter constructorParameter,
            int ordinal,
            Class<?> instanceClass) {
        Component componentAnnotation = constructorParameter.getAnnotation(Component.class);
        if (componentAnnotation == null) {
            Collection<Field> instanceFields = ReflectionUtil.getAllFields(instanceClass);
            for (Field instanceField : instanceFields) {
                Component fieldAnnotation = instanceField.getAnnotation(Component.class);
                if (fieldAnnotation != null && fieldAnnotation.constructorParam() == ordinal) {
                    if (componentAnnotation != null) {
                        throw new ManyMatchConstructorFieldsException(instanceClass,
                                constructorParameter.getType(),
                                ordinal);
                    }

                    componentAnnotation = fieldAnnotation;
                }
            }
        }

        return componentAnnotation;
    }
}
