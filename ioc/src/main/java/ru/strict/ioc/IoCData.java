package ru.strict.ioc;

public class IoCData {

    private Class clazzInstance;
    private Object[] constructorArguments;

    public IoCData(Class clazzInstance, Object[] constructorArguments) {
        this.clazzInstance = clazzInstance;
        this.constructorArguments = constructorArguments;
    }

    public Class getClazzInstance() {
        return clazzInstance;
    }

    public Object[] getConstructorArguments() {
        return constructorArguments;
    }
}
