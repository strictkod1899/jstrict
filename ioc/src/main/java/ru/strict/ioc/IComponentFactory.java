package ru.strict.ioc;

public interface IComponentFactory<T> {
    <T> T getComponent();
}
