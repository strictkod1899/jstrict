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
        if (userParameters != null) {
            for (Constructor cons : arrConstructors) {
                int consParametersCount = cons.getParameterCount();

                if (consParametersCount == userParameters.length) {
                    Class[] consParameters = cons.getParameterTypes();
                    for (int i = 0; i < consParametersCount; i++) {
                        Class consParameter = consParameters[i];
                        if (consParameter != userParameters[i].getClass()) {
                            boolean checkBySuperClass = IsSuperClass(consParameter, userParameters[i].getClass());
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

    public static boolean IsInstanceOf(Class checkClass, Class startClass){
        boolean result = false;
        if(checkClass == startClass){
            result = true;
        }else{
            result = IsSuperClass(checkClass, startClass);
        }

        return result;
    }

    public static boolean IsSuperClass(Class checkClass, Class startClass){
        boolean result = false;

        Class superClass = startClass.getSuperclass();
        if (superClass != Object.class) {
            if (checkClass == superClass) {
                result = true;
            }else{
                result = IsSuperClass(checkClass, superClass);

                if(!result){
                    result = IsInterface(checkClass, superClass);
                }
            }
        }else {
            result = false;
        }

        return result;
    }

    public static boolean IsInterface(Class checkClass, Class startClass){
        boolean result = false;

        Class[] interfaces = startClass.getInterfaces();
        for(Class interfaceItem : interfaces){
            if (interfaceItem != Object.class) {
                if (checkClass == interfaceItem) {
                    result = true;
                }else{
                    result = IsInterface(checkClass, interfaceItem);
                }
            }else {
                result = false;
            }
        }



        return result;
    }
}
