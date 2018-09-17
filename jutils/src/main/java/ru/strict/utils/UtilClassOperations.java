package ru.strict.utils;

/**
* Класс описывает статический метод для определения имени текущего класса
*/
public class UtilClassOperations {

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
     * Получить текущий класс
     * @return
     */
    public static Class getCurrentClass(String currentClassname){
        Class result = null;
        try {
            result = Class.forName(currentClassname);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return result;
    }

    /**
     * Пример использования:
     * Class<List<Object>> clazz = ClassUtil.<List<Object>>castClass(List.class);
     * @param aClass
     * @param <T>
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> Class<T> castClass(Class<?> aClass) {
        return (Class<T>)aClass;
    }
}
