package ru.strict.ioc;

import ru.strict.ioc.exceptions.SessionInstanceExistsException;
import ru.strict.ioc.exceptions.SingletonInstanceExistsException;

import java.util.Arrays;
import java.util.Objects;

class IoCData {
    private Class<?> instanceClass;
    private Object[] constructorArguments;
    private InstanceType type;
    private Object sourceInstance;
    private Object componentInstance;

    public IoCData(Class<?> instanceClass, Object[] constructorArguments, InstanceType type) {
        this.instanceClass = instanceClass;
        this.constructorArguments = constructorArguments;
        this.type = type;
    }

    /**
     * Связь данных для создания объекта типа singleton
     */
    public IoCData(Object componentInstance) {
        this.componentInstance = componentInstance;
        this.sourceInstance = componentInstance;
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

    public <T> T getSourceInstance() {
        return (T) sourceInstance;
    }

    public void setSourceInstance(Object sourceInstance) {
        this.sourceInstance = sourceInstance;
    }

    public <T> T getComponentInstance() {
        return (T) componentInstance;
    }

    public void setComponentInstance(Object componentInstance) {
        if (this.componentInstance != null) {
            if (type == InstanceType.SINGLETON) {
                throw new SingletonInstanceExistsException(this.componentInstance, componentInstance);
            } else if (type == InstanceType.SESSION) {
                throw new SessionInstanceExistsException(this.componentInstance, componentInstance);
            }
        }
        this.componentInstance = componentInstance;
    }

    public void closeSessionInstance() {
        if (type != InstanceType.SESSION) {
            throw new UnsupportedOperationException(
                    String.format("Unsupported operation [closeSessionInstance] for type = %s", type));
        }
        this.componentInstance = null;
    }

    @Override
    public String toString() {
        return "IoCData{" +
                "instanceClass=" + instanceClass +
                ", constructorArguments=" + Arrays.toString(constructorArguments) +
                ", type=" + type +
                ", sourceInstance=" + sourceInstance +
                ", componentInstance=" + componentInstance +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        IoCData ioCData = (IoCData) o;
        return Objects.equals(instanceClass, ioCData.instanceClass) &&
                Arrays.equals(constructorArguments, ioCData.constructorArguments) &&
                type == ioCData.type &&
                Objects.equals(sourceInstance, ioCData.sourceInstance) &&
                Objects.equals(componentInstance, ioCData.componentInstance);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(instanceClass, type, sourceInstance, componentInstance);
        result = 31 * result + Arrays.hashCode(constructorArguments);
        return result;
    }
}
