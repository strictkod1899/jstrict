package ru.strict.utils;

import java.util.Collection;
import java.util.Map;

/**
* Генерация хэш-кода
*/
public class UtilHashCode {

    private static final int INIT_HASH_CODE = 17;
    private static final int CONSTANT = 37;

    public static int createHashCode(Object... objects) {
        int result = processHashCode(INIT_HASH_CODE, objects);
        return result;
    }

    /**
    * Получить хэш-код, если объект перед этим вызывает super.hashCode()
    * <p><b>Пример использования:</b></p>
    * <code><pre style="background-color: white; font-family: consolas">
    *       public int hashCode(){
    *           int superHashCode = super.hashCode();
    *           return UtilHashCode.createSubHashCode(superHashCode, obj1, obj2, obj3);
    *       }
    * </pre></code>
    */
    public static int createSubHashCode(int startHashCode, Object... objects){
        int result = processHashCode(startHashCode, objects);
        return result;
    }

    /**
    * Реализация расчета хэш-кода
    */
    private static int processHashCode(int startHashCode, Object... objects){
        int result = startHashCode;

        for (Object object : objects){
            if (object instanceof Collection) {
                Collection collectionObjects = (Collection) object;
                for (Object collectionObject : collectionObjects)
                    result = processHashCode(result, collectionObject);
            } else if (object instanceof Object[]) {
                Object[] collectionObjects = (Object[]) object;
                for (Object collectionObject : collectionObjects)
                    result = processHashCode(result, collectionObject);
            } else if (object instanceof Map) {
                Collection collectionObjects = ((Map) object).values();
                for (Object collectionObject : collectionObjects)
                    result = processHashCode(result, collectionObject);
            }else
                result = CONSTANT * result + getIntegerValueItem(object);
        }

        return result;
    }

    private static int getIntegerValueItem(Object object){
        int itemValue = 0;
        if(object == null)
            itemValue = 0;
        else if (object instanceof Integer || object instanceof Byte || object instanceof Short
                || object instanceof Character)
            itemValue = (int) object;
        else if (object instanceof Long)
            itemValue = ((int) ((long) object - ((long) object >>> 32)));
        else if (object instanceof Float)
            itemValue = Float.floatToIntBits((float) object);
        else if (object instanceof Double) {
            long longObject = Double.doubleToLongBits((double) object);
            itemValue = ((int) (longObject - (longObject >>> 32)));
        } else if (object instanceof Boolean)
            itemValue = ((Boolean) object ? 1 : 0);
        else
            itemValue = object.hashCode();

        return itemValue;
    }
}
