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
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()){
            return false;
        }

        KeyValue<?, ?> keyValue = (KeyValue<?, ?>) obj;
        return Objects.equals(key, keyValue.key) &&
                Objects.equals(value, keyValue.value);
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
