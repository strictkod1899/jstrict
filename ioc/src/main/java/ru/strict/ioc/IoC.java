package ru.strict.ioc;

import ru.strict.ioc.box.ComponentSupplier;

public interface IoC {
    void addComponent(Class<?> componentClass, Object... constructorArguments);

    void addComponent(Class<?> componentClass, InstanceType type, Object... constructorArguments);

    void addComponent(String name, Class<?> componentClass, Object... constructorArguments);

    /**
     * Если в {@param constructorArguments} в качестве одного из элементов передать .class, то, по возможности, он будет
     * внедрен как зависимость.
     * Если в {@param constructorArguments} ничего не передано, то будет использован единственный конструктор,
     * либо пустой
     *
     * @param componentClass Класс создаваемого компонента
     * @param constructorArguments Параметры передаваемые в конструктор класса --component--
     */
    void addComponent(String name, Class<?> componentClass, InstanceType type, Object... constructorArguments);

    void addSingleton(Class<?> componentClass, ComponentSupplier<?> componentSupplier);

    void addSingleton(String name, Class<?> componentClass, ComponentSupplier<?> componentSupplier);

    void addSingleton(Object component);

    void addSingleton(String name, Object component);

    <T> T getComponent(Class<T> componentClass);

    <T> T getComponent(String name);

    void closeSessionInstance(Class<?> componentClass);

    void closeSessionInstance(String name);
}
