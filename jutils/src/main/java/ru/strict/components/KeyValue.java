package ru.strict.components;

import java.util.Objects;

/**
 * Значение по ключу
 * @param <KEY> тип ключа
 * @param <VALUE> тип значения
 */
public class KeyValue<KEY, VALUE> {
    private KEY key;
    private VALUE value;

    public KeyValue(KEY key, VALUE value) {
        this.key = key;
        this.value = value;
    }

    public KEY getKey() {
        return key;
    }

    public VALUE getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        KeyValue<?, ?> object = (KeyValue<?, ?>) o;
        return Objects.equals(key, object.key) &&
                Objects.equals(value, object.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key, value);
    }

    @Override
    public String toString() {
        return String.format("%s -> %s", key.toString(), value.toString());
    }
}
