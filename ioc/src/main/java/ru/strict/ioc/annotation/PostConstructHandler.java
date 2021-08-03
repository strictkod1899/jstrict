package ru.strict.ioc.annotation;

import lombok.experimental.UtilityClass;
import ru.strict.utils.ReflectionUtil;

import javax.annotation.PostConstruct;

@UtilityClass
public class PostConstructHandler {

    public static void invokePostConstructMethod(Object instance) {
        ReflectionUtil.invokeMethodByAnnotation(instance, PostConstruct.class);
    }
}
