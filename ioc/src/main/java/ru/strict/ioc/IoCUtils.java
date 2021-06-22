package ru.strict.ioc;

import ru.strict.ioc.annotations.ComponentHandler;
import ru.strict.ioc.exceptions.ManyMatchConstructorsException;

import java.lang.reflect.Constructor;

public final class IoCUtils {

    private IoCUtils() {}

    public static <T> Constructor<T> findConstructor(Class<T> instanceClass) {
        return findConstructor(instanceClass, false);
    }

    public static <T> Constructor<T> findConstructor(Class<T> instanceClass, boolean onlyPublic) {
        Constructor[] constructors =
                onlyPublic ? instanceClass.getConstructors() : instanceClass.getDeclaredConstructors();
        Constructor mainConstructor = ComponentHandler.getMainConstructor(constructors);
        if (mainConstructor == null) {
            if (constructors.length == 1) {
                mainConstructor = constructors[0];
            } else {
                throw new ManyMatchConstructorsException(instanceClass);
            }
        }

        return mainConstructor;
    }

    /**
     * Создать объекты для аргументов указанного конструктора
     */
    public static Object[] createConstructorArguments(Constructor<?> constructor, IoC ioc) {
        Class<?>[] argumentTypes = constructor.getParameterTypes();
        Object[] arguments = new Object[argumentTypes.length];
        for (int i = 0; i < arguments.length; i++) {
            arguments[i] = ioc.getComponent(argumentTypes[i]);
        }

        return arguments;
    }
}
