package ru.strict.ioc.annotations;

import net.sf.cglib.proxy.Callback;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import ru.strict.ioc.IoC;
import ru.strict.logging.ILogger;
import ru.strict.logging.LogLevel;
import ru.strict.utils.ReflectionUtil;
import ru.strict.validate.Validator;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.*;

public class LoggingHandler implements InvocationHandler, MethodInterceptor, Callback {

    private Object instance;
    private Logging classAnnotation;
    private Class<? extends ILogger>[] defaultLoggersClasses;

    private IoC ioc;

    public LoggingHandler(Object instance,
            Logging classAnnotation,
            Class<? extends ILogger>[] defaultLoggersClasses,
            IoC ioc) {
        Validator.isNull(instance, "instance");
        Validator.isNull(classAnnotation, "classAnnotation");
        Validator.isNull(defaultLoggersClasses, "defaultLoggersClasses");
        Validator.isNull(ioc, "ioc");

        this.instance = instance;
        this.classAnnotation = classAnnotation;
        this.defaultLoggersClasses = defaultLoggersClasses;
        this.ioc = ioc;
    }

    public static <T> T getLoggedInstance(T instance,
            IoC ioc,
            Class<? extends ILogger>[] defaultLoggersClasses) {
        if (instance == null) {
            return null;
        }

        Class clazz = instance.getClass();
        Annotation annotation = clazz.getAnnotation(Logging.class);
        boolean useLogging = annotation != null;
        if (!useLogging) {
            // Если класс не помечен аннотацией, то проверим, есть ли помеченные методы
            Method[] methods = clazz.getMethods();
            for (Method method : methods) {
                if (method.isAnnotationPresent(Logging.class)) {
                    useLogging = true;
                    break;
                }
            }
        }

        if (useLogging) {
            // Если класс или какой-либо метод помечен аннотацией, то создадим прокси объект
            LoggingHandler handler = new LoggingHandler(instance, (Logging) annotation, defaultLoggersClasses, ioc);

            Constructor<?> constructor = IoC.findConstructor(instance.getClass());
            Object[] arguments = ioc.createConstructorArguments(constructor);
            instance = ReflectionUtil.createCglibProxy(instance.getClass(), handler, constructor, arguments);
        }
        return instance;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        boolean methodAccessible = method.isAccessible();
        method.setAccessible(true);
        Object result = null;

        Collection<Class<? extends ILogger>> classLoggers = new HashSet<>();
        Collection<Class<? extends ILogger>> methodLoggers = new HashSet<>();

        // key - логгер, value - уровень логирования
        Map<ILogger, LogLevel> loggersMap = new HashMap<>();

        classLoggers.addAll(Arrays.asList(defaultLoggersClasses));
        if (classAnnotation != null) {
            classLoggers.addAll(Arrays.asList(classAnnotation.value()));
            Collection<ILogger> loggers =
                    LoggerHandler.createLoggers(classLoggers.toArray(new Class[0]), instance.getClass(), ioc);
            LogLevel classLogLevel = classAnnotation.logLevel();
            loggers.forEach(logger -> loggersMap.put(logger, classLogLevel));
        }


        Logging methodAnnotation = null;
        try {
            instance.getClass()
                    .getDeclaredMethod(method.getName(), method.getParameterTypes())
                    .getAnnotation(Logging.class);
        } catch (NoSuchMethodException ex) {
            // ignore
        }

        if (classAnnotation != null || methodAnnotation != null) {
            if (methodAnnotation != null) {
                Class<? extends ILogger>[] methodLoggersClasses = methodAnnotation.value();
                if (methodAnnotation.onlyThis()) {
                    classLoggers.clear();
                }
                methodLoggers.addAll(Arrays.asList(methodLoggersClasses));
                methodLoggers.addAll(Arrays.asList(defaultLoggersClasses));

                Collection<ILogger> methodLoggersInstances = LoggerHandler.createLoggers(
                        methodLoggers.toArray(new Class[0]),
                        instance.getClass(),
                        ioc);
                LogLevel methodLogLevel = methodAnnotation.logLevel();
                methodLoggersInstances.forEach(logger -> loggersMap.put(logger, methodLogLevel));
            }

            try {
                loggersMap.forEach((logger, level) -> logger.log(level,
                        "Run method [%s] in class [%s]",
                        method.getName(),
                        instance.getClass().getName()));
                result = method.invoke(instance, args);
                loggersMap.forEach((logger, level) -> logger.log(level,
                        "Finished method [%s] in class [%s]",
                        method.getName(),
                        instance.getClass()));
            } catch (Exception ex) {
                loggersMap.forEach((logger, level) -> logger.error(String.format(
                        "An error occurred in method [%s] in class [%s]",
                        method.getName(),
                        instance.getClass().getName()),
                        ex
                ));
                method.setAccessible(methodAccessible);
                throw ex.getCause();
            }
        } else {
            result = method.invoke(instance, args);
        }
        method.setAccessible(methodAccessible);

        return result;
    }

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        return invoke(obj, method, args);
    }
}
