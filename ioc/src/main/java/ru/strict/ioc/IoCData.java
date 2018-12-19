package ru.strict.ioc;

import ru.strict.ioc.exceptions.SessionInstanceExistsException;
import ru.strict.ioc.exceptions.SingletonInstanceExistsException;
import ru.strict.utils.UtilData;

import java.util.Optional;

class IoCData {

    private Class clazzInstance;
    private Object[] constructorArguments;
    private InstanceType type;
    private Object singletonInstance;
    private Object sessionInstance;

    public IoCData(Class clazzInstance, Object[] constructorArguments, InstanceType type) {
        this.clazzInstance = clazzInstance;
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

    public Class getClazzInstance() {
        return clazzInstance;
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
        if(this.singletonInstance != null){
            throw new SingletonInstanceExistsException(this.singletonInstance, singletonInstance);
        }
        this.singletonInstance = singletonInstance;
    }

    public <T> T getSessionInstance() {
        return (T) sessionInstance;
    }

    public void setSessionInstance(Object sessionInstance) {
        if(this.sessionInstance != null){
            throw new SessionInstanceExistsException(this.sessionInstance, sessionInstance);
        }
        this.sessionInstance = sessionInstance;
    }

    public void closeSessionInstance(){
        this.sessionInstance = null;
    }

    @Override
    public String toString() {
        return UtilData.join(", ", type.name(),
                Optional.ofNullable(clazzInstance).map(o -> o.toString()).orElse(null),
                Optional.ofNullable(singletonInstance).map(o -> o.toString()).orElse(null));
    }
}
