package ru.strict.util;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import ru.strict.validate.Validator;

import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

/**
 * Управление Reflection API
 */
public class ReflectionUtil {

    public static <INSTANCE> INSTANCE createInstance(Class<INSTANCE> instanceClass,
            Object... userConstructorParameters) {
        return createInstance(instanceClass, true, userConstructorParameters);
    }

    /**
     * Создать объект определенного класса, с передачей параметров, используя только доступные конструктуоры
     *
     * @param clazzInstance Класс объект, которого надо создать
     */
    public static <INSTANCE> INSTANCE createInstance(Class<INSTANCE> clazzInstance,
            boolean classAsArgument,
            Object... userConstructorParameters) {
        INSTANCE result = null;
        Constructor targetConstructor =
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

    public static <INSTANCE> INSTANCE createDeclaredInstance(Class<INSTANCE> clazzInstance,
            Object... userConstructorParameters) {
        return createDeclaredInstance(clazzInstance, true, userConstructorParameters);
    }

    /**
     * Создать объект определенного класса, с передачей параметров, используя не только публичные конструктуоры, но и
     * приватные
     *
     * @param clazzInstance Класс объект, которого надо создать
     */
    public static <INSTANCE> INSTANCE createDeclaredInstance(Class<INSTANCE> clazzInstance,
            boolean classAsArgument,
            Object... userConstructorParameters) {
        INSTANCE result = null;
        Constructor targetConstructor =
                findConstructor(clazzInstance.getDeclaredConstructors(), userConstructorParameters, classAsArgument);

        if (targetConstructor != null) {
            try {
                boolean isAccessible = targetConstructor.isAccessible();
                targetConstructor.setAccessible(true);
                result = (INSTANCE) targetConstructor.newInstance(userConstructorParameters);
                targetConstructor.setAccessible(isAccessible);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }

        return result;
    }

    public static Constructor findConstructor(Constructor[] constructors, Object[] userConstructorParameters) {
        return findConstructor(constructors, userConstructorParameters, true);
    }

    public static Constructor findConstructor(Constructor[] constructors,
            Object[] userConstructorParameters,
            boolean classAsArgument) {
        Constructor targetConstructor = null;

        // Если были переданы параметры конструктора
        if (userConstructorParameters != null && userConstructorParameters.length != 0) {
            for (Constructor constructor : constructors) {
                int constructorParametersCount = constructor.getParameterCount();

                if (constructorParametersCount == userConstructorParameters.length) {
                    Class[] constructorParameters = constructor.getParameterTypes();
                    for (int i = 0; i < constructorParametersCount; i++) {
                        Class constructorParameter = constructorParameters[i];
                        Class userConstructorParameter =
                                userConstructorParameters[i] instanceof Class && !classAsArgument
                                        ? (Class) userConstructorParameters[i]
                                        : userConstructorParameters[i].getClass();
                        if (constructorParameter != userConstructorParameter &&
                                !isPrimitive(userConstructorParameter, constructorParameter)) {
                            boolean checkBySuperClass = isSuperClass(constructorParameter, userConstructorParameter);
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
            for (Constructor constructor : constructors) {
                if (constructor.getParameterCount() == 0) {
                    targetConstructor = constructor;
                }
            }
        }

        return targetConstructor;
    }

    public static Class<?>[] getInterfaces(Class<?> clazz) {
        if (clazz == null) {
            return null;
        }

        List<Class<?>> interfaces = new ArrayList<>();
        Arrays.asList(clazz.getInterfaces()).forEach(interfaces::add);
        Class<?> superClass = clazz.getSuperclass();
        if (superClass != null) {
            Arrays.asList(getInterfaces(superClass)).forEach(interfaces::add);
        }

        return interfaces.toArray(new Class<?>[0]);
    }

    /**
     * Проверить, является ли класс экземпляром другого класса
     *
     * @param checkClass (Неизвестный класс) Проверяемый класс
     * @param startClass (Требуемый класс) Класс, относительно которого проверяем принадлежность к экземпляру
     * @return
     */
    public static boolean isInstanceOf(Class checkClass, Class startClass) {
        boolean result = false;
        if (checkClass == startClass) {
            result = true;
        } else {
            result = isSuperClass(checkClass, startClass);
        }

        return result;
    }

    public static boolean isSuperClass(Class checkClass, Class startClass) {
        boolean result = false;

        Class superClass = startClass.getSuperclass();

        if (superClass != null) {
            if (superClass != Object.class) {
                if (checkClass == superClass) {
                    result = true;
                } else {
                    result = isSuperClass(checkClass, superClass);

                    if (!result) {
                        result = isInterface(checkClass, superClass);
                    }
                }
            }

            if (superClass == Object.class || !result) {
                result = isInterface(checkClass, startClass);
            }
        } else {
            result = isInterface(checkClass, startClass);
        }

        return result;
    }

    public static boolean isInterface(Class checkClass, Class startClass) {
        boolean result = false;

        Class[] interfaces = startClass.getInterfaces();
        for (Class interfaceItem : interfaces) {
            if (result) {
                return true;
            }

            if (interfaceItem != Object.class) {
                if (checkClass == interfaceItem) {
                    result = true;
                } else {
                    result = isInterface(checkClass, interfaceItem);
                }
            } else {
                result = false;
            }
        }
        return result;
    }

    public static boolean isPrimitive(Class checkClass, Class startClass) {
        boolean result = false;
        if (checkClass.getName().equals("java.lang.Byte") && startClass.getName().equals("byte")) {
            result = true;
        } else if (checkClass.getName().equals("java.lang.Short") && startClass.getName().equals("short")) {
            result = true;
        } else if (checkClass.getName().equals("java.lang.Integer") && startClass.getName().equals("int")) {
            result = true;
        } else if (checkClass.getName().equals("java.lang.Long") && startClass.getName().equals("long")) {
            result = true;
        } else if (checkClass.getName().equals("java.lang.Float") && startClass.getName().equals("float")) {
            result = true;
        } else if (checkClass.getName().equals("java.lang.Double") && startClass.getName().equals("double")) {
            result = true;
        } else if (checkClass.getName().equals("java.lang.Character") && startClass.getName().equals("char")) {
            result = true;
        } else if (checkClass.getName().equals("java.lang.Boolean") && startClass.getName().equals("boolean")) {
            result = true;
        }
        return result;
    }

    /**
     * Вызвать метод, который помечен указанной аннотацией. Если будет найдено несколько методов, то будет выброшена
     * ошибка
     *
     * @param source Объект, у которого будет вызван метод
     * @param annotationClass Класс аннотации
     * @param <A> Тип аннотации
     */
    public static <A extends Annotation> Object invokeMethodByAnnotation(Object source,
            Class<A> annotationClass,
            Object... args) {
        Validator.isNull(source, "source");
        Validator.isNull(annotationClass, "annotationClass");

        int argsCount = Optional.ofNullable(args)
                .map(a -> a.length)
                .orElse(0);

        Class<?> sourceClass = source.getClass();
        Method[] methods = sourceClass.getDeclaredMethods();

        Method foundedMethod = null;
        for (Method method : methods) {
            A annotation = method.getAnnotation(annotationClass);
            if (annotation != null) {
                if (foundedMethod != null) {
                    throw new IllegalArgumentException(String.format("By annotation [%s] founded several methods",
                            annotationClass));
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
    public static <A extends Annotation> void invokeVoidMethodsByAnnotation(Object source,
            Class<A> annotationClass,
            Object... args) {
        Validator.isNull(source, "source");
        Validator.isNull(annotationClass, "annotationClass");

        int argsCount = Optional.ofNullable(args)
                .map(a -> a.length)
                .orElse(0);

        Class<?> sourceClass = source.getClass();
        Method[] methods = sourceClass.getDeclaredMethods();

        List<Method> foundedMethods = new LinkedList<>();
        for (Method method : methods) {
            A annotation = method.getAnnotation(annotationClass);
            if (annotation != null) {
                foundedMethods.add(method);
            }
        }

        for (Method foundedMethod : foundedMethods) {
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
    public static <T> T createInterfaceProxy(Class<?> instanceClass, InvocationHandler handler) {
        Class<?>[] instanceInterfaces = ReflectionUtil.getInterfaces(instanceClass);

        return (T) Proxy.newProxyInstance(
                instanceClass.getClassLoader(),
                instanceInterfaces,
                handler);
    }

    /**
     * Создать прокси, с помощью библиотеки cglib
     */
    public static <T> T createCglibProxy(Class<?> instanceClass,
            MethodInterceptor handler,
            Constructor<?> instanceConstructor,
            Object... constructorArgs) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(instanceClass);
        enhancer.setCallbackType(handler.getClass());
        enhancer.setCallback(handler);
        enhancer.setUseCache(true);
        enhancer.setAttemptLoad(true);

        Class<?>[] argumentTypes = instanceConstructor.getParameterTypes();
        return (T) enhancer.create(argumentTypes, constructorArgs);
    }

    public static Collection<Field> getAllFields(Class instanceClass) {
        List<Field> fields = new ArrayList<>();

        Field[] classFields = instanceClass.getDeclaredFields();
        fields.addAll(Arrays.asList(classFields));
        Class superClass = instanceClass.getSuperclass();
        if (superClass != null && superClass != Object.class) {
            fields.addAll(getAllFields(superClass));
        }

        return fields;
    }

    public static Field getField(Class objectClass, String fieldName) {
        Collection<Field> fields = getAllFields(objectClass);

        return fields.stream()
                .filter(field -> field.getName().equals(fieldName))
                .findFirst()
                .orElse(null);
    }

    public static <T> T getFieldValue(Object object, String fieldName) {
        try {
            Field targetField = getField(object.getClass(), fieldName);

            T result = null;
            if (targetField != null) {
                boolean isAccessible = targetField.isAccessible();
                targetField.setAccessible(true);

                result = (T) targetField.get(object);

                targetField.setAccessible(isAccessible);
            }

            return result;
        } catch (IllegalAccessException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static void setField(Object object, String fieldName, Object field) {
        try {
            Field targetField = getField(object.getClass(), fieldName);

            if (targetField == null) {
                throw new NoSuchFieldException(String.format("No such field [%s] into [%s]", fieldName, object.getClass()));
            } else {
                boolean isAccessible = targetField.isAccessible();
                targetField.setAccessible(true);

                targetField.set(object, field);

                targetField.setAccessible(isAccessible);
            }
        } catch (NoSuchFieldException | IllegalAccessException ex) {
            throw new RuntimeException(ex);
        }
    }
}
