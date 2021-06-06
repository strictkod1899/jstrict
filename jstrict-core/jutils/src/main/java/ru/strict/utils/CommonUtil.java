package ru.strict.utils;

import ru.strict.validate.Validator;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

public final class CommonUtil {

    private static final BigDecimal HUNDRED = new BigDecimal(100);

    private CommonUtil() {
    }

    public static <T> int compareTo(T source, T compareValue) throws UnsupportedOperationException {
        if (source == null && compareValue == null) {
            return 0;
        } else if (source == null || compareValue == null) {
            return -1;
        }

        if (compareValue instanceof Comparable) {
            return ((Comparable) source).compareTo(compareValue);
        } else {
            throw new UnsupportedOperationException(String.format("This type [%s] for comparable not supported",
                    source.getClass().toString()));
        }
    }

    /**
     * Округлить число, до определнного количества знаков после запятой
     *
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
        number = (double) roundValue / pow;
        return number;
    }

    /**
     * Получить процентное соотношение значения от общей суммы
     */
    public static int calcPercent(double totalSum, double partValue) {
        int result;
        double doubleResult = partValue / (totalSum / 100);
        if ((doubleResult - ((int) doubleResult)) > 0.5) {
            result = (int) Math.ceil(doubleResult);
        } else {
            result = (int) doubleResult;
        }
        return result;
    }

    /**
     * Получить сумму без НДС.
     * Будет произведено округление до 2 знаков в дробной части в большую сторону
     *
     * @param sum Сумма с НДС
     * @param vatPercent Процент НДС (5%, 10% и т.д.)
     */
    public static BigDecimal getSumWithoutVat(BigDecimal sum, double vatPercent) {
        return getSumWithoutVat(sum, vatPercent, 2, RoundingMode.CEILING);
    }

    /**
     * Получить сумму без НДС.
     * Будет произведено округление до {@param scale} знаков в дробной части в большую сторону
     *
     * @param sum Сумма с НДС
     * @param vatPercent Процент НДС (5%, 10% и т.д.)
     * @param scale Количество знаков в дробной части
     */
    public static BigDecimal getSumWithoutVat(BigDecimal sum, double vatPercent, int scale) {
        return getSumWithoutVat(sum, vatPercent, scale, RoundingMode.CEILING);
    }

    /**
     * Получить сумму без НДС.
     * Будет произведено округление до 2 знаков в дробной части указанным способом округления {@param roundingMode}
     *
     * @param sum Сумма с НДС
     * @param vatPercent Процент НДС (5%, 10% и т.д.)
     * @param roundingMode Режим округления
     */
    public static BigDecimal getSumWithoutVat(BigDecimal sum, double vatPercent, RoundingMode roundingMode) {
        return getSumWithoutVat(sum, vatPercent, 2, roundingMode);
    }

    /**
     * Получить сумму без НДС.
     * Будет произведено округление до {@param scale} знаков в дробной части указанным способом округления
     * {@param roundingMode}
     *
     * @param sum Сумма с НДС
     * @param vatPercent Процент НДС (5%, 10% и т.д.)
     * @param scale Количество знаков в дробной части
     * @param roundingMode Режим округления
     */
    public static BigDecimal getSumWithoutVat(BigDecimal sum, double vatPercent, int scale, RoundingMode roundingMode) {
        Validator.isNull(sum, "sum");
        Validator.isNull(roundingMode, "roundingMode");
        Validator.isLess(vatPercent, "vatPercent", 0);
        Validator.isLess(scale, "scale", 0);

        BigDecimal dVat = BigDecimal.valueOf(vatPercent);
        BigDecimal dVatDivided = dVat.divide(HUNDRED);
        BigDecimal totalPercent = BigDecimal.ONE.add(dVatDivided);
        return sum.divide(totalPercent, scale, roundingMode);
    }

    /**
     * Получить сумму НДС из переданной суммы {@param sum}
     * Будет произведено округление до 2 знаков в дробной части в большую сторону
     *
     * @param sum Сумма из которой определяется НДС
     * @param vatPercent процент НДС (5%, 10% и т.д.)
     */
    public static BigDecimal getVatFromSum(BigDecimal sum, double vatPercent) {
        return getVatFromSum(sum, vatPercent, 2, RoundingMode.CEILING);
    }

    /**
     * Получить сумму НДС из переданной суммы {@param sum}
     * Будет произведено округление до {@param scale} знаков в дробной части в большую сторону
     *
     * @param sum Сумма из которой определяется НДС
     * @param vatPercent процент НДС (5%, 10% и т.д.)
     */
    public static BigDecimal getVatFromSum(BigDecimal sum, double vatPercent, int scale) {
        return getVatFromSum(sum, vatPercent, scale, RoundingMode.CEILING);
    }

    /**
     * Получить сумму НДС из переданной суммы {@param sum}
     * Будет произведено округление до 2 знаков в дробной части указанным способом округления {@param roundingMode}
     *
     * @param sum Сумма из которой определяется НДС
     * @param vatPercent процент НДС (5%, 10% и т.д.)
     */
    public static BigDecimal getVatFromSum(BigDecimal sum, double vatPercent, RoundingMode roundingMode) {
        return getVatFromSum(sum, vatPercent, 2, roundingMode);
    }

    /**
     * Получить сумму НДС из переданной суммы {@param sum}
     * Будет произведено округление до {@param scale} знаков в дробной части указанным способом округления
     * {@param roundingMode}
     *
     * @param sum Сумма из которой определяется НДС
     * @param vatPercent процент НДС (5%, 10% и т.д.)
     * @param scale Количество знаков в дробной части
     * @param roundingMode Режим округления
     */
    public static BigDecimal getVatFromSum(BigDecimal sum, double vatPercent, int scale, RoundingMode roundingMode) {
        Validator.isNull(sum, "sum");
        Validator.isNull(roundingMode, "roundingMode");
        Validator.isLess(vatPercent, "vatPercent", 0);
        Validator.isLess(scale, "scale", 0);

        BigDecimal dVat = BigDecimal.valueOf(vatPercent);
        BigDecimal dVatSum = dVat.add(HUNDRED);
        BigDecimal sumMultiplied = sum.multiply(dVat);
        return sumMultiplied.divide(dVatSum, scale, roundingMode);
    }
}
