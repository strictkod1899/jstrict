package ru.strict.i18n;

import ru.strict.validate.Validator;

public final class CurrencyUnits {
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

    public Currency getCurrency() {
        return currency;
    }

    public String[] getIntegerUnits() {
        return integerUnits;
    }

    public String[] getFractionalUnits() {
        return fractionalUnits;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Currency currency;
        private String[] integerUnits;
        private String[] fractionalUnits;

        public Builder currency(Currency currency) {
            this.currency = currency;
            return this;
        }

        public Builder integerUnits(String[] integerUnits) {
            this.integerUnits = integerUnits;
            return this;
        }

        public Builder fractionalUnits(String[] fractionalUnits) {
            this.fractionalUnits = fractionalUnits;
            return this;
        }

        public CurrencyUnits build() {
            return new CurrencyUnits(currency, integerUnits, fractionalUnits);
        }
    }
}
