package ru.strict.ioc.annotation;

import lombok.experimental.UtilityClass;
import ru.strict.ioc.IoC;
import ru.strict.ioc.exception.ManyMatchComponentAnnotationException;
import ru.strict.ioc.exception.ManyMatchConstructorFieldsException;
import ru.strict.util.ReflectionUtil;
import ru.strict.validate.CommonValidate;

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

    public static void fillIoCByConfiguration(Class<?> configurationClass, IoC ioc) {
        var methods = getReturnedComponentMethods(configurationClass);

        for (Method method : methods) {
            Class<?> returnClass = method.getReturnType();

            String componentName;
            Component componentAnnotation = method.getAnnotation(Component.class);
            if (CommonValidate.isEmptyOrNull(componentAnnotation.value())) {
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
            var parameterClasses = method.getParameterTypes();
            var args = new Object[parameterClasses.length];

            for (int i = 0; i < parameterClasses.length; i++) {
                var parameterClass = parameterClasses[i];
                var parameterInstance = ioc.getComponent(parameterClass);
                args[i] = parameterInstance;
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
