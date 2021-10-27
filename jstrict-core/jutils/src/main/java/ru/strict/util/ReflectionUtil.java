package ru.strict.util;

import lombok.experimental.UtilityClass;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import ru.strict.exception.ValidateException;
import ru.strict.validate.Validator;

import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@UtilityClass
public class ReflectionUtil {

    public <INSTANCE> INSTANCE createInstance(Class<INSTANCE> instanceClass,
            Object... userConstructorParameters) {
        return createInstance(instanceClass, true, userConstructorParameters);
    }

    /**
     * Создать объект определенного класса, с передачей параметров, используя только доступные конструктуоры
     *
     * @param clazzInstance Класс объект, которого надо создать
     */
    public <INSTANCE> INSTANCE createInstance(Class<INSTANCE> clazzInstance,
            boolean classAsArgument,
            Object... userConstructorParameters) {
        INSTANCE result = null;
        var targetConstructor =
                findConstructor(clazzInstance.getConstructors(), userConstructorParameters, classAsArgument);

        if (targetConstructor != null) {
            try {
                result = (INSTANCE) targetConstructor.newInstance(userConstructorParameters);
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException ex) {
                throw new RuntimeException(ex);
            }
        }

        return result;
    }

    public <INSTANCE> INSTANCE createDeclaredInstance(Class<INSTANCE> clazzInstance,
            Object... userConstructorParameters) {
        return createDeclaredInstance(clazzInstance, true, userConstructorParameters);
    }

