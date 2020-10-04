package ru.strict.ioc.annotations;

import ru.strict.utils.ReflectionUtil;

import javax.annotation.PostConstruct;

public class PostConstructHandler {

    public static void invokePostConstructMethod(Object instance) {
        ReflectionUtil.invokeMethodByAnnotation(instance, PostConstruct.class);
    }
}
