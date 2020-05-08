package ru.strict.ioc;

import ru.strict.ioc.exceptions.MatchInstanceTypeException;

public class SynchronizedIoC extends IoC {

    public SynchronizedIoC() {
        super();
    }

    @Override
    public synchronized <RESULT> void addComponent(Class<RESULT> clazz,
            InstanceType type,
            Object... constructorArguments) {
        super.addComponent(clazz, type, constructorArguments);
    }

    @Override
    public synchronized <RESULT> void addComponent(Class<RESULT> clazz,
            Class component,
            InstanceType type,
            Object... constructorArguments) {
        super.addComponent(clazz, component, type, constructorArguments);
    }

    @Override
    public synchronized void addComponent(String caption,
            Class component,
            InstanceType type,
            Object... constructorArguments) {
        super.addComponent(caption, component, type, constructorArguments);
    }

    @Override
    public synchronized <RESULT> void addComponent(Class<RESULT> clazz,
            String caption,
            Class component,
            InstanceType type,
            Object... constructorArguments) {
        super.addComponent(clazz, caption, component, type, constructorArguments);
    }

    @Override
    public synchronized void addSingleton(String caption, Object component) {
        super.addSingleton(caption, component);
    }

    @Override
    public synchronized <RESULT> void addSingleton(Class<RESULT> clazz, Object component) {
        super.addSingleton(clazz, component);
    }

    @Override
    public synchronized <RESULT> void addSingleton(Class<RESULT> clazz, String caption, Object component) {
        super.addSingleton(clazz, caption, component);
    }

    @Override
    public synchronized <RESULT> RESULT getComponent(String caption) {
        return super.getComponent(caption);
    }

    @Override
    public synchronized <RESULT> RESULT getComponent(Class<RESULT> clazz) {
        return super.getComponent(clazz);
    }

    @Override
    public synchronized <RESULT> void closeSessionInstance(Class<RESULT> clazz) throws MatchInstanceTypeException {
        super.closeSessionInstance(clazz);
    }

    @Override
    public synchronized void closeSessionInstance(String caption) throws MatchInstanceTypeException {
        super.closeSessionInstance(caption);
    }
}
