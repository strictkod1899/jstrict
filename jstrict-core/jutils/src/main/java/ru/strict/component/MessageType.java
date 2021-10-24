package ru.strict.component;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import ru.strict.model.Codeable;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Getter
public enum MessageType implements Codeable<String> {
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

    public static MessageType getByCode(String code) {
        return VALUES_BY_CODE.get(code);
    }
}
