package ru.strict.ioc.box;

import lombok.experimental.UtilityClass;
import ru.strict.ioc.IoC;
import ru.strict.utils.ReflectionUtil;

import java.lang.reflect.ParameterizedType;

@UtilityClass
public class ComponentFactoryProcessor {

    public static ComponentFactory<?> getComponent(ParameterizedType componentType, IoC ioc) {
        ComponentFactory<?> component = null;
        Class<?> componentClass = (Class<?>) componentType.getRawType();
        if (ReflectionUtil.isInstanceOf(ComponentFactory.class, componentClass)) {
            Class<?> genericClass = (Class<?>) componentType.getActualTypeArguments()[0];
            component = new ComponentFactory<>(genericClass, ioc);
        }

        return component;
    }
}
