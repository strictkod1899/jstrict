package ru.strict.i18n;

import com.ibm.icu.text.RuleBasedNumberFormat;
import ru.strict.validate.Validator;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * Форматирование числового представления суммы к тексту прописью.
 * Например, если есть сумма 123, то вывод на российскую валюту будет таким: 'сто двадцать три рубля'.
 */
public class MoneyFormatter {

    private static final Locale RUS_LOCALE = Locale.forLanguageTag("ru");
    private static final RuleBasedNumberFormat RUS_NUMBER_FORMATTER = new RuleBasedNumberFormat(
            RUS_LOCALE,
            RuleBasedNumberFormat.SPELLOUT
    );

    private static final Map<Currency, CurrencyUnits> DEFAULT_UNITS = new HashMap<>();

    static {
        DEFAULT_UNITS.put(
                Currency.RUB,
                CurrencyUnits.builder()
                        .currency(Currency.RUB)
                        .integerUnits(new String[]{ "рублей",
                                "рубль",
                                "рубля",
                                "рубля",
                                "рубля",
                                "рублей",
                                "рублей",
                                "рублей",
                                "рублей",
                                "рублей" })
                        .fractionalUnits(new String[]{ "копеек",
                                "копейка",
                                "копейки",
                                "копейки",
                                "копейки",
                                "копеек",
                                "копеек",
                                "копеек",
                                "копеек",
                                "копеек" })
                        .build()
        );
        DEFAULT_UNITS.put(
                Currency.KZT,
                CurrencyUnits.builder()
                        .currency(Currency.KZT)
                        .integerUnits(new String[]{ "тенге",
                                "тенге",
                                "тенге",
                                "тенге",
                                "тенге",
                                "тенге",
                                "тенге",
                                "тенге",
                                "тенге",
                                "тенге" })
                        .fractionalUnits(new String[]{ "тиын",
                                "тиын",
                                "тиын",
                                "тиын",
                                "тиын",
                                "тиын",
                                "тиын",
                                "тиын",
                                "тиын",
                                "тиын" })
                        .build()
        );
        DEFAULT_UNITS.put(
                Currency.USD,
                CurrencyUnits.builder()
                        .currency(Currency.USD)
                        .integerUnits(new String[]{ "долларов",
                                "доллар",
                                "доллара",
                                "доллара",
                                "доллара",
                                "долларов",
                                "долларов",
                                "долларов",
                                "долларов",
                                "долларов" })
                        .fractionalUnits(new String[]{ "центов",
                                "цент",
                                "цента",
                                "цента",
                                "цента",
                                "центов",
                                "центов",
                                "центов",
                                "центов",
                                "центов" })
                        .build()
        );
        DEFAULT_UNITS.put(
                Currency.EUR,
                CurrencyUnits.builder()
                        .currency(Currency.EUR)
                        .integerUnits(new String[]{ "евро",
                                "евро",
                                "евро",
                                "евро",
                                "евро",
                                "евро",
                                "евро",
                                "евро",
                                "евро",
                                "евро" })
                        .fractionalUnits(new String[]{ "центов",
                                "цент",
                                "цента",
                                "цента",
                                "цента",
                                "центов",
                                "центов",
                                "центов",
                                "центов",
                                "центов" })
                        .build()
        );
        DEFAULT_UNITS.put(
                Currency.GBP,
                CurrencyUnits.builder()
                        .currency(Currency.GBP)
                        .integerUnits(new String[]{ "фунтов стерлингов",
                                "фунт стерлингов",
                                "фунта стерлингов",
                                "фунта стерлингов",
                                "фунта стерлингов",
                                "фунтов стерлингов",
                                "фунтов стерлингов",
                                "фунтов стерлингов",
                                "фунтов стерлингов",
                                "фунтов стерлингов" })
                        .fractionalUnits(new String[]{ "пенсов",
                                "пенс",
                                "пенса",
                                "пенса",
                                "пенса",
                                "пенсов",
                                "пенсов",
                                "пенсов",
                                "пенсов",
                                "пенсов" })
                        .build()
        );
        DEFAULT_UNITS.put(
                Currency.CNY,
                CurrencyUnits.builder()
                        .currency(Currency.CNY)
                        .integerUnits(new String[]{ "юаней",
                                "юань",
                                "юаня",
                                "юаня",
                                "юаня",
                                "юаней",
                                "юаней",
                                "юаней",
                                "юаней",
                                "юаней" })
                        .fractionalUnits(new String[]{ "", "", "", "", "", "", "", "", "", "" })
                        .build()
        );
        DEFAULT_UNITS.put(
                Currency.BYN,
                CurrencyUnits.builder()
                        .currency(Currency.BYN)
                        .integerUnits(new String[]{ "белорусских рублей",
                                "белорусский рубль",
                                "белорусских рубля",
                                "белорусских рубля",
                                "белорусских рубля",
                                "белорусских рублей",
                                "белорусских рублей",
                                "белорусских рублей",
                                "белорусских рублей",
                                "белорусских рублей" })
                        .fractionalUnits(new String[]{ "копеек",
                                "копейка",
                                "копейки",
                                "копейки",
                                "копейки",
                                "копеек",
                                "копеек",
                                "копеек",
                                "копеек",
                                "копеек" })
                        .build()
        );
        DEFAULT_UNITS.put(
                Currency.UAH,
                CurrencyUnits.builder()
                        .currency(Currency.UAH)
                        .integerUnits(new String[]{ "гривен",
                                "гривна",
                                "гривны",
                                "гривны",
                                "гривны",
                                "гривен",
                                "гривен",
                                "гривен",
                                "гривен",
                                "гривен" })
                        .fractionalUnits(new String[]{ "", "", "", "", "", "", "", "", "", "" })
                        .build()
        );
        DEFAULT_UNITS.put(
                Currency.KGS,
                CurrencyUnits.builder()
                        .currency(Currency.KGS)
                        .integerUnits(new String[]{ "киргизских сомов",
                                "киргизский сом",
                                "киргизских сома",
                                "киргизских сома",
                                "киргизских сома",
                                "киргизских сомов",
                                "киргизских сомов",
                                "киргизских сомов",
                                "киргизских сомов",
                                "киргизских сомов" })
                        .fractionalUnits(new String[]{ "", "", "", "", "", "", "", "", "", "" })
                        .build()
        );
        DEFAULT_UNITS.put(
                Currency.AMD,
                CurrencyUnits.builder()
                        .currency(Currency.AMD)
                        .integerUnits(new String[]{ "армянских драмов",
                                "армянский драм",
                                "армянских драма",
                                "армянских драма",
                                "армянских драма",
                                "армянских драмов",
                                "армянских драмов",
                                "армянских драмов",
                                "армянских драмов",
                                "армянских драмов" })
                        .fractionalUnits(new String[]{ "", "", "", "", "", "", "", "", "", "" })
                        .build()
        );
        DEFAULT_UNITS.put(
                Currency.TRY,
                CurrencyUnits.builder()
                        .currency(Currency.TRY)
                        .integerUnits(new String[]{ "турецкая лира",
                                "турецкая лира",
                                "турецкая лира",
                                "турецкая лира",
                                "турецкая лира",
                                "турецкая лира",
                                "турецкая лира",
                                "турецкая лира",
                                "турецкая лира",
                                "турецкая лира" })
                        .fractionalUnits(new String[]{ "", "", "", "", "", "", "", "", "", "" })
                        .build()
        );
        DEFAULT_UNITS.put(
                Currency.THB,
                CurrencyUnits.builder()
                        .currency(Currency.THB)
                        .integerUnits(new String[]{ "тайский бат",
                                "тайский бат",
                                "тайский бат",
                                "тайский бат",
                                "тайский бат",
                                "тайский бат",
                                "тайский бат",
                                "тайский бат",
                                "тайский бат",
                                "тайский бат" })
                        .fractionalUnits(new String[]{ "", "", "", "", "", "", "", "", "", "" })
                        .build()
        );
        DEFAULT_UNITS.put(
                Currency.KRW,
                CurrencyUnits.builder()
                        .currency(Currency.KRW)
                        .integerUnits(new String[]{ "южнокорейская вона",
                                "южнокорейская вона",
                                "южнокорейская вона",
                                "южнокорейская вона",
                                "южнокорейская вона",
                                "южнокорейская вона",
                                "южнокорейская вона",
                                "южнокорейская вона",
                                "южнокорейская вона",
                                "южнокорейская вона" })
                        .fractionalUnits(new String[]{ "", "", "", "", "", "", "", "", "", "" })
                        .build()
        );
        DEFAULT_UNITS.put(
                Currency.AED,
                CurrencyUnits.builder()
                        .currency(Currency.AED)
                        .integerUnits(new String[]{ "дирхам ОАЭ",
                                "дирхам ОАЭ",
                                "дирхам ОАЭ",
                                "дирхам ОАЭ",
                                "дирхам ОАЭ",
                                "дирхам ОАЭ",
                                "дирхам ОАЭ",
                                "дирхам ОАЭ",
                                "дирхам ОАЭ",
                                "дирхам ОАЭ" })
                        .fractionalUnits(new String[]{ "", "", "", "", "", "", "", "", "", "" })
                        .build()
        );
        DEFAULT_UNITS.put(
                Currency.UZS,
                CurrencyUnits.builder()
                        .currency(Currency.UZS)
                        .integerUnits(new String[]{ "узбекский сум",
                                "узбекский сум",
                                "узбекский сум",
                                "узбекский сум",
                                "узбекский сум",
                                "узбекский сум",
                                "узбекский сум",
                                "узбекский сум",
                                "узбекский сум",
                                "узбекский сум" })
                        .fractionalUnits(new String[]{ "", "", "", "", "", "", "", "", "", "" })
                        .build()
        );
        DEFAULT_UNITS.put(
                Currency.MNT,
                CurrencyUnits.builder()
                        .currency(Currency.MNT)
                        .integerUnits(new String[]{ "монгольский тугрик",
                                "монгольский тугрик",
                                "монгольский тугрик",
                                "монгольский тугрик",
                                "монгольский тугрик",
                                "монгольский тугрик",
                                "монгольский тугрик",
                                "монгольский тугрик",
                                "монгольский тугрик",
                                "монгольский тугрик" })
                        .fractionalUnits(new String[]{ "", "", "", "", "", "", "", "", "", "" })
                        .build()
        );
        DEFAULT_UNITS.put(
                Currency.PLN,
                CurrencyUnits.builder()
                        .currency(Currency.PLN)
                        .integerUnits(new String[]{ "польский злотый",
                                "польский злотый",
                                "польский злотый",
                                "польский злотый",
                                "польский злотый",
                                "польский злотый",
                                "польский злотый",
                                "польский злотый",
                                "польский злотый",
                                "польский злотый" })
                        .fractionalUnits(new String[]{ "", "", "", "", "", "", "", "", "", "" })
                        .build()
        );
        DEFAULT_UNITS.put(
                Currency.AZN,
                CurrencyUnits.builder()
                        .currency(Currency.AZN)
                        .integerUnits(new String[]{ "азербайджанский манат",
                                "азербайджанский манат",
                                "азербайджанский манат",
                                "азербайджанский манат",
                                "азербайджанский манат",
                                "азербайджанский манат",
                                "азербайджанский манат",
                                "азербайджанский манат",
                                "азербайджанский манат",
                                "азербайджанский манат" })
                        .fractionalUnits(new String[]{ "", "", "", "", "", "", "", "", "", "" })
                        .build()
        );
    }

