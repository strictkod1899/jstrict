package ru.strict.ioc.annotations;

import ru.strict.ioc.box.ComponentFactoryProcessor;
import ru.strict.ioc.exceptions.ManyMatchComponentAnnotationException;
import ru.strict.ioc.exceptions.ManyMatchConstructorFieldsException;
import ru.strict.utils.ReflectionUtil;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Parameter;
import java.util.Collection;

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
