package ru.strict.models;

import java.util.HashMap;
import java.util.Map;

public enum PhoneType implements IModel<Integer> {
    /**
     * Неизвестный тип
     */
    UNKNOWN(-1),
    /**
     * Стационарный номер телефона
     */
    FIXED(1),
    /**
     * Мобильный номер телефона
     */
    MOBILE(2),
    /**
     * Факс
     */
    FAX(3),
    /**
     * Бесплатный номер телефона (на него бесплатно звонить). Например, горячая линия у банков и др.
     */
    FREE(4),
    /**
     * Международный номер телефона
     */
    INTERNATIONAL(5);

    private static final Map<Integer, PhoneType> VALUES_BY_ID = new HashMap<>();

    static {
        for (PhoneType item : values()) {
            VALUES_BY_ID.put(item.getId(), item);
        }
    }

    private Integer id;

    PhoneType(int id) {
        this.id = id;
    }

    @Override
    public Integer getId() {
        return id;
    }

    public static PhoneType getById(Integer id) {
        return VALUES_BY_ID.get(id);
    }
}
