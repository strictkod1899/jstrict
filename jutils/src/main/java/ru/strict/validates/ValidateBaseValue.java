package ru.strict.validates;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Класс описывает методы, для проверки валидности значений стандартных типов
 */
//TODO: перевести валидацию на регулярные выражения Pattern
public class ValidateBaseValue {

    /**
     * Проверка строки, чтобы она была не пустой и не равно нулю
     *
     * @param str Проверяемая строка
     * @return Если строка не содержит пустых символов и является корректной, то возвращается true, иначе false
     */
    public static boolean isNotEmptyOrNull(String str) {
        boolean result = false;

        if (str != null) {
            Pattern pattern = Pattern.compile("\\S");
            Matcher match = pattern.matcher(str);
            if(match.find()){
                result = true;
            }
        }

        return result;
    }

    /**
     * Проверка корректности приведения строки к целочисленному типу
     *
     * @param str Проверяемая строка
     * @return Если текстовое поле содержит корректные данные, то возвращается true, иначе false
     */
    public static boolean isValidateInteger(String str) {
        boolean result = false;

        if (str != null) {
            Pattern pattern = Pattern.compile("[+-]?\\d+");
            Matcher match = pattern.matcher(str);
            result = match.matches();
        }

        return result;
    }

    /**
     * Проверка корректности приведения строки к дробному числовому типу
     *
     * @param str Проверяемая строка
     * @return Если текстовое поле содержит корректные данные, то возвращается true, иначе false
     */
    public static boolean isValidateDouble(String str) {
        boolean result = false;

        if (str != null) {
            Pattern pattern = Pattern.compile("([+-]?\\d+\\.?\\d*)");
            Matcher match = pattern.matcher(str);
            result = match.matches();
        }

        return result;
    }
}