    /**
     * Создать объект определенного класса, с передачей параметров, используя не только публичные конструктуоры, но и
     * приватные
     *
     * @param clazzInstance Класс объект, которого надо создать
     */
    public <INSTANCE> INSTANCE createDeclaredInstance(Class<INSTANCE> clazzInstance,
            boolean classAsArgument,
            Object... userConstructorParameters) {
        INSTANCE result = null;
        var targetConstructor =
                findConstructor(clazzInstance.getDeclaredConstructors(), userConstructorParameters, classAsArgument);

        if (targetConstructor != null) {
            try {
                var isAccessible = targetConstructor.isAccessible();
                targetConstructor.setAccessible(true);
                result = (INSTANCE) targetConstructor.newInstance(userConstructorParameters);
                targetConstructor.setAccessible(isAccessible);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }

        return result;
    }

    public Constructor<?> findConstructor(Constructor<?>[] constructors, Object[] userConstructorParameters) {
        return findConstructor(constructors, userConstructorParameters, true);
    }

    public Constructor<?> findConstructor(Constructor<?>[] constructors,
            Object[] userConstructorParameters,
            boolean classAsArgument) {
        Constructor<?> targetConstructor = null;

        // Если были переданы параметры конструктора
        if (userConstructorParameters != null && userConstructorParameters.length != 0) {
            for (var constructor : constructors) {
                int constructorParametersCount = constructor.getParameterCount();

                if (constructorParametersCount == userConstructorParameters.length) {
                    var constructorParameters = constructor.getParameterTypes();
                    for (int i = 0; i < constructorParametersCount; i++) {
                        var constructorParameter = constructorParameters[i];
                        var userConstructorParameter =
                                userConstructorParameters[i] instanceof Class && !classAsArgument
                                        ? (Class<?>) userConstructorParameters[i]
                                        : userConstructorParameters[i].getClass();
                        if (constructorParameter != userConstructorParameter &&
                                !isPrimitive(userConstructorParameter, constructorParameter)) {
                            var checkBySuperClass = isSuperClass(constructorParameter, userConstructorParameter);
                            if (!checkBySuperClass) {
                                break;
                            }
                        }

                        if (i == constructorParametersCount - 1) {
                            targetConstructor = constructor;
                        }
                    }
                }
            }
        } else {
            // Если требуется вызывать пустой конструктор
            for (var constructor : constructors) {
                if (constructor.getParameterCount() == 0) {
                    targetConstructor = constructor;
                }
            }
        }

        return targetConstructor;
    }

    public List<Class<?>> getInterfaces(Class<?> clazz) {
        if (clazz == null) {
            return null;
        }

        var interfaces = new ArrayList<Class<?>>(Arrays.asList(clazz.getInterfaces()));
        var superClass = clazz.getSuperclass();
        if (superClass != null) {
            interfaces.addAll(getInterfaces(superClass));
        }

        return Collections.unmodifiableList(interfaces);
    }

    /**
     * Проверить, является ли {@param expectedInstanceClass} экземпляром (или родителем) для класса {@param checkedClass}
     */
    public boolean isInstanceOf(Class<?> expectedInstanceClass, Class<?> checkedClass) {
        return expectedInstanceClass == checkedClass || isSuperClass(expectedInstanceClass, checkedClass);
    }

    /**
     * Определить, является ли {@param expectedSuperClass} базовым классом или интерфейсом для {@param checkedClass}
     */
    public boolean isSuperClass(Class<?> expectedSuperClass, Class<?> checkedClass) {
        var result = false;

        var superClass = checkedClass.getSuperclass();

        if (superClass != null) {
            if (superClass != Object.class) {
                if (expectedSuperClass == superClass) {
                    result = true;
                } else {
                    result = isSuperClass(expectedSuperClass, superClass);

                    if (!result) {
                        result = isInterface(expectedSuperClass, superClass);
                    }
                }
            }

            if (superClass == Object.class || !result) {
                result = isInterface(expectedSuperClass, checkedClass);
            }
        } else {
            result = isInterface(expectedSuperClass, checkedClass);
        }

        return result;
    }

    /**
     * Определить, является ли {@param expectedInterfaceClass} базовым классом или интерфейсом для {@param checkedClass}
     */
    public boolean isInterface(Class<?> expectedInterfaceClass, Class<?> checkedClass) {
        boolean result = false;

        var interfaces = checkedClass.getInterfaces();
        for (var interfaceItem : interfaces) {
            if (result) {
                return true;
            }

            if (interfaceItem != Object.class) {
                if (expectedInterfaceClass == interfaceItem) {
                    result = true;
                } else {
                    result = isInterface(expectedInterfaceClass, interfaceItem);
                }
            } else {
                result = false;
            }
        }
        return result;
    }

    public boolean isPrimitive(Class<?> expectedClass, Class<?> checkedClass) {
        if (expectedClass.getName().equals("java.lang.Byte") && checkedClass.getName().equals("byte")) {
            return true;
        } else if (expectedClass.getName().equals("java.lang.Short") && checkedClass.getName().equals("short")) {
            return true;
        } else if (expectedClass.getName().equals("java.lang.Integer") && checkedClass.getName().equals("int")) {
            return true;
        } else if (expectedClass.getName().equals("java.lang.Long") && checkedClass.getName().equals("long")) {
            return true;
        } else if (expectedClass.getName().equals("java.lang.Float") && checkedClass.getName().equals("float")) {
            return true;
        } else if (expectedClass.getName().equals("java.lang.Double") && checkedClass.getName().equals("double")) {
            return true;
        } else if (expectedClass.getName().equals("java.lang.Character") && checkedClass.getName().equals("char")) {
            return true;
        } else {
            return expectedClass.getName().equals("java.lang.Boolean") && checkedClass.getName().equals("boolean");
        }
    }

    /**
     * Вызвать метод, который помечен указанной аннотацией. Если будет найдено несколько методов, то будет выброшена
     * ошибка
     *
     * @param source Объект, у которого будет вызван метод
     * @param annotationClass Класс аннотации
     * @param <A> Тип аннотации
     */
    public <A extends Annotation> Object invokeMethodByAnnotation(Object source,
            Class<A> annotationClass,
            Object... args) {
        Validator.isNull(source, "source");
        Validator.isNull(annotationClass, "annotationClass");

        var argsCount = Optional.ofNullable(args)
                .map(a -> a.length)
                .orElse(0);

        var sourceClass = source.getClass();
        var methods = sourceClass.getDeclaredMethods();

        Method foundedMethod = null;
        for (var method : methods) {
            A annotation = method.getAnnotation(annotationClass);
            if (annotation != null) {
                if (foundedMethod != null) {
                    throw new ValidateException("By annotation [%s] founded several methods", annotationClass);
                }
                foundedMethod = method;
            }
        }

        if (foundedMethod != null) {
            try {
                if (foundedMethod.getParameterCount() == argsCount) {
                    return foundedMethod.invoke(source, args);
                } else {
                    return null;
                }
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        } else {
            return null;
        }
    }

    /**
     * Вызвать методы, которые помечены указанной аннотацией
     *
     * @param source Объект, у которого будет вызван метод
     * @param annotationClass Класс аннотации
     * @param <A> Тип аннотации
     */
    public <A extends Annotation> void invokeMethodsByAnnotation(Object source,
            Class<A> annotationClass,
            Object... args) {
        Validator.isNull(source, "source");
        Validator.isNull(annotationClass, "annotationClass");

        int argsCount = Optional.ofNullable(args)
                .map(a -> a.length)
                .orElse(0);

        var sourceClass = source.getClass();
        var methods = sourceClass.getDeclaredMethods();

        var foundedMethods = new LinkedList<Method>();
        for (var method : methods) {
            A annotation = method.getAnnotation(annotationClass);
            if (annotation != null) {
                foundedMethods.add(method);
            }
        }

        for (var foundedMethod : foundedMethods) {
            try {
                if (foundedMethod.getParameterCount() == argsCount) {
                    foundedMethod.invoke(source, args);
                }
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    /**
     * Создать прокси, если есть интерфейсы (используется обычный java proxy)
     */
    public <T> T createInterfaceProxy(Class<?> instanceClass, InvocationHandler handler) {
        var instanceInterfaces = ReflectionUtil.getInterfaces(instanceClass);

        return (T) Proxy.newProxyInstance(
                instanceClass.getClassLoader(),
                instanceInterfaces.toArray(Class<?>[]::new),
                handler);
    }

    /**
     * Создать прокси, с помощью библиотеки cglib
     */
    public <T> T createCglibProxy(Class<?> instanceClass,
            MethodInterceptor handler,
            Constructor<?> instanceConstructor,
            Object... constructorArgs) {
        var enhancer = new Enhancer();
        enhancer.setSuperclass(instanceClass);
        enhancer.setCallbackType(handler.getClass());
        enhancer.setCallback(handler);
        enhancer.setUseCache(true);
        enhancer.setAttemptLoad(true);

        var argumentTypes = instanceConstructor.getParameterTypes();
        return (T) enhancer.create(argumentTypes, constructorArgs);
    }

    /**
     * Получить все поля класса, включая приватные
     */
    public Collection<Field> getAllFields(Class<?> instanceClass) {
        var fields = new ArrayList<Field>();

        var classFields = instanceClass.getDeclaredFields();
        fields.addAll(Arrays.asList(classFields));
        var superClass = instanceClass.getSuperclass();
        if (superClass != null && superClass != Object.class) {
            fields.addAll(getAllFields(superClass));
        }

        return fields;
    }

    /**
     * Получить указанное поле класса, даже, если оно приватное
     */
    public Field getField(Class<?> objectClass, String fieldName) {
        var fields = getAllFields(objectClass);

        return fields.stream()
                .filter(field -> field.getName().equals(fieldName))
                .findFirst()
                .orElse(null);
    }

    /**
     * Получить значение указанного поля класса, даже, если оно приватное
     */
    public <T> T getFieldValue(Object object, String fieldName) {
        try {
            var targetField = getField(object.getClass(), fieldName);

            T result = null;
            if (targetField != null) {
                var isAccessible = targetField.isAccessible();
                targetField.setAccessible(true);

                result = (T) targetField.get(object);

                targetField.setAccessible(isAccessible);
            }

            return result;
        } catch (IllegalAccessException ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     * Установить значение в указанное поле класса, даже, если оно приватное
     */
    public void setField(Object object, String fieldName, Object field) {
        try {
            var targetField = getField(object.getClass(), fieldName);

            if (targetField == null) {
                throw new NoSuchFieldException(String.format("No such field [%s] into [%s]", fieldName, object.getClass()));
            } else {
                var isAccessible = targetField.isAccessible();
                targetField.setAccessible(true);

                targetField.set(object, field);

                targetField.setAccessible(isAccessible);
            }
        } catch (NoSuchFieldException | IllegalAccessException ex) {
            throw new RuntimeException(ex);
        }
    }
}
