package ru.strict.ioc.box;

public interface IComponentFactory<T> {
    <T> T getComponent();
}