    /**
     * Получить текстовое представление числа на русском языке
     *
     * @param number Число, которое переводится в текстовое представление
     * @return Например, если передать число 123, то результат будет таким: 'сто двадцать три'
     */
    public String formatNumberToWords(BigInteger number) {
        Validator.isNull(number, "number");

        return RUS_NUMBER_FORMATTER.format(number);
    }

    /**
     * Получить текстовое представление числа. Язык определяется переданной локалью
     *
     * @param number Число, которое переводится в текстовое представление
     * @param locale Определяет язык интернационализации
     * @return Например, если передать число 123, то результат будет таким: 'сто двадцать три'
     */
    public String formatNumberToWords(BigInteger number, Locale locale) {
        Validator.isNull(number, "number");
        Validator.isNull(locale, "locale");

        return locale.equals(RUS_LOCALE) ?
                formatNumberToWords(number) :
                new RuleBasedNumberFormat(locale, RuleBasedNumberFormat.SPELLOUT).format(number);
    }

    /**
     * Получить текстовое представление целой части суммы (без копеек) на русском языке.
     *
     * @param sum Сумма, которая переводится в текстовое представление
     * @param currency Определяет валюту форматирования (рубли, тенге и др.)
     * @return Например, если передать число 123, то результат будет таким: 'сто двадцать три рубля'
     */
    public String formatMoneyToWords(BigInteger sum, Currency currency) {
        Validator.isNull(sum, "sum");
        Validator.isNull(currency, "currency");

        String textSum = formatNumberToWords(sum);
        String unit = getMoneyUnit(sum, currency, UnitType.INTEGER);
        return String.format("%s %s", textSum, unit);
    }

