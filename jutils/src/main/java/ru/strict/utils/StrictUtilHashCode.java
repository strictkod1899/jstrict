package ru.strict.utils;

import java.util.Collection;
import java.util.Map;

public class StrictUtilHashCode {

    public static int createHashCode(Object... objects) {
        int result = 17;
        int constant = 37;

        for (Object object : objects) {
            if (object instanceof Integer || object instanceof Byte || object instanceof Short
                    || object instanceof Character)
                result = constant * result + (int) object;
            else if (object instanceof Long)
                result = constant * result + ((int) ((long) object - ((long) object >>> 32)));
            else if (object instanceof Float)
                result = constant * result + Float.floatToIntBits((float) object);
            else if (object instanceof Double) {
                long longObject = Double.doubleToLongBits((double) object);
                result = constant * result + ((int) (longObject - (longObject >>> 32)));
            } else if (object instanceof Boolean)
                result = constant * result + ((Boolean) object ? 1 : 0);
            else if (object instanceof Collection) {
                Collection collectionObjects = (Collection) object;
                for (Object collectionObject : collectionObjects)
                    result = constant * result + (collectionObject == null ? 0 : createHashCode(collectionObject));
            } else if (object instanceof Object[]) {
                Object[] collectionObjects = (Object[]) object;
                for (Object collectionObject : collectionObjects)
                    result = constant * result + (collectionObject == null ? 0 : createHashCode(collectionObject));
            } else if (object instanceof Map) {
                Collection collectionObjects = ((Map) object).values();
                for (Object collectionObject : collectionObjects)
                    result = constant * result + (collectionObject == null ? 0 : createHashCode(collectionObject));
            } else
                result = constant * result + (object == null ? 0 : object.hashCode());
        }

        return result;
    }
}