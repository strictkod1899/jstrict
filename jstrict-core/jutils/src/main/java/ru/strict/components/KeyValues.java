package ru.strict.components;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Упорядоченная последовательность пар 'ключ-значение'
 */
public class KeyValues<KEY, VALUE> extends ArrayList<KeyValue<KEY, VALUE>> {

    public KeyValues() {
        super();
    }

    public KeyValues(int initialCapacity) {
        super(initialCapacity);
    }

    public KeyValues(Collection<? extends KeyValue<KEY, VALUE>> c) {
        super(c);
    }

    public boolean add(KEY key, VALUE value) {
        return add(new KeyValue(key, value));
    }

    public boolean addIfNotExists(KEY key, VALUE value) {
        KeyValue<KEY, VALUE> object = new KeyValue<>(key, value);
        return addIfNotExists(object);
    }

    public boolean addIfNotExists(KeyValue<KEY, VALUE> object) {
        if (!contains(object)) {
            return add(object);
        } else {
            return false;
        }
    }

    public void removeByKey(KEY key) {
        removeIf(kv -> kv.getKey().equals(key));
    }

    public List<KEY> keys() {
        return stream().map(KeyValue::getKey).collect(Collectors.toList());
    }

    public VALUE getValue(KEY key) {
        return stream()
                .filter(kv -> kv.getKey().equals(key))
                .findFirst()
                .map(KeyValue::getValue)
                .orElse(null);
    }
}
