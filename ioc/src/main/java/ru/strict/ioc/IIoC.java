package ru.strict.ioc;

public interface IIoC {
    void addComponent(String caption, Class component, Object...constructorArguments);
    <RESULT >void addComponent(Class<RESULT> clazz, Class component, Object...constructorArguments);
    <RESULT> void addComponent(Class<RESULT> clazz, String caption, Class component, Object...constructorArguments);
    <RESULT> RESULT getComponent(Class<RESULT> clazz);
    <RESULT> RESULT getComponent(String caption);
}
