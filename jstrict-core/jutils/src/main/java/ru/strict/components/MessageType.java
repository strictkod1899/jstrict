package ru.strict.components;

import java.util.HashMap;
import java.util.Map;

public enum MessageType {
    TRACE("TRACE"),
    INFO("INFO"),
    WARNING("WARN"),
    ERROR("ERROR"),
    FATAL("FATAL");

    private static final Map<String, MessageType> VALUES_BY_CODE = new HashMap<>();

    static {
        for (MessageType item : values()) {
            VALUES_BY_CODE.put(item.getCode(), item);
        }
    }

    private final String code;

    MessageType(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static MessageType getByCode(String code) {
        return VALUES_BY_CODE.get(code);
    }
}
