package ru.strict.ioc;

import ru.strict.ioc.exceptions.SessionInstanceExistsException;
import ru.strict.ioc.exceptions.SingletonInstanceExistsException;
import ru.strict.utils.StringUtil;

import java.util.Optional;

class IoCData {

    private Class instanceClass;
    private Object[] constructorArguments;
    private InstanceType type;
    private Object singletonInstance;
    private Object sessionInstance;

    public IoCData(Class instanceClass, Object[] constructorArguments, InstanceType type) {
        this.instanceClass = instanceClass;
        this.constructorArguments = constructorArguments;
        this.type = type;
    }

    /**
     * Связь данных для создания объекта типа singleton
     */
    public IoCData(Object singletonInstance) {
        this.singletonInstance = singletonInstance;
        type = InstanceType.SINGLETON;
    }

    public Class getInstanceClass() {
        return instanceClass;
    }

    public Object[] getConstructorArguments() {
        return constructorArguments;
    }

    public InstanceType getType() {
        return type;
    }

    public <T> T getSingletonInstance() {
        return (T) singletonInstance;
    }

    public void setSingletonInstance(Object singletonInstance) {
        if (this.singletonInstance != null) {
            throw new SingletonInstanceExistsException(this.singletonInstance, singletonInstance);
        }
        this.singletonInstance = singletonInstance;
    }

    public <T> T getSessionInstance() {
        return (T) sessionInstance;
    }

    public void setSessionInstance(Object sessionInstance) {
        if (this.sessionInstance != null) {
            throw new SessionInstanceExistsException(this.sessionInstance, sessionInstance);
        }
        this.sessionInstance = sessionInstance;
    }

    public void closeSessionInstance() {
        this.sessionInstance = null;
    }

    @Override
    public String toString() {
        return StringUtil.join(", ", type.name(),
                Optional.ofNullable(instanceClass).map(o -> o.toString()).orElse(null),
                Optional.ofNullable(singletonInstance).map(o -> o.toString()).orElse(null));
    }
}
