package ru.strict.components;

import java.util.Objects;

public class ResultObject<OBJECT> extends ResultMessages {

    private OBJECT object;

    public ResultObject() {
        super();
    }

    public OBJECT getObject() {
        return object;
    }

    public void setObject(OBJECT object) {
        this.object = object;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ResultObject<?> object = (ResultObject<?>) o;
        return Objects.equals(this.object, object.object);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), object);
    }
}
