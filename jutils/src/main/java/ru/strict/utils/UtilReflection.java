package ru.strict.utils;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * Управление Reflection API
 */
public class UtilReflection {

    /**
     * Создать объект определенного класса, с передачей параметров, используя только доступные конструктуоры
     * @param clazzInstance Класс объект, которого надо создать
     */
    public static <INSTANCE> INSTANCE createInstance(Class<INSTANCE> clazzInstance, Object...userConstructorParameters) {
        INSTANCE result = null;
        Constructor targetConstructor = findConstructor(clazzInstance.getConstructors(), userConstructorParameters);

        if(targetConstructor!=null) {
            try {
                result = (INSTANCE) targetConstructor.newInstance(userConstructorParameters);
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException ex) {
                throw new RuntimeException(ex);
            }
        }

        return result;
    }

    /**
     * Создать объект определенного класса, с передачей параметров, используя не только публичные конструктуоры, но и приватные
     * @param clazzInstance Класс объект, которого надо создать
     */
    public static <INSTANCE> INSTANCE createDeclaredInstance(Class<INSTANCE> clazzInstance, Object...userConstructorParameters) {
        INSTANCE result = null;
        Constructor targetConstructor = findConstructor(clazzInstance.getDeclaredConstructors(), userConstructorParameters);

        if(targetConstructor!=null) {
            try {
                boolean isAccessible = targetConstructor.isAccessible();
                if(!isAccessible) {
                    targetConstructor.setAccessible(true);
                }
                result = (INSTANCE) targetConstructor.newInstance(userConstructorParameters);
                if(!isAccessible) {
                    targetConstructor.setAccessible(false);
                }
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException ex) {
                throw new RuntimeException(ex);
            }
        }

        return result;
    }

    private static Constructor findConstructor(Constructor[] constructors, Object[] userConstructorParameters){
        Constructor targetConstructor = null;

        // Если были переданы параметры конструктора
        if (userConstructorParameters != null && userConstructorParameters.length != 0) {
            for (Constructor constructor : constructors) {
                int constructorParametersCount = constructor.getParameterCount();

                if (constructorParametersCount == userConstructorParameters.length) {
                    Class[] constructorParameters = constructor.getParameterTypes();
                    for (int i = 0; i < constructorParametersCount; i++) {
                        Class constructorParameter = constructorParameters[i];
                        if (constructorParameter != userConstructorParameters[i].getClass() && !isPrimitive(userConstructorParameters[i].getClass(), constructorParameter)) {
                            boolean checkBySuperClass = isSuperClass(constructorParameter, userConstructorParameters[i].getClass());
                            if(!checkBySuperClass) {
                                break;
                            }
                        }

                        if (i == constructorParametersCount - 1)
                            targetConstructor = constructor;
                    }
                }
            }
        } else {
            // Если требуется вызывать пустой конструктор
            for (Constructor constructor : constructors) {
                if (constructor.getParameterCount() == 0)
                    targetConstructor = constructor;
            }
        }

        return targetConstructor;
    }

    /**
     * Проверить, является ли класс экземпляром другого класса
     * @param checkClass Проверяемый класс
     * @param startClass Класс, относительно которого проверяем принадлежность к экземпляру
     * @return
     */
    public static boolean isInstanceOf(Class checkClass, Class startClass){
        boolean result = false;
        if(checkClass == startClass){
            result = true;
        }else{
            result = isSuperClass(checkClass, startClass);
        }

        return result;
    }

    public static boolean isSuperClass(Class checkClass, Class startClass){
        boolean result = false;

        Class superClass = startClass.getSuperclass();

        if(superClass != null) {
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
        }

        return result;
    }

    public static boolean isInterface(Class checkClass, Class startClass){
        boolean result = false;

        Class[] interfaces = startClass.getInterfaces();
        for(Class interfaceItem : interfaces){
            if (interfaceItem != Object.class) {
                if (checkClass == interfaceItem) {
                    result = true;
                }else{
                    result = isInterface(checkClass, interfaceItem);
                }
            }else {
                result = false;
            }
        }
        return result;
    }

    public static boolean isPrimitive(Class checkClass, Class startClass){
        boolean result = false;
        if(checkClass.getName().equals("java.lang.Byte") && startClass.getName().equals("byte")){
            result = true;
        } else if(checkClass.getName().equals("java.lang.Short") && startClass.getName().equals("short")){
            result = true;
        } else if(checkClass.getName().equals("java.lang.Integer") && startClass.getName().equals("int")){
            result = true;
        } else if(checkClass.getName().equals("java.lang.Long") && startClass.getName().equals("long")){
            result = true;
        } else if(checkClass.getName().equals("java.lang.Float") && startClass.getName().equals("float")){
            result = true;
        } else if(checkClass.getName().equals("java.lang.Double") && startClass.getName().equals("double")){
            result = true;
        } else if(checkClass.getName().equals("java.lang.Character") && startClass.getName().equals("char")){
            result = true;
        } else if(checkClass.getName().equals("java.lang.Boolean") && startClass.getName().equals("boolean")){
            result = true;
        }
        return result;
    }
}
