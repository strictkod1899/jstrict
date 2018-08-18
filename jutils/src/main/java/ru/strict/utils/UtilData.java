package ru.strict.utils;

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
}
