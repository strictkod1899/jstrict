package ru.strict.components;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

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

    public boolean add(String key, String value){
        return add(new KeyValue(key, value));
    }

    public void remove(String key){
        removeIf(kv -> kv.getKey().equals(key));
    }

    public List<KEY> keys(){
        return stream().map(kv -> kv.getKey()).collect(Collectors.toList());
    }

    public VALUE getValue(String key){
        KeyValue<KEY, VALUE> object = stream()
                .filter(kv -> kv.getKey().equals(key))
                .findFirst()
                .orElse(null);
        if(object != null){
            return object.getValue();
        }else{
            return null;
        }
    }
}