    /**
     * Получить текстовое представление целой части суммы (без копеек). Язык определяется переданной локалью
     *
     * @param sum Сумма, которая переводится в текстовое представление
     * @param currency Определяет валюту форматирования (рубли, тенге и др.)
     * @param locale Определяет язык интернационализации
     * @return Например, если передать число 123, то результат будет таким: 'сто двадцать три рубля'
     */
    public String formatMoneyToWords(BigInteger sum, Currency currency, Locale locale) {
        Validator.isNull(sum, "sum");
        Validator.isNull(currency, "currency");
        Validator.isNull(locale, "locale");

        String textSum = formatNumberToWords(sum, locale);
        String unit = getMoneyUnit(sum, currency, UnitType.INTEGER);
        return String.format("%s %s", textSum, unit);
    }

    /**
     * Получить текстовое представление суммы в рублях с копейками.
     * Копейки не переводятся в текстовое представление.
     *
     * @param sum Сумма, которая переводится в текстовое представление
     * @return Например, если передать 123.11, то результат будет следующим: 'сто двадцать три рубля 11 копеек'.
     * Если копеек нет, то в результате будет только целая часть (рубли)
     */
    public String formatMoneyToWords(BigDecimal sum) {
        return formatMoneyToWords(sum, Currency.RUB);
    }

