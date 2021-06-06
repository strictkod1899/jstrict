package ru.strict.ioc.annotations;

import ru.strict.ioc.exceptions.ManyMatchComponentAnnotationException;
import ru.strict.ioc.exceptions.ManyMatchConstructorsException;

import java.lang.reflect.Constructor;
import java.lang.reflect.Parameter;

public class ComponentHandler {

    public static Object[] getConstructorArguments(Constructor constructor) {
        Object[] constructorArguments = new Object[constructor.getParameterCount()];
        Parameter[] parameters = constructor.getParameters();
        for (int i = 0; i < parameters.length; i++) {
            Component componentAnnotation = parameters[i].getAnnotation(Component.class);
            if (componentAnnotation != null) {
                constructorArguments[i] = componentAnnotation.value();
            } else {
                constructorArguments[i] = parameters[i].getType();
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
