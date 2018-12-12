package ru.strict.ioc;

import ru.strict.utils.UtilData;

import java.util.Objects;

class IoCKeys {
    private String caption;
    private Class clazz;

    public IoCKeys(String caption, Class clazz) {
        this.caption = caption;
        this.clazz = clazz;
    }

    public String getCaption() {
        return caption;
    }

    public Class getClazz() {
        return clazz;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || !(o instanceof IoCKeys)){
            return false;
        }

        IoCKeys object = (IoCKeys) o;
        return Objects.equals(caption, object.caption) && Objects.equals(clazz, object.clazz);
    }

    @Override
    public int hashCode() {
        return Objects.hash(caption, clazz);
    }

    @Override
    public String toString() {
        return UtilData.join(" / ", caption, clazz.toString());
    }
}
