package ru.strict.utils;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * Управление Reflection API
 */
public class UtilReflection {

    /**
     * Создать объект определенного класса, с передачей со
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
                        if (consParameter != userParameters[i].getClass())
                            break;

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
}
