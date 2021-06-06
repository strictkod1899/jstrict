package ru.strict.i18n;

import java.util.HashMap;
import java.util.Map;

/**
 * Валюта
 */
public enum Currency {
    /**
     * Россия (Российский рубль)
     */
    RUB(1, "RUB"),
    /**
     * Казахстан (Тенге)
     */
    KZT(2, "KZT"),
    /**
     * Несколько стран (Доллар США)
     */
    USD(3, "USD"),
    /**
     * Несколько стран (Евро)
     */
    EUR(4, "EUR"),
    /**
     * Несколько стран (Фунт Стерлингов)
     */
    GBP(5, "GBP"),
    /**
     * Китай (Китайский юань)
     */
    CNY(6, "CNY"),
    /**
     * Беларусь (Беларусский рубль)
     */
    BYN(7, "BYN"),
    /**
     * Украина (Гривна)
     */
    UAH(8, "UAH"),
    /**
     * Кыргызстан (Сом)
     */
    KGS(9, "KGS"),
    /**
     * Армения (Драхм)
     */
    AMD(10, "AMD"),
    /**
     * Турция (Турецкая лира)
     */
    TRY(11, "TRY"),
    /**
     * Таиланд (Бат)
     */
    THB(12, "THB"),
    /**
     * Южная Корея (Вон)
     */
    KRW(13, "KRW"),
    /**
     * Объединенные Арабские Эмираты (Дирхам)
     */
    AED(14, "AED"),
    /**
     * Узбекистан (Узбекский сум)
     */
    UZS(15, "UZS"),
    /**
     * Монголия (Тугрик)
     */
    MNT(16, "MNT"),
    /**
     * Польша (Польский злотый)
     */
    PLN(17, "PLN"),
    /**
     * Азербайджан (Азерб. манат)
     */
    AZN(18, "AZN");

    private static final Map<Integer, Currency> VALUES_BY_ID = new HashMap<>();
    private static final Map<String, Currency> VALUES_BY_MARK = new HashMap<>();

    static {
        for (Currency item : values()) {
            VALUES_BY_ID.put(item.getId(), item);
            VALUES_BY_MARK.put(item.getMark(), item);
        }
    }

    private final Integer id;
    private final String mark;

    Currency(Integer id, String mark) {
        this.id = id;
        this.mark = mark;
    }

    public Integer getId() {
        return id;
    }

    public String getMark() {
        return mark;
    }

    public static Currency getById(Integer id) {
        return VALUES_BY_ID.get(id);
    }

    public static Currency getByMark(String mark) {
        return VALUES_BY_MARK.get(mark);
    }
}
