package ru.strict.ioc;

public class IoCKeys {
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
}
