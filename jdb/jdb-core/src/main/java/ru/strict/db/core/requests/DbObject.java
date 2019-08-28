package ru.strict.db.core.requests;

import java.util.Objects;

public class DbObject<T> implements IDbRequest {

    private T object;

    public DbObject(T object) {
        if(object == null){
            throw new IllegalArgumentException("object is NULL");
        }
        this.object = object;
    }

    public T getObject() {
        return object;
    }

    @Override
    public String getSql() {
        return object.toString();
    }

    @Override
    public String toString(){
        return getSql();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DbObject<?> dbObject = (DbObject<?>) o;
        return Objects.equals(object, dbObject.object);
    }

    @Override
    public int hashCode() {
        return Objects.hash(object);
    }
}
