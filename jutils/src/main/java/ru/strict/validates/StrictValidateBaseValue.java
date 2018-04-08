package ru.strict.validates;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Класс описывает методы, для проверки валидности значений стандартных типов
 */
//TODO: перевести валидацию на регулярные выражения Pattern
public class StrictValidateBaseValue {

    /**
     * Проверка корректности заполнения текстового поля
     *
     * @param str Проверяемая строка
     * @return Если строка не содержит пустых символов и является корректной, то возвращается true, иначе false
     */
    public static boolean isValidateString(String str) {

        if (str != null) {
            String[] masScanNameUser;
            String[] masScanNameUser2;
            masScanNameUser = str.split(" ");      // Разбиваем текст чтобы исключить ситуацию с содержанием одних пробелов
            masScanNameUser2 = str.split("", 2);   // Разбиваем текст чтобы проверить имя на наличие пробелов вначале строки

            if (str.equals("") || masScanNameUser.length == 0 || masScanNameUser2[0].equals(" "))
                return false;
            else
                return true;
        } else
            return false;
    }

    /**
     * Проверка корректности приведения строки к целочисленному типу
     *
     * @param str Проверяемая строка
     * @return Если текстовое поле содержит корректные данные, то возвращается true, иначе false
     */
    public static boolean isValidateInteger(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }

    /**
     * Проверка корректности приведения строки к дробному числовому типу
     *
     * @param str Проверяемая строка
     * @return Если текстовое поле содержит корректные данные, то возвращается true, иначе false
     */
    public static boolean isValidateDouble(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }

    /**
     * Проверка корректности приведения строки к дате
     *
     * @param str Проверяемая строка
     * @return Если текстовое поле содержит корректные данные, то возвращается true, иначе false
     */
    public static boolean isValidateDate(String str) {
        //TODO: Надо доработать, а то это фигня какая то
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        try {
            sdf.format(sdf.parse(str));
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    /**
     * Получить корректную дату из переданной строки, при условии, что приведения тепов возможно.
     *
     * @param str Строка, которую необходимо преобразовать
     * @return Корректная дата в формате dd-MM-yyyy
     */
    public static Date getValidateDate(String str) {
        return null;
        //TODO: Доработать!
            //sdf.format(sdf.parse(str.trim().replace(".", "-")));
    }

}
