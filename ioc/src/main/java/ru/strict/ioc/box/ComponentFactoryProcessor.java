package ru.strict.ioc.box;

import lombok.experimental.UtilityClass;
import ru.strict.ioc.IoC;
import ru.strict.utils.ReflectionUtil;

import java.lang.reflect.Parameter;
import java.lang.reflect.ParameterizedType;

@UtilityClass
public class ComponentFactoryProcessor {

    public static Object getConstructorArgument(Parameter constructorParameter) {
        if (ReflectionUtil.isInstanceOf(ComponentFactory.class, constructorParameter.getType())) {
            return constructorParameter.getParameterizedType();
        } else {
            return constructorParameter.getType();
        }
    }

    public static <T> T getComponent(ParameterizedType componentType, IoC ioc) {
        T component = null;
        Class<?> componentClass = (Class<?>) componentType.getRawType();
        if (ReflectionUtil.isInstanceOf(ComponentFactory.class, componentClass)) {
            componentType.getActualTypeArguments()[0].getTypeName();
        }

        return component;
    }
}
