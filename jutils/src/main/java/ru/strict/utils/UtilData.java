package ru.strict.utils;

import ru.strict.validates.ValidateBaseValue;

import java.io.UnsupportedEncodingException;

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
            if(ValidateBaseValue.isNotEmptyOrNull(defaultEncoding)) {
                result = new String(value.getBytes(defaultEncoding), encodingOutput);
            }else{
                result = new String(value.getBytes(), encodingOutput);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return result;
    }
}
