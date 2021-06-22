package ru.strict.ioc;

import lombok.Data;

@Data
class IoCKey {
    private final String name;
    private final Class<?> clazz;
}
