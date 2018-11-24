package ru.strict.utils;

import ru.strict.validates.ValidateBaseValue;

import java.io.*;

public class UtilData {

    /**
     * Округлить число, до определнного количества знаков после запятой
     * @param number Исходное число
     * @param scale Количество знаков после запятой
     * @return
     */
    public static double round(double number, int scale) {
        int pow = 1;
        for (int i = 0; i < scale; i++) {
            pow *= 10;
        }

        number = number * pow;
        int roundValue = (int) Math.round(number);
        number = (double)roundValue / pow;
        return number;
    }

    public static String convertStringToUTF8(String value){
        return convertStringToEncode(value, null, "UTF-8");
    }

    public static String convertStringFromISOToUTF8(String value){
        return convertStringToEncode(value, "iso-8859-1", "UTF-8");
    }
    public static String convertStringFromEncodeToUTF8(String value, String defaultEncode){
        return convertStringToEncode(value, defaultEncode, "UTF-8");
    }

    public static String convertStringToEncode(String value, String defaultEncoding, String encodingOutput){
        String result = null;
        try {
            if(!ValidateBaseValue.isEmptyOrNull(defaultEncoding)) {
                result = new String(value.getBytes(defaultEncoding), encodingOutput);
            }else{
                result = new String(value.getBytes(), encodingOutput);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return result;
    }

    public static <T> int compareTo(T source, T compareValue){
        int result = -1;

        if(compareValue instanceof Comparable){
            result = ((Comparable) source).compareTo(compareValue);
        }else {
            throw new UnsupportedOperationException("This type for comparable not supported");
        }

        return result;
    }

    /**
     * Объединить строки с использованием общего разделителя.
     * Пустые или нулевые строки будут пропущены
     */
    public static String join(String separator, String...strings) {
        String result = "";
        for(String item : strings){
            if(!ValidateBaseValue.isEmptySpaceOrNull(item)){
                if(!ValidateBaseValue.isEmptyOrNull(result)){
                    result += separator;
                }
    
                result += item;
            }
        }
    
        return result;
    }

    /**
     * Если строка равна null, тогда вернется пустая строка
     * @param str
     * @return
     */
    public static String nullToEmpty(String str){
        return str == null ? "" : str;
    }

    /**
     * Если строка пустая, тогда вернется null
     * @param str
     * @return
     */
    public static String emptyToNull(String str){
        return ValidateBaseValue.isEmptyOrNull(str) ? null : str;
    }
}
