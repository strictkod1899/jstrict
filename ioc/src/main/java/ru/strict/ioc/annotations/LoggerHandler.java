package ru.strict.ioc.annotations;

import ru.strict.ioc.IoC;
import ru.strict.ioc.exceptions.ConstructorNotFoundException;
import ru.strict.ioc.exceptions.ManyMatchConstructorsException;
import ru.strict.logging.ILogger;
import ru.strict.logging.LoggerBase;
import ru.strict.utils.ReflectionUtil;
import ru.strict.validate.Validator;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class LoggerHandler {

    public static void injectLogger(Object instance,
            IoC ioc,
            Class<? extends ILogger> defaultLoggerClass) {
        try {
            Collection<Field> fields = ReflectionUtil.getAllFields(instance.getClass());
            for (Field field : fields) {
                Logger annotation = field.getAnnotation(Logger.class);
                if (annotation != null) {
                    Class<? extends ILogger> loggerClass = annotation.value() != LoggerBase.class ?
                            annotation.value() :
                            defaultLoggerClass;

                    Validator.isNull(loggerClass, "loggerClass");
                    ILogger logger = createLogger(loggerClass, instance.getClass(), ioc);
                    boolean isAccessible = field.isAccessible();
                    field.setAccessible(true);
                    field.set(instance, logger);
                    field.setAccessible(isAccessible);
                }
            }
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    private static ILogger createLogger(Class<? extends ILogger> loggerClass, Class<?> objectClass, IoC ioc) {
        List<ILogger> loggers = createLoggers(new Class[]{ loggerClass }, objectClass, ioc);
        if (loggers.isEmpty()) {
            return null;
        } else {
            return loggers.get(0);
        }
    }

    static List<ILogger> createLoggers(Class<? extends ILogger>[] loggersClasses, Class<?> objectClass, IoC ioc) {
        List<ILogger> loggers = new ArrayList<>(loggersClasses.length);
        for (Class<? extends ILogger> loggerClass : loggersClasses) {
            Constructor<?>[] constructors = loggerClass.getConstructors();
            if (constructors.length == 0) {
                throw new NullPointerException(String.format("Constructor for create logger not found [%s]",
                        loggersClasses));
            } else if (constructors.length > 1) {
                throw new ManyMatchConstructorsException(loggerClass);
            }
            Constructor constructor = constructors[0];
            Class<?>[] parameters = constructor.getParameterTypes();
            Object[] arguments = new Object[parameters.length];

            if (parameters.length == 1) {
                arguments[0] = objectClass;
            } else {
                // Первый аргумент в конструкторе логгера, должен быть класс логируемого объекта
                arguments[0] = objectClass;
                for (int i = 1; i < parameters.length; ++i) {
                    arguments[i] = ioc.getComponent(parameters[i]);
                }
            }

            ILogger logger = ReflectionUtil.createInstance(loggerClass, arguments);
            if (logger == null) {
                throw new ConstructorNotFoundException(loggerClass, Class.class);
            }

            loggers.add(logger);
        }

        return loggers;
    }
}
