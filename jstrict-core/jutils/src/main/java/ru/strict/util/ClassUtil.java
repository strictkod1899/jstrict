package ru.strict.util;

import java.io.File;
import java.net.URISyntaxException;

public class ClassUtil {

    /**
     * Получить класс по имени
     *
     * @return Если класс не наден, тогда вернется null
     */
    public static Class getClassByName(String className) {
        try {
            return Class.forName(className);
        } catch (ClassNotFoundException e) {
            return null;
        }
    }

    public static String getFilePathByClass(Class<?> appClass, String fileName) {
        return String.format("%s%s%s", getPathByClass(appClass), File.separator, fileName);
    }

    /**
     * Получить путь до директории класса
     *
     * @throws RuntimeException (URISyntaxException)
     */
    public static String getPathByClass(Class<?> appClass) {
        String result = null;

        if (appClass != null) {
            try {
                File file = new File(appClass.getProtectionDomain().getCodeSource().getLocation().toURI());
                if (file.isDirectory()) {
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
     */
    @SuppressWarnings("unchecked")
    public static <T> Class<T> castClass(Class<?> aClass) {
        return (Class<T>) aClass;
    }

    /**
     * Проверить равенство двух классов
     *
     * @param checkClass (Неизвестный класс) Проверяемый класс
     * @param startClass (Требуемый класс) Класс, относительно которого проверяем принадлежность к экземпляру
     */
    public static boolean isEquals(Class checkClass, Class startClass) {
        return checkClass == startClass;
    }

}
