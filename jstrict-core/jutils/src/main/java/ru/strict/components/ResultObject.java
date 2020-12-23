package ru.strict.components;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Objects;

public class ResultObject<OBJECT> extends ResultMessages {

    private OBJECT object;

    private ResultObject(Collection<Message> emptyAlerts,
            Collection<Message> emptyMessages,
            OBJECT object) {
        super(emptyAlerts, emptyMessages);
        this.object = object;
    }

    public OBJECT getObject() {
        return object;
    }

    public void setObject(OBJECT object) {
        this.object = object;
    }

    public static <T> ResultObject<T> byList() {
        return byList(null);
    }

    public static <T> ResultObject<T> bySet() {
        return bySet(null);
    }

    public static <T> ResultObject<T> byList(T object) {
        return new ResultObject<>(new ArrayList<>(), new ArrayList<>(), object);
    }

    public static <T> ResultObject<T> bySet(T object) {
        return new ResultObject<>(new LinkedHashSet<>(), new LinkedHashSet<>(), object);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        ResultObject<OBJECT> object = (ResultObject<OBJECT>) o;
        return Objects.equals(this.object, object.object);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), object);
    }
}
