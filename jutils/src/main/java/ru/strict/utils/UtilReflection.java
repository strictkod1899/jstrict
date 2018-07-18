package ru.strict.utils;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * Управление Reflection API
 */
public class UtilReflection {

    /**
     * Создать объект определенного класса, с передачей параметров
     * @param clazzInstance Класс объект, которого надо создать
     */
    public static <INSTANCE> INSTANCE createInstance(Class<INSTANCE> clazzInstance, Object...userParameters) {
        Constructor constructor = null;
        Constructor[] arrConstructors = clazzInstance.getConstructors();
        // Если были переданы параметры конструктора
        if (userParameters != null && userParameters.length != 0) {
            for (Constructor cons : arrConstructors) {
                int consParametersCount = cons.getParameterCount();

                if (consParametersCount == userParameters.length) {
                    Class[] consParameters = cons.getParameterTypes();
                    for (int i = 0; i < consParametersCount; i++) {
                        Class consParameter = consParameters[i];
                        if (consParameter != userParameters[i].getClass() && !isPrimitive(userParameters[i].getClass(), consParameter)) {
                            boolean checkBySuperClass = isSuperClass(consParameter, userParameters[i].getClass());
                            if(!checkBySuperClass) {
                                break;
                            }
                        }

                        if (i == consParametersCount - 1)
                            constructor = cons;
                    }
                }
            }
        } else {
            // Если требуется вызывать пустой конструктор
            for (Constructor cons : arrConstructors) {
                if (cons.getParameterCount() == 0)
                    constructor = cons;
            }
        }

        if(constructor!=null) {
            try {
                return (INSTANCE) constructor.newInstance(userParameters);
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException ex) {
                ex.printStackTrace();
                return null;
            }
        }else
            return null;
    }

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
        if (superClass != Object.class) {
            if (checkClass == superClass) {
                result = true;
            }else{
                result = isSuperClass(checkClass, superClass);

                if(!result){
                    result = isInterface(checkClass, superClass);
                }
            }
        }else {
            result = isInterface(checkClass, startClass);
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
