package ru.strict.ioc;

import lombok.Getter;
import lombok.Setter;
import ru.strict.ioc.box.ComponentSupplier;
import ru.strict.ioc.exception.SessionInstanceExistsException;
import ru.strict.ioc.exception.SingletonInstanceExistsException;

class IoCData {
    @Getter
    private Class<?> instanceClass;
    @Getter
    private Object[] constructorArguments;
    @Getter
    private InstanceType type;
    @Getter
    @Setter
    private Object sourceInstance;
    private Object componentInstance;

    private ComponentSupplier<?> singletonSupplier;

    public IoCData(Class<?> instanceClass, Object[] constructorArguments, InstanceType type) {
        this.instanceClass = instanceClass;
        this.constructorArguments = constructorArguments;
        this.type = type;
    }

    /**
     * Связь данных для создания объекта типа singleton с использованием supplier
     */
    public IoCData(ComponentSupplier<?> componentSupplier) {
        this.singletonSupplier = componentSupplier;
        type = InstanceType.SINGLETON;
    }

    /**
     * Связь данных для создания объекта типа singleton
     */
    public IoCData(Object componentInstance) {
        this.componentInstance = componentInstance;
        this.sourceInstance = componentInstance;
        type = InstanceType.SINGLETON;
    }

    public boolean isExistsComponentInstance() {
        return componentInstance != null;
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

    public boolean withSupplier() {
        return singletonSupplier != null;
    }

    public <T> T getFromSupplier() {
         return (T) singletonSupplier.get();
    }
}
