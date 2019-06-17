package ru.strict.utils;

import java.io.File;
import java.net.URISyntaxException;

public class UtilClass {

    /**
     * Определить имя текщего класса
     * @return
     */
    public static String getCurrentClassname(){
        try{
            throw new RuntimeException();
        }catch(RuntimeException ex){
            return ex.getStackTrace()[1].getClassName();
        }
    }

    /**
     * Получить класс по имени
     * @return Если класс не наден, тогда вернется null
     */
    public static Class getClassByName(String className){
        Class result = null;
        try {
            result = Class.forName(className);
        } catch (ClassNotFoundException e) {}

        return result;
    }


    /**
     * Получить путь до директории класса
     * @param appClass
     */
    public static String getPathByClass(Class appClass) {
        String result = null;

        if(appClass != null) {
            try {
                File file = new File(appClass.getProtectionDomain().getCodeSource().getLocation().toURI());
                if(file.isDirectory()) {
                    result = file.getPath();
                } else {
                    result = file.getParentFile().getPath();
                }
            } catch (URISyntaxException ex) {
                throw new RuntimeException(ex);
            }
        }

        return result;
    }

    /**
     * Пример использования:
     * Class<List<Object>> clazz = UtilClass.<List<Object>>castClass(List.class);
     * @param aClass
     * @param <T>
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> Class<T> castClass(Class<?> aClass) {
        return (Class<T>)aClass;
    }

    /**
     * Проверить равенство двух классов
     * @param checkClass (Неизвестный класс) Проверяемый класс
     * @param startClass (Требуемый класс) Класс, относительно которого проверяем принадлежность к экземпляру
     * @return
     */
    public static boolean isEquals(Class checkClass, Class startClass){
        return checkClass == startClass;
    }

}