    /**
     * Получить текстовое представление суммы в указанной валюте с копейками.
     * Копейки не переводятся в текстовое представление.
     *
     * @param sum Сумма, которая переводится в текстовое представление
     * @param currency Определяет валюту форматирования (рубли, тенге и др.)
     * @return Например, если передать 123.11, то результат будет следующим: 'сто двадцать три рубля 11 копеек'.
     * Если копеек нет, то в результате будет только целая часть (рубли)
     */
    public String formatMoneyToWords(BigDecimal sum, Currency currency) {
        return formatMoneyToWords(sum, currency, RUS_LOCALE);
    }

    /**
     * Получить текстовое представление суммы в указанной валюте с копейками.
     * Копейки не переводятся в текстовое представление.
     *
     * @param sum Сумма, которая переводится в текстовое представление
     * @param currency Определяет валюту форматирования (рубли, тенге и др.)
     * @param locale
     * @return Например, если передать 123.11, то результат будет следующим: 'сто двадцать три рубля 11 копеек'.
     * Если копеек нет, то в результате будет только целая часть (рубли)
     */
    public String formatMoneyToWords(BigDecimal sum, Currency currency, Locale locale) {
        Validator.isNull(sum, "sum");
        Validator.isNull(currency, "currency");
        Validator.isNull(locale, "locale");

        // Целая часть
        BigInteger integerSum = sum.toBigInteger();
        // Дробная часть
        BigInteger fractionalSum = sum.subtract(sum.setScale(0, RoundingMode.DOWN)).unscaledValue();

        String integerSumText = locale.equals(RUS_LOCALE) ?
                formatMoneyToWords(integerSum, currency) :
                formatMoneyToWords(integerSum, currency, locale);
        String fractionalSumText = null;

        if (fractionalSum != null && fractionalSum.compareTo(BigInteger.ZERO) > 0) {
            String fractionalUnit = getMoneyUnit(fractionalSum, currency, UnitType.FRACTIONAL);
            fractionalSumText = String.format("%s %s", fractionalSum, fractionalUnit);
        }

        if (fractionalSumText == null) {
            return integerSumText;
        } else {
            return String.format("%s %s", integerSumText, fractionalSumText);
        }
    }

    /**
     * Получить текстовое представление валюты для переданной суммы на русском языке.
     *
     * @param sum Сумма, для которое определяется формат валюты
     * @param currency Определяет валюту форматирования
     * @param unitType Определяет тип единицы (рубли, копейки)
     * @return Например, если передать 123 и определить тип единицы 'рубли', то результат будет: 'рубля'.
     * Если передать 100 и определить тип единицы 'рубли', то результат будет: 'рублей'.
     */
    public String getMoneyUnit(BigInteger sum, Currency currency, UnitType unitType) {
        Validator.isNull(sum, "sum");
        Validator.isNull(currency, "currency");
        Validator.isNull(unitType, "unitType");

        String sumText = sum.toString();
        String unit = null;

        switch (unitType) {
            case INTEGER: {
                int startIndex = sumText.length() - 1;
                for (int i = startIndex; i >= 0; --i) {
                    int num = Character.getNumericValue(sumText.charAt(i));
                    if ((sumText.length() - i) % 3 == 1 && i != 0 && sumText.charAt(i - 1) == '1') {
                        if (startIndex == i) {
                            unit = getIntegerUnit(currency, 5);
                        }
                    } else {
                        if (startIndex == i) {
                            unit = getIntegerUnit(currency, num);
                        } else if (unit == null || unit.length() == 0) {
                            unit = getIntegerUnit(currency, 5);
                        }
                    }
                }
                break;
            }
            case FRACTIONAL: {
                if (sumText.length() > 1) {
                    if (sumText.charAt(0) != '1') {
                        unit = getFractionalUnit(currency,
                                Character.getNumericValue(sumText.charAt(sumText.length() - 1)));
                    } else {
                        unit = getFractionalUnit(currency, 0);
                    }
                } else {
                    unit = getFractionalUnit(currency, Character.getNumericValue(sumText.charAt(0)));
                }
                break;
            }
        }

        return unit;
    }

    private CurrencyUnits getUnits(Currency currency) {
        return DEFAULT_UNITS.get(currency);
    }

    private String getIntegerUnit(Currency currency, int unitPosition) {
        return getUnits(currency).getIntegerUnits()[unitPosition];
    }

    private String getFractionalUnit(Currency currency, int unitPosition) {
        return getUnits(currency).getFractionalUnits()[unitPosition];
    }
}
