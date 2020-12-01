package ru.strict.ioc;

import java.util.Collection;

public class ConstructorArgument {
    private final Object value;
    private Class<?> genericClass;
    private boolean collection;

    public ConstructorArgument(Object value) {
        this.value = value;
    }

    public ConstructorArgument(Class<Collection<?>> collectionClass, Class<?> genericClass) {
        this.value = collectionClass;
        this.genericClass = genericClass;
        collection = true;
    }

    public Object getValue() {
        return value;
    }

    public Class<?> getGenericClass() {
        return genericClass;
    }

    public boolean isCollection() {
        return collection;
    }
}
