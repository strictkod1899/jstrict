package ru.strict.util;

import lombok.Data;

/**
 * Значение по ключу
 *
 * @param <KEY> тип ключа
 * @param <VALUE> тип значения
 */
@Data
public class KeyValue<KEY, VALUE> {
    private final KEY key;
    private final VALUE value;
}
