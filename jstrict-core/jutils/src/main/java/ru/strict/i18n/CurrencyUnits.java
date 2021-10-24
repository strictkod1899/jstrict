package ru.strict.i18n;

import lombok.Builder;
import lombok.Value;
import ru.strict.validate.Validator;

@Value
@Builder
public class CurrencyUnits {
    private final Currency currency;
    /**
     * Валюта целочисленной части.
     * От 0 до 9
     */
    private final String[] integerUnits;
    /**
     * Валюта дробной части.
     * От 0 до 9
     */
    private final String[] fractionalUnits;

    private CurrencyUnits(Currency currency, String[] integerUnits, String[] fractionalUnits) {
        Validator.isNull(currency, "currency");
        Validator.isNull(integerUnits, "integerUnits");
        Validator.isNull(fractionalUnits, "fractionalUnits");
        if (integerUnits.length != 10) {
            throw new IllegalArgumentException("Count of Integer units not equals 10");
        }
        if (fractionalUnits.length != 10) {
            throw new IllegalArgumentException("Count of Fractional units not equals 10");
        }
        this.currency = currency;
        this.integerUnits = integerUnits;
        this.fractionalUnits = fractionalUnits;
    }
}
