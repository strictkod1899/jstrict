package ru.strict.ioc.annotations;

import ru.strict.ioc.ConstructorArgument;
import ru.strict.ioc.exceptions.ManyMatchComponentAnnotationException;
import ru.strict.utils.ReflectionUtil;

import java.lang.reflect.Constructor;
import java.lang.reflect.Parameter;
import java.util.Collection;

public class ComponentHandler {

    public static ConstructorArgument[] getConstructorArguments(Constructor constructor) {
        ConstructorArgument[] constructorArguments = new ConstructorArgument[constructor.getParameterCount()];
        Parameter[] parameters = constructor.getParameters();
        for (int i = 0; i < parameters.length; i++) {
            Component componentAnnotation = parameters[i].getAnnotation(Component.class);
            if (componentAnnotation != null) {
                constructorArguments[i] = new ConstructorArgument(componentAnnotation.value());
            } else {
                Class<?> argumentClass = parameters[i].getType();
                if (ReflectionUtil.isInstanceOf(Collection.class, argumentClass)) {
                    Class<?> genericClass = ReflectionUtil.getGenericType(argumentClass);
                    constructorArguments[i] = new ConstructorArgument(argumentClass, genericClass);
                } else {
                    constructorArguments[i] = new ConstructorArgument(argumentClass);
                }
            }
        }
        return constructorArguments;
    }

    public static Constructor getMainConstructor(Constructor[] constructors) {
        Constructor mainConstructor = null;

        for (Constructor constructor : constructors) {
            if (constructor.isAnnotationPresent(Component.class)) {
                if (mainConstructor != null) {
                    throw new ManyMatchComponentAnnotationException(constructor.getDeclaringClass());
                }
                mainConstructor = constructor;
            }
        }

        return mainConstructor;
    }
}
